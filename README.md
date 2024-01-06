#Prerequisites
Before you begin, ensure you have the following installed on your machine:

Java JDK (version 8)
Maven
Your IDE (Eclipse)
MySQL or another relational database of your choice

#Getting Started
##Clone the repository:
git clone https://github.com/Somnath700/PatientHub.git

##Create a MySQL database and update the application.properties file with your database configuration:

properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/patient_hub_service?createTableIfNotExists=true
spring.datasource.username = root
spring.datasource.password = 
spring.jpa.hibernate.ddl-auto = update

##Open the project in your IDE and build the project using Maven.

Run the application:
mvn spring-boot:run
The application will be accessible at http://localhost:8080.

Usage
Once the application is running, you can use the following endpoints:

Get Patient by id: http://localhost:8080/patients/{id}   method-Get
Add new Patient: http://localhost:8080/patients/add-patient    method-Put
Update Patient by ID: http://localhost:8080/patients/{id}     method-Post
Delete Patient by ID: http://localhost:8080/patients/{id}     method-Delete


