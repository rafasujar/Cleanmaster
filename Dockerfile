# Etapa de construcción
  FROM maven:3.9.9-eclipse-temurin-23 AS builder
  WORKDIR /app
  COPY pom.xml .
  COPY src/ ./src/
  RUN mvn clean package -DskipTests

  # Etapa de ejecución
  FROM eclipse-temurin:23-jre
  WORKDIR /app
  COPY --from=builder /app/target/Cleanmaster-0.0.1-SNAPSHOT.jar app.jar

  # Exponer los puertos
  EXPOSE 8080
  EXPOSE 3306
  ENTRYPOINT ["java", "-jar", "app.jar"]
