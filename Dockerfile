# Etapa 1: Compilación con Maven e Java 17
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen de ejecución liviana
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/franquicias-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]