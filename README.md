# Hotel Management System

## Project Description

The Hotel Management System is designed to streamline operations for both hotel employees and guests. This system offers functionalities for customers to search available rooms, make reservations, check in and out, and generate invoices. Employees can manage room availability, maintain customer profiles, oversee housekeeping schedules, and handle billing. The backend is implemented using Spring Boot, and the system supports role-based access control with JWT authentication.

## Project Structure

- **Customer Management**: Users can register, login, view, update profiles, and change passwords.
- **Employee Management**: Admins can manage hotel employees and staff.
- **Search Functionality**: Search for reservations by customer name or ID, date, customer info, available rooms with details (price, facilities, capacity, size, features).
- **Reservation Management**: Customers can book, modify, and cancel reservations (cancellation needs admin approval).
- **Room Management**: Admins manage room types, availability, and status.
- **Check-In/Check-Out**: Manage customer arrival and departure processes.
- **Housekeeping Management**: Schedule and track housekeeping tasks and employees.
- **Billing**: Generate and manage invoices for customer reservations.

## ER Diagram

![ER Diagram](path/to/er-diagram.png)

## How to Build, Package, and Run the Application

### Build and Package

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/project2-hotel-management-system.git
   cd project2-hotel-management-system
2. **Build the Project**
   ```bash
   ./mvnw clean install
3. **Package the application**
   ```bash
   ./mvnw package
### Run with Docker
1. **Create Docker Image**
   ```bash
   docker build -t hotel-management-system .
2. **Push Docker Image to DockerHub**
   ```bash
   docker tag hotel-management-system yourdockerhubusername/hotel-management-system
   docker push yourdockerhubusername/hotel-management-system .
3. **Run with Docker Compose**
   ```bash
   docker-compose up
## DockerHub Repository

[DockerHub Link](https://hub.docker.com/repository/docker/yourdockerhubusername/hotel-management-system)

## API Documentation

The APIs are documented using OpenAPI 3.1.0 standards. The generated documentation can be accessed at
[API specefication](https://app.swaggerhub.com/apis/AYSARFAWAZ77/finalprojectweb/1.0.0).

## Postman Collection

A Postman collection that includes a complete testing scenario to simulate the user journey on the system interface can be found in the repository: [Postman Collection](path/to/postman-collection.json)

## Lessons Learned

Throughout this project, we have learned the following:

- How to set up a Spring Boot application with a structured architecture.
- Implementation of role-based access control using JWT.
- Effective use of JPA for database operations and entity relationships.
- Building and running Docker images and containers.
- API versioning strategies in RESTful web services.
- Comprehensive testing and documentation of APIs using Postman and OpenAPI.

## Contributors

- **Student 1**: [GitHub](https://github.com/student1)
- **Student 2**: [GitHub](https://github.com/student2)
