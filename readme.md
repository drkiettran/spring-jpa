# Spring JPA project
This project demonstrates the use of Spring JPA library framework that 
enables you to access to the sample database `sakila`.

## Assumptions
MySql should be up and running. If not, start it up as a container. The script to start MySQL
up as in a container is in the container folder.

```shell script
cd /home/student/cisc_525/container
./mysql.sh
```

To find out the IP address of MySQL database that runs as a container, do these:

```shell script
docker inspect mysql | grep -i ipaddress
```


### MySql database and logins
- database name: `sakila`
- userid/password: `student/password`

## Build software
```shell script
./mvnw clean package
```

## Run Spring Boot application
To run the application successfully, you must provide the following environment variables:

```shell script
export MYSQL_HOST=x.x.x.x
export MYSQL_PORT=3306
export MYSQL_USER=student
export MYSQL_PASSWORD=password
```

You then can run the application using either one of these commands: 

```shell script
./mvnw clean spring-boot:run

```

Or

```shell script
java -cp ./target/spring-jpa.jar
```
## REST APIs
Provided endpoints are:
- get all actors: `http://localhost:8080/actor`. 
For example, `curl http://localhost:8080/actor | jq` 
- get actor by id: `http://localhost:8080/actor/{id}`
For example, `curl http://localhost:8080/actor/199 | jq`
- get list of actors by last name: `http://localhost:8080/actor/{last_name}`
For example, `curl http://localhost:8080/last_name/willis | jq`

You can install `jq` application via this command:

```shell script
sudo apt install jq

```
 
## SWAGGER UI
Visit this website: `http://localhost:8080`. Try out some examples with this information:

- id = `1` through `100`
- last names: `Willis` & `ZellWeger`