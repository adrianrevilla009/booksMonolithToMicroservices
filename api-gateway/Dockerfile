FROM maven:3.9.0-eclipse-temurin-17 as builder
WORKDIR /booksMonolithToMicroservices/api-gateway
COPY /src /booksMonolithToMicroservices/api-gateway/src
COPY pom.xml /booksMonolithToMicroservices/api-gateway
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /usr/src/app/

COPY --from=builder /booksMonolithToMicroservices/api-gateway/target/*.jar /usr/src/app/
EXPOSE 8082
CMD [ "java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar" ]