## Database Selection

There are multiple databases around the market but every solution has its own caveat. Amoung all database I prefer MySQL
a better solution for my project. Major problem with my project is to maintain relation between different modules which
is only possible via sql database solution. MySQL is open source database solution and to add steroids on the project I will be using [MySqlWorkbench](https://www.mysql.com/products/workbench/).

## Features of Database

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

## Database Scripting With Spring Data JPA

Spring Data JPA helps to generate database itself using existing database models. [Readmore about Spring Data JPA](https://spring.io/projects/spring-data-jpa)
</p>

## Database Setup

Now Lets Setup Database in __MySqlWorkbench__ by following the steps below.

1. Open __MySqlWorkbench__
1. Connect to an Instance
1. Create new Schema on Connected Server
1. Give Schema name as `walmart` and click apply
1. Select Apply on prompt and Finish.
1. Now Run the Project and Database Will Be created automatically using `Spring Data JPA`.

`Note :` __To Have a Working Database Connected with project You need to configure database name on application.properties.__

You can find [application.properties](../WalMart/src/main/resources/application.properties) on `WalMart\src\main\resources`.

````
#Configuring Datasource, JPA, Hibernate
spring.datasource.url= jdbc:mysql://localhost:3306/walmart?useSSL=false
spring.datasource.username= walmart
spring.datasource.password= password
````
Change `spring.datasource.username` and `spring.datasource.password` to your user name and password form __MySqlWorkbench__ instance.

## Database Models
Every Database Models are located inside individual [API controller](../WalMart/src/main/java/com/itsubedibesh/walmart/controllers/api) directory.

## Database Schema Reverse Enginered

If you Follow Workbench Reverse Enginering then you will get [model](./Assets/Schema/Model/Inventory_Management.mwb) which will give ER-Diagram as output of your schema as
following.
<p>
<a href="Assets/Images/Inventory Management Schema Diagram WorkBench8.0.png"><img src="Assets/Images/Inventory Management Schema Diagram WorkBench8.0.png" alt="ER Diagram"></a>
</p>