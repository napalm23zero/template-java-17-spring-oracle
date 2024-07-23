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
                    │   │   ├── AuthenticationController.java
                    │   │   ├── PersonController.java
                    │   │   ├── _GenericController.java
                    │   │   └── exception/
                    │   │       ├── GlobalExceptionHandler.java
                    │   │       ├── ApiException.java
                    │   │       └── NotFoundException.java
                    │   ├── security/
                    │   │   ├── CustomUserDetails.java
                    │   │   ├── CustomUserDetailsService.java
                    │   ├── service/
                    │   │   ├── PersonService.java
                    │   │   ├── UserService.java
                    │   │   └── _GenericService.java
                    ├── application/
                    │   ├── dto/
                    │   │   ├── PersonFilterDTO.java
                    │   │   ├── PersonRequestDTO.java
                    │   │   ├── PersonResponseDTO.java
                    │   │   ├── UserFilterDTO.java
                    │   │   ├── UserRequestDTO.java
                    │   │   ├── UserResponseDTO.java
                    │   ├── mapper/
                    │   │   ├── PersonMapper.java
                    │   │   ├── UserMapper.java
                    │   │   └── _GenericMapper.java
                    │   └── usecase/
                    │       └── person/
                    │           ├── CreatePersonUseCase.java
                    │           ├── DeletePersonUseCase.java
                    │           ├── FindPersonUseCase.java
                    │           ├── GetPersonUseCase.java
                    │           ├── UpdatePersonUseCase.java
                    │       └── user/
                    │           ├── CreateUserUseCase.java
                    │           ├── DeleteUserUseCase.java
                    │           ├── FindUserUseCase.java
                    │           ├── GetUserUseCase.java
                    │           ├── UpdateUserUseCase.java
                    ├── domain/
                    │   ├── entity/
                    │   │   ├── GenericEntity.java
                    │   │   ├── Person.java
                    │   │   ├── Role.java
                    │   │   └── User.java
                    │   ├── repository/
                    │   │   ├── PersonRepository.java
                    │   │   ├── RoleRepository.java
                    │   │   └── UserRepository.java
                    │   └── specification/
                    │       ├── _GenericSpecification.java
                    │       ├── FieldCache.java
                    │       ├── PersonSpecification.java
                    │       └── UserSpecification.java
                    ├── infrastructure/
                    │   ├── config/
                    │   │   ├── DataInitializer.java
                    │   │   ├── JwtRequestFilterUtil.java
                    │   │   ├── SecurityConfig.java
                    │   │   ├── JwtUtil.java
                    │   │   └── SwaggerConfig.java
                    ├── shared/
                    │   ├── exception/
                    │   │   ├── ApiException.java
                    │   │   ├── GlobalExceptionHandler.java
                    │   │   └── NotFoundException.java
                    │   ├── validation/
                    │   │   ├── EmailProviderValidation.java
                    │   │   ├── HumanDateValidation.java
                    │   │   └── NameValidation.java
                    │   └── utils/
                    │       └── ParseSortUtils.java
                    └── TemplateApplication.java
```

## Explanation of Clean Architecture and SOLID Principles

### Adapters Layer

- **Controller:** The `AuthenticationController` and `PersonController` classes handle HTTP requests and map them to the appropriate use cases. They serve as interface adapters that translate user actions into application actions.
- **Exception:** The `GlobalExceptionHandler`, `ApiException`, and `NotFoundException` classes handle specific exceptions related to various operations.
- **Security:** The `CustomUserDetails` and `CustomUserDetailsService` classes handle user details and authentication mechanisms.

### Application Layer

- **DTO (Data Transfer Object):** Classes like `UserRequestDTO`, `UserResponseDTO`, and `UserFilterDTO` are used to transfer data between the layers. They ensure that the internal domain models are not exposed to the external world.
- **Mapper:** The `UserMapper` and `_GenericMapper` classes are responsible for mapping between domain models and DTOs, facilitating data transformation and ensuring separation of concerns.
- **Use Case:** The use cases (`CreateUserUseCase`, `DeleteUserUseCase`, `GetUserUseCase`, `UpdateUserUseCase`, `FindUserUseCase`) encapsulate the business logic and application-specific rules. They orchestrate the execution of business rules without being influenced by the delivery mechanism.

### Domain Layer

- **Entity:** Classes like `GenericEntity` and `User` represent the core business entities of the application. They are the most stable and central part of the application.
- **Repository:** The `UserRepository` interface defines the contract for data access, ensuring that the domain layer is not dependent on the data access layer's implementation details.
- **Specification:** Classes like `_GenericSpecification` and `UserSpecification` are used for defining criteria for querying the data.

### Shared Layer

- **Exception:** Centralized exception handling classes like `ApiException`, `GlobalExceptionHandler`, and `NotFoundException`.
- **Validation:** Custom validators such as `NameValidation`, `EmailProviderValidation`, and `HumanDateValidation` are used for validating fields in DTOs.
- **Utils:** Utility classes like `ParseSortUtils` provide common utility functions that can be used across the application.

### Infrastructure Layer

- **Config:** Classes like `DataInitializer`, `SecurityConfig`, `JwtUtil`, and `SwaggerConfig` handle infrastructure-specific configurations and bootstrapping. They ensure that the application is properly set up and configured.

## Authentication Workflow

1. **Login Request:**

   - The client sends a POST request to `/api/authenticate` with username and password.
   - The `AuthenticationController` handles this request, authenticates the user using `AuthenticationManager`, and generates a JWT token using `JwtUtil`.

2. **Token Generation:**

   - The `JwtUtil` class generates a JWT token with the user's username and expiration time.
   - The token is returned to the client.

3. **Token Validation:**

   - For subsequent requests, the client includes the JWT token in the `Authorization` header.
   - The `JwtRequestFilterUtil` intercepts the request, extracts the token, and validates it using `JwtUtil`.
   - If valid, the user details are loaded, and the security context is updated.

4. **Secure Endpoints:**
   - All endpoints, except `/authenticate` and Swagger docs, are secured.
   - Requests to secure endpoints require a valid JWT token.

## SOLID Principles Applied

1. **Single Responsibility Principle (SRP):** Each class in the project has a single responsibility. For instance, the `UserService` class handles business logic, while the `UserController` class handles HTTP requests.
2. **Open/Closed Principle (OCP):** The project is designed to be open for extension but closed for modification. New features can be added through new use cases or extending existing ones without modifying the core logic.
3. **Liskov Substitution Principle (LSP):** The domain models and DTOs can be substituted without affecting the correctness of the program.
4. **Interface Segregation Principle (ISP):** The `UserRepository` interface provides a clear contract for data access, ensuring that clients are not forced to depend on methods they do not use.
5. **Dependency Inversion Principle (DIP):** The project depends on abstractions (interfaces) rather than concrete implementations. For example, the `UserRepository` interface is used by the service layer, decoupling the business logic from the data access layer.

This structure and adherence to Clean Architecture and SOLID principles ensure that the project is modular, testable, and maintainable. Each layer has a specific role, promoting separation of concerns and making it easier to manage and extend the application.