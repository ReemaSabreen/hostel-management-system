# Database Documentation
Hostel Management System

---

## 1. Database Overview

The Hostel Management System uses a relational database to store and manage all operational data related to hostel activities.

The database stores information about:

- Users
- Students
- Rooms
- Room Allocations
- Fees
- Payments
- Complaints
- Mess Menu
- Mess Attendance

The database is implemented using **MySQL** and managed through **Spring Boot JPA/Hibernate**.

---

## 2. Database Name
hostel_management


---

## 3. Tables Implemented

The system contains the following tables:

| Table Name | Description |
|------------|-------------|
| users | Stores login credentials and roles |
| students | Stores student profile details |
| rooms | Stores hostel room details |
| room_allocations | Stores room allocation records |
| fees | Stores fee records for students |
| payments | Stores payment transactions |
| complaints | Stores complaints raised by students |
| mess_menu | Stores mess food menu |
| mess_attendance | Stores mess attendance of students |

---

## 4. Tables Not Implemented

The following modules were planned but not implemented in this version of the system:

- Visitor Management
- Reports Module

These tables may appear in the database schema but are not actively used in the application.

---

## 5. Table Descriptions

### 5.1 Users Table

Stores system users such as administrators, wardens, students, and mess managers.

Key fields:

- user_id (Primary Key)
- username
- email
- password_hash
- role
- is_active
- created_at
- updated_at

Roles supported:

- ADMIN
- WARDEN
- STUDENT
- MESS_MANAGER

---

### 5.2 Students Table

Stores detailed information about hostel students.

Key fields:

- student_id (Primary Key)
- user_id (Foreign Key)
- enrollment_no
- first_name
- last_name
- date_of_birth
- gender
- course
- year
- phone
- emergency_contact
- address

Relationship:

User (1) —— (1) Student


Each student is linked to a system user.

---

### 5.3 Rooms Table

Stores hostel room details.

Key fields:

- room_id (Primary Key)
- room_number
- floor
- type
- capacity
- current_occupancy
- status
- rent_per_month

Room Types:

- SINGLE
- DOUBLE
- TRIPLE
- QUAD

Room Status:

- AVAILABLE
- OCCUPIED
- MAINTENANCE

---

### 5.4 Room Allocations Table

Stores which student is allocated to which room.

Key fields:

- allocation_id (Primary Key)
- student_id (Foreign Key)
- room_id (Foreign Key)
- allocation_date
- deallocation_date
- status

Allocation Status:

- ACTIVE
- COMPLETED



Relationship:
Student (1) —— (Many) RoomAllocation
Room (1) —— (Many) RoomAllocation


---

### 5.5 Fees Table

Stores fee records generated for students.

Key fields:

- fee_id (Primary Key)
- student_id (Foreign Key)
- fee_type
- amount
- due_date
- status
- created_at

Fee Types:

- ROOM_RENT
- MESS
- SECURITY_DEPOSIT
- OTHER

Fee Status:

- PENDING
- PAID
- OVERDUE

Relationship:
Student (1) —— (Many) Fees

---

### 5.6 Payments Table

Stores payment transactions made by students.

Key fields:

- payment_id (Primary Key)
- fee_id (Foreign Key)
- amount
- payment_method
- transaction_id
- payment_date

Payment Methods:

- UPI
- CARD
- CASH

Relationship:
Fee (1) —— (Many) Payments


---

### 5.7 Complaints Table

Stores complaints raised by students.

Key fields:

- complaint_id (Primary Key)
- student_id (Foreign Key)
- ticket_no
- category
- subject
- description
- priority
- status
- created_at
- resolved_at
- remarks

Complaint Categories:

- MAINTENANCE
- MESS
- CLEANLINESS
- OTHER

Complaint Status:

- OPEN
- IN_PROGRESS
- RESOLVED


Relationship:
Student (1) —— (Many) Complaints
---

### 5.8 Mess Menu Table

Stores the daily food menu for the hostel mess.

Fields include:

- menu_id
- meal_type
- menu_items
- date

---

### 5.9 Mess Attendance Table

Stores records of students attending mess meals.

Key fields:

- attendance_id
- student_id
- date
- meal_type
- status


Relationship:
Student (1) —— (Many) MessAttendance
---



## 6. Database Relationships

The major relationships in the database are:

User → Student (One-to-One)

Student → RoomAllocation (One-to-Many)

Room → RoomAllocation (One-to-Many)

Student → Fees (One-to-Many)

Fees → Payments (One-to-Many)

Student → Complaints (One-to-Many)

Student → MessAttendance (One-to-Many)


---

## 7. ER Diagram

The ER diagram illustrating the relationships between database entities is shown below.

Refer to:

database/ER_Diagram.png

## 8. Sample SQL Queries

Get all students:

SELECT * FROM students;

Get available rooms:

SELECT * FROM rooms
WHERE status = 'AVAILABLE';

Get student fees:

SELECT * FROM fees
WHERE student_id = 1;

Get complaints raised by students:

SELECT * FROM complaints
WHERE student_id = 1;

## 9. Conclusion

The database design ensures efficient storage and retrieval of hostel management data.

The schema supports:

- Student management
- Room allocation
- Fee management
- Payment tracking
- Complaint handling
- Mess management

The relational design ensures **data consistency, scalability, and maintainability** for hostel operations.

---