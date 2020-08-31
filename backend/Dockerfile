# Docker Image which is used as foundation to create
# a custom Docker Image with this Dockerfile
FROM maven:3.6.3-openjdk-14-slim AS build
FROM openjdk:14-jdk-alpine

# Copies src and pom to Docker environment
COPY src /home/my-book/src
COPY pom.xml /home/my-book

# Packages backend
RUN mvn -f /home/my-book/pom.xml clean package

# Grabs Jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} mybook.jar

# Finally runs the application
ENTRYPOINT ["java","-jar","/mybook.jar"]