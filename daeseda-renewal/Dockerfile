# Gradle 빌드용 이미지
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN gradle build -x test

# 실행용 JDK 이미지
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
