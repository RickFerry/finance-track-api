FROM maven:3.6.3-jdk-8 AS build

COPY . .
RUN mvn clean install

FROM openjdk:8-jdk-alpine

EXPOSE 8080

COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]