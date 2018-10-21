# 

The above folder structure represents a small application with a RESTful API that interacts with MongoDb database to persist Customer records and is supported by a UI application which consumes the RESTful API. 
In a nutshell the application will allow the user to perform the following actions either from the GUI or from any http client. 
	1.	List customers 
	2.	Create a new customer 
	3.	Update a customer 
	4.	Deletes a customer

The project is divided into 2 parts 
	1.	CustomerProj - This contains the server side code for the Spring RESTFul Api which connects to MongoDb database instance 
	2.	customerui - The UI for the CRUD operations is developed in React.js
I have segregated the UI and backend projects. Hence they need to be deployed separately.
A bit about the UI 
	1.	I have used React.js components and specifically the 'create-react-app' module to bring up the framwork.
	2.	Have used ReactRouterDOM for navigation and dynamic routing.
	3.	Used axios as an interface with backend REST Api 
	4.	And used the 'validator' to create customized FormValidator for user input
	
A bit about the REST API
	1.	I have used SpringBoot to develop REST API for all the CRUD operations. Imlpemented CRUDRespository from the Spring Mongodb API for interaction with database. 
	2.	Used javax.validation on for user input requests from UI and also validate data requests from HTTP clients like curl, postman.
	3.	Have added a bit of exception handling to filter out cryptic messages from stacktrace and provide user friendly messages to user Have implemented spring logging.


	The REST API application is currently configured to connect to MongoDb on localhost:27017/test. The details are in the application.properties.
	In order for the application to run we need to have a MongoDb instance running.

	To execute the REST API you would need to checkout the "CustomerProj" 
	Its a maven project. So you would need to install maven on your local and then build the project
	<yourpath>/CustomerProj>mvn clean package

	Once the build is complete you can run the project using Spring-boot run. Spring boot plugin has its own embedded tomcat. But i have used jetty in the pom.xml
	<yourpath>/CustomerProj>mvn spring-boot:run

	This should ideally run on http://localhost:8080/customers
	
	You can validate the REST API from command line curl tool.
	for e.g This will save a new Customer record using curl tool.
		curl -i -X POST -H "Content-Type:application/json" -d  "{ \"emailAddress\" : \"test@google.com\" ,\"lastName\":\"test\",\"firstName\":\"test\",\"address\":\"\"}" http://localhost:8080/customers
	This will give a list of all the customers
		curl http://localhost:8080/customers
	curl -i -X PUT -H "Content-Type:application/json" -d  "{ \"emailAddress\" : \"test@google.com\" ,\"lastName\":\"Jon\",\"firstName\":\"Lee\",\"address\":\"\"}" http://localhost:8080/customers/test@google.com

Room for improvement (well lots of it !!)

