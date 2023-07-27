# Vending Machine Change Tracker Spring Boot Application

## Assumptions

The following are the assumptions made while implementing the Vending Machine Application to track change

	1. Initialise the vending machine - Reset the Vending Machine Coin Pool with the latest input list of coins (i.e., reset to a new state)
	2. Register coins that have been deposited by a user - add the input list of coins to the Coin Pool
	3. Return the correct change to a user as coins when an order is received and remove the coins from the machine - Removes the coins that make up the Order Value from the Coin Pool or throws the custom Exception

## Test-Harness Class
The ```Test-Harness class``` (REPL a.k.a Read-Evaluate-Print-Loop) is present in the path ```com/oracle/tasks/test_harness/VendingMachineChangeTrackerCommandLineTestHarness.java```

## Steps to execute Test-Harness Class

1. Launch the Vending Machine Application

   1.1 Do a clean build in gradle that generates an application jar in the Project root path (vendingMachine/build/libs/)
   
   1.2 Copy the absolute path of the generated jar

   1.3 run the following command in the terminal - java -jar absolutePath

2. Once the application is started, execute the VendingMachineChangeTrackerTestHarnessClass for various scenarios 

       Test-Harness Class Output:

          Welcome to Vending Machine Test Runner!
          Please verify whether the Vending Machine SpringBoot Application is up and running before choosing any of the following options.!
          Enter '1' to initialize vending machine
          Enter '2' to register coins
          Enter '3' to get change
          Enter '0' to exit
          Enter your choice: 

3. Scenario 1: Initialize the VendingMachine

     3.1 Vending machine is initialized with the coins entered by the user

            Application Logger Info:

            2023-07-27 11:23:03.841  INFO 27724 --- [nio-8080-exec-1] .c.VendingMachineChangeTrackerController : VendingMachineChangeTrackerController - initializeVendingMachine method invoked
            2023-07-27 11:23:03.842  INFO 27724 --- [nio-8080-exec-1] s.VendingMachineChangeTrackerServiceImpl : VendingMachineChangeTrackerServiceImpl - initializeVendingMachine method invoked
            2023-07-27 11:23:03.842  INFO 27724 --- [nio-8080-exec-1] c.o.t.b.VendingMachineChangeTracker      : VendingMachineChangeTracker - VendingMachineChangeTracker Constructor called
            2023-07-27 11:23:03.842  INFO 27724 --- [nio-8080-exec-1] c.o.t.b.VendingMachineChangeTracker      : Coin Pool After Initialization [Coin{value=1}, Coin{value=1}, Coin{value=2}, Coin{value=2}, Coin{value=5}, Coin{value=5}, Coin{value=10}]

            Test-Harness Class Output:

            Enter the coins available separated by spaces: (eg:1 2 5) 1 1 2 2 5 5 10
            11:23:03.603 [main] DEBUG org.springframework.web.client.RestTemplate - HTTP POST http://localhost:8080/api/v1/vending-machine/initialize
            11:23:03.613 [main] DEBUG org.springframework.web.client.RestTemplate - Accept=[text/plain, application/json, application/*+json, */*]
            11:23:03.627 [main] DEBUG org.springframework.web.client.RestTemplate - Writing [[Coin{value=1}, Coin{value=1}, Coin{value=2}, Coin{value=2}, Coin{value=5}, Coin{value=5}, Coin{value=10}]] with org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
            11:23:03.868 [main] DEBUG org.springframework.web.client.RestTemplate - Response 200 OK
            11:23:03.870 [main] DEBUG org.springframework.web.client.RestTemplate - Reading to [java.lang.String] as "text/plain;charset=UTF-8"
            Vending Machine Initialised successfully

