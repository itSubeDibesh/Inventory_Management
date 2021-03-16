## __Conceptulization__
After the [`requirements analysis`](./Requirements_Analysis.md) is completed it's time __conceptulize the schema__ out of the requirements.
As our requirements clears up models like __users, products, warehouses, customers and sales__ we will be conceptulize the schema with a mockup and create a proper [data dictionary](#Data-Dictionary).

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

| S.N   | Model                       | Name     | Data Type     | Enum Options        | Data Length | Attribute                   | Allow Null (TRUE/FALSE)   | Description                                          |
| :---: | :------------------------:  | :------------:  | :----------:  | :-----------------: | :---------: | :-------------------------:  | :----------------------:  | :-------------------------------------------------:  |
|   1   | Roles                       | RoleId          | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
|   2   | Roles                       | Name            | VarChar       | -                   | 50          | UNIQUE                       | FALSE                     | Unique Role Assigned to Users                        |     
|   3   | Roles                       | Description     | Text          | -                   | -           | -                            | TRUE                      | Role Description                                     |     
|   4   | Roles                       | CreatedAt       | DateTime      | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
|   5   | Roles                       | UpdatedAt       | DateTime      | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|   6   | Access                      | AccessId        | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
|   7   | Access                      | Name            | VarChar       | -                   | 50          | UNIQUE                       | FALSE                     | Unique Access Assigned to Users                      |     
|   8   | Access                      | Description     | Text          | -                   | -           | -                            | TRUE                      | Access Description                                   |     
|   9   | Access                      | CreatedAt       | DateTime      | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
|  10   | Access                      | UpdatedAt       | DateTime      | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|  11   | RoleAccess                  | RoleAccessId    | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
|  12   | RoleAccess                  | GroupName       | VarChar       | -                   | 50          | -                            | FALSE                     | Unique Access Assigned to Users                      |     
|  13   | RoleAccess                  | RoleId          | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Role to RoleAccess Table                        |     
|  14   | RoleAccess                  | AccessId        | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Access to RoleAccess Table                      |     
|  15   | RoleAccess                  | Status          | Enum          | Active,Inactive     | -           | DEFAULT(Active)              | FALSE                     | Save RoleAccess To Active or Inactive                |     
|  16   | RoleAccess                  | CreatedAt       | DateTime      | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
|  17   | RoleAccess                  | UpdatedAt       | DateTime      | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|  18   | Logins                      | LoginId         | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
|  19   | Logins                      | GroupName       | VarChar       | -                   | 50          | -                            | FALSE                     | Unique Access Assigned to User from RoleAccess       |     
|  20   | Logins                      | Phone           | BigInt        | -                   | -           | -                            | TRUE                      | Save Users Phone Number                              |     
|  21   | Logins                      | Email           | Text          | -                   | -           | -                            | FALSE                     | Save Users Email Address                             |     
|  22   | Logins                      | User Name       | VarChar       | -                   | 20          | UNIQUE                       | FALSE                     | Save User Name To Access Login                       |     
|  23   | Logins                      | Password        | Text          | -                   | -           | -                            | FALSE                     | Save Password To Access Login                        |     
|  24   | Logins                      | CreatedAt       | DateTime      | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
|  25   | Logins                      | UpdatedAt       | DateTime      | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |          
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|  26   | Users                       | UserId          | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
|  27   | Users                       | LoginId         | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Login to Users Table                            |     
|  28   | Users                       | FullName        | VarChar       | -                   | 200         | -                            | FALSE                     | Save User Full Name                                  |     
|  29   | Users                       | Contact Number  | BigInt        | -                   | -           | -                            | FALSE                     | Save Users Contact Number                            |     
|  30   | Users                       | Address         | Text          | -                   | -           | -                            | FALSE                     | Save Users Address                                   |     
|  31   | Users                       | DOB             | Date          | -                   | -           | -                            | FALSE                     | Save Users Date of Birth                             |     
|  32   | Users                       | Gender          | Enum          | Male,Female,Others  | -           | -                            | FALSE                     | Save Users Gender                                    |     
|  33   | Users                       | TPIN            | VarChar       | -                   | 25          | -                            | FALSE                     | Save Users Transaction PIN                           |     
|  34   | Users                       | CreatedAt       | DateTime      | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
|  35   | Users                       | UpdatedAt       | DateTime      | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|  36   | ProductCategories           | CategoryId      | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
|  37   | ProductCategories           | ParentId        | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Own Self if Sub Categories Exists               |     
|  38   | ProductCategories           | Name            | VarChar       | -                   | 200         | -                            | FALSE                     | Categories Name                                      |        
|  39   | ProductCategories           | Remarks         | Text          | -                   | -           | -                            | TRUE                      | Categories Remarks                                   |        
|  40   | ProductCategories           | CreatedAt       | DateTime      | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
|  41   | ProductCategories           | UpdatedAt       | DateTime      | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|  42   | Product                     | ProductId       | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |     
|  43   | Product                     | CategoryId      | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Categories to Products                          |     
|  44   | Product                     | VendorsName     | VarChar       | -                   | 200         | -                            | FALSE                     | Products Vendors Name                                |        
|  45   | Product                     | Name            | VarChar       | -                   | 200         | -                            | FALSE                     | Products Name                                        |        
|  46   | Product                     | Invoice Number  | VarChar       | -                   | 20          | -                            | FALSE                     | Products Purchase Invoice                            |        
|  47   | Product                     | Invoice Date    | DateTime      | -                   | -           | -                            | FALSE                     | Products Purchase Date                               |        
|  48   | Product                     | CreatedAt       | DateTime      | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
|  49   | Product                     | UpdatedAt       | DateTime      | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|  50   | WarehouseAndMart            | WMId            | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |       
|  51   | WarehouseAndMart            | Name            | VarChar       | -                   | 200         | -                            | FALSE                     | Warehouse or Mart Name                               |        
|  52   | WarehouseAndMart            | Type            | Enum          | Warehouse, Mart     | -           | DEFAULT(Warehouse)           | FALSE                     | Save Warehouse or Mart                               |     
|  53   | WarehouseAndMart            | Address         | VarChar       | -                   | 200         | -                            | FALSE                     | Save Warehouse or Mart Address                       |        
|  54   | WarehouseAndMart            | Contact         | BigInt        | -                   | -           | -                            | FALSE                     | Save Warehouse or Mart Contact                       |        
|  55   | WarehouseAndMart            | Description     | Text          | -                   | -           | -                            | TRUE                      | Warehouse or Mart Contact  Description               |        
|  56   | WarehouseAndMart            | CreatedAt       | DateTime      | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
|  57   | WarehouseAndMart            | UpdatedAt       | DateTime      | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|  58   | WarehouseAndMartProducts    | WMProductId     | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |       
|  59   | WarehouseAndMartProducts    | WMId            | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link WarehouseAndMart to WarehouseAndMartProducts    |     
|  60   | WarehouseAndMartProducts    | ProductId       | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Product to WarehouseAndMartProducts             |     
|  61   | WarehouseAndMartProducts    | AvailableQTY    | BigInt        | -                   | 200         | -                            | FALSE                     | Available Qunatity                                   |        
|  62   | WarehouseAndMartProducts    | PurchasedQTY    | BigInt        | -                   | 200         | -                            | FALSE                     | Purchased Qunatity                                   |        
|  63   | WarehouseAndMartProducts    | PurchasedPrice  | BigInt        | -                   | 200         | -                            | FALSE                     | Purchased Price                                      |        
|  64   | WarehouseAndMartProducts    | SellingPrice    | BigInt        | -                   | 200         | -                            | FALSE                     | Selling Price                                        |              
|  65   | WarehouseAndMartProducts    | CreatedAt       | DateTime      | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
|  66   | WarehouseAndMartProducts    | UpdatedAt       | DateTime      | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|  67   | DamagedAndExpiry            | DEId            | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |         
|  68   | DamagedAndExpiry            | ProductId       | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Product to WarehouseAndMartProducts             |     
|  69   | DamagedAndExpiry            | Type            | Enum          | Damage, Expiry      | -           | DEFAULT(Expiry)              | FALSE                     | Save Damage or Expiry                                |     
|  70   | DamagedAndExpiry            | Qunatity        | BigInt        | -                   | 200         | -                            | FALSE                     | Damaged or Exxpired Qunatity                         |                    
|  71   | DamagedAndExpiry            | CreatedAt       | DateTime      | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
|  72   | DamagedAndExpiry            | UpdatedAt       | DateTime      | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|  73   | Customers                   | CustomerId      | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |         
|  74   | Customers                   | TPIN            | VarChar       | -                   | 25          | -                            | FALSE                     | Save Customers Transaction PIN                       |     
|  75   | Customers                   | Name            | VarChar       | -                   | 200         | -                            | FALSE                     | Save Customers Name                                  |          
|  76   | Customers                   | Address         | VarChar       | -                   | 200         | -                            | FALSE                     | Save Customers Address                               |        
|  77   | Customers                   | Contact         | BigInt        | -                   | -           | -                            | FALSE                     | Save Customers Contact                               |                          
|  78   | Customers                   | CreatedAt       | DateTime      | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
|  79   | Customers                   | UpdatedAt       | DateTime      | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|  80   | Sales                       | SalesId         | Int           | -                   | -           | AUTO INCREAMENT, PRIMARY KEY | FALSE                     | Has Primary key and used to maintain identity        |         
|  81   | Sales                       | Invoice Number  | VarChar       | -                   | 20          | -                            | FALSE                     | Products Sales Invoice                               |        
|  82   | Sales                       | Invoice Date    | DateTime      | -                   | -           | -                            | FALSE                     | Products Sales Date                                  |        
|  83   | Sales                       | ProductId       | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Sales to Products                               |     
|  84   | Sales                       | CustomerId      | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Sales to Customers                              |     
|  85   | Sales                       | UserId          | Int           | -                   | -           | FOREIGN KEY                  | FALSE                     | Link Sales to User                                   |            
|  86   | Sales                       | AmountReceived  | Double        | -                   | -           | -                            | FALSE                     | Save Amounts Received                                |                          
|  87   | Sales                       | AmountReturned  | Double        | -                   | -           | -                            | FALSE                     | Save Amounts Returned                                |                          
|  88   | Sales                       | CreatedAt       | DateTime      | -                   | -           | CURRENTTIMESTAMP()           | FALSE                     | Save Current Time Stamp on Insert                    |     
|  89   | Sales                       | UpdatedAt       | DateTime      | -                   | -           | ONUPDATECURRENTTIMESTAMP()   | TRUE                      | Save Current Time Stamp on Update                    |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     
|       |                             |                 |               |                     |             |                              |                           |                                                      |     

As you can observe now we have some what image of database but the clear picture we need to [`implement database`](./Database_Implementation.md) itself.