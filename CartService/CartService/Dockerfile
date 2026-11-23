FROM eclipse-temurin:25-jdk AS build
COPY build/libs/CartService-0.0.1-SNAPSHOT.jar CartService-0.0.1-SNAPSHOT.jar
RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*
ENTRYPOINT ["java", "-jar", "CartService-0.0.1-SNAPSHOT.jar"]