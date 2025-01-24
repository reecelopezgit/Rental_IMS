# Use a lightweight OpenJDK image as the base
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy application source code and configuration into the container
COPY src/ /app/src/
COPY config/ /app/config/

# Copy all JAR dependencies into the container
COPY libs/ /app/libs/

# Compile and run the application, setting the classpath to include all JAR files in the libs folder
CMD ["java", "-cp", "libs/*:src", "com.app.SpringBootApp"]