4. Scenario 2: Register the coins deposited by the user
        
     4.1 The coins entered by the user is updated in the coin pool
             
            Application Logger Info:
            
            2023-07-27 11:23:30.811  INFO 27724 --- [nio-8080-exec-2] .c.VendingMachineChangeTrackerController : VendingMachineChangeTrackerController - registerCoins method invoked
            2023-07-27 11:23:30.812  INFO 27724 --- [nio-8080-exec-2] s.VendingMachineChangeTrackerServiceImpl : VendingMachineChangeTrackerServiceImpl - registerCoins method invoked
            2023-07-27 11:23:30.812  INFO 27724 --- [nio-8080-exec-2] c.o.t.b.VendingMachineChangeTracker      : VendingMachineChangeTracker - registerCoins method invoked
            2023-07-27 11:23:30.820  INFO 27724 --- [nio-8080-exec-2] c.o.t.b.VendingMachineChangeTracker      : Coin Pool after registration [Coin{value=1}, Coin{value=1}, Coin{value=2}, Coin{value=2}, Coin{value=5}, Coin{value=5}, Coin{value=10}, Coin{value=5}, Coin{value=10}]

            Test-Harness Class Output:

            Enter your choice: 2
            Enter the coin values deposited by the user separated by spaces: (eg:2 2 2 5 5) 5 10
            11:23:30.790 [main] DEBUG org.springframework.web.client.RestTemplate - HTTP POST http://localhost:8080/api/v1/vending-machine/register-coins
            11:23:30.791 [main] DEBUG org.springframework.web.client.RestTemplate - Accept=[text/plain, application/json, application/*+json, */*]
            11:23:30.791 [main] DEBUG org.springframework.web.client.RestTemplate - Writing [[Coin{value=5}, Coin{value=10}]] with org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
            11:23:30.828 [main] DEBUG org.springframework.web.client.RestTemplate - Response 200 OK
            11:23:30.829 [main] DEBUG org.springframework.web.client.RestTemplate - Reading to [java.lang.String] as "text/plain;charset=UTF-8"
            Vending Machine Coins Registered successfully

5. Scenario 3: User needs to specify the product value
        
      5.1 Scenario 1: **Happy Path/Positive case** - User gives a product value in such a way that the change is returned by the vending machine eg: 15
 
      5.1.1 Change coins returned to the user with an HTTP response code 200 and the value of the coins is reduced in the coin pool 

             Application Logger Info:
             
             2023-07-27 14:05:56.735  INFO 10432 --- [nio-8080-exec-3] .c.VendingMachineChangeTrackerController : VendingMachineChangeTrackerController - getChange method invoked
             2023-07-27 14:05:56.735  INFO 10432 --- [nio-8080-exec-3] s.VendingMachineChangeTrackerServiceImpl : VendingMachineChangeTrackerServiceImpl - getChange method invoked
             2023-07-27 14:05:56.735  INFO 10432 --- [nio-8080-exec-3] c.o.t.b.VendingMachineChangeTracker      : VendingMachineChangeTracker - getChange method invoked
             2023-07-27 14:05:56.735  INFO 10432 --- [nio-8080-exec-3] c.o.t.b.VendingMachineChangeTracker      : Order Value is 15
             2023-07-27 14:05:56.735  INFO 10432 --- [nio-8080-exec-3] c.o.t.b.VendingMachineChangeTracker      : Coin Pool [Coin{value=1}, Coin{value=1}, Coin{value=2}, Coin{value=2}, Coin{value=5}, Coin{value=5}, Coin{value=10}, Coin{value=5}, Coin{value=10}]
             2023-07-27 14:05:56.736  INFO 10432 --- [nio-8080-exec-3] c.o.t.b.VendingMachineChangeTracker      : Coins Making-Up the Order Value [1, 2, 2, 5, 5]
             2023-07-27 14:05:56.737  INFO 10432 --- [nio-8080-exec-3] c.o.t.b.VendingMachineChangeTracker      : Coin Pool [Coin{value=1}, Coin{value=10}, Coin{value=5}, Coin{value=10}]

             Test-Harness Class Output:

             Enter the order value: (eg:10)15
             13:25:32.741 [main] DEBUG org.springframework.web.client.RestTemplate - HTTP POST http://localhost:8080/api/v1/vending-machine/get-change
             13:25:32.741 [main] DEBUG org.springframework.web.client.RestTemplate - Accept=[text/plain, application/json, application/*+json, */*]
             13:25:32.741 [main] DEBUG org.springframework.web.client.RestTemplate - Writing [15] with org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
             13:25:32.775 [main] DEBUG org.springframework.web.client.RestTemplate - Response 200 OK
             13:25:32.775 [main] DEBUG org.springframework.web.client.RestTemplate - Reading to [java.lang.String] as "application/json"
             Change coins: [{"value":1},{"value":2},{"value":2},{"value":5},{"value":5}]

      5.2 Scenario 2: **Exception case** - User gives a product value in such a way that the vending machine cannot return the change
      
      5.2.1 Exception with the message **Exact Change Not Available** is thrown by the vending machine with an HTTP response code of 400

            Application Logger info:

            2023-07-27 13:24:24.098  INFO 8436 --- [nio-8080-exec-3] .c.VendingMachineChangeTrackerController : VendingMachineChangeTrackerController - getChange method invoked
            2023-07-27 13:24:24.098  INFO 8436 --- [nio-8080-exec-3] s.VendingMachineChangeTrackerServiceImpl : VendingMachineChangeTrackerServiceImpl - getChange method invoked
            2023-07-27 13:24:24.098  INFO 8436 --- [nio-8080-exec-3] c.o.t.b.VendingMachineChangeTracker      : VendingMachineChangeTracker - getChange method invoked
            2023-07-27 13:24:24.098  INFO 8436 --- [nio-8080-exec-3] c.o.t.b.VendingMachineChangeTracker      : Order Value is 69
            2023-07-27 13:24:24.098  INFO 8436 --- [nio-8080-exec-3] c.o.t.b.VendingMachineChangeTracker      : Coin Pool [Coin{value=1}, Coin{value=1}, Coin{value=2}, Coin{value=2}, Coin{value=5}, Coin{value=5}, Coin{value=10}, Coin{value=5}, Coin{value=10}]
            2023-07-27 13:24:24.106 ERROR 8436 --- [nio-8080-exec-3] endingMachineApplicationExceptionHandler : Exact Change Not Available Exception

            Test-Harness Class Output:

            Enter your choice: 3
            Enter the order value: (eg:10)67
            14:08:04.869 [main] DEBUG org.springframework.web.client.RestTemplate - HTTP POST http://localhost:8080/api/v1/vending-machine/get-change
            14:08:04.870 [main] DEBUG org.springframework.web.client.RestTemplate - Accept=[text/plain, application/json, application/*+json, */*]
            14:08:04.870 [main] DEBUG org.springframework.web.client.RestTemplate - Writing [67] with org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
            14:08:04.888 [main] DEBUG org.springframework.web.client.RestTemplate - Response 400 BAD_REQUEST

