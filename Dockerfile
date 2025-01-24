# Use OpenJDK 8 as the base image
FROM openjdk:8-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy source code, dependencies, and configuration into the container
COPY src/ /app/src/
COPY libs/ /app/libs/
COPY config/ /app/config/

# Compile the Java code in two steps for better error visibility
RUN mkdir out
RUN find src -name "*.java" > sources.txt && \
    javac -cp "libs/*" -d out @sources.txt

# Run the compiled Spring Boot application
CMD ["java", "-cp", "libs/*:out", "app.SpringBootApp"]
