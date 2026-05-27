# 🏥 Patient Management REST API

A production-style RESTful backend API built with **Spring Boot** that simulates a real-world hospital patient records management system. 
Built to practice and demonstrate **Spring REST**, **Exception Handling**, **Input Validation**, and **Logging** concepts.

---

## 🚀 Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming language |
| Spring Boot | 4.x | Core framework |
| Spring Web | Included | REST API creation |
| Spring Data JPA | Included | Database operations |
| MySQL | 8.x | Relational database |
| Hibernate | Auto | ORM mapping |
| SLF4J + Logback | Included | Logging |
| Bean Validation | Jakarta EE | Input validation |
| Lombok | Latest | Boilerplate reduction |
| Maven | 3.x | Build tool |

---

## 📁 Project Structure

```
src/main/java/com/patient/
├── controllers/
│   └── PatientController.java        ← REST endpoints
├── services/
│   ├── interfaces/
│   │   └── PatientServiceInterface.java
│   └── PatientServiceImpl.java       ← Business logic
├── dao/
│   └── PatientRepository.java        ← JPA repository
├── model/
│   └── Patient.java                  ← Entity / DB table
├── dto/
│   ├── PatientRequestDTO.java        ← Input with validations
│   ├── PatientResponseDTO.java       ← API response
│   └── PatientUpdateRequestDTO.java  ← Update input
├── exceptions/
│   ├── PatientNotFoundException.java ← Custom exception
│   └── GlobalExceptionHandler.java  ← @ControllerAdvice
└── PatientManagementApiApplication.java
```

---

## ⚙️ Setup & Run Locally

### Prerequisites
- Java 17+
- MySQL 8.x
- Maven 3.x
- Postman (for testing)

### Step 1 — Clone the repository
```bash
git clone https://github.com/your-username/patient-management-api.git
cd patient-management-api
```

### Step 2 — Create MySQL database
```sql
CREATE DATABASE patient_db;
```

### Step 3 — Configure application.properties
Open `src/main/resources/application.properties` and update:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/patient_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### Step 4 — Run the application
```bash
mvn spring-boot:run
```

The server starts at: `http://localhost:8080`

---

## 📌 API Endpoints

Base URL: `http://localhost:8080/patients`

### ➕ Create Patient
```
POST /patients/addPatient
```
**Request Body:**
```json
{
  "firstName": "Rahul",
  "lastName": "Sharma",
  "patientAge": 32,
  "gender": "MALE",
  "email": "rahul.sharma@gmail.com",
  "phoneNumber": "9876543210",
  "bloodGroup": "B+",
  "address": "Hyderabad, Telangana",
  "disease": "Typhoid Fever"
}
```
**Response:** `200 OK` with saved patient details including auto-generated ID, admittedOn, and status.

---

### 📋 Get All Patients
```
GET /patients/allPatients
```
**Response:** `200 OK` with list of all patients.

---

### 🔍 Get Patient by ID
```
GET /patients/getPatientById/{patientId}
```
**Response:** `200 OK` with patient details.  
**Error:** `404 NOT FOUND` if patient ID doesn't exist.

---

### 🔎 Search Patients by Disease
```
GET /patients/getPatientByDisease?byDisease=Typhoid Fever
```
**Response:** `200 OK` with list of matching patients.

---

### 🔄 Filter Patients by Status
```
GET /patients/getPatientsByStatus/{status}
```
**Example:** `/patients/getPatientsByStatus/Admitted`  
**Response:** `200 OK` with filtered list.

---

### ✏️ Update Patient Details
```
PUT /patients/updatePatientDetails/{patientId}
```
**Request Body (all fields optional):**
```json
{
  "address": "Chennai, Tamil Nadu",
  "disease": "Malaria",
  "status": "Discharged"
}
```
**Response:** `200 OK` with updated patient data.  
**Error:** `404 NOT FOUND` if patient ID doesn't exist.

---

