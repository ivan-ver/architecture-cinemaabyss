FROM maven:3.9-eclipse-temurin-21-jammy as builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE ${PORT}

ENTRYPOINT ["sh", "-c", "exec java -jar /app/app.jar"]