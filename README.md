# Bank-Account-Kata

Application for Deposit and Withdraw for SG with Rest API

## Test with swagger
Run App : mvn spring-boot:run

Link : http://localhost:8080/swagger-ui/index.html

## Account API

Method | Path          | Description                     |
-------|---------------|---------------------------------|
GET    | /accounts     | retrieve all the accounts       |
GET    | /accounts/{id} | retrieve one account by its ID |
POST   | /accounts      | store a new account            |
PUT    | /accounts      | update an existing account     |
DELETE | /accounts/{id} | remove an account byt its ID   |

## Balance API

Method | Path              | Description                                         |
-------|-------------------|-----------------------------------------------------|
GET    | /balance/deposit  | deposit money into account                          |
GET    | /balance/withdraw | withdraw money from account                         |
GET    | /balance/check-history | check operations history for the given account |

