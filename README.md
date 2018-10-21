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
Room for improvement (well lots of it !!)


