FROM openjdk:latest
MAINTAINER bayueliuhuo
WORKDIR /app
EXPOSE 9090
ADD target/0.0.1-SNAPSHOT /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
