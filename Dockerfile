FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

RUN apt-get update && apt-get install -y maven wget python3 python3-pip && \
    pip3 install --break-system-packages huggingface_hub

COPY pom.xml .
COPY src ./src
COPY download_model.py .

RUN python3 download_model.py

RUN mvn package -DskipTests -q

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/model /app/model
COPY --from=build /app/target/jira-claude-bot-1.0.0.jar app.jar

ENV MODEL_PATH=/app/model

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
