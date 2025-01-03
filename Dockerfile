# Build stage
FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/caseStudy.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
