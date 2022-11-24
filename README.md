 
# E Wallet Payment System

![E_Wallet_Payment_System](https://user-images.githubusercontent.com/105907169/203858500-b46d1c83-03c3-4842-b3f4-bdeefaa22d43.jpg)

 **REST API SERVICE**

The **Shopper's Club** has developed this REST API, which allows **Customers** to browse the **Products**, Save them to **Cart** for future, perform all the basic fundamental **CRUD** operations like any other Shopping Application and place **Orders** as per their requirements.

The  **Shopper's Club** API also provides **User, Admin Authentication & Validation** for every operation. 

The application's primary objective is to provide **Customers with both a Simple & User-Friendly Shopping Experience.**

The application will be used by the **two** categories of users:

- **Admin (Administrator)**

- **User (Customer)** 
## Tech Stack

- JAVA
- SPRING
- SPRINGBOOT
- HIBERNATE
- MAVEN
- J.D.B.C
- MYSQL
- POSTMAN

## Dependencies

- SPRING DATA JPA
- SPRING BOOT DEVTOOLS
- SPRING WEB
- HIBERNATE
- MYSQL DRIVER
- VALIDATION
- LOMBOK
- SWAGGER UI





## System Structure

The REST API allows a Customer to **Signup, Login** & **Browse** through the products and add them to **Cart** wishlist, as well as the Administrator to **View** all the **Orders, Customers & Products** with filtering options. 

Administrator can **Add, Update, View, Delete** **Products** & other **Administrators** from the **Database**.
  

- Administrator
    -
    - Login & Logout
    - View all Products
    - View all Orders of an User
    - View all Customers
    - Add New Products into Database
    - Remove Products from Database
    - Update Products in Database 
    - Delete Admin from Database

- User / Customer
    -
    - Signup 
    - Login & Logout
    - Update all Personal Details and Address 
    - View all Products with filtering options
    - Add Products to Cart
    - Update Products Quantity in Cart 
    - Delete Products from Cart
    - Empty Cart
    - Add Order
    - Cancel Order
    - View Orders with filtering options
    - Delete Customer Account
    




## Setting & Installation 

Install the Spring Tools Suite 
```bash
https://spring.io/tools
```

Install MySQL Community Server

```bash
https://dev.mysql.com/downloads/mysql/
```

Clone the Project

```bash
git clone https://github.com/TejasMedade/Online-Shopping-System
```

Open MySQL Server
```bash
Create a New Database in SQL: "Online_Shopping_System" 
```
Go to the Project Directory & Open SQL Folder > SQL Tables

```bash
Create The Same Tables in Your "Online_Shopping_System" Database 
```

Create a Admin For Your Database

```bash
INSERT INTO ADMIN VALUES('Admin_01','Admin@01');
```




## Run Locally


Go to the Project Directory

```bas
Open the Online_Shopping_System Folder with S.T.S
```

Go to **src/main/resources > application.properties** & change your username and password (MySQL server username & password)

```bash
spring.datasource.username="username"

spring.datasource.password="password"
```

To change the **Server Port**

```bash
server.port=8088
```

Go to **com.masai package > Online_Shopping_System.java**

```bash
Run as Spring Boot App !
```
Open the following URL for Swagger-UI 
```bash
http://localhost:8088/swagger-ui/
```
 
 ## ER Diagram
 
![E-Wallet(1)](https://user-images.githubusercontent.com/105907169/203405312-629dd99b-0150-4025-9301-213571f2824e.png)


## URL
```bash
http://localhost:8088
```


## Contributions

Contributions are always **Welcome** !

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **Greatly Appreciated**.

If you have a suggestion that would make this REST API better, Please fork the repo and create a pull request. You can also connect with me for further development of this application !

Don't forget to give the project a star ! Thank You !

## 🔗 Contact Me
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://tejasmedade.github.io/)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/TejasMedade)

[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/TejasMedade)


## Authors

- [Tejas Vilas Medade](https://github.com/tejasmedade)

- [Shreyas Vilas Medade](https://github.com/medadeshreyas)
## Acknowledgements

- [Masai School](https://www.masaischool.com/)
 

