# EazyBytes Users Microservice

Welcome to the EazyBytes Users Microservice! This project is a robust microservice designed to manage customer accounts and profiles with scalability, security, and performance in mind. It demonstrates advanced engineering practices, cutting-edge technologies, and scalable architectural design.

---

## Project Highlights

### Microservice Architecture
This service adheres to modern microservice principles:
- Stateless Design: Ensures scalability and resilience.
- Separation of Concerns: Dedicated service for user management.
- High-Performance: Optimized with Redis caching for faster profile retrieval.

### Security
- JWT Authentication: Secure access for customer profiles and sign-in.
- Role-Based Access Control (RBAC): Fine-grained permissions to safeguard sensitive data.

### Database Management
- Relational Database: Utilizes JPA for seamless integration with SQL-backed databases.
- Immutable Entities: Ensures data integrity with audit trails.

### Advanced Features
- Redis Caching: Accelerated profile fetch with in-memory caching.
- Dynamic Account Number Generation: Uses the Luhn Algorithm for creating unique and valid account numbers.
- Validation: Input validation to prevent unauthorized data manipulation.
- Docker Containerization: Simplifies deployment and scalability.

---

## Technologies Used

- Java: Core programming language.
- Spring Boot: Framework for rapid microservice development.
- Spring Data JPA: Database abstraction layer.
- Redis: High-performance in-memory caching.
- JWT: Authentication framework.
- Docker: Containerization for deployment.
- Lombok: Simplified Java code with annotations.
- Swagger/OpenAPI: Seamless API documentation.

---

## Key Components

### Controllers
- `CustomerProfileController`: Fetches user profiles securely.
- `CustomerSignUpController`: Handles customer registration.
- `CustomerSignInController`: Manages customer authentication.

### Services
- `CustomerProfileService`: Fetches profiles with Redis caching.
- `CustomerSignUpService`: Validates and registers new customers.
- `CustomerSignInService`: Issues JWT tokens for authenticated users.

### Entities
- `CustomerEntity`: Represents customer data with immutability and audit trails.

---

## API Endpoints

### Customer Profile
- `GET /customers/profile`: Fetches the authenticated customer's profile.

### Customer Sign Up
- `POST /customers/signup`: Registers a new customer.

### Customer Sign In
- `POST /customers/signin`: Authenticates a customer and returns a JWT token.

---

## Current Features

The following features are currently implemented in this microservice:

| Feature Category | Feature | Status | Description |
|------------------|---------|--------|-------------|
| **Authentication & Security** | Customer Registration | ✅ Implemented | New customer signup with validation |
| | Customer Sign-In | ✅ Implemented | Email/password authentication |
| | JWT Authentication | ✅ Implemented | Secure token-based access control |
| | Password Hashing | ✅ Implemented | BCrypt encryption for passwords |
| | Role-Based Access Control | ✅ Implemented | RBAC with customer roles |
| **Account Management** | Profile Retrieval | ✅ Implemented | Get authenticated customer profile |
| | Account Number Generation | ✅ Implemented | Unique account numbers with Luhn algorithm |
| | Basic Account Info | ✅ Implemented | Store customer and account details |
| **Performance & Reliability** | Redis Caching | ✅ Implemented | High-performance profile caching |
| | Input Validation | ✅ Implemented | Request validation and error handling |
| | Database Management | ✅ Implemented | JPA with SQL database integration |
| **Documentation & Deployment** | API Documentation | ✅ Implemented | Swagger/OpenAPI integration |
| | Docker Support | ✅ Implemented | Containerization for deployment |
| | Audit Trails | ✅ Implemented | Created/updated timestamps |

---

## Planned Features & Roadmap

The following features are planned for development to create a complete banking accounts service:

| Priority | Feature Category | Feature | Description | Business Impact |
|----------|------------------|---------|-------------|-----------------|
| **High** | **Account Lifecycle** | Account Closure | Deactivate/delete customer accounts safely | Customer retention & compliance |
| | | Account Updates | Modify customer information (name, email, etc.) | Customer service & data accuracy |
| | | Multiple Account Types | Allow customers to open additional accounts | Revenue growth & customer satisfaction |
| | **Core Banking Operations** | Deposit Operations | Accept money deposits to accounts | Core banking functionality |
| | | Withdrawal Operations | Process money withdrawals from accounts | Core banking functionality |
| | | Balance Inquiries | Real-time account balance checking | Customer self-service |
| **Medium** | **Transaction Management** | Transaction History | View account transaction history | Customer transparency |
| | | Account Statements | Generate account statements (PDF/digital) | Regulatory compliance |
| | | Transaction Limits | Set and enforce daily/monthly limits | Risk management |
| | **Account Administration** | Account Status Management | Freeze/unfreeze accounts | Fraud prevention |
| | | Admin Search & Filtering | Search customers by various criteria | Administrative efficiency |
| | | Account Linking | Link accounts for families/joint accounts | Enhanced customer service |
| **Low** | **Advanced Features** | Beneficiary Management | Add/manage payment beneficiaries | Enhanced user experience |
| | | Inter-Account Transfers | Transfer money between own accounts | Customer convenience |
| | | Transaction Categories | Categorize transactions for reporting | Financial insights |
| | | Notification System | Real-time transaction notifications | Customer engagement |

### Implementation Roadmap

#### Phase 1: Core Account Operations (Priority: High)
- **Account Updates API**: `PUT /customers/profile` - Update customer information
- **Account Closure API**: `DELETE /customers/account` - Safely close accounts
- **Multiple Accounts**: Redesign data model to support multiple accounts per customer

#### Phase 2: Banking Transactions (Priority: High)
- **Deposit API**: `POST /accounts/{accountId}/deposit` - Process deposits
- **Withdrawal API**: `POST /accounts/{accountId}/withdraw` - Process withdrawals  
- **Balance API**: `GET /accounts/{accountId}/balance` - Get current balance

#### Phase 3: Transaction Management (Priority: Medium)
- **Transaction History API**: `GET /accounts/{accountId}/transactions` - View transaction history
- **Transaction Limits**: Implement daily/monthly transaction limits
- **Account Status Management**: Admin APIs for account freeze/unfreeze

#### Phase 4: Advanced Features (Priority: Low)
- **Admin Panel APIs**: Search, filter, and manage customer accounts
- **Beneficiary Management**: Add/remove payment beneficiaries
- **Enhanced Reporting**: Account statements and analytics

### Contributing
Contributors are welcome to help implement these planned features. Please check the repository issues for specific feature requests and implementation guidelines.

---