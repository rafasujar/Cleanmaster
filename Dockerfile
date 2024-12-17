# Etapa de construcción
FROM maven:3.9.9-eclipse-temurin-23 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:23-jre
WORKDIR /app
COPY --from=builder /app/target/tu-aplicacion.jar .
ENTRYPOINT ["java", "-jar", "tu-aplicacion.jar"]
