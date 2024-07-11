# Template Java 17 Spring Boot Application

## Welcome, Hustlers of Hustle Tech!

So, you’ve decided to dive into the dark arts of Java and Spring Boot? Good choice! Let’s get this bad boy up and running. Whether you’re using devcontainers like a true modern developer or just wanna run the Dockerfile straight up, I got you covered.

### Quick Start Guide

#### 1. Running with DevContainers (because you're fancy)

1. **Open your VSCode**:

   - If you don’t have it, stop right here and get it. Seriously.

2. **Open this Project Folder**:

   - Navigate to where you cloned this repository and open it.

3. **Reopen in Container**:

   - VSCode should automatically detect the `.devcontainer` folder and ask you if you want to reopen in a container. Click yes. If it doesn’t, hit F1, type “Dev Containers: Reopen in Container” and hit enter.

4. **Watch the Magic**:

   - Grab a coffee or whatever because this might take a few minutes the first time. You’ll see a bunch of stuff happening. That’s your environment being set up. Cool, right?

5. **Start Coding**:

   - You’re all set. Your environment is ready. Start writing some badass code.

6. **Run the Application Inside the DevContainer**:

   - Open a terminal inside the devcontainer and run:
     ```sh
     mvn spring-boot:run
     ```
   - Your application should be running at [http://localhost:6080](http://localhost:6080).

7. **Access the Swagger Page from the Host Machine**:
   - Open your browser and navigate to [http://localhost:6080/api/swagger-ui.html](http://localhost:6080/api/swagger-ui.html).

#### 2. Running with Dockerfile (for the minimalists)

1. **Build the Docker Image**:

   - Open your terminal (you do have Docker installed, don’t you?) and run:
     ```sh
     docker build -t template-java-17-spring .
     ```

2. **Run the Docker Container**:

   - Once the build is done, run:
     ```sh
     docker run -d -p 8080:8080 template-java-17-spring
     ```

3. **Boom!**:

   - Your application should be running at [http://localhost:8080](http://localhost:8080). If it’s not, check your steps, you probably messed up somewhere.

4. **Access the Swagger Page**:
   - Open your browser and navigate to [http://localhost:8080/api/swagger-ui.html](http://localhost:8080/api/swagger-ui.html).

### Project Structure (aka Clean Architecture)

This project follows the Clean Architecture principles because we like our code clean and our minds clearer. Here’s a quick breakdown:

- **adapters/controller**: Where the HTTP requests are handled. You know, the endpoints.
- **application/dto**: Data Transfer Objects. Fancy term for stuff that gets passed around.
- **application/mapper**: Maps DTOs to entities and vice versa. Like a tour guide for data.
- **application/usecase/user**: The real MVPs. These are your use cases – the core business logic.
- **domain/entity**: The heart and soul. Your entities live here.
- **infrastructure/config**: Configuration files. Not the most exciting, but necessary.
- **repository**: Handles data access. Repositories are like the data ninjas of your application.
- **service**: Service layer. Manages business logic and calls repositories.

### Dependencies

Here’s what we’re working with:

- **Spring Boot**: Because who likes writing boilerplate code?
- **Lombok**: Less code, more fun.
- **Spring Data JPA**: For that sweet ORM goodness.
- **Oracle JDBC**: To connect to our Oracle database.
- **Springdoc OpenAPI**: Swagger UI for API documentation.

### Dev Setup

- **Java 17**: We like living on the edge.
- **Maven**: For managing dependencies.
- **Docker**: Because containers are awesome.

### Closing Notes

You’ve made it this far. Either you’re really dedicated, or you just scrolled down to see if this README would ever end. Either way, good job. Now go forth and code like the wind!

May the force be with you, always.

---

Feel free to customize any part of this README to better suit your project’s specifics or your personal style.
