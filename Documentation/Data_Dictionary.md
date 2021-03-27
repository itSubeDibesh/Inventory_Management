## __Conceptulization__

After the [`requirements analysis`](./Requirements_Analysis.md) is completed it's time __conceptulize the schema__ out
of the requirements. As our requirements clears up models like __users, products, warehouses, customers and sales__ we
will be conceptulize the schema with a mockup and create a proper [data dictionary](#Data-Dictionary).

___Note :___ __STAMP_FLAGS__ denots __Created AT__ and __Updated AT__ on table.

<pre>
Inventory Management System (IMS)
    |
 ___V___
|       |
|       |----------> Roles
|       |          |--------> Role Id
|       |          |--------> Name
|       |          |--------> Description
|       |__________|--------> STAMP_FLAGS
|       |
|       |----------> Access
|       |          |--------> Access Id
|       |          |--------> Name
|       |          |--------> Description
|       |__________|--------> STAMP_FLAGS
|       |
|       |----------> Role Access
|       |          |--------> Role Access Id
|       |          |--------> Group Name
|       |          |--------> Role Id
|       |          |--------> Access Id
|       |          |--------> Status
|       |__________|--------> STAMP_FLAGS
|       |
|       |----------> Logins
|       |          |--------> Login Id
|       |          |--------> Group Name
|       |          |--------> Phone
|       |          |--------> Email
|       |          |--------> User Name
|       |          |--------> Password
|       |__________|--------> STAMP_FLAGS
|       |
|       |----------> Users
|       |          |--------> User Id
|       |          |--------> Login Id
|       |          |--------> Full Name
|       |          |--------> Contact Number
|       |          |--------> Address
|       |          |--------> DOB
|       |          |--------> Gender
|       |          |--------> TPIN
|       |__________|--------> STAMP_FLAGS
|       |
|       |----------> Product Categories
|       |          |--------> Category Id
|       |          |--------> Parent Id
|       |          |--------> Name
|       |          |--------> Remarks
|       |__________|--------> STAMP_FLAGS
|       |
|       |----------> Products
|       |          |--------> Product Id
|       |          |--------> Category Id
|       |          |--------> Name
|       |          |--------> Vendors Name
|       |          |--------> Invoice Number
|       |          |--------> Invoice Date 
|       |__________|--------> STAMP_FLAGS
|       |
|       |----------> Warehouse And Mart
|       |          |--------> WM Id
|       |          |--------> Name
|       |          |--------> Type (Warehouse, Mart)
|       |          |--------> Address
|       |          |--------> Contact
|       |          |--------> Desccription
|       |__________|--------> STAMP_FLAGS
|       |
|       |---------->  Warehouse And Mart Products
|       |          |--------> WM Product Id
|       |          |--------> WM Id
|       |          |--------> Product Id
|       |          |--------> Available QTY
|       |          |--------> Purchase QTY
|       |          |--------> Purchase Price
|       |          |--------> Selling Price
|       |__________|--------> STAMP_FLAGS
|       |
|       |----------> Damage And Expiry
|       |          |--------> DE Id
|       |          |--------> Product Id
|       |          |--------> Type (Damage,Expiry)
|       |          |--------> QTY
|       |__________|--------> STAMP_FLAGS
|       |
|       |----------> Customers
|       |          |--------> Customer Id
|       |          |--------> TPIN
|       |          |--------> Name
|       |          |--------> Address
|       |          |--------> Contact
|       |__________|--------> STAMP_FLAGS
|       |
|       |----------> Sales
|       |          |--------> Sales Id
|       |          |--------> Invoice Number
|       |          |--------> Invoice Date
|       |          |--------> Product ID
|       |          |--------> Customer ID
|       |          |--------> User ID
|       |          |--------> Amount Received
|       |          |--------> Amount Returned
|       |__________|--------> STAMP_FLAGS
|_______|
</pre>

## __Data Dictionary__

After [`conceptulization`](#Conceptulization) it's time create __data dictionary__.

| Model                       | Name     | Data Type     | Enum Options        | Data Length | Attribute                   | Allow Null (TRUE/FALSE)   | Description                                          |
| :------------------------:  | :------------:  | :----------:  | :-----------------: | :---------: | :-------------------------:  | :----------------------:  | :-------------------------------------------------:  |
| Roles                       | RoleId          | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
| Roles                       | Name            | VarChar       | -                   | 50          | UNIQUE                       | FALSE                     | Unique Role Assigned to Users                        |     
| Roles                       | Description     | Text          | -                   | -           | -                            | TRUE                      | Role Description                                     |     
| Roles                       | CreatedAt       | TimeStamp     | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
| Roles                       | UpdatedAt       | TimeStamp     | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|                             |                 |               |                     |             |                              |                           |                                                      |          
| Access                      | AccessId        | BigInt        | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
| Access                      | Name            | VarChar       | -                   | 50          | UNIQUE                       | FALSE                     | Unique Access Assigned to Users                      |     
| Access                      | Description     | Text          | -                   | -           | -                            | TRUE                      | Access Description                                   |     
| Access                      | CreatedAt       | TimeStamp     | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
| Access                      | UpdatedAt       | TimeStamp     | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|                             |                 |               |                     |             |                              |                           |                                                      |     
| RoleAccess                  | RoleAccessId    | BigInt        | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
| RoleAccess                  | GroupId         | Int           | -                   | -           | -                            | FALSE                     | Link Logins with User Group                          |     
| RoleAccess                  | RoleId          | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Role to RoleAccess Table                        |     
| RoleAccess                  | AccessId        | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Access to RoleAccess Table                      |     
| RoleAccess                  | Status          | Enum          | Active,Inactive     | -           | DEFAULT(Active)              | FALSE                     | Save RoleAccess To Active or Inactive                |     
| RoleAccess                  | CreatedAt       | TimeStamp     | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
| RoleAccess                  | UpdatedAt       | TimeStamp     | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|                             |                 |               |                     |             |                              |                           |                                                      |     
| Logins                      | LoginId         | BigInt        | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
| Logins                      | GroupId         | Int           | -                   | -           | -                            | FALSE                     | Link Logins with User Group                          |     
| Logins                      | Phone           | BigInt        | -                   | -           | -                            | TRUE                      | Save Users Phone Number                              |     
| Logins                      | Email           | Text          | -                   | -           | -                            | FALSE                     | Save Users Email Address                             |     
| Logins                      | Image           | Text          | -                   | -           | -                            | TRUE                      | Save Users Image                                     |     
| Logins                      | User Name       | VarChar       | -                   | 20          | UNIQUE                       | FALSE                     | Save User Name To Access Login                       |     
| Logins                      | Password        | Text          | -                   | -           | -                            | FALSE                     | Save Password To Access Login                        |     
| Logins                      | CreatedAt       | TimeStamp     | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
| Logins                      | UpdatedAt       | TimeStamp     | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |          
|                             |                 |               |                     |             |                              |                           |                                                      |     
| Users                       | UserId          | BigInt        | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
| Users                       | LoginId         | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Login to Users Table                            |     
| Users                       | FullName        | VarChar       | -                   | 200         | -                            | FALSE                     | Save User Full Name                                  |     
| Users                       | Contact Number  | BigInt        | -                   | -           | -                            | FALSE                     | Save Users Contact Number                            |     
| Users                       | Address         | Text          | -                   | -           | -                            | FALSE                     | Save Users Address                                   |     
| Users                       | DOB             | Date          | -                   | -           | -                            | FALSE                     | Save Users Date of Birth                             |     
| Users                       | Gender          | Enum          | Male,Female,Others  | -           | -                            | FALSE                     | Save Users Gender                                    |     
| Users                       | TPIN            | VarChar       | -                   | 25          | -                            | FALSE                     | Save Users Transaction PIN                           |     
| Users                       | CreatedAt       | TimeStamp     | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
| Users                       | UpdatedAt       | TimeStamp     | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|                             |                 |               |                     |             |                              |                           |                                                      |     
| ProductCategories           | CategoryId      | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
| ProductCategories           | ParentId        | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Own Self if Sub Categories Exists               |     
| ProductCategories           | Name            | VarChar       | -                   | 200         | -                            | FALSE                     | Categories Name                                      |        
| ProductCategories           | Remarks         | Text          | -                   | -           | -                            | TRUE                      | Categories Remarks                                   |        
| ProductCategories           | CreatedAt       | TimeStamp     | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
| ProductCategories           | UpdatedAt       | TimeStamp     | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|                             |                 |               |                     |             |                              |                           |                                                      |     
| Product                     | ProductId       | BigInt        | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
| Product                     | CategoryId      | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Categories to Products                          |     
| Product                     | VendorsName     | VarChar       | -                   | 200         | -                            | FALSE                     | Products Vendors Name                                |        
| Product                     | Name            | VarChar       | -                   | 200         | -                            | FALSE                     | Products Name                                        |        
| Product                     | Invoice Number  | VarChar       | -                   | 20          | -                            | FALSE                     | Products Purchase Invoice                            |        
| Product                     | Invoice Date    | TimeStamp     | -                   | -           | -                            | FALSE                     | Products Purchase Date                               |        
| Product                     | CreatedAt       | TimeStamp     | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
| Product                     | UpdatedAt       | TimeStamp     | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|                             |                 |               |                     |             |                              |                           |                                                      |     
| WarehouseAndMart            | WMId            | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |       
| WarehouseAndMart            | Name            | VarChar       | -                   | 200         | -                            | FALSE                     | Warehouse or Mart Name                               |        
| WarehouseAndMart            | Type            | Enum          | Warehouse, Mart     | -           | DEFAULT(Warehouse)           | FALSE                     | Save Warehouse or Mart                               |     
| WarehouseAndMart            | Address         | VarChar       | -                   | 200         | -                            | FALSE                     | Save Warehouse or Mart Address                       |        
| WarehouseAndMart            | Contact         | BigInt        | -                   | -           | -                            | FALSE                     | Save Warehouse or Mart Contact                       |        
| WarehouseAndMart            | Description     | Text          | -                   | -           | -                            | TRUE                      | Warehouse or Mart Contact  Description               |        
| WarehouseAndMart            | CreatedAt       | TimeStamp     | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
| WarehouseAndMart            | UpdatedAt       | TimeStamp     | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|                             |                 |               |                     |             |                              |                           |                                                      |     
| WarehouseAndMartProducts    | WMProductId     | BigInt        | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |       
| WarehouseAndMartProducts    | WMId            | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link WarehouseAndMart to WarehouseAndMartProducts    |     
| WarehouseAndMartProducts    | ProductId       | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Product to WarehouseAndMartProducts             |     
| WarehouseAndMartProducts    | AvailableQTY    | Double        | -                   | -           | -                            | FALSE                     | Available Qunatity                                   |        
| WarehouseAndMartProducts    | PurchasedQTY    | Double        | -                   | -           | -                            | FALSE                     | Purchased Qunatity                                   |        
| WarehouseAndMartProducts    | PurchasedPrice  | Double        | -                   | -           | -                            | FALSE                     | Purchased Price                                      |        
| WarehouseAndMartProducts    | SellingPrice    | Double        | -                   | -           | -                            | FALSE                     | Selling Price                                        |              
| WarehouseAndMartProducts    | CreatedAt       | TimeStamp     | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
| WarehouseAndMartProducts    | UpdatedAt       | TimeStamp     | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|                             |                 |               |                     |             |                              |                           |                                                      |     
| DamagedAndExpiry            | DEId            | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |         
| DamagedAndExpiry            | ProductId       | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Product to WarehouseAndMartProducts             |     
| DamagedAndExpiry            | Type            | Enum          | Damage, Expiry      | -           | DEFAULT(Expiry)              | FALSE                     | Save Damage or Expiry                                |     
| DamagedAndExpiry            | Qunatity        | BigInt        | -                   | 200         | -                            | FALSE                     | Damaged or Exxpired Qunatity                         |                    
| DamagedAndExpiry            | CreatedAt       | TimeStamp     | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
| DamagedAndExpiry            | UpdatedAt       | TimeStamp     | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|                             |                 |               |                     |             |                              |                           |                                                      |     
| Customers                   | CustomerId      | BigInt        | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |         
| Customers                   | TPIN            | VarChar       | -                   | 25          | -                            | FALSE                     | Save Customers Transaction PIN                       |     
| Customers                   | Name            | VarChar       | -                   | 200         | -                            | FALSE                     | Save Customers Name                                  |          
| Customers                   | Address         | VarChar       | -                   | 200         | -                            | FALSE                     | Save Customers Address                               |        
| Customers                   | Contact         | BigInt        | -                   | -           | -                            | FALSE                     | Save Customers Contact                               |                          
| Customers                   | CreatedAt       | TimeStamp     | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
| Customers                   | UpdatedAt       | TimeStamp     | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|                             |                 |               |                     |             |                              |                           |                                                      |     
| Sales                       | SalesId         | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |         
| Sales                       | Invoice Number  | VarChar       | -                   | 20          | -                            | FALSE                     | Products Sales Invoice                               |        
| Sales                       | Invoice Date    | TimeStamp     | -                   | -           | -                            | FALSE                     | Products Sales Date                                  |        
| Sales                       | ProductId       | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Sales to Products                               |     
| Sales                       | CustomerId      | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Sales to Customers                              |     
| Sales                       | UserId          | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Sales to User                                   |            
| Sales                       | AmountReceived  | Double        | -                   | -           | -                            | FALSE                     | Save Amounts Received                                |                          
| Sales                       | AmountReturned  | Double        | -                   | -           | -                            | FALSE                     | Save Amounts Returned                                |                          
| Sales                       | CreatedAt       | TimeStamp     | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
| Sales                       | UpdatedAt       | TimeStamp     | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|                             |                 |               |                     |             |                              |                           |                                                      |     
| UserGroup                   | GroupId         | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |         
| UserGroup                   | Group Name      | VarChar       | -                   | 50          | -                            | FALSE                     | Group Name                                           |            
|                             |                 |               |                     |             |                              |                           |                                                      |

As you can observe now we have some what image of database but the clear picture we need
to [`implement database`](./Database_Implementation.md) itself. After Implementing Datbaase now we can autogenerate the
data disctinary and compare the changes made.
<p>
<a href="Assets/Images/Data Dictionary PhpMyAdmin.png"><img src="Assets/Images/Data Dictionary PhpMyAdmin.png" alt="Data Dictionary"></a>
</p>