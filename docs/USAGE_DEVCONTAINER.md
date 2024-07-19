
## Running the Application in a DevContainer

### Prerequisites

Before you begin, ensure you have the following installed on your system:

- Docker
- Visual Studio Code
- Remote - Containers Extension for VSCode

### Step 1: Clone the Repository

First, clone the repository to your local machine:

```bash
git clone https://github.com/napalm23zero/template-java-17-spring-oracle.git
cd template-java-17-spring-oracle
```

### Step 2: Configure Environment Variables

This project uses `.env` files to manage configuration settings. The `.env` file is provided to manage sensitive data.

Create a file named `.env` in the root directory and add the following content:

```env
ORACLE_PASSWORD=1q2w3e4r5t6y
ORACLE_USER=template
ORACLE_DATABASE=template_app
ORACLE_HOST=template-oracle-db
ORACLE_PORT=1521
SERVER_PORT=8080
```

### Step 3: Open the Project in VSCode

Open the project in Visual Studio Code:

```bash
code .
```

### Step 4: Reopen in Container

Open the Command Palette (F1) and select:

```plaintext
Remote-Containers: Reopen in Container
```

### Step 5: Wait for the Dev Container to Build and Start

This might take a few minutes depending on your system and internet speed.

### Step 6: Run the Application

Once the container is running, open a terminal in VSCode and run:

```bash
mvn clean install
mvn spring-boot:run
```

### Step 7: Access the Application

- **API Endpoint:** [http://localhost:8080/api](http://localhost:8080/api)
- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Database Access

When running the application in a DevContainer, use the following connection string to access the Oracle database:

- **JDBC URL:** `jdbc:oracle:thin:@//template-oracle-db:1521/template_app`
- **Username:** `template`
- **Password:** `1q2w3e4r5t6y`

### Accessing Swagger Endpoint Outside the Container

If you want to access the Swagger UI outside of the dev container, follow these steps:

1. **Ensure the application is running inside the dev container as described above.**

2. **Open your web browser and navigate to:**

   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
