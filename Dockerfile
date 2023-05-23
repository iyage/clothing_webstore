#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY . .
RUN mvn clean package -Dmaven.test.skip=true -DskipTests



FROM openjdk:19

COPY --from=build target/clothingapp-0.0.1-SNAPSHOT.jar webstore-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","webstore-api.jar"]
