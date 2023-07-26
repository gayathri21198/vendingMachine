# Vending Machine Change Tracker Spring Boot Application

## Test-Harness Class
The ```Test-Harness class``` (REPL a.k.a Read-Evaluate-Print-Loop) is present in the path ```com/oracle/tasks/test_harness/VendingMachineChangeTrackerCommandLineTestHarness.java```

## Assumption

The following are the assumptions made while implementing the Vending Machine Application to track change

	1. Initialise the vending machine - Reset the Vending Machine Coin Pool with the latest input list of coins (i.e., reset to a new state)
	2. Register coins that have been deposited by a user - add the input list of coins to the Coin Pool
	3. Return the correct change to a user as coins when an order is received and remove the coins from the machine - Removes the coins that make up the Order Value from the Coin Pool or throws the custom Exception

## API Endpoints
The application provides support for the ```Swagger``` feature to view all the available API-Endpoints and its local Url is ```http://localhost:8080/swagger-ui.html#/``` (See application.properties for the <Port Number>)

The Vending Machine Change Tracker Application has the following API Endpoints:

```python
POST /api/v1/vending-machine/initialize: Initialize the vending machine to a known state with the initial float.
POST /api/v1/vending-machine/register-coins: Register coins deposited by the user.
POST /api/v1/vending-machine/get-change: Return the correct change to the user when an order is received.
GET /api/v1/vending-machine: Return the API version
```
## Project Modules
The ```Controller``` contains the API-Endpoints that invoke the ```Service Layer``` which in turn invokes the ```Business-Logic classes``` (instead of the Data Layer as there is no need for a persistence layer for this problem). The ```project structure``` has been added at the end of the file.

## System-Reliability

## 1) Fault-Tolerance
The ```Advice``` module hosts the ```interceptor``` along with handling the custom exception and prevents the application from crashing

## 2) Security
The ```Identity & Access Management``` of the APIs (Authentication and Authorization) of the end-points could be added using ```Spring Security filters``` or other mechanisms such using OAuth or other mechanisms


### 3) Logging
```Logging``` statements have been added to ```track the application flow``` at the required places

### 4) System Health and other useful info using ```Actuator```
The ```Actuator``` dependency has been added to get the ```System-Reliability``` Information like the health, metrics, info, dump, env, etc and proves as an effective & powerful tool when debugging the production issues

```python
Local Host Actuator Endpoint - http://localhost:8080/actuator/health returns whether the application is Up & Running
```

### 5) Java Docs
The Java Docs have been added at the class level


## Project Structure
The project follows the typical Spring Boot Project hierarchy. The project structure is as follows

```bash

vendingMachine/
|-- gradle/
|   |-- wrapper/
|   |   |-- gradle-wrapper.jar
|   |   |-- gradle-wrapper.properties
|-- src/
|   |-- main/
|   |   |-- java/
|   |   |   |-- com/
|   |   |   |   |-- oracle/
|   |   |   |   |   |-- tasks/
|   |   |   |   |   |   |-- advice/
|   |   |   |   |   |   |   |-- VendingMachineApplicationExceptionHandler.java
|   |   |   |   |   |   |-- controller/
|   |   |   |   |   |   |   |-- VendingMachineChangeTrackerController.java
|   |   |   |   |   |   |-- exception/
|   |   |   |   |   |   |   |-- ExactChangeNotAvailableException.java
|   |   |   |   |   |   |-- model/
|   |   |   |   |   |   |   |-- Coin.java
|   |   |   |   |   |   |   |-- Currency.java
|   |   |   |   |   |   |-- service/
|   |   |   |   |   |   |   |-- VendingMachineChangeTrackerService.java
|   |   |   |   |   |   |   |-- VendingMachineChangeTrackerServiceImpl.java
|   |   |   |   |   |   |-- business-logic/
|   |   |   |   |   |   |   |-- VendingMachineChangeTracker.java
|   |   |   |   |   |   |-- VendingMachineApplication.java
|   |   |-- resources/
|   |   |   |-- application.properties
|   |-- test/
|   |   |-- java/
|   |   |   |-- com/
|   |   |   |   |-- oracle/
|   |   |   |   |   |   |-- tasks/
|   |   |   |   |   |   |   |-- advice/
|   |   |   |   |   |   |   |   |-- VendingMachineApplicationExceptionHandlerTest.java
|   |   |   |   |   |   |   |-- controller/
|   |   |   |   |   |   |   |   |-- VendingMachineChangeTrackerControllerTest.java
|   |   |   |   |   |   |   |-- service/
|   |   |   |   |   |   |   |   |-- VendingMachineChangeTrackerServiceImplTest.java
|   |   |   |   |   |   |   |-- test_harness/
|   |   |   |   |   |   |   |   |-- VendingMachineChangeTrackerCommandLineTestHarness.java
|   |   |   |   |   |   |   |-- business-logic/
|   |   |   |   |   |   |   |   |-- VendingMachineChangeTrackerTest.java
|-- build.gradle
|-- gradlew
|-- gradlew.bat
|-- README.md
|-- settings.gradle
```
