# Stage 1: Build the application
# Using Maven because real devs don't need GUIs to build stuff
FROM maven:3.8.6-openjdk-17 as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies
# Pre-downloading dependencies to save time later. Time is money, right?
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
# If you don't know what this does, maybe coding isn't your thing
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
# Because we like our containers lean and mean
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
# Where all the magic happens
WORKDIR /app

# Copy the built application from the previous stage
# This is where we put the good stuff
COPY --from=builder /app/target/template-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port on which the application will run
# 8080, because who doesn't love clichés?
EXPOSE 8080

# Run the application
# Here we go, hold on to your butts!
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
