# Walmart [![time tracker](https://wakatime.com/badge/github/itSubeDibesh/Inventory_Management.svg)](https://wakatime.com/badge/github/itSubeDibesh/Inventory_Management) 

[!["JAVA"](https://img.shields.io/badge/Java-ff8800?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com) 
[!["Spring Boot"](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot) [!["Maven"](https://img.shields.io/badge/Maven-0769AD?style=for-the-badge&logoColor=white)](https://mvnrepository.com/)
[!["Tom Cat"](https://img.shields.io/badge/Apache_Tomcat-orange?style=for-the-badge&logoColor=white)](http://tomcat.apache.org/)  
[!["Mustache"](https://img.shields.io/badge/Mustache-darkorange?style=for-the-badge&logoColor=white)](https://mustache.github.io/) 
[!["HTML5"](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/HTML5) [!["JavaScript"](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)](https://developer.mozilla.org/en-US/docs/Web/JavaScript) 
[!["IQuery"](	https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white)](https://jquery.com/) 
[!["Css3"](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/Css) 
[!["Bootstrap"](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)](https://getbootstrap.com/) 
[!["MySql"](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/) 
[!["MySql WorkBench"](https://img.shields.io/badge/MySQL_WorkBench-darkblue?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/) 
[!["Github"](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https:/www.github.com/) 
[!["Github Projects"](https://img.shields.io/badge/GitHub_Projects-100000?style=for-the-badge&logo=github&logoColor=white)](https:/www.github.com/) 
[!["MD"](https://img.shields.io/badge/Markdown-000000?style=for-the-badge&logo=markdown&logoColor=white)](https://www.markdownguide.org) 
[!["Windows 10"](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)](https://www.microsoft.com/en-us/software-download/windows10) 
[!["IntelliJ"](https://img.shields.io/badge/IntelliJ-parotgreen?style=for-the-badge&logoColor=white)](https://www.jetbrains.com/idea/) 

## __Table of Contents__

- [User Requirements](#User-Requirements)
- [Requirements Analysis](#Requirements-Analysis) 
- [Language and Tool Selection](#Language-and-Tool-Selection)
- [Application Setup](#Application-Setup)
- [Database Selection](#Database-Selection)
    1. [Features of Database](#Features-of-Database)
    1. [Database Scripting With Spring Data JPA](#Database-Scripting-With-Spring-Data-JPA)
    1. [Database Setup](#Database-Setup)
    1. [First Login Credentials](#First-Login-Credentials)
    1. [Database Schema Reverse Enginered](#Database-Schema-Reverse-Enginered)
- [Project Workflow](#Project-Workflow)
- [Folder Structure](#Folder-Structure)
- [Features of Application](#Features-of-Application)
- [Spring MVC Workflow](#Spring-MVC-Workflow)
  1. [The DispatcherServlet](#The-DispatcherServlet)
- [Controller Types](#Controller-Types)
- [Limitation of Project](#Limitation-of-Project)
- [Further Enhancement](#Further-Enhancement)
- [Reference Documentation](#Reference-Documentation)
- [Guides](#Guides)

## __User Requirements__

You have been recently hired by a highly credible company in Nepal, which is a branch office of a __US based company__.
Since you are joined recently stakes are high on you. In the meantime a project comes by asking you to design __an
application__ that requires you to manage an __inventory on a shopping mall like Walmart__. Your system should be fully
functional as well as deployable on the server so that the application can be logged into and then daily transactions be
carried out. The transactions carried are categorized as follows:

1. __Sales__
    - ___Customer sales :___ Customer sales should be able to create a customer and provide a named bill that is tax
      valid. For this case you can use the TPIN number of the customer which can be generally called a PAN number. Every
      transaction should be recorded in the database along with the tax amount of the sales.

2. __Inventory management__
    - ___Add item :___ Item should be added to the inventory and a unique identifier of each item should be given so
      that the code can be pasted on the item which can later be used to retrieve the information of the item including
      price and quantity from the database.

    - ___Transfer Item to another warehouse :___ There are several Walmart stores through the country. The problem is
      that sometimes an item needs to be transferred from one store to the other. In such scenario your software
      application must have provision to transfer the item from one warehouse to the other warehouse.

    - ___Revoke damaged and expired items :___ There are cases when your item is expired or has been damaged. In such
      case your application should be able to revoke such item to be unsalable and thus prevent the items from ever
      dispatching to the customer. Our ambition is always to put the customer satisfaction upfront and thrive to deliver
      the best product and experience a customer can dream of.

3. __Administration__
    - ___User Creation :___ Create a user so that the user often a sales boy or a salesgirl can log into and then
      generate bills.

    - ___User Delete :___ Whenever your employee leaves the office there should be some way to revoke or delete the user
      account so that the credentials passed to the employee would no longer work.

    - ___User Edit :___ Your application should be able to edit the information of the employee like address, contact
      number which are changed often times while engaged in employment.

    - ___Store TPIN account :___ Your application design should have some functions to save the user details on a
      database. The data can be used whenever the user ask to bill to a particular customer id. Remember you need to
      have the customer searchable through the __TPIN number__ and hence eliminate the time it takes to type all the
      detail while billing.

__Criteria__:
This application should include the following things:

1. Documentation regarding the project and the solution you purpose.
1. UML diagrams for the classes you require.
1. Implementation on either swing based application or spring based web application.
1. Strictly follow the object oriented paradigm.
1. Strictly follow MVC (Model, View, and Controller) design of software programming. Database design should be as per
   the classes you define. Can also use Hibernate framework to ease your work.

## __Requirements Analysis__

Summarizing all the user requirements we can conclude the following measures as the actual user requirements. According
to client requirements we must create an inventory application with following requirements:

1. __User__
    1. Create, update, delete and list users
    1. User roles and permission
    1. User login and access management
1. __Products__
    1. Create, update, delete and list products
    1. Implement product categories and sub-categories
    1. Purchase and salse sources of products also should be maintained
1. __Warehouse__
    1. Create, update, delete and list warehouse
    1. Transfure product from one ware house to other
    1. Revoke the damaged and expired items
1. __Customers__
    1. Create, update, delete and list customers
    1. Implement __TPIN number__ of the customer ___(PAN number)___
1. __Sales__
    1. Products should be salable by users to customers
    1. Receipts and sales history should be maintain

Briefing out the requirements, we will be creating application which would store details of users, products, warehouses,
customers and sales made to customer.

## __Language and Tool Selection__
As per requirement language and some tools ware preety much sorted whereas some tools were picked at development phase. The list of tools used while developing the project are as follows.

````
Backend : Java, Spring Boot Framework
Package Manager: Maven
Server : Apache Tomcat
Frontend : Html5
Template Engine : Mustache
Styling : Bootstrap4, Css
Scripting : JS and Mini Js Libraries Like jQuery
Documentation : MarkDown (MD)
Database : MySQL
Database Management system : MySQL Workbench
IDE : Intelij
Version Control : GitHub
Project Manager : GitHub Projects
Development OS: Windows 10 Home Edition
````

[!["JAVA"](https://img.shields.io/badge/Java-ff8800?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com) 
[!["Spring Boot"](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot) 
[!["Maven"](https://img.shields.io/badge/Maven-0769AD?style=for-the-badge&logoColor=white)](https://mvnrepository.com/)
[!["Tom Cat"](https://img.shields.io/badge/Apache_Tomcat-orange?style=for-the-badge&logoColor=white)](http://tomcat.apache.org/)  

[!["Mustache"](https://img.shields.io/badge/Mustache-darkorange?style=for-the-badge&logoColor=white)](https://mustache.github.io/) 
[!["HTML5"](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/HTML5) 

[!["JavaScript"](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)](https://developer.mozilla.org/en-US/docs/Web/JavaScript) 
[!["IQuery"](	https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white)](https://jquery.com/) 

[!["Css3"](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/Css) 
[!["Bootstrap"](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)](https://getbootstrap.com/) 

[!["MySql"](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/) 
[!["MySql WorkBench"](https://img.shields.io/badge/MySQL_WorkBench-darkblue?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/) 

[!["Github"](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https:/www.github.com/) 
[!["Github Projects"](https://img.shields.io/badge/GitHub_Projects-100000?style=for-the-badge&logo=github&logoColor=white)](https:/www.github.com/) 
[!["MD"](https://img.shields.io/badge/Markdown-000000?style=for-the-badge&logo=markdown&logoColor=white)](https://www.markdownguide.org) 

[!["Windows 10"](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)](https://www.microsoft.com/en-us/software-download/windows10) 
[!["IntelliJ"](https://img.shields.io/badge/IntelliJ-parotgreen?style=for-the-badge&logoColor=white)](https://www.jetbrains.com/idea/) 


## __Application Setup__
Starting application is easy after you configure number of things. Follow the steps below to run the project.
1. Open Terminal
1. Clone the Project using `git clone https://github.com/itSubeDibesh/Inventory_Management.git` command
1. [Setup Database](#Database-Setup)
1. Go to the cloned Directory
1. Run `mvnw package` for __windows__ or `./mvnw package` for __mac__. This will create Jar file inside `target` sub-directory.
1. Go inside `target` sub-directory
1. Now run the jar file in there. Use command `java -jar walmart-0.0.1.jar` [walmart-0.0.1 is the name of your created jar file.]
1. Your application Should be running at port printed on your terminal.

## __Database Selection__

There are multiple databases around the market but every solution has its own caveat. Amoung all database I prefer MySQL
a better solution for my project. Major problem with my project is to maintain relation between different modules which
is only possible via sql database solution. MySQL is open source database solution and to add steroids on the project I will be using [MySqlWorkbench](https://www.mysql.com/products/workbench).

### __Features of Database__

Some of the major features of MySQL are as follows:

- Ease of Management – The software very easily gets downloaded and also uses an event scheduler to schedule the tasks
  automatically.
- Robust Transactional Support – Holds the ACID (Atomicity, Consistency, Isolation, Durability) property, and also
  allows distributed multi-version support.
- Comprehensive Application Development – MySQL has plugin libraries to embed the database into any application. It also
  supports stored procedures, triggers functions, views and many more for application development.
- High Performance – Provides fast load utilities with distinct memory caches and table index partitioning.
- Low Total Cost Of Ownership – This reduces licensing costs and hardware expenditures.
- Open Source & 24 * 7 Support – This RDBMS can be used on any platform and offers 24*7 support for open source and
  enterprise edition.
- Secure Data Protection – MySQL supports powerful mechanisms to ensure that only authorized users have access to the
  databases.
- High Availability – MySQL can run high-speed master/slave replication configurations and it offers cluster servers.
- Scalability & Flexibility – With MySQL you can run deeply embedded applications and create data warehouses holding a
  humongous amount of data.

### __Database Scripting With Spring Data JPA__
`Spring Data JPA API` provides __JpaTemplate__ class to integrate spring application with JPA. ___JPA (Java Persistent API)___ is the sun specification for persisting objects in the enterprise application. It is currently used as the replacement for complex entity beans.

The implementation of JPA specification are provided by many vendors such as:
- Hibernate
- Toplink
- iBatis
- OpenJPA etc.

#### __Advantage of Spring JpaTemplate__
You don't need to write the before and after code for persisting, updating, deleting or searching object such as creating Persistence instance, creating EntityManagerFactory instance, creating EntityTransaction instance, creating EntityManager instance, commiting EntityTransaction instance and closing EntityManager.

Spring Data JPA helps to generate database itself using JPA API Under the hood configured with moduls you create. 
[Readmore about Spring Data JPA](https://spring.io/projects/spring-data-jpa)
</p>

### __Database Setup__

Now Lets Setup Database in __MySqlWorkbench__ by following the steps below.

1. Open __MySqlWorkbench__
1. Connect to an Instance
1. Create new Schema on Connected Server
1. Give Schema name as `walmart` and click apply
1. Select Apply on prompt and Finish.
1. Now Run the Project and Database Will Be created automatically using `Spring Data JPA`.

`Note :` __To Have a Working Database Connected with project You need to configure database name and password on application.properties.__

You can find [application.properties](./src/main/resources/application.properties) on `\src\main\resources`.

````
#Configuring Datasource, JPA, Hibernate
spring.datasource.url= jdbc:mysql://localhost:3306/walmart?useSSL=false
spring.datasource.username= walmart
spring.datasource.password= password
````
Change `spring.datasource.username` and `spring.datasource.password` to your user name and password form __MySqlWorkbench__ instance.

 ### __First Login Credentials__

Your First Login Credentials are seeded to database at inital database setup. Use following credentials to login into app.
````
Username : admin
Password : password
```` 

### __Database Schema Reverse Enginered__

If you Follow Workbench Reverse Enginering then you will get [model](./Assets/Schema/Model/Inventory_Management.mwb) which will give ER-Diagram as output of your schema as
following.
<p>
<a href="Assets/Images/Inventory Management Schema Diagram WorkBench8.0.png"><img src="Assets/Images/Inventory Management Schema Diagram WorkBench8.0.png" alt="ER Diagram"></a>
</p>

## __Project Workflow__
So when the server serves the file a workflow is created which clears up the request made from clients and files served to clients. __Spring boot framework__ follows coding standard utilizing [convention over configuration](https://en.wikipedia.org/wiki/Convention_over_configuration) simplyfing and optimizing the code yet completing [spring mvc workflow](#Spring-MVC-Workflow) as well. 

"**Convention over configuration is a software design paradigm used by software frameworks that attempts to decrease the number of decisions that a developer using the framework is required to make without necessarily losing flexibility.**" 

!["Spring Boot Workflow"](https://media.geeksforgeeks.org/wp-content/uploads/20190822182410/Spring-Boot-flow-architecture.jpg)

As shown in the figure above when a request is made from client a controller handles the request and redirects to a service layer, which simultaneously requests the revilent model and with ["Spring Data JPA"](#Database-Scripting-With-Spring-Data-JPA) a request to database is made and result is thus sent to client filtering through service layer and back to controller.

## __Folder Structure__
The core folder of application is shown below.
<a href="Assets/Images/Folder Structure.png"><img src="Assets/Images/Folder Structure.png" alt="ER Diagram"></a>
</p>

As you can see inside controllers folder there are 5 different sub folders which servs their owne purpose.
1. `API directory` includes individual api endpoints, Repository to make database calls and model used for specific table in database.
1. ` Configuration directory` includes global configurations used in controllers to optimize workflows.
1. `Error directory` includes a error handling methord for invalid requests.
1. `Seeder Directory` includes initial seeder data for the database.
1. `Web Directory` includesall web controllers and DTOs(Data Transfer Objects) for individual table.

## __Features of Application__
The features of Application are as follows:
<pre>
                                       |---------> Users
                                       |---------> Logins [Login and Authorize Users]
                                       |---------> Products
                                       |---------> Categories
  Create, Read, Update and Delete <----|---------> Sales
                                       |---------> Customers
                                       |---------> Warehouse And Marts
                                       |---------> Damages [Except Update]
                                       |---------> Warehouse And Marts Products [Transfer Products]
</pre>

## __Spring MVC Workflow__
The Spring Web MVC framework provides Model-View-Controller (MVC) architecture and ready components that can be used to develop flexible and loosely coupled web applications. The MVC pattern results in separating the different aspects of the application (input logic, business logic, and UI logic), while providing a loose coupling between these elements.

- The Model encapsulates the application data and in general they will consist of POJO.
- The View is responsible for rendering the model data and in general it generates HTML output that the client's browser can interpret.
- The Controller is responsible for processing user requests and building an appropriate model and passes it to the view for rendering.

### __The DispatcherServlet__

The Spring Web model-view-controller (MVC) framework is designed around a DispatcherServlet that handles all the HTTP requests and responses. The request processing workflow of the Spring Web MVC DispatcherServlet is illustrated in the following diagram.

!["DispatcherServlet"](https://www.tutorialspoint.com/spring/images/spring_dispatcherservlet.png)

Following is the sequence of events corresponding to an incoming HTTP request to DispatcherServlet:-

- After receiving an HTTP request, DispatcherServlet consults the HandlerMapping to call the appropriate Controller.

- The Controller takes the request and calls the appropriate service methods based on used GET or POST method. The service method will set model data based on defined business logic and returns view name to the DispatcherServlet.

- The DispatcherServlet will take help from ViewResolver to pickup the defined view for the request.

- Once view is finalized, The DispatcherServlet passes the model data to the view which is finally rendered on the browser.

All the above-mentioned components, i.e. HandlerMapping, Controller, and ViewResolver are parts of WebApplicationContext w which is an extension of the plainApplicationContext with some extra features necessary for web applications.

[Read More](https://www.tutorialspoint.com/spring/spring_web_mvc_framework.htm)
## __Controller Types__
Basically there are two types of controller. Rest controller and Web controller.
Rest Controllers are used for making restfull services like __API__ where as Web controllers are used to handel client request. In our project All the rest controllers are listed under [api directory](./src/main/java/com/itsubedibesh/walmart/controllers/api) and web cotroller are listed under [web directory](src\main\java\com\itsubedibesh\walmart\controllers\web).

## __Limitation of Project__
With every projects flaws are surely listed. Some of the drawbacks of projects are as follows.
1. Poor Security implementation - Lack of CSRF policy, Lower security implementation using session only.
1. Lack of roles and permissions
1. Lack of Warehouse and Mart seperation

## __Further Enhancement__ 
Some of the further enhancement of project are as follows:
1. Implement Proper Security Policy
1. Implement Proper Role and Permission
1. Implement Event logging
1. Implement Purchase and Sales Analysis   
1. Implement Proper Seperation policy

## __Reference Documentation__

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-developing-web-applications)
* [Mustache](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-spring-mvc-template-engines)

## __Guides__

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

