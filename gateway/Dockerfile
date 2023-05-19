FROM maven:3.9.0-eclipse-temurin-17 as builder
WORKDIR /booksMonolithToMicroservices/gateway
COPY /src /booksMonolithToMicroservices/gateway/src
COPY pom.xml /booksMonolithToMicroservices/gateway
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /usr/src/app/

COPY --from=builder /booksMonolithToMicroservices/gateway/target/*.jar /usr/src/app/
EXPOSE 8082
CMD [ "java", "-jar", "gateway-0.0.1-SNAPSHOT.jar" ]