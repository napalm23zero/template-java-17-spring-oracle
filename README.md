# template-java-17-spring-oracle

<p align="center">
  <img src="https://img.icons8.com/color/48/000000/java-coffee-cup-logo.png" alt="Java">
  <img src="https://img.icons8.com/color/48/000000/spring-logo.png" alt="Spring">
  <img src="https://img.icons8.com/color/48/000000/oracle-logo.png" alt="Oracle">
  <img src="https://img.icons8.com/color/48/000000/visual-studio-code-2019.png" alt="VSCode">
  <img src="https://img.icons8.com/color/48/000000/docker.png" alt="Docker">
</p>

## About the Project

Welcome to the **template-java-17-spring-oracle** repository! This template application is crafted by Rodrigo Dantas, a Brazilian software engineer, with the aim to showcase the very best practices in Java 17, Spring Boot, and Oracle Database. This project serves as a portfolio piece and an exemplary guide for newbies stepping into the world of clean architecture.

## About Me

Hello, I'm **Rodrigo Dantas**, the tech nerd behind this project. With a passion for crafting clean, efficient, and scalable code, I dedicate myself to helping others grow in their software engineering journey. Feel free to reach out to me if you have any questions, need assistance, or just want to talk tech!

**Contact me:**

<p align="center">
  <a href="mailto:rodrigo.dantas@hustletech.dev"><img src="https://img.icons8.com/color/48/000000/email.png" alt="Email"></a>
  <a href="https://www.instagram.com/napalm23zero"><img src="https://img.icons8.com/color/48/000000/instagram-new.png" alt="Instagram"></a>
  <a href="https://twitter.com/napalm23zero"><img src="https://img.icons8.com/color/48/000000/twitter.png" alt="Twitter"></a>
  <a href="https://github.com/napalm23zero"><img src="https://img.icons8.com/color/48/000000/github.png" alt="GitHub"></a>
  <a href="https://www.linkedin.com/in/napalm23zero"><img src="https://img.icons8.com/color/48/000000/linkedin.png" alt="LinkedIn"></a>
  <a href="https://www.tiktok.com/@napalm23zero"><img src="https://img.icons8.com/color/48/000000/tiktok.png" alt="TikTok"></a>
  <a href="https://discord.com/users/napalm23zero"><img src="https://img.icons8.com/color/48/000000/discord-logo.png" alt="Discord"></a>
  <a href="https://steamcommunity.com/id/napalm23zero"><img src="https://img.icons8.com/color/48/000000/steam.png" alt="Steam"></a>
  <a href="https://open.spotify.com/user/22shqo6vu5mqvdgwxi66gawta"><img src="https://img.icons8.com/color/48/000000/spotify.png" alt="Spotify"></a>
  <a href="https://www.youtube.com/@napalm23zero"><img src="https://img.icons8.com/color/48/000000/youtube-play.png" alt="YouTube"></a>
  <a href="https://www.twitch.tv/napalm23zero"><img src="https://img.icons8.com/color/48/000000/twitch.png" alt="Twitch"></a>
</p>

I'm here to help, so don't hesitate to contact me via email or any of my social networks. Let's embark on this coding adventure together!

# Table of Contents

