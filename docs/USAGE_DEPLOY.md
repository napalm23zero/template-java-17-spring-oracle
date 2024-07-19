
## Build, Test and Deploying the Application (GitHub Actions)

### Build and Test Workflow

The project uses GitHub Actions for continuous integration and testing. The build and test process is defined in the `build-and-test.yml` file.

#### Build and Test Workflow

1. **Trigger**: The workflow is triggered on pushes and pull requests to the `main` branch.
2. **Environment**: The job runs on `ubuntu-latest`.
3. **Services**: An Oracle database service is set up using the custom `Dockerfile.database`.
4. **Steps**:
   - **Checkout code**: Uses `actions/checkout@v2` to check out the code from the repository.
   - **Set up JDK 17**: Uses `actions/setup-java@v2` to set up Java Development Kit 17.
   - **Cache Maven packages**: Caches Maven dependencies to speed up the build process.
   - **Install dependencies**: Installs Maven dependencies offline.
   - **Build with Maven**: Compiles the code and packages it, skipping tests.
   - **Run tests**: Executes the test suite.

#### How to Check the Workflow

1. Go to your repository on GitHub.
2. Click on `Actions`.
3. Select the `Build and Test` workflow from the list.
4. You can see the history of workflow runs, their statuses, and detailed logs for each step.

### Deploying the Application

#### Choosing a Cloud Provider

To deploy this application, you need to set the `CLOUD_PROVIDER` secret to one of the following values:

- `AWS`
- `GCP`
- `Azure`
- `Heroku`

#### Setting Up Secrets

1. Go to the repository on GitHub.
2. Click on `Settings`.
3. Go to `Secrets` and click on `New repository secret`.
4. Add the following secrets based on your chosen cloud provider:

**For GCP:**

- `CLOUD_PROVIDER`: `GCP`
- `GCP_SA_KEY`
- `GCP_PROJECT_ID`
- `GCP_SA_EMAIL`
- `GCP_BUCKET_NAME`
- `GKE_CLUSTER_NAME`
- `GKE_CLUSTER_REGION`

**For AWS:**

- `CLOUD_PROVIDER`: `AWS`
- `AWS_ACCESS_KEY_ID`
- `AWS_SECRET_ACCESS_KEY`
- `AWS_REGION`
- `AWS_BUCKET_NAME`

**For Azure:**

- `CLOUD_PROVIDER`: `Azure`
- `AZURE_CREDENTIALS`
- `AZURE_WEBAPP_NAME`
- `AZURE_RESOURCE_GROUP`

**For Heroku:**

- `CLOUD_PROVIDER`: `Heroku`
- `HEROKU_API_KEY`
- `HEROKU_APP_NAME`

### Deployment Workflow

The deployment process is automated using GitHub Actions. The deployment workflow is defined in the `deploy-cloud.yml` file.

#### How It Works

1. **Trigger**: The workflow is triggered on pushes to the `main` branch.
2. **Environment**: The job runs on `ubuntu-latest`.
3. **Steps**:
   - **Checkout code**: Uses `actions/checkout@v2` to check out the code from the repository.
   - **Set up the cloud provider**: Based on the `CLOUD_PROVIDER` secret, sets up the necessary environment for deployment.
   - **Deploy to the cloud**: Executes the deployment script specific to the chosen cloud provider.

#### How to Check the Deployment Workflow

1. Go to your repository on GitHub.
2. Click on `Actions`.
3. Select the `Deploy` workflow from the list.
4. You can see the history of workflow runs, their statuses, and detailed logs for each step.

By following these instructions, you can ensure that your application is built, tested, and deployed seamlessly across different cloud providers.