6. Scenario 4: Reinitialize the vending machine with new coins
    
     6.1 The coin pool gets initialized to a new state with the coins entered by the user

         Application Logger Info:

         2023-07-27 14:10:49.437  INFO 10432 --- [nio-8080-exec-6] .c.VendingMachineChangeTrackerController : VendingMachineChangeTrackerController - initializeVendingMachine method invoked
         2023-07-27 14:10:49.437  INFO 10432 --- [nio-8080-exec-6] s.VendingMachineChangeTrackerServiceImpl : VendingMachineChangeTrackerServiceImpl - initializeVendingMachine method invoked
         2023-07-27 14:10:49.437  INFO 10432 --- [nio-8080-exec-6] c.o.t.b.VendingMachineChangeTracker      : VendingMachineChangeTracker - VendingMachineChangeTracker Constructor called
         2023-07-27 14:10:49.438  INFO 10432 --- [nio-8080-exec-6] c.o.t.b.VendingMachineChangeTracker      : Coin Pool After Initialization [Coin{value=6}, Coin{value=7}, Coin{value=3}]


         Test-Harness Class output:

         Enter your choice: 1
         Enter the coins available separated by spaces: (eg:1 2 5) 6 7 3
         14:10:49.428 [main] DEBUG org.springframework.web.client.RestTemplate - HTTP POST http://localhost:8080/api/v1/vending-machine/initialize
         14:10:49.429 [main] DEBUG org.springframework.web.client.RestTemplate - Accept=[text/plain, application/json, application/*+json, */*]
         14:10:49.429 [main] DEBUG org.springframework.web.client.RestTemplate - Writing [[Coin{value=6}, Coin{value=7}, Coin{value=3}]] with org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
         14:10:49.440 [main] DEBUG org.springframework.web.client.RestTemplate - Response 200 OK
         14:10:49.441 [main] DEBUG org.springframework.web.client.RestTemplate - Reading to [java.lang.String] as "text/plain;charset=UTF-8"
         Vending Machine Initialised successfully

7. Exit the Vending Machine Application by entering the choice '0'.

## API Endpoints
The application provides support for the ```Swagger``` feature to view all the available API-Endpoints and its local Url is ```http://localhost:8080/swagger-ui.html#/``` (See application.properties for the <Port Number>)

The Vending Machine Change Tracker Application has the following API Endpoints:

```python```
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
