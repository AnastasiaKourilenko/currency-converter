## Prerequisites
JDK 11+, Maven, PostgreSQL
## Default DB config
```   
DB name: cc
username: cc
password: cc
```
## How to run
- Create schema in your DB 
- Create user with admin privileges for the schema above  
- If your db connection params are different, put correct values to `application.properties` file
- `mvn clean spring-boot:run` :heavy_check_mark: 

