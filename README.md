# Beşinci hafta ödevi teslim tarihi : 14 Eylül Salı - Saat 23:00

![hm05](https://user-images.githubusercontent.com/45206582/132606840-bcc89ab7-37f4-4bbd-a950-227b838b0b3c.PNG)

How to run the project:
-----------------------

On a separate terminal and since this is a maven project you just need to go to the root of the project and perform the command:
```
mvn clean install
```
or if you don't have installed maven on your OS

```
mvnw clan install
```


This will run the unit tests of the project and create the jar file.

After having the jar file you can simply run:

```
java -jar target/wallet-service.jar
```

Since this is a Spring Boot project, you can also run the project with below command;
```
mvn spring-boot:run
```

or if you don't have installed maven on your OS
```
mvnw spring-boot:run
```

The project will run on port 8080 (configured as default).

# Instructor Path
| Request 	|                  Route                  	|                                             Body                                            	|                        Description                        	|
|:-------:	|:---------------------------------------:	|:---------------------------------------------------------------------------------------------:|:---------------------------------------------------------:	|
|   GET   	|               /instructor               	|                                            EMPTY                                            	|                    List all instructor                    	|
|   GET   	|            /instructor/{:id}            	|                                            EMPTY                                            	|                      Get a instructor                     	|
|   POST  	|               /instructor               	|{ name:":name", address:"address", phoneNumber:":phoneNumber", salary:":salary", type:":type"} |                      Add a instructor                     	|
|   POST  	|  /instructor/{:instructorId}/{:courseId}  |                                            EMPTY      	                                    |                 Set Instructor Course Relationship            |
|   PUT   	|               /instructor               	| { id:":id", name:":name", address:"address", phoneNumber:":phoneNumber", salary:":salary", type:":type" }|                    Update a instructor             |
|  DELETE 	|            /instructor/{:id}            	|                                            EMPTY                                            	|                    Delete a instructor                    	|
|   PUT   	|        /instructor/increaseSalary         |                      { instructorId:":instructorId", percent:":percent" }                     |                    Update salary a instructor                 |
|   PUT   	|        /instructor/decreaseSalary         |                      { instructorId:":instructorId", percent:":percent" }                     |                    Update salary a instructor                 |
|   GET   	|    /instructor/get-transactions-by-date   |                                            EMPTY                                            	|              Get List all transactions by date                |
|   GET   	|    /instructor/get-transactions-by-id     |                                            EMPTY                                            	|         Get List all transactions by instructor id            |


# Course Path
| Request 	|     Route     	|                                 Body                                 	                            |   Description   	|
|:-------:	|:-------------:	|:-------------------------------------------------------------------------------------------------:|:---------------:	|
|   GET   	|    /course    	|                                 EMPTY                                	                            | List all course 	|
|   GET   	| /course/{:id} 	|                                 EMPTY                                	                            |   Get a course  	|
|   POST  	|    /course    	|      { courseName:":courseName", courseCredit:":courseCredit", courseCode:":courseCode" }      	|   Add a course  	|
|   PUT   	|    /course    	| { id:":id" ,courseName:":courseName", courseCredit:":courseCredit", courseCode:":courseCode" } 	| Update a course 	|
|  DELETE 	| /course/{:id} 	|                                 EMPTY                                	                            | Delete a course 	|

# Student Path

| Request 	|      Route     	|                              Body                              	            |    Description   	|
|:-------:	|:--------------:	|:-----------------------------------------------------------------------------:| :----------------:|
|   GET   	|    /student    	|                              EMPTY                             	            | List all student 	|
|   GET   	| /student/{:id} 	|                              EMPTY                             	            |   Get a student  	|
|   POST  	|    /student    	|      { name:":name", gender:":gender", age:":age", address:":address" }      	|   Add a student  	|
|   POST  	|/student/{:studentId}/{:courseId}|                              EMPTY      	                    | Set Student Course Relationship |
|   PUT   	|    /student    	| { id:":id" ,name:":name", gender:":gender", age:":age", address:":address" } 	| Update a student 	|
|  DELETE 	| /student/{:id} 	|                              EMPTY                             	            | Delete a student 	|


# Log Path

| Request 	|      Route     	         |                              Body                              	            |    Description       	        |
|:-------:	|:--------------------------:|:----------------------------------------------------------------------------:| :----------------------------:|
|   GET   	|   /logs       	         |                              EMPTY                             	            | List all logs 	            |
|   GET   	|   /logs/{:status}          |                              EMPTY                             	            | Returns logs with error code 	|
|   GET   	|   /logs/created/{:date}    |  	                        EMPTY                                           | Returns logs between two dates|
