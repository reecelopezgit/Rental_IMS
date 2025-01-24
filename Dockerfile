# Step 1: Use a lightweight OpenJDK image
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the project files into the container
COPY src/ /app/src/
COPY config/ /app/config/
COPY libs/ /app/libs/

# Step 4: Compile the Java code
RUN javac -cp "libs/*" -d out $(find src -name "*.java")

# Step 5: Run the compiled Spring Boot application
CMD ["java", "-cp", "libs/*:out", "app.SpringBootApp"]
