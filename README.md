# CaseStudy Application

## Introduction
This is a Java SpringBoot BackEnd application offering Rest APIs.

## Features
- **Project Management:** The user can list create, update, and delete Project.
- **Model Management:** User can list the Model, add Model to Project and can update, delete the Model.
- **Part Management:** User can list the Part, add Part to Model and can update, delete the Part.
- **Logging Management:** User can List And filter the That appliyed to Parts and Models.
- **Comprehensive Swagger OpenAPI Documentation:** The application includes Swagger documentation, accessible at [Swagger UI](http://localhost:8080/show-swagger).

## Technology Stack
- Java 17
- Spring Boot v3.3.7
- RESTful API
- Spring Data, JPA & Hibernate
- PostgreSQL
- Maven
- Lombok
- Swagger-UI, open-API v3.0.1
- JSON
- Docker

## Application Overview

### Branches
- **'main'** branch: Contains swagger-ui
### Run the application with Docker
- **1. Install Docker Desktop** (if they don't have it installed).
- **2. Pull the Docker images**
-     docker pull alimzahiroglu/case-study:latest
-     docker pull postgres:latest
- 3.Place the **docker-compose.yml** file in a directory.
- **4. Run the application with Docker Compose:**
-     docker compose up --build
- application will be available on: **http://localhost:8080/show-swagger**

### Database ER Diagram

![Database ER Diagram](https://github.com/user-attachments/assets/dc9ff493-8019-4695-b0ba-9cc77ab16c78)


