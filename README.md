# 🧠 Smart Habit Tracker API (Spring Boot)

A production-ready backend application built using **Spring Boot** that allows users to create habits, track daily progress, maintain streaks, and analyze performance.

---

## 🚀 Features

* 🔐 User Authentication (JWT-based)
* 📌 Create, update, delete habits
* ✅ Track daily habit completion
* 🔥 Streak calculation (current & longest)
* 📊 Habit analytics & statistics
* ⏰ Reminder support (stored)
* 🛡️ Secure REST APIs

---

## 🏗️ Architecture

```
Client (Frontend)
        ↓
Controller Layer (REST APIs)
        ↓
Service Layer (Business Logic)
        ↓
Repository Layer (JPA)
        ↓
Database (MySQL)
```

---

## 🧩 Tech Stack

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* Spring Security (JWT)
* MySQL
* Lombok
* Maven

---

## 📁 Project Structure

```
com.kashif.habittracker
│
├── config          # Security & configurations
├── controller      # REST controllers
├── dto             # Request & response DTOs
├── entity          # JPA entities
├── repository      # Data access layer
├── service         # Interfaces
├── service.impl    # Business logic
├── security        # JWT & filters
├── exception       # Global exception handling
└── util            # Helper classes
```

---

## 🗄️ Database Design

### 👤 Users

* id (PK)
* name
* email (unique)
* password
* created_at
* updated_at

### 📌 Habits

* id (PK)
* user_id (FK)
* name
* description
* frequency (DAILY / WEEKLY)
* goal
* reminder_time
* created_at
* updated_at

### 📅 Habit Tracking

* id (PK)
* habit_id (FK)
* date
* status (COMPLETED / SKIPPED)

### 🔗 Relationships

* One User → Many Habits
* One Habit → Many Tracking Records
* Cascade delete enabled

---

## 🔐 Authentication (JWT)

### Flow:

1. User logs in
2. Server generates JWT token
3. Client sends token in header:

   ```
   Authorization: Bearer <token>
   ```
4. Server validates token for each request

---

## 📡 API Endpoints

### 🔑 Auth APIs

```
POST /api/auth/register
POST /api/auth/login
```

### 📌 Habit APIs

```
POST   /api/habits
GET    /api/habits
GET    /api/habits/{id}
PUT    /api/habits/{id}
DELETE /api/habits/{id}
```

### ✅ Tracking APIs

```
POST /api/habits/{id}/complete
POST /api/habits/{id}/skip
```

### 📊 Analytics APIs

```
GET /api/habits/{id}/streak
GET /api/habits/{id}/stats
```

---

## 🔥 Core Business Logic

### ✅ Habit Completion

* Prevent duplicate entries for the same day
* Store completion status

### 🔥 Streak Calculation

* Counts consecutive completed days
* Stops on missed/skipped day

### 📊 Statistics

* Total tracked days
* Completed days
* Success rate:

  ```
  (completed / total) * 100
  ```

---

## ⚠️ Edge Cases Handled

* ❌ Duplicate habit completion
* ❌ Unauthorized access to other users' data
* ❌ Invalid input data
* ❌ Missing records
* ❌ Cascade deletion

---

## 🧪 Testing

* Use Postman
* Test all endpoints with JWT authentication

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```
git clone https://github.com/your-username/smart-habit-tracker.git
cd smart-habit-tracker
```

### 2. Configure Database

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/habit_tracker
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Copy application-example.properties to application.properties and update values before running the project.
```

### 3. Run Application

```
mvn spring-boot:run
```

## 👨‍💻 Author

**Kashif Jawed**

---

## ⭐ Contributing

Feel free to fork this repository and contribute!

---

## 📜 License

This project is open-source and available under the Apache 2.0 License.
