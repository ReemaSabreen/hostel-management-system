# Hostel Management System

A full-stack **Hostel Management System** designed to streamline hostel operations such as student management, room allocation, fee tracking, complaints, and mess services.

The system uses **role-based access control** to ensure secure and organized access for different types of users.

---

# Project Overview

Managing hostel operations manually can lead to inefficiencies and errors.  
This project provides a centralized digital platform for managing hostel administration tasks efficiently.

The system supports:

- Student record management
- Room allocation and occupancy tracking
- Hostel fee generation and payment tracking
- Complaint submission and resolution
- Mess menu and attendance management

---

# System Roles

The system uses **Role-Based Access Control (RBAC)**.

| Role | Responsibilities |
|-----|------------------|
| Admin | Full system control |
| Warden | Manage students and room allocations |
| Student | Access personal hostel services |
| Mess Manager | Manage mess operations |

---

# System Architecture

```
Frontend (ReactJS)
        │
        │ REST API
        ▼
Backend (Spring Boot)
        │
        │ JPA / Hibernate
        ▼
MySQL Database
```

---

# Technology Stack

## Frontend

- ReactJS
- Axios
- CSS

## Backend

- Spring Boot
- Spring Data JPA
- Spring Security
- JWT Authentication

## Database

- MySQL

## Tools

- Postman (API testing)
- GitHub (version control)

---

# Project Structure

```
Hostel_Management
│
├── backend
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   └── config
│
├── frontend
│   ├── components
│   ├── pages
│   ├── services
│   └── styles
│
├── database
│   ├── schema
│   └── ER_Diagram.png
│
├── docs
│   ├── API_Documentation.md
│   ├── Database_Documentation.md
│   ├── User_Manual.md
│   └── Testing_Report.md
│
└── README.md
```

---

# Features

## Authentication

- User login
- JWT-based authentication
- Role-based authorization

## Student Management

- Add student
- Update student details
- Delete student
- Checkout student

## Room Management

- Create rooms
- View available rooms
- Allocate rooms
- Deallocate rooms
- Track room occupancy

## Fee Management

- Generate student fees
- Update fee records
- View all fees
- Identify fee defaulters

## Payment System

- Students can pay hostel fees
- Track payment history
- Generate payment receipts

## Complaint Management

- Students can raise complaints
- Admin/Warden can update complaint status
- Complaint workflow tracking

## Mess Management

- Create mess menu
- View mess menu
- Mark student mess attendance

---

# Installation Guide

## 1. Clone the Repository

```
git clone https://github.com/yourusername/hostel-management-system.git
cd hostel-management-system
```

---

## 2. Backend Setup

Navigate to backend folder:

```
cd backend
```

Configure database in `.env` or `application.properties`.

Example configuration:

```
spring.datasource.url=jdbc:mysql://localhost:3306/hostel_management
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
```

Run the backend:

```
mvn spring-boot:run
```

Backend runs at:

```
http://localhost:8080
```

---

## 3. Frontend Setup

Navigate to frontend folder:

```
cd frontend
```

Install dependencies:

```
npm install
```

Run frontend:

```
npm start
```

Frontend runs at:

```
http://localhost:3000
```

---

# API Base URL

```
http://localhost:8080/api
```

Detailed API documentation can be found in:

```
docs/API_Documentation.md
```

---

# Database

The system uses **MySQL** as the database.

Database schema and ER diagram are located in:

```
database/
```

Files included:

- Database schema
- ER Diagram

---

# Documentation

Project documentation is located in the `docs` folder.

| Document | Description |
|--------|-------------|
| API_Documentation.md | List of REST API endpoints |
| Database_Documentation.md | Database design and tables |
| User_Manual.md | Instructions for using the system |
| Testing_Report.md | Testing results and test cases |

---

# Screenshots

Screenshots of the system UI are available in:

```
docs/screenshots
```

Screenshots include:

- Login page
- Dashboard
- Student management
- Room management
- Fee management
- Complaint module
- Mess module

---

# Future Improvements

Possible future enhancements include:

- Visitor management module
- Reports and analytics dashboard
- Email notifications
- Mobile application integration

---

# Conclusion

The Hostel Management System provides a scalable and organized solution for managing hostel operations.

By integrating role-based access control, automated workflows, and centralized data management, the system improves administrative efficiency and transparency.

---