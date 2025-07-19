# 🏥 Spring Boot gRPC Health Care Application

This is a microservice-based application using **Spring Boot** and **gRPC** to simulate a Health Care system. It demonstrates gRPC service communication with patients and uses Protobuf to define common message types.

---

## 📁 Project Structure

spring-real-time-learning/
├── common-proto/ # Shared protobuf definitions
├── patient-service/ # Microservice for handling patients
└── pom.xml # Aggregator parent project


---

## 🔧 Technologies Used

- Java 21
- Spring Boot 3.3.x
- Spring Boot gRPC Starter (`spring-grpc`)
- gRPC / Protocol Buffers (`proto3`)
- H2 In-Memory Database (for local dev/test)
- Maven

---

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+
- `grpcurl` and/or `grpcui` for testing

### 1. Clone the Repository

```bash
git clone https://github.com/your-repo/spring-real-time-learning.git
cd spring-real-time-learning


2. Build All Modules

mvn clean install

▶️ Running the Application
Navigate to the patient-service module:

cd patient-service
mvn spring-boot:run

🧪 gRPC API Testing
✅ List All Services

grpcurl -plaintext localhost:9090 list

✅ Launch Web gRPC UI

grpcui -plaintext localhost:9090

✅ Register a New Patient

grpcurl -plaintext -d '{
  "first_name": "John",
  "last_name": "Thomas",
  "email": "john.thomas@example.com",
  "phone": "123-456-7890",
  "address": "123 Main St"
}' localhost:9090 com.halfacode.patient.PatientService/RegisterPatient
