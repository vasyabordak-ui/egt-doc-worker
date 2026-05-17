package com.etg.jirabot.service;

import ai.djl.ModelException;
import ai.djl.huggingface.tokenizers.Encoding;
import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import ai.djl.inference.Predictor;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.Batchifier;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

/**
 * Local embeddings using sentence-transformers/all-MiniLM-L6-v2 via DJL.
 * Produces 384-dimensional embeddings. No external API calls needed.
 */
@Service
public class EmbeddingService {

    private static final Logger log = LoggerFactory.getLogger(EmbeddingService.class);
    private static final String MODEL_NAME = "sentence-transformers/all-MiniLM-L6-v2";
    public static final int EMBEDDING_DIM = 384;

    private ZooModel<String, float[]> model;
    private Predictor<String, float[]> predictor;

    @PostConstruct
    public void init() throws ModelException, IOException {
        log.info("Loading local embedding model: {}", MODEL_NAME);

        Criteria<String, float[]> criteria = Criteria.builder()
                .setTypes(String.class, float[].class)
                .optModelUrls("djl://ai.djl.huggingface.pytorch/" + MODEL_NAME)
                .optTranslator(new SentenceTranslator())
                .optProgress(new ProgressBar())
                .build();

        model = criteria.loadModel();
        predictor = model.newPredictor();
        log.info("Embedding model loaded successfully (dim={})", EMBEDDING_DIM);
    }

    @PreDestroy
    public void destroy() {
        if (predictor != null) predictor.close();
        if (model != null) model.close();
    }

    public float[] embed(String text) {
        try {
            return predictor.predict(text);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate embedding: " + e.getMessage(), e);
        }
    }

    /**
     * Mean pooling translator for sentence transformers.
     */
    private static class SentenceTranslator implements Translator<String, float[]> {

        private HuggingFaceTokenizer tokenizer;

        @Override
        public void prepare(TranslatorContext ctx) throws IOException {
            tokenizer = HuggingFaceTokenizer.newInstance(MODEL_NAME);
        }

        @Override
        public NDList processInput(TranslatorContext ctx, String input) {
            Encoding encoding = tokenizer.encode(input, true);
            NDManager manager = ctx.getNDManager();
            NDArray inputIds = manager.create(encoding.getIds());
            NDArray attentionMask = manager.create(encoding.getAttentionMask());
            NDArray tokenTypeIds = manager.create(encoding.getTypeIds());
            return new NDList(inputIds, attentionMask, tokenTypeIds);
        }

        @Override
        public float[] processOutput(TranslatorContext ctx, NDList list) {
            // Mean pooling over token embeddings
            NDArray tokenEmbeddings = list.get(0);
            NDArray meanPooled = tokenEmbeddings.mean(new int[]{0});
            // L2 normalize
            NDArray norm = meanPooled.norm();
            NDArray normalized = meanPooled.div(norm);
            return normalized.toFloatArray();
        }

        @Override
        public Batchifier getBatchifier() {
            return null;
        }
    }
}
