
Wallet Topup and Deduct System
=====================================


This is a payment system implementation using wallet balance. It allows users to add amount to their wallet and deduct amount as required via separate transactions.

## Description

This is the implementation of online wallet payment system where topup and deduction transactions will be done. The solution has been implemented using Java - Spring Boot. The data modeling consists of a user table and a transaction table. User table contains the wallet balance which keeps getting updated on every topup/deduct API request. Each topup/deduct API request is captured as a transaction which stores the details of the transaction like balance, user ID, status etc.

## APIs covered:
* Sign Up - This API is used for the user registration
* Login - This API is used for login and authentication
* Topup - This API is used to add balance to the wallet
* Deduct - This API is used to deduct balance from the wallet
* Balance - This API is used to fetch the current balance of the given wallet
* Get User - This API is used to fetch the user details
* Get Transaction - This API is used to fetch the details of a single transaction
* Get All Users - This API is used to fetch all the users

## API Documentation

[Click here to view the API Documentation](https://documenter.getpostman.com/view/18403833/2sA3dygWA1)

## Data modeling - Entity Relationship Diagram

[Click here to view the ER diagram](https://app.diagrams.net/?tags=%7B%7D&lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=Untitled%20Diagram.drawio#R%3Cmxfile%3E%3Cdiagram%20id%3D%22C5RBs43oDa-KdzZeNtuy%22%20name%3D%22Page-1%22%3E7VptV%2BI6EP41fNx7%2BkIRP8qLrrvqZQVXvV%2FuiW0oWUPDpkGov%2F5O2vSNFAQVuKucw9HOZJo28zyTzkxbs9vj%2BRlHk9El8zCtWYY3r9mdmmWZpm3DP6mJEo3dPE4UPieeMsoVffKMldJQ2inxcFgyFIxRQSZlpcuCALuipEOcs1nZbMho%2BaoT5GNN0XcR1bW3xBOjRNu0jnL9V0z8UXpls6HWN0apsVpJOEIemxVUdrdmtzljIjkaz9uYSuelfrk9j27pxWPj7NuP8De6aX0fXP38kkx2uskp2RI4DsSrp35%2BHJ5%2B%2FVn%2FdTfpnf6YGWcnD9%2B%2BqFOMJ0Snyl83IeZqvSJKnRjOyJiiAKTWkAWir0bABS1EiR%2FAsQv3BmfarSfMBQH%2Fn6gBwSagdUeEehcoYlO5glAg9zGVWiPGyTNMiygMmaCAYS4UlaxGyaIvzwS1AVqOQ7DppW4xM9UFCoWycRmlaBKSh%2FiGpckYcZ8ELSYEG6cTsWngYU9JGc6xIDh7zJgjz18TDAWa9AaeF6iowDnDbIwFj8AkHW0qMFSg1Z1EnOWstQxlMiow1jKVIVKR4mdTZ1e7hshCgQ8%2ByC5n2eXLmXVDu57ZqLpeo3w5RAH3AAnckl4MixyEg8JKc1XMzA1YamssPfdArtkn8PeCBb5GWPC5KJCT4qFYSs1wglwS%2BBexTaeea67VoqWKwblDGtNiRDwPBzFtBBIoYZbkyoSRQMRecVrwA9%2B1jb%2BcmgM31AbZzGX4SXMu2iwAhiESUwkDbWdYUncVI0diTNWhTsWVQf4yFaMyxC9Sr7GceSUOrABcYHP22xx9R3fIsZ9%2FNa%2F%2BPR9XbEvdMSI0w7wvOPnkqGtorkuEpag71u5Qr7w9S0O9h8Jwxrh3AH6b4X7U3HO4mxrwV2iMPzro24pj06jvOZB1PAElFLg5pKeUIfFBEX3%2FADVtPTfbbYTqW3ObYyQICzqQ%2BWWw%2FsMC7EnNgMj4%2FZDwbiFgnX3nW3qCLUuoS%2BaRIUkAPUD8Noib%2B06uzCMN4wFHQYhcGcUakEsqfuNQ8b9fxe8YL1f85vtV%2FI7z51T85rHG1kPJv0ENkEX7m2r%2BSvJtbYNqapBD6ItpmMHeYoxipO9VfzryW8O0qqLfKaaW3sg5GQPPxQcvBPYcy1UF%2FW5xr6jo8UyG8KeoA%2FcMf2UDYLf46%2FVi8l7JOO9k2Fe%2BaTpA%2FzboqzoF24K%2BspR0VpcZxiCa5OFfNfSJCfGaujPhyGa9hZ0SovECIT5Rd2HfXKhqQuyUC3qKr2GMPR%2Bn3QZYLRHRNaZxp7GbjyQ9iaRdYFq5%2B2S1jgPvRH43AmL3Gug0YJcoiDTvw9w8uisK91IAJJXYmRcHO1EJHXmXS7FZ1R0I2ZS7eIWL0m9kEPfxKqyXPQiKxXxVgylT8titT%2BVlrOgo9GQc5N0Eu6nATMnVWJgiWak6K%2BeNNlG9sdCWSOV0osQV2kSvaDVUOlzvNKzxVFrRHttp3ytRXaJ5yXDTbpjW0SuGV33dLWk57Zd3wCynhL1trtmSam7tVaResQ7%2B7t30lj2Lkm5hm1EGfusELKEEoXRB9X98HC1uorMREbgPtyU1M44m5Y11LRIsD7FNOk%2BVmNvGtjDXq9VOt3PTHhxA3xroFWnpjkHX33npuUieSriEuxT36DRMd%2B10RGENtqeEpjlcbFGQYVQ5vVn2L0UPmLZgp%2Ffj9GSBQAspy5yIuzxJAem%2BMJLnK1KIaotpTp7Z3GeZ0v7SnOM105xlKW0Vb96Y0TgLX0RazQXurZvRNI5fmOjVGQ2I%2BffGiXn%2B1bbd%2FQ8%3D%3C%2Fdiagram%3E%3C%2Fmxfile%3E)

## Features
* Java-Spring Boot has been used for the implementation.

* It provides authentication using JWT token.

* It allows asynchronous transactions via multithreading and allows upto 200 threads at a time + 100 requests in queue. 10 minimum idle threads are available.

* Caching is enabled for all the methods which includes database operations like query, insert and update.

* REST APIs are used for all the operations.

* MySQL database has been used.

* Simple Logging Facade (slf4j) is the framework used for logging.

* Spring Boot Test (JUnit) has been used for unit test cases.

## Usage Instructions

1. Download the given postman collection - Wallet Topup.postman_collection.json and import it in the postman.

2. Clone this repository.

3. Pre-requisites to run this project: Java 17, Eclipse IDE, Postman, MySQL workbench

4. Import this project to Eclipse IDE & update the project in order to download all maven dependencies mentioned in pom.xml.

5. In Eclipse, go to help->Install new software-> Put the following URL - https://projectlombok.org/p2 and download lombok. This is required to auto-generate getters and setters.

6. Configure the MySQL database and create a database 'walletTopup'.

7. Go to src/main/resources/application.properties and update the credentials of the MySQL connection.

8. If pom.xml gives an error on the external resources link, you will have to download them in order to mitigate it.

9. Go to TopupApplication.java and run the application. An embedded tomcat server will get started.

10. Run the APIs in the postman. 

## Developer

* Nishal Beegamudre
</br></br>
<a  href="https://www.linkedin.com/in/nishal-beegamudre/" target="_blank"><img alt="LinkedIn" src="https://img.shields.io/badge/linkedin%20-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" /></a>




