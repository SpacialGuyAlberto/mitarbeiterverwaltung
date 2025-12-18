# ----- Phase 1: Build -----
FROM gradle:8.7-jdk17 AS build
WORKDIR /app


COPY build.gradle* settings.gradle* gradle.properties* ./
COPY gradle ./gradle
COPY gradlew ./

# Descarga deps (cache)
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon || true

# Copiamos el código y construimos
COPY src ./src
RUN ./gradlew clean bootJar --no-daemon

# ----- Phase 2: Runtime -----
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copiamos el jar generado
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
