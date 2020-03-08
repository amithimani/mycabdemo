# New-CAB BACKEND APPLICATION

Back End api that provides services for
* Get Driver details based on driverID 
* Create/Update/Delete Driver details
* Find Drivers based on availability status
* Select and Deselect Cars associated with driver
* Get Car details based on carID
* Create/Update/Delete Car details

## Service Metadata
* **Service Name:** backend_application
* **Context Root:** /backend_application/v1.0
* **Version:** 1.0.0

## API EndPoints

* **POST /v1/drivers:** Means to create driver entity 
* **POST /v1/cars:** Means to create car entity 
* **POST /v1/drivers/{driverId}:** associate car entity with driver entity
* **PUT /v1/drivers/{driverId}:** Update driver location
* **PUT /v1/cars/{carId}:** Update car information
* **DELETE /v1/drivers/{driverId}:** Soft Delete Driver entity
* **DELETE /v1/cars/{carId}:** Soft Delete Car entity
* **GET /v1/drivers:** Get Driver details
* **GET /v1/cars:** Get Cars details


## Important Information About Application
* Application is implemented using DAO design pattern
* Application is using logger intercepter
* Request body/parameters are validated using Javax annotation 
* All the functional flow has unit testcase
* Application is using in-memory H2 database, sample data is created using data.sql file
* Application throws custom exceptions

TODO:
* Hystrix for database calls
* global exception handler 
* User Identity and authorization
* Performance measurement and improvement


### Prerequisites
* Git
* JDK 8 or later
* Maven 3.0 or later

### Deployment

#### Run locally as Spring-Boot application in its own container

```
mvn spring-boot:run
```
#### Run application in Docker container
```
docker build -t testapp/backendapi .

docker run -p 8080:8080 -t testapp/backendapi
```
