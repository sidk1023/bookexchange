#
# Build stage
#
FROM openjdk:17-alpine AS build
COPY ../.mvn .mvn
COPY ../mvnw .
COPY ../pom.xml .
COPY ../src src
RUN chmod +x mvnw

RUN ./mvnw clean package
#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /target/bookexchange-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]
