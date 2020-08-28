FROM openjdk:14-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} mybook.jar
ENTRYPOINT ["java","-jar","/mybook.jar"]