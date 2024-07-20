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
                    │   │       ├── GlobalExceptionHandler.java
                    │   │       ├── ApiException.java
                    │   │       └── NotFoundException.java
                    │   ├── service/
                    │   │   ├── UserService.java
                    │   │   └── _GenericService.java
                    ├── application/
                    │   ├── dto/
                    │   │   ├── UserRequestDTO.java
                    │   │   ├── UserResponseDTO.java
                    │   │   └── UserFilterDTO.java
                    │   ├── mapper/
                    │   │   ├── UserMapper.java
                    │   │   └── _GenericMapper.java
                    │   └── usecase/
                    │       └── user/
                    │           ├── CreateUserUseCase.java
                    │           ├── DeleteUserUseCase.java
                    │           ├── GetUserUseCase.java
                    │           ├── UpdateUserUseCase.java
                    │           └── FindUserUseCase.java
                    ├── domain/
                    │   ├── entity/
                    │   │   ├── GenericEntity.java
                    │   │   └── User.java
                    │   ├── repository/
                    │   │   └── UserRepository.java
                    │   └── specification/
                    │       ├── _GenericSpecification.java
                    │       └── UserSpecification.java
                    ├── shared/
                    │   ├── exception/
                    │   │   ├── ApiException.java
                    │   │   ├── GlobalExceptionHandler.java
                    │   │   └── NotFoundException.java
                    │   ├── validation/
                    │   │   ├── NameValidation.java
                    │   │   ├── EmailProviderValidation.java
                    │   │   └── HumanDateValidation.java
                    │   └── utils/
                    │       └── ParseSortUtils.java
                    └── infrastructure/
                        └── config/
                            ├── DatabaseConfig.java
                            ├── SwaggerConfig.java
                            └── TemplateApplication.java
```

## Explanation of Clean Architecture and SOLID Principles

### Adapters Layer

- **Controller:** The `UserController` class handles HTTP requests and maps them to the appropriate use cases. It serves as an interface adapter that translates user actions into application actions.
- **Exception:** The `GlobalExceptionHandler`, `ApiException`, and `NotFoundException` classes handle specific exceptions related to various operations.

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

- **Config:** Classes like `DatabaseConfig`, `SwaggerConfig`, and `TemplateApplication` handle infrastructure-specific configurations and bootstrapping. They ensure that the application is properly set up and configured.

## SOLID Principles Applied

1. **Single Responsibility Principle (SRP):** Each class in the project has a single responsibility. For instance, the `UserService` class handles business logic, while the `UserController` class handles HTTP requests.
2. **Open/Closed Principle (OCP):** The project is designed to be open for extension but closed for modification. New features can be added through new use cases or extending existing ones without modifying the core logic.
3. **Liskov Substitution Principle (LSP):** The domain models and DTOs can be substituted without affecting the correctness of the program.
4. **Interface Segregation Principle (ISP):** The `UserRepository` interface provides a clear contract for data access, ensuring that clients are not forced to depend on methods they do not use.
5. **Dependency Inversion Principle (DIP):** The project depends on abstractions (interfaces) rather than concrete implementations. For example, the `UserRepository` interface is used by the service layer, decoupling the business logic from the data access layer.

This structure and adherence to Clean Architecture and SOLID principles ensure that the project is modular, testable, and maintainable. Each layer has a specific role, promoting separation of concerns and making it easier to manage and extend the application.
