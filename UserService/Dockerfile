FROM eclipse-temurin:25-jdk AS build
COPY build/libs/UserService-0.0.1-SNAPSHOT.jar UserService.jar
ENTRYPOINT ["java", "-jar", "UserService.jar"]
