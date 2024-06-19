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
3. **./mvnw package**
4. 
