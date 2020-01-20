# Spring JPA project
This project demonstrates the use of Spring JPA library framework that 
enables you to access to the sample database sakila.

## Assumptions
MySql should be up and running.

```shell script
cd /home/student/cisc_525/container
./mysql.sh
```
### MySql database and logins
- database name: `sakila`
- userid/password: `student/password`

## Build software
```shell script
./mvnw clean package
```

## Run Spring Boot application
```shell script
./mvnw clean spring-boot:run

```

Or

```shell script
java -cp ./target/spring-jpa.jar
```
## REST APIs
Provided endpoints are:
- get all actors: `http://localhost:8080/actor`
- get actor by id: `http://localhost:8080/actor/{id}`
- get list of actors by last name: `http://localhost:8080/actor/{last_name}`
 
 
## SWAGGER UI
Visit this website: `http://localhost:8080`. Try out some examples with this information:

- id = 1 through 100
- last names: Willis & ZellWeger