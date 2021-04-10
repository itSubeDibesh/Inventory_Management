# Walmart [![time tracker](https://wakatime.com/badge/github/itSubeDibesh/Inventory_Management.svg)](https://wakatime.com/badge/github/itSubeDibesh/Inventory_Management)

## __Table of Contents__

- [User Requirements](#User-Requirements)
- [Requirements Analysis](#Requirements-Analysis)
- [Tool Selection](#Tool-Selection)
- [Language Selection](#Language-Selection)
- [Application Setup](#Application-Setup)
- [Database Selection](#Database-Selection)
    1. [Features of Database](#Features-of-Database)
    1. [Database Scripting With Spring Data JPA](#Database-Scripting-With-Spring-Data-JPA)
    1. [Database Setup](#Database-Setup)
    1. [Database Schema Reverse Enginered](#Database-Schema-Reverse-Enginered)
- [Project Workflow](#Project-Workflow)
- [Folder Structure](#Folder-Structure)
- [Features of Application](#Features-of-Application)
- [Spring MVC Workflow](#Spring-MVC-Workflow)
- [Controller Types and Lists](#Controller-Types-and-Lists)
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

## __Tool Selection__

## __Language Selection__

## __Application Setup__

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

### __Database Schema Reverse Enginered__

If you Follow Workbench Reverse Enginering then you will get [model](./Assets/Schema/Model/Inventory_Management.mwb) which will give ER-Diagram as output of your schema as
following.
<p>
<a href="Assets/Images/Inventory Management Schema Diagram WorkBench8.0.png"><img src="Assets/Images/Inventory Management Schema Diagram WorkBench8.0.png" alt="ER Diagram"></a>
</p>

## __Project Workflow__

## __Folder Structure__

## __Features of Application__

## __Spring MVC Workflow__

## __Controller Types and Lists__

## __Limitation of Project__

## __Further Enhancement__ 

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

