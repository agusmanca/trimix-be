FROM openjdk:19-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/TrimixBE-0.0.1-SNAPSHOT.jar TrimixBE.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/TrimixBE.jar"]