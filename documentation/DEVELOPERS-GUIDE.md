# Developers Guide

## Project Setup
- Download Project
  - git clone git@github.com:CCThomas/my-book.git
- For Development
  - MySql for DB (Or just use the Docker Service)
  - Maven & Java is needed for backend
  - npm is needed for frontend
- Formatting
  - [https://github.com/spring-io/spring-javaformat](https://github.com/spring-io/spring-javaformat)
  - You have two options for running the formatter
    - Command Line
      - `mvn spring-javaformat:apply`
    - IDE Configuration
      - [Eclipse](https://github.com/spring-io/spring-javaformat#eclipse)
      - [Intellij](https://github.com/spring-io/spring-javaformat#intellij-idea)
  - Note the Spring.io Formatting Pluggin does not run clean up, like removing unused imports.

## Run Project
- Spin up Database, needed for Backend
  - `docker-compose up -d db`
- Run Backend
  - Navigate to `./backend`
  - `mvn spring-boot:run`
- Run Frontend
  - Navigate to `./frontend`
  - `npm start`