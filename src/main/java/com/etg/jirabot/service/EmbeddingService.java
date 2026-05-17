package com.etg.jirabot.service;

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
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class EmbeddingService {

    private static final Logger log = LoggerFactory.getLogger(EmbeddingService.class);
    public static final int EMBEDDING_DIM = 384;

    private ZooModel<String, float[]> model;
    private Predictor<String, float[]> predictor;

    @PostConstruct
    public void init() throws Exception {
        log.info("Loading local embedding model...");

        Criteria<String, float[]> criteria = Criteria.builder()
                .setTypes(String.class, float[].class)
                .optModelUrls("https://huggingface.co/sentence-transformers/all-MiniLM-L6-v2/resolve/main/")
                .optModelName("all-MiniLM-L6-v2")
                .optTranslator(new SentenceTranslator())
                .optProgress(new ProgressBar())
                .optEngine("PyTorch")
                .build();

        model = criteria.loadModel();
        predictor = model.newPredictor();
        log.info("Embedding model loaded (dim={})", EMBEDDING_DIM);
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

    private static class SentenceTranslator implements Translator<String, float[]> {

        private HuggingFaceTokenizer tokenizer;

        @Override
        public void prepare(TranslatorContext ctx) throws IOException {
            tokenizer = HuggingFaceTokenizer.newInstance(
                    "sentence-transformers/all-MiniLM-L6-v2",
                    java.util.Map.of("padding", "true", "truncation", "true", "maxLength", "512")
            );
        }

        @Override
        public NDList processInput(TranslatorContext ctx, String input) {
            Encoding encoding = tokenizer.encode(input);
            NDManager manager = ctx.getNDManager();
            NDArray inputIds = manager.create(encoding.getIds()).reshape(1, -1);
            NDArray attentionMask = manager.create(encoding.getAttentionMask()).reshape(1, -1);
            NDArray tokenTypeIds = manager.create(encoding.getTypeIds()).reshape(1, -1);
            return new NDList(inputIds, attentionMask, tokenTypeIds);
        }

        @Override
        public float[] processOutput(TranslatorContext ctx, NDList list) {
            NDArray tokenEmbeddings = list.get(0);
            // Mean pool over sequence dimension (dim=1)
            NDArray meanPooled = tokenEmbeddings.mean(new int[]{1});
            NDArray squeezed = meanPooled.squeeze();
            // L2 normalize
            float norm = (float) Math.sqrt(squeezed.dot(squeezed).toFloatArray()[0]);
            NDArray normalized = squeezed.div(norm);
            return normalized.toFloatArray();
        }

        @Override
        public Batchifier getBatchifier() {
            return null;
        }
    }
}