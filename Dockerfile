FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Install maven
RUN apt-get update && apt-get install -y maven wget python3 python3-pip && \
    pip3 install --break-system-packages huggingface_hub

COPY pom.xml .
COPY src ./src

# Download the model during build
RUN python3 -c "
from huggingface_hub import snapshot_download
snapshot_download(
    repo_id='sentence-transformers/all-MiniLM-L6-v2',
    local_dir='/app/model',
    ignore_patterns=['*.msgpack', '*.h5', 'flax_model*', 'tf_model*', 'rust_model*']
)
print('Model downloaded')
"

# Build jar
RUN mvn package -DskipTests -q

FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy model
COPY --from=build /app/model /app/model

COPY --from=build /app/target/jira-claude-bot-1.0.0.jar app.jar

ENV MODEL_PATH=/app/model

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
