FROM ubuntu:18.04 AS build

RUN apt update && apt upgrade -y
RUN apt-get install openjdk-8-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:8-jdk-slim

EXPOSE 8080

COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]