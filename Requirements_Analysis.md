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
customers and sales made to customer. The Analyzed requirements clears up the basic infrastructure of application
although the core visualization of project needs a workflow and further techinical documentations as follows:

1. [Database Implementation](./Database_Implementation.md)