### 🗑️ Delete Patient
```
DELETE /patients/deletePatient/{patientId}
```
**Response:** `200 OK` with success message.  
**Error:** `404 NOT FOUND` if patient ID doesn't exist.

---

## ✅ Input Validation

Validation is applied on `PatientRequestDTO`. Invalid requests return `400 BAD REQUEST` with field-level error messages.

| Field | Rule |
|-------|------|
| firstName | Cannot be blank |
| lastName | Cannot be blank |
| patientAge | Must be between 1 and 120 |
| gender | Cannot be blank |
| email | Must be a valid email format |
| phoneNumber | Must be exactly 10 digits |
| bloodGroup | Cannot be blank |
| disease | Cannot be blank |

**Validation Error Response:**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "BAD REQUEST",
  "messages": {
    "email": "Please provide a valid email",
    "phoneNumber": "Phone must be exactly 10 digits"
  }
}
```

---

## 🚨 Exception Handling

All exceptions are handled centrally via `GlobalExceptionHandler` using `@RestControllerAdvice`.

| Exception | HTTP Status | When Triggered |
|-----------|-------------|----------------|
| `PatientNotFoundException` | 404 NOT FOUND | Patient ID not found |
| `MethodArgumentNotValidException` | 400 BAD REQUEST | Validation failure |
| `Exception` (fallback) | 500 INTERNAL ERROR | Unexpected errors |

**Error Response Format:**
```json
{
  "timestamp": "2024-01-15T10:45:22",
  "status": 404,
  "error": "Not Found",
  "message": "Patient with ID Patient Id : 99 Not Found"
}
```

---

## 📝 Logging

Multi-level logging is implemented using **SLF4J + Logback** across all layers.

| Level | Used For |
|-------|----------|
| `INFO` | Patient registration, update, delete operations |
| `DEBUG` | Incoming requests, fetch operations |
| `ERROR` | Exception scenarios |

Logs are written to both console and `patient.log` file.

Configure log level in `application.properties`:
```properties
logging.level.com.patient=DEBUG
logging.file.name=patient.log
```

---

## 🗄️ Patient Data Model

| Field | Type | Description |
|-------|-===--|-------------|
| patientId | int | Auto-generated primary key |
| firstName | String | Patient's first name |
| lastName | String | Patient's last name |
| patientAge | int | Age (1–120) |
| gender | String | MALE / FEMALE / OTHER |
| email | String | Unique email address |
| phoneNumber | String | 10-digit phone number |
| bloodGroup | String | A+, A-, B+, B-, AB+, AB-, O+, O- |
| address | String | Home address (optional) |
| disease | String | Primary diagnosis |
| admittedOn | LocalDateTime | Auto-set on registration |
| status | String | Admitted / Discharged |

---

## 💡 Key Concepts Demonstrated

- `@RestController`, `@RequestMapping`, `@PathVariable`, `@RequestParam`, `@RequestBody`
- `@ControllerAdvice` for centralized exception handling
- `orElseThrow()` with custom `RuntimeException`
- `@Valid` with Bean Validation annotations on DTOs
- `SLF4J` logger with `log.info()`, `log.debug()`, `log.error()`
- Spring Data JPA derived queries (`findByDisease`, `findByStatus`)
- `BeanUtils.copyProperties()` for DTO mapping
- Layered architecture with Service Interface pattern

---

## 🔮 Future Enhancements

- [ ] Add Doctor entity and link patients to doctors
- [ ] Swagger / OpenAPI documentation
- [ ] Pagination and sorting for patient lists
- [ ] Spring Security for authentication
- [ ] Unit tests with JUnit 5 and Mockito
- [ ] Docker support with Docker Compose

---

## 👨‍💻 Author

**Nandan**  
Java Backend Developer  
[LinkedIn Profile](https://www.linkedin.com/in/hari-hara-nandan-cv-608240354) | [GitHub](https://github.com/Hari-2426)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
