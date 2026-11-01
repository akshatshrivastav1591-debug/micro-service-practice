FROM eclipse-temurin:25-jdk AS build
LABEL authors="Akshat"
COPY build/libs/PaymentService-0.0.1-SNAPSHOT.jar PaymentService-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar","PaymentService-0.0.1-SNAPSHOT.jar"]