- [Project Structure and Clean Architecture](#project-structure-and-clean-architecture)
  - [Project Structure](#project-structure)
  - [Explanation of Clean Architecture and SOLID Principles](#explanation-of-clean-architecture-and-solid-principles)
  - [Adapters Layer](#adapters-layer)
  - [Application Layer](#application-layer)
  - [Domain Layer](#domain-layer)
  - [Infrastructure Layer](#infrastructure-layer)
- [SOLID Principles Applied](#solid-principles-applied)
- [Running the Application in a DevContainer](#running-the-application-in-a-devcontainer)
  - [Database Access](#database-access)
  - [Accessing Swagger Endpoint Outside the Container](#accessing-swagger-endpoint-outside-the-container)
- [Running the Application with Docker Compose](#running-the-application-with-docker-compose)
  - [Database Access](#database-access-1)
  - [Docker Compose Configuration](#docker-compose-configuration)
  - [Dockerfile](#dockerfile)
- [Database Initialization](#database-initialization)

## Project Structure and Clean Architecture

This project follows the principles of Clean Architecture, ensuring a clear separation of concerns and high maintainability. Below is an overview of the project structure and how it aligns with Clean Architecture and SOLID principles:

### Project Structure

```
src/
└── main/
    └── java/
        └── com/
            └── hustletech/
                └── template/
                    ├── adapters/
                    │   ├── controller/
                    │   │   ├── UserController.java
                    │   │   └── exception/
                    │   │       ├── UserNotFoundException.java
                    │   ├── service/
                    │       ├── UserService.java
                    ├── application/
                    │   ├── dto/
                    │   │   ├── UserRequestDTO.java
                    │   │   └── UserResponseDTO.java
                    │   ├── mapper/
                    │   │   └── UserMapper.java
                    │   └── usecase/
                    │       └── user/
                    │           ├── CreateUserUseCase.java
                    │           ├── DeleteUserUseCase.java
                    │           ├── GetUserUseCase.java
                    │           └── UpdateUserUseCase.java
                    ├── domain/
                    │   ├── entity/
                    │   │   ├── GenericEntity.java
                    │   │   └── User.java
                    │   └── repository/
                    │       └── UserRepository.java
                    └── infrastructure/
                        └── config/
                            ├── DatabaseConfig.java
                            ├── SwaggerConfig.java
                            └── TemplateApplication.java
```

## Explanation of Clean Architecture and SOLID Principles

### Adapters Layer

- **Controller:** The `UserController` class handles HTTP requests and maps them to the appropriate use cases. It serves as an interface adapter that translates user actions into application actions.
- **Exception:** The `UserNotFoundException` class is used to handle specific exceptions related to user operations.

### Application Layer

- **DTO (Data Transfer Object):** Classes like `UserRequestDTO` and `UserResponseDTO` are used to transfer data between the layers. They ensure that the internal domain models are not exposed to the external world.
- **Mapper:** The `UserMapper` class is responsible for mapping between domain models and DTOs, facilitating data transformation and ensuring separation of concerns.
- **Use Case:** The use cases (`CreateUserUseCase`, `DeleteUserUseCase`, `GetUserUseCase`, `UpdateUserUseCase`) encapsulate the business logic and application-specific rules. They orchestrate the execution of business rules without being influenced by the delivery mechanism.

### Domain Layer

- **Entity:** Classes like `GenericEntity` and `User` represent the core business entities of the application. They are the most stable and central part of the application.
- **Repository:** The `UserRepository` interface defines the contract for data access, ensuring that the domain layer is not dependent on the data access layer's implementation details.

### Infrastructure Layer

- **Config:** Classes like `DatabaseConfig`, `SwaggerConfig`, and `TemplateApplication` handle infrastructure-specific configurations and bootstrapping. They ensure that the application is properly set up and configured.

## SOLID Principles Applied

1. **Single Responsibility Principle (SRP):** Each class in the project has a single responsibility. For instance, the `UserService` class handles business logic, while the `UserController` class handles HTTP requests.
2. **Open/Closed Principle (OCP):** The project is designed to be open for extension but closed for modification. New features can be added through new use cases or extending existing ones without modifying the core logic.
3. **Liskov Substitution Principle (LSP):** The domain models and DTOs can be substituted without affecting the correctness of the program.
4. **Interface Segregation Principle (ISP):** The `UserRepository` interface provides a clear contract for data access, ensuring that clients are not forced to depend on methods they do not use.
5. **Dependency Inversion Principle (DIP):** The project depends on abstractions (interfaces) rather than concrete implementations. For example, the `UserRepository` interface is used by the service layer, decoupling the business logic from the data access layer.

This structure and adherence to Clean Architecture and SOLID principles ensure that the project is modular, testable, and maintainable. Each layer has a specific role, promoting separation of concerns and making it easier to manage and extend the application.

## Running the Application in a DevContainer

1. **Clone the repository:**

   ```bash
   git clone https://github.com/napalm23zero/template-java-17-spring-oracle.git
   cd template-java-17-spring-oracle
   ```

2. **Open the project in VSCode:**

   ```bash
   code .
   ```

3. **Open the Command Palette (F1) and select:**

   ```plaintext
   Remote-Containers: Reopen in Container
   ```

4. **Wait for the dev container to build and start.** This might take a few minutes depending on your system and internet speed.

5. **Once the container is running, open a terminal in VSCode and run:**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

6. **Access the application:**

   - **API Endpoint:** [http://localhost:6080/api](http://localhost:6080/api)
   - **Swagger UI:** [http://localhost:6080/swagger-ui.html](http://localhost:6080/swagger-ui.html)

### Datbase Access

When running the application in a DevContainer, use the following connection string to access the Oracle database:

- **JDBC URL:** `jdbc:oracle:thin:@//template-oracle-db:1521/XE`
- **Username:** `TEMPLATE_APP`
- **Password:** `darkSide123`

### Accessing Swagger Endpoint Outside the Container

If you want to access the Swagger UI outside of the dev container, follow these steps:

1. **Ensure the application is running inside the dev container as described above.**

2. **Open your web browser and navigate to:**

   [http://localhost:6080/swagger-ui.html](http://localhost:6080/swagger-ui.html)

## Running the Application with Docker Compose

To deploy and run the application using Docker containers, follow these steps:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/napalm23zero/template-java-17-spring-oracle.git
   cd template-java-17-spring-oracle
   ```

2. **Ensure Docker and Docker Compose are installed on your system.**

3. **Build and start the Docker containers:**

   ```bash
   docker-compose -f .docker/docker-compose.yaml up --build
   ```

4. **Access the application:**

   - **API Endpoint:** [http://localhost:8080/api](http://localhost:8080/api)
   - **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Datbase Access

When running the application with Docker Compose, use the following connection string to access the Oracle database:

- **JDBC URL:** `jdbc:oracle:thin:@//localhost:1521/XE`
- **Username:** `TEMPLATE_APP`
- **Password:** `darkSide123`

### Docker Compose Configuration

Refer to the `.docker/docker-compose.yaml` file for the Docker Compose configuration, which sets up the following services:

- **template-java-17-spring:** The main application service, built from the provided Dockerfile.
- **template-oracle-db:** An Oracle XE database service.

### Dockerfile

The Dockerfile used to build the `template-java-17-spring` service can be found in the root directory. It consists of two stages
a build stage using Maven and an execution stage using a slim Java 17 base image.

## Database Initialization

The `init.sql` file is used to initialize the Oracle database with the necessary schema and user. This file is automatically executed when the `template-oracle-db` container starts.

Happy coding!
