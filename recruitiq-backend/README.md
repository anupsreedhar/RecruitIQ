
# recruitiq-backend

## RecruitIQ Backend API

RecruitIQ Backend is a **Spring Boot–based REST API** that powers the RecruitIQ platform. It handles core recruitment workflows such as candidate management, job postings, interviews, authentication, and integrations.

---

## 🧩 Technology Stack

* **Language**: Java 17+
* **Framework**: Spring Boot
* **Build Tool**: Maven
* **API Style**: RESTful APIs
* **Security**: Spring Security / JWT (if enabled)
* **Documentation**: OpenAPI / Swagger
* **Database**: PostgreSQL / MySQL (configurable)
* **ORM**: JPA / Hibernate

---

## 📂 Project Structure

```
recruitiq-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/zelexon/recruitiq/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── dto/
│   │   │       ├── entity/
│   │   │       └── config/
│   │   └── resources/
│   │       ├── application.yml
│   │       └── db/
│   └── test/
├── pom.xml
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

* Java 17 or later
* Maven 3.8+
* Database (PostgreSQL/MySQL)

### Build & Run

```bash
cd recruitiq-backend
mvn clean install
mvn spring-boot:run
```

Application starts at:

```
http://localhost:8080
```

---

## 📘 API Documentation

Once the application is running, access Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

or

```
http://localhost:8080/v3/api-docs
```

---

## 🔐 Security (High-Level)

* Token-based authentication (JWT)
* Role-based access control (Admin, Recruiter, Candidate)
* Input validation & exception handling
* Secure password storage (BCrypt)

---

## 🧪 Testing

```bash
mvn test
```

---

## 📦 Deployment

* Can be containerized using Docker
* Supports CI/CD pipelines (GitHub Actions, Jenkins)
* Cloud-ready (AWS / Azure / GCP)

---

## 👨‍💻 Maintainer

**Anup Sreedharan**
Architect – RecruitIQ

---
