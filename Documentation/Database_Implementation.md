## Database Selection
There are multiple databases around the market but every solution has its own caveat. Amoung all database I prefer MySQL a better solution for my project. Major problem with my project is to maintain relation between different modules which is only possible via sql database solution. MySQL is open source database solution and is flexible with [phpMyAdmin](https://www.phpmyadmin.net/) and [MySqlWorkbench](https://www.mysql.com/products/workbench/) as well. If you are wondering differences between __phpMyAdmin__ and __MySqlWorkbench__ feel free to visit [mysql-workbench-vs-phpmyadmin](https://stackshare.io/stackups/mysql-workbench-vs-phpmyadmin).

I will be using __MySqlWorkbench__ as my prior tool but configuring it to __phpMyAdmin__ is a piece of cake.  
## Features of Database
Some of the major features of MySQL are as follows:
- Ease of Management – The software very easily gets downloaded and also uses an event scheduler to schedule the tasks automatically.
- Robust Transactional Support – Holds the ACID (Atomicity, Consistency, Isolation, Durability) property, and also allows distributed multi-version support. 
- Comprehensive Application Development – MySQL has plugin libraries to embed the database into any application. It also supports stored procedures, triggers functions, views and many more for application development. 
- High Performance – Provides fast load utilities with distinct memory caches and table index partitioning.
- Low Total Cost Of Ownership – This reduces licensing costs and hardware expenditures.
- Open Source & 24 * 7 Support –  This RDBMS can be used on any platform and offers 24*7 support for open source and enterprise edition.
- Secure Data Protection – MySQL supports powerful mechanisms to ensure that only authorized users have access to the databases.
- High Availability – MySQL can run high-speed master/slave replication configurations and it offers cluster servers.
- Scalability & Flexibility – With MySQL you can run deeply embedded applications and create data warehouses holding a humongous amount of data.

## Database Scripting
After Database Selection its time to implement the database. I will be using __phpMyAdmin__ web interface to generate database schema and export the script to __MySqlWorkbench__ so that further task can be easily done. The Schema generated from the [database](./Assets/Schema/inventory_phpmyAdmin_Dumpped.sql) is shown below.

<p>
<a href="Assets/Images/Inventory Management Schema Diagram phpMyAdmin 5.0.4.png"><img src="Assets/Images/Inventory Management Schema Diagram phpMyAdmin 5.0.4.png" alt="Schema Diagram"></a>
</p>


## Database Setup
Now Lets Setup Database in __MySqlWorkbench__ by following the steps below.
1. Open __MySqlWorkbench__ 
1. Connect to an Instance
1. Create new Schema on Connected Server
1. Give Schema name as `inventory` and click apply
1. Select Apply on prompt and Finish.
1. Select Schemas under Navigator and select __inventory__ schema.
1. Right Click on __inventory__ schema and select __Set as Default Schema__
1. Open [database file](./Assets/Schema/inventory_phpmyAdmin_Dumpped.sql) script into new query tab.
1. Exequete the Query 
 Thats It Database setup and import successfull.

## Database Schema Reverse Enginered
If you Follow Workbench Reverse Engenere then you will get models which will give ER-Diagram as output of your schema as following.
[Reverse Engenered Database](./Assets/Schema/inventory_Workbench_Dumpped.sql)
<p>
<a href="Assets/Images/Inventory Management Schema Diagram WorkBench8.0.png"><img src="Assets/Images/Inventory Management Schema Diagram WorkBench8.0.png" alt="ER Diagram"></a>
</p>