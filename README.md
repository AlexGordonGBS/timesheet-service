# timesheet-service - CRUD service for timesheets

## Functionality

<p> This is REST API for the timesheet maintenance of some fictious company. The following standard endpoints are implemented: </p>
   
   * ADD a new timesheet
   * EDIT an existing timesheet
   * DELETE an existing timesheet
   * display an existing timesheet by ID
   * display all timesheets
   * display all timesheet for the given client

<p> Additionally utility endpoints are implemented: </p>

   * LOAD data from the given CSV file to the database
   * DELETE ALL data from the database

## Business Rules

### ADD function
<p> All fields except for the ID are mandatory. The ID value is generated by the sevice. The ADD function is NOT idempotent.
Therefore a new entity with a new generated ID would be created every time when the ADD function is called. In real life we might want to implement some
business rules for "the uniqueness by business keys". </p>

### EDIT function
<p> All fields are mandatory. The ID value MUST be provided in the URL. If the ID is not provided - error is generated. </p>

### DELETE function
<p>DELETE operation requires the ID to be provided in the URI. The DELETE function is implemented as idempotent.
Therefore is a timesheet too be deleted is NOT found then we return a success. So the DELETE can be executed many tumes
for the same entity without breaking the data.</p>

### DisplayByID function
<p>This function must have an ID in the URL. If the entity is NOT found then we return 404 error. Otherwise we return
a requested timesheet JSON. </p>

### DisplayByClient function
<P>Parameter "client" in URL is required. This function returns all timesheets for the certain client. If no timesheets 
are found we return an empty list - no errors are generated,</p>

### DisplayAll function
<P>This function returns all timesheets.</p>

## Prerequisites
* JVM 1.8
```
    java -version
    java version "1.8.0_211"
    Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
    Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)
```
* Gradle 4.10
```
    gradle --version
    ------------------------------------------------------------
    Gradle 4.10
    ------------------------------------------------------------
    Build time:   2018-08-27 18:35:06 UTC
    Revision:     ee3751ed9f2034effc1f0072c2b2ee74b5dce67d
    Kotlin DSL:   1.0-rc-3
    Kotlin:       1.2.60
    Groovy:       2.4.15
    Ant:          Apache Ant(TM) version 1.9.11 compiled on March 23 2018
    JVM:          1.8.0_211 (Oracle Corporation 25.211-b12)
    OS:           Mac OS X 10.14.3 x86_64
```
* git version 2.18.0
```
    git --version
    git version 2.18.0
```
## How to clone, build and run
* create a new folder somewehere on your machine
```
    mkdir tempDir
```
* navigate to your new directory you just created:
```
    cd tempDir
```
* clone the project into your new directory:
```
    git clone https://github.com/AlexGordonGBS/timesheet-service.git
```
* navigate to the timesheet-service directory inside your tempDir:
```
    cd timesheet-service
```
* run the build:
```
    gradle clean build
```
* start the service:
```
    java -jar build/libs/timesheet-service-0.0.1-SNAPSHOT.jar
```
* PostMan collection to test the project can be found here:
```
    ...timesheet-service/src/test/resources/Timesheet-service.postman_collection.json
```

// By Alex Gordon - 2019.
