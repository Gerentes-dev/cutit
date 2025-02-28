#
# Build stage
#
FROM gradle:8.3-jdk17 AS build
WORKDIR /app
COPY . /app
RUN ./gradlew clean build -x test

#
# Package stage
#
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]