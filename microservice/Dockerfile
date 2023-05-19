FROM maven:3.9.0-eclipse-temurin-17 as builder
WORKDIR /booksMonolithToMicroservices/microservice
COPY /src /booksMonolithToMicroservices/microservice/src
COPY pom.xml /booksMonolithToMicroservices/microservice
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /usr/src/app/

COPY --from=builder /booksMonolithToMicroservices/microservice/target/*.jar /usr/src/app/
EXPOSE 8081
CMD [ "java", "-jar", "microservice-0.0.1-SNAPSHOT.jar" ]