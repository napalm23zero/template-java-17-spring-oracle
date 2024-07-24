## Running the Application with Docker Compose

### Prerequisites

Before you begin, ensure you have the following installed on your system:

- Docker
- Docker Compose

### Step 1: Clone the Repository

First, clone the repository to your local machine:

```bash
git clone https://github.com/napalm23zero/template-java-17-spring-oracle.git
cd template-java-17-spring-oracle
```

### Step 2: Configure Environment Variables

This project uses `.env` files to manage configuration settings. The `.env` file is provided to manage sensitive data. Here's a simple one with the following content, just for testing. Once you deploy this app in production, change these values:

```env
ORACLE_PASSWORD=1q2w3e4r5t6y
ORACLE_USER=template
ORACLE_DATABASE=template_app
ORACLE_HOST=template-oracle-db-build
ORACLE_PORT=1521
SERVER_PORT=8080
```

### Step 3: Running the Application

#### Option 1: Run Backend + Oracle Database from Docker Compose

To run both the backend and the Oracle database using Docker Compose, follow these steps:

1. Ensure you have the `.env` file configured as described above.
2. Use the provided script to set the environment variables correctly based on your OS and start Docker Compose:

   - **On Unix (Linux/macOS):**
   
     ```bash
     ./start-docker-compose.sh
     ```

   - **On Windows PowerShell:**

     ```powershell
     .\start-docker-compose.ps1
     ```

3. Access the application:

   - **API Endpoint:** [http://localhost:8080/api](http://localhost:8080/api)
   - **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

#### Option 2: Run Backend with External Database

To run only the backend and connect to an external Oracle database, follow these steps:

1. Update the `.env` file with your external database details:

   ```env
   ORACLE_PASSWORD=your_external_db_password
   ORACLE_USER=your_external_db_user
   ORACLE_DATABASE=your_external_db_service_name
   ORACLE_HOST=your_external_db_host
   ORACLE_PORT=your_external_db_port
   ```

2. Start the backend service using the external database configuration:

   - **On Unix (Linux/macOS):**
   
     ```bash
     ./start-docker-compose.sh
     ```

   - **On Windows PowerShell:**

     ```powershell
     .\start-docker-compose.ps1
     ```

3. Access the application:

   - **API Endpoint:** [http://localhost:8080/api](http://localhost:8080/api)
   - **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Database Access

When running the application with Docker Compose, use the following connection string to access the Oracle database:

- **JDBC URL:** `jdbc:oracle:thin:@localhost:1521/template_app`
- **Username:** `template`
- **Password:** `1q2w3e4r5t6y`
