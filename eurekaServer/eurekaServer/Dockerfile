FROM eclipse-temurin:25-jdk AS build
RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*
COPY build/libs/eurekaServer-0.0.1-SNAPSHOT.jar eurekaServer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "eurekaServer-0.0.1-SNAPSHOT.jar"]