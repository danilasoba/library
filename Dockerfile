# Use a base image with JDK installed
FROM openjdk:17-jdk-slim AS build

# Set the working directory
WORKDIR /app

# Copy the jar file from the target folder into the container
COPY target/library-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
