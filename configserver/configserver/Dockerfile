FROM eclipse-temurin:25-jdk AS build
# Install curl and clean up the package manager cache to keep the image slim
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*
COPY build/libs/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "configserver-0.0.1-SNAPSHOT.jar"]