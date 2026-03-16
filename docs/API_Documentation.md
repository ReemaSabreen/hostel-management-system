# API Documentation
**Hostel Management System**

---

## 1. API Overview

The Hostel Management System provides a RESTful API that enables interaction between the frontend application and the backend server.

### Supported Modules
- Authentication
- Student Management
- Room Management
- Room Allocation
- Fee Management
- Payment Processing
- Complaint Management
- Mess Management

**Base URL:** `http://localhost:8080/api`  

**Response Format:** All responses are returned in **JSON format**.

---


# 2. Authentication API

## Base Path

```
/api/auth
```

---

## 2.1 Register User

Creates a new system user.

### Endpoint

```
POST /api/auth/register
```

### Request Body

```json
{
  "username": "john",
  "email": "john@example.com",
  "passwordHash": "password123",
  "role": "STUDENT"
}
```

### Response

```json
{
  "userId": 1,
  "username": "john",
  "email": "john@example.com",
  "role": "STUDENT"
}
```
---

## 2.2 Login

Authenticates a user.

### Endpoint

```
POST /api/auth/login
```

### Request

```json
{
  "username": "john",
  "password": "password123"
}
```

### Response

```json
{
  "token": "jwt_token",
  "role": "STUDENT"
}
```

---

## 2.3 Reset Password

### Endpoint

```
POST /api/auth/reset-password
```

### Parameters

```
email
newPassword
```

---

## 2.4 Forgot Password

### Endpoint

```
POST /api/auth/forgot-password
```

---

## 2.5 Update User Status

### Endpoint

```
PUT /api/auth/status/{id}
```

---

## 2.6 Update User Profile

### Endpoint

```
PUT /api/auth/profile/{id}
```
---

# 3. Student API

## Base Path

```
/api/students
```

---

## 3.1 Create Student

### Endpoint

```
POST /api/students
```

### Request

```json
{
  "userId": 5,
  "enrollmentNo": "ENR001",
  "firstName": "John",
  "lastName": "Doe",
  "course": "B.Tech",
  "year": 3
}
```

---

## 3.2 Get All Students

### Endpoint

```
GET /api/students
```

---

## 3.3 Get Student By ID

### Endpoint

```
GET /api/students/{id}
```

---

## 3.4 Update Student

### Endpoint

```
PUT /api/students/{id}
```

---

## 3.5 Delete Student

### Endpoint

```
DELETE /api/students/{id}
```

---

# 4. Room API

## Base Path

```
/api/rooms
```

---

## 4.1 Create Room

### Endpoint

```
POST /api/rooms
```

### Request

```json
{
  "roomNumber": "A101",
  "floor": 1,
  "type": "SINGLE",
  "rentPerMonth": 8000
}
```

---

## 4.2 Get All Rooms

### Endpoint

```
GET /api/rooms
```

---

## 4.3 Get Available Rooms

### Endpoint

```
GET /api/rooms/available
```

---

## 4.4 Update Room

### Endpoint

```
PUT /api/rooms/{roomId}
```

---

## 4.5 Delete Room

### Endpoint

```
DELETE /api/rooms/{roomId}
```

---

# 5. Room Allocation API

---

## 5.1 Allocate Room

### Endpoint

```
POST /api/rooms/allocate
```

### Request

```
studentId
roomId
```

---

## 5.2 Deallocate Room

### Endpoint

```
PUT /api/rooms/deallocate/{allocationId}
```

---

## 5.3 Get Student Allocations

### Endpoint

```
GET /api/rooms/student/{studentId}
```

---

## 5.4 Get Current Allocation

### Endpoint

```
GET /api/rooms/current/{studentId}
```

---

# 6. Fee API

## Base Path

```
/api/fees
```

---

## 6.1 Create Fee

### Endpoint

```
POST /api/fees
```

### Request

```json
{
  "student": {
    "studentId": 1
  },
  "feeType": "ROOM_RENT",
  "amount": 8000
}
```

---

## 6.2 Get Fee By ID

### Endpoint

```
GET /api/fees/{feeId}
```

---

## 6.3 Update Fee

### Endpoint

```
PUT /api/fees/{feeId}
```

---

## 6.4 Get Student Fees

### Endpoint

```
GET /api/fees/student/{studentId}
```

---

## 6.5 Get All Fees

### Endpoint

```
GET /api/fees
```

---

## 6.6 Get Fee Defaulters

### Endpoint

```
GET /api/fees/defaulters
```

---

# 7. Payment API

---

## 7.1 Make Payment

### Endpoint

```
POST /api/fees/{feeId}/pay
```

### Request

```json
{
  "amount": 8000,
  "paymentMethod": "UPI",
  "transactionId": "TXN12345"
}
```

---

## 7.2 Get Student Payments

### Endpoint

```
GET /api/fees/payments/{studentId}
```

---

## 7.3 Get Payment Receipt

### Endpoint

```
GET /api/fees/receipt/{paymentId}
```

---

# 8. Complaint API

## Base Path

```
/api/complaints
```

---

## 8.1 Raise Complaint

### Endpoint

```
POST /api/complaints/raise
```

### Request

```json
{
  "ticketNo": "CMP001",
  "student": {
    "studentId": 1
  },
  "category": "MAINTENANCE",
  "subject": "Fan not working",
  "description": "Room fan is not functioning properly",
  "priority": "HIGH"
}
```

---

## 8.2 Get Student Complaints

### Endpoint

```
GET /api/complaints/student/{studentId}
```

---

## 8.3 Get All Complaints

### Endpoint

```
GET /api/complaints
```

---

## 8.4 Update Complaint Status

### Endpoint

```
PUT /api/complaints/{complaintId}/status
```

### Parameters

```
status = OPEN | IN_PROGRESS | RESOLVED
```

---

# 9. Mess Management API

## Base Path

```
/api/mess
```

---

## 9.1 Create Mess Menu

### Endpoint

```
POST /api/mess/menu
```

---

## 9.2 Get Mess Menu

### Endpoint

```
GET /api/mess/menu
```

---

## 9.3 Mark Mess Attendance

### Endpoint

```
POST /api/mess/attendance
```

---

## 9.4 Get Student Mess Attendance

### Endpoint

```
GET /api/mess/attendance/{studentId}
```

---

# 10. Authentication Requirements

Some API endpoints require authentication.

Authentication is handled using **JWT (JSON Web Tokens)**.

### Steps

1. Login using `/api/auth/login`
2. Receive JWT token
3. Include token in the request header

### Example

```
Authorization: Bearer <token>
```

---

# 11. Error Responses

### Example Error Response

```json
{
  "timestamp": "2026-03-16T10:00:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "Room full"
}
```

---

# 12. Conclusion

The API architecture follows **RESTful design principles** and enables efficient communication between the frontend and backend components.

The API supports all major hostel management functionalities including:

- Student management
- Room allocation
- Fee processing
- Complaint handling
- Mess management
