# Use the official OpenJDK 17 image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven Wrapper and ensure it's executable
COPY mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "target/receipt-processor-0.0.1-SNAPSHOT.jar"]
