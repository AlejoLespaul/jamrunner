FROM maven as Builder
WORKDIR /app
COPY . /app
RUN mvn clean package

FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash git npm

WORKDIR /app
COPY --from=Builder /app/target/*.jar /app/jamrunner.jar
ENTRYPOINT ["java", "-jar","jamrunner.jar"]