# Automated API Testing - Swagger Pet Store

This project is an automated testing suite developed in Java to validate the [Swagger Pet Store API](https://petstore.swagger.io). It uses the **RestAssured** library for HTTP requests and **TestNG** as the test framework. The goal is to ensure the correct functionality of the API endpoints for user management, pet management, and order placement.

## What It Does

The test suite covers the following scenarios:
- Creating users with valid and invalid data
- Logging in and out users
- Retrieving pet details by ID (valid and invalid)
- Querying available pets
- Placing pet purchase orders

Each test uses custom DTOs to serialize/deserialize API data and runs through a well-structured, modular framework.

## How to run with IntelliJ

1. Clone the repository.
2. Make sure you have **Java 8+** and **Maven** installed.
3. Navigate to the project root directory.
4. Install dependencies using:
```bash
mvn clean install
```

5. Using IntelliJ, make sure you have installed the Lombok plugin and have enabled annotation processing. Otherwise, builder method from Lombok won't work when it's needed.
6. With IntelliJ, open the project and execute each test class to validate scenarios individually. You can also run the suite.xml file to execute the whole suite.