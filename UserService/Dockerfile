FROM eclipse-temurin:25-jdk AS build
COPY build/libs/UserService-0.0.1-SNAPSHOT.jar UserService.jar
RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*
ENTRYPOINT ["java", "-jar", "UserService.jar"]
