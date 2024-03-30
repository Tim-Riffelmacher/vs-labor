FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY --from=build /app/target/product-1.0.0.war product-1.0.0.war

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "product-1.0.0.war"]