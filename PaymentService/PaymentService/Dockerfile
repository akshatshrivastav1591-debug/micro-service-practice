FROM eclipse-temurin:25-jdk AS build
LABEL authors="Akshat"
COPY build/libs/PaymentService-0.0.1-SNAPSHOT.jar PaymentService-0.0.1-SNAPSHOT.jar
RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*
ENTRYPOINT ["java", "-jar","PaymentService-0.0.1-SNAPSHOT.jar"]