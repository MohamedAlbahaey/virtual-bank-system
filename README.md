# Virtual Bank System 💳🏦

A simplified **Virtual Banking System** built during my internship at [Ejada](https://www.ejada.com/) under the **Integration Team**.  
The project demonstrates **modern distributed system design** with **Spring Boot microservices, Kafka, and WSO2 API Gateway**.


## 🚀 Key Features
- Independent **microservices** for Users, Accounts, and Transactions  
- **Backend-for-Frontend (BFF)** layer to aggregate API calls  
- **Synchronous & Asynchronous communication** (RestTemplate, WebClient, Kafka)  
- **Centralized logging** via a Kafka consumer service  
- **WSO2 API Gateway** for API security, publishing, and throttling  


## 📂 Project Structure
```
virtual-bank-system/
│── bffService/                 # Backend-for-Frontend (API aggregator) 
│── userService/                # Handles authentication & user profiles 
│── accountService/             # Manages accounts & balances 
│── transactionService/         # Handles transfers & transaction history 
│── loggingService/             # Kafka consumer for centralized logging
│── api-gateway-config/         # WSO2 API Manager configs
│── README.md                   # Project documentation
```

## 📦 Setup

1. Clone the repository  
   ```bash
   git clone https://github.com/your-username/virtual-bank-system.git
   cd virtual-bank-system
   ```
2. Run each service with Maven
   ```bash
   mvn spring-boot:run
   ```
3. Start Kafka (Zookeeper + Broker)
4. Configure WSO2 API Gateway with the provided APIs
5. Test endpoints using Postman

## 🙏 Acknowledgment
Developed during a **1-month internship at [Ejada](https://www.ejada.com/)** with guidance from the Integration Team.  
