# Use OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven build artifact
COPY target/tripplanner-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 9093

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
