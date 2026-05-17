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
import java.nio.file.Paths;
import java.util.Map;

@Service
public class EmbeddingService {

    private static final Logger log = LoggerFactory.getLogger(EmbeddingService.class);
    public static final int EMBEDDING_DIM = 384;

    private ZooModel<String, float[]> model;
    private Predictor<String, float[]> predictor;

    @PostConstruct
    public void init() throws Exception {
        String modelPath = System.getenv().getOrDefault("MODEL_PATH", "/app/model");
        log.info("Loading embedding model from: {}", modelPath);

        Criteria<String, float[]> criteria = Criteria.builder()
                .setTypes(String.class, float[].class)
                .optModelPath(Paths.get(modelPath))
                .optModelName("pytorch_model")
                .optTranslator(new SentenceTranslator(modelPath))
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

        private final String modelPath;
        private HuggingFaceTokenizer tokenizer;

        SentenceTranslator(String modelPath) {
            this.modelPath = modelPath;
        }

        @Override
        public void prepare(TranslatorContext ctx) throws IOException {
            tokenizer = HuggingFaceTokenizer.newInstance(
                    Paths.get(modelPath, "tokenizer.json"),
                    Map.of("padding", "true", "truncation", "true", "maxLength", "512")
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
            NDArray meanPooled = tokenEmbeddings.mean(new int[]{1}).squeeze();
            float[] arr = meanPooled.toFloatArray();
            float norm = 0f;
            for (float v : arr) norm += v * v;
            norm = (float) Math.sqrt(norm);
            for (int i = 0; i < arr.length; i++) arr[i] /= norm;
            return arr;
        }

        @Override
        public Batchifier getBatchifier() {
            return null;
        }
    }
}
