# Stage 1: Build the application
FROM maven:3.8.6-openjdk-17 as builder

# Set working directory inside the container (It's where the magic happens)
WORKDIR /app

# Copy the pom.xml and download dependencies
# Pre-download dependencies (No one likes waiting, even in a galaxy far, far away)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package

# Stage 2: Create the final image
# Use a slim base image for Java 17 (Slim because we're on a diet)
FROM openjdk:17-jdk-slim

# Set working directory inside the container (Again, where the magic happens)
WORKDIR /app

# Copy the built application (The good stuff)
COPY --from=builder /app/target/template-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose port 8080 (Because we're predictable)
EXPOSE 8080

# Run the application (Here we go, hold on to your butts!)
ENTRYPOINT ["java", "-jar", "/app/app.jar"]