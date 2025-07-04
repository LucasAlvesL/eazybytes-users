# EazyBytes Users Microservice

Welcome to the EazyBytes Users Microservice! This project is a robust microservice designed to manage customer accounts and profiles with scalability, security, and performance in mind. It demonstrates advanced engineering practices, cutting-edge technologies, and scalable architectural design.

---

## Project Highlights

### Microservice Architecture
This service adheres to modern microservice principles:
- **Stateless Design**: Ensures scalability and resilience.
- **Separation of Concerns**: Dedicated service for user management.
- **High-Performance**: Optimized with Redis caching for faster profile retrieval.

### Security
- **JWT Authentication**: Secure access for customer profiles and sign-in.
- **Role-Based Access Control (RBAC)**: Fine-grained permissions to safeguard sensitive data.
- **Password Encryption:** All passwords are securely hashed using BCrypt.

### Database Management
- **Relational Database**: Utilizes JPA for seamless integration with SQL-backed databases.
- **Immutable Entities**: Ensures data integrity with audit trails.

### Advanced Features
- **Redis Caching**: Accelerated profile fetch with in-memory caching.
- **Dynamic Account Number Generation**: Uses the Luhn Algorithm for creating unique and valid account numbers.
- **Validation**: Input validation to prevent unauthorized data manipulation.
- **Docker Containerization**: Simplifies deployment and scalability.

## Continuous Integration / Continuous Deployment (CI/CD)
Currently, this project does not include a CI/CD pipeline configuration.  
To automate building, testing, and deploying this microservice, you can set up a pipeline using popular tools such as:

- **GitHub Actions** (`.github/workflows/maven.yml`)

### A typical CI/CD workflow for this Java/Spring Boot project could include:
1. Automatic build and test on each push or pull request.
2. Code quality checks.
3. Docker image build and push.
4. Deployment to staging/production environments.

---

## Technologies Used
- **Java**: Core programming language.
- **Spring Boot**: Framework for rapid microservice development.
- **Spring Data JPA**: Database abstraction layer.
- **Redis**: High-performance in-memory caching.
- **JWT**: Authentication framework.
- **Docker**: Containerization for deployment.
- **Lombok**: Simplified Java code with annotations.
- **Swagger/OpenAPI**: Seamless API documentation.

---

## API Endpoints

### Customer Profile
- `GET /customers/profile`: Fetches the authenticated customer's profile.

### Customer Sign Up
- `POST /customers/signup`: Registers a new customer.

### Customer Sign In
- `POST /customers/signin`: Authenticates a customer and returns a JWT token.

---