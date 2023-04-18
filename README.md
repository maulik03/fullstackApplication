# fullstackApplication
This is a simple CRUD app which has been divided into 2 parts frontend which is in react and backend which is in Java with springboot framework.

#Requirements

1.Mysql

2.React and Node.js version 17+

3.Java (Springboot Framework) version 17+


**Here are the steps to run the whole application.**

1. make sure you have the mysql is installed locally if not please install it or you can create a docker container using below command.

`docker run \
  --name local-mysql \
  -p 33061:3306 \
  -e MYSQL_ROOT_PASSWORD=p4ssw0rd \
  -d mysql:latest`

2. then go to start.spring.io to create a backend project 

3. write a code and then use your choice of IDE to run the application or `mvn clean package` command to run the application

4. use `npm init react-app react-frontend`

**Note: there are few libraries which needs to be downloded inside of this project so open the terminal/cmd and navigate to this project and download below libraries**

- `npm install bootstrap --save`
- `npm add axios`
- `npm install react-router-dom`

5. use this commnad to start the frontend `npm start`


# App url

- Front end url: http://localhost:3000

- Back end url: http://localhost:8080/api/v1/movies

# Note 
Please make sure your mysql Container, backend api is running then start your front end application.