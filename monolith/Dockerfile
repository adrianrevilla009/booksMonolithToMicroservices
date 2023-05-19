FROM maven:3.9.0-eclipse-temurin-17 as builder
WORKDIR /booksMonolithToMicroservices/monolith
COPY /src /booksMonolithToMicroservices/monolith/src
COPY pom.xml /booksMonolithToMicroservices/monolith
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /usr/src/app/

COPY --from=builder /booksMonolithToMicroservices/monolith/target/*.jar /usr/src/app/
EXPOSE 8080
CMD [ "java", "-jar", "monolith-0.0.1-SNAPSHOT.jar" ]