# Organization_API
## Reuben Gathii
### App Description
An application for creating an organisational API outlining the news departments and users
#### Technologies Used
* Java
* Spark
* Postman 
* Postgres
* Jet Brains
##### Database Setup
In the Postgres, in the psql:
* CREATE DATABASE organization;
* CREATE TABLE news(id SERIAL PRIMARY KEY, news VARCHAR, departmentId int);
* CREATE TABLE departments(id SERIAL PRIMARY KEY,department_id int,name VARCHAR);
* CREATE TABLE users (id SERIAL PRIMARY KEY, username VARCHAR, address VARCHAR,phone int,email VARCHAR,department_id int, position VARCHAR, roles VARCHAR);
##### Set Up
* Clone the repo 
* Select account  
* Navigate to cloned directory 
* Open directory with IDE of our choice
* Run the application using Gradle
##### License
MIT https://opensource.org/licenses/MIT
