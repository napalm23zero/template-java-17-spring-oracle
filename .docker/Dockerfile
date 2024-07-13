# Stage 1: Build the application
FROM openjdk:17-slim-bullseye AS builder

# Who's the boss?
LABEL maintainer="Rodrigo Dantas"
LABEL email="rodrigo.dantas@hustletech.dev"

# Installing a bunch of stuff because every ninja needs some tools
RUN apt-get update && apt-get install -y maven git python3 unzip curl coreutils

# Set working directory inside the container (It's where the magic happens)
WORKDIR /app

# Copy the pom.xml and download dependencies
# Pre-download dependencies (No one likes waiting, even in a galaxy far, far away)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
# Use the same base image for Java 17
FROM openjdk:17-slim-bullseye

# Set working directory inside the container (Again, where the magic happens)
WORKDIR /app

# Copy the built application (The good stuff)
COPY --from=builder /app/target/template-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose port 8080 (Because we're predictable)
EXPOSE 8080

# Run the application (Here we go, hold on to your butts!)
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
