 
# E Wallet Payment System

![E_Wallet_Payment_System](https://user-images.githubusercontent.com/105907169/203858500-b46d1c83-03c3-4842-b3f4-bdeefaa22d43.jpg)



 ## **REST API SERVICE**

**E PAY WALLET** has developed this REST API, which allows **Customers** to transfer **Money** to their **Digital Wallet** from **Bank**, and vice-versa, **Customers** can maintain **Transactions** with other **Registered Customers** on the E PAY, can make **Transactions** directly through Bank Account to their saved **Beneficiaries**.
**Customers** through **E PAY** can also make **Payments** for their **Utility & Other Bills** and can perform all the basic fundamental **CRUD** operations like any other Digital Wallet Application.

**E Pay** API also provides **Customer, Admin Authentication & Validation**. 

The api's primary objective is to provide **Customers with both a Simple & User-Friendly Digital Wallet Payment Experience**.

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

The REST API allows a Customer to **Signup, Login** & make **Transactions** to their **Contacts**, **Beneficiaries** and other **Utility Bills**, as well as the Administrator to **View** all the **Customers, Beneficiaries & Transactions of an User, ** for better customer support . 

- User / Customer
    -
    - Signup 
    - Login & Logout
    - Update all Personal Details and Address 
    - Add Bank Account, Update and View all bank details
    - Top Up Wallet From Bank Account Balance
    - Transfer Money From Wallet to Bank Account
    - Transfer Funds to other Customers
    - Transfer Funds to Beneficiaries
    - Make Bill Payments
    - Check Wallet Balance
    - View all Transactions
    - View all Bill Payments
    - Add, Delete and View all Beneficiaries
    - Delete Customer Account


- Administrator
    -
    - Login & Logout
    - View all registered Customers 
    - View all Customer details
    - View all Customer Bank Accounts
    - View all Customer Beneficiaries
    - View all Customers Transactions
    - Delete Admin from Database



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
git clone https://github.com/TejasMedade/E-Wallet-Payment-System
```

Open MySQL Server
```bash
Create a New Database in SQL: "e_wallet" 
```
Create a Admin For Your Database

```bash
INSERT INTO ADMIN VALUES('1001','admin_email','admin_first_name','admin_last_name','admin_mobile_number','admin_password');
```

Note for Admin & User
```bash
Admin Id : Min=1000, Max=1010 ; User Id : Enter Your Registered Mobile Number For Login, User Validation and Authentication. 
```


## Run Locally


Go to the Project Directory

```bas
Open the Payment Wallet Application Folder with S.T.S
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
## API REFERENCES

## Customer Controller
![Screenshot 2022-11-25 at 02-28-34 Swagger UI](https://user-images.githubusercontent.com/105907169/203862673-27cb3929-3ed0-40de-ad43-c3147d3b5569.png)



## Wallet Controller 


![Screenshot 2022-11-25 at 02-30-27 Swagger UI](https://user-images.githubusercontent.com/105907169/203862704-869fb029-4b83-473a-9eba-91c76c78b64d.png)

## Address Controller


![Screenshot 2022-11-25 at 02-27-29 Swagger UI](https://user-images.githubusercontent.com/105907169/203862722-b0ef2994-cdb7-4774-91d1-421f1e6338c6.png)

## Bank Account Controller


![Screenshot 2022-11-25 at 02-27-55 Swagger UI](https://user-images.githubusercontent.com/105907169/203862765-4db87a6c-496e-4520-8f34-75ab6e02c62c.png)

## Beneficiary Controller


![Screenshot 2022-11-25 at 02-28-10 Swagger UI](https://user-images.githubusercontent.com/105907169/203862839-0e952f2d-f623-43f3-8138-7a2da3fbd3a1.png)


## Bill Payment Controller

![Screenshot 2022-11-25 at 02-28-22 Swagger UI](https://user-images.githubusercontent.com/105907169/203862904-2508b507-a06f-479c-b3d7-069c3a78f203.png)


## Customer Login Logout Controller


![Screenshot 2022-11-25 at 02-29-05 Swagger UI](https://user-images.githubusercontent.com/105907169/203862971-11f8bd90-090e-4012-b310-530076442020.png)


## Admin Login Logout Controller


![Screenshot 2022-11-25 at 02-28-53 Swagger UI](https://user-images.githubusercontent.com/105907169/203863015-61d11dd5-efb6-42bd-9c81-75fd0091ac6f.png)

## Transactions Controller

![Screenshot 2022-11-25 at 02-29-19 Swagger UI](https://user-images.githubusercontent.com/105907169/203863039-06043861-886f-48a0-871c-b5cc84aa348d.png)


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

- [Shreyas Vilas Medade](https://github.com/medadeshreyas)

- [Tejas Vilas Medade](https://github.com/tejasmedade)



## Acknowledgements

- [Masai School](https://www.masaischool.com/)
 

