# Simple Bank Springboot App 
Simple Account Transaction Application

### Content :-
1. Source code
2. Executables 
   1. Executable jar and jar file to attached as dependency
   2. Post man collection to test APIs
   3. Config folder to set configurations such profile to use and database details

### How to Start Executables :-

In side "executable" directory double-click on "`start-simple-bank-app.bat`" in Windows machine start the app with default
configurations. Any changes to configurations can be done at configuration files inside "config" directory.
Logs will be places in side "logs" directory and default h2 database will be created under "data" directory.

Application support MySQL database as well. Inorder to use MySQL please change active profile as mysql and change database
details inside "`application-mysql.yml`" file.

### API Documentation :-

Once the application is started, goto "[http://localhost:10444/swagger-ui/#/]()" url to access api documentation.

### Postman Collection of All APIs :-

Import "SimpleBank.postman_collection.json" file to Postman. 

1. Use "Create User" request to create a new user
2. Get the id of created user, then append in `"user" :{"id": user_id}` of "Create Account" request to create a account for the user.
3. To create a transaction, use "Create Transaction" request with account id.
   1. If the transaction type is Debit, please set `"transactionType": "Debit"` and set `"debitAmount": "1000"`
   2. If the transaction type is Debit, please set `"transactionType": "Credit"` and set `"creditAmount": "1000`"
   3. An initial transaction will be added when new account created.

### Build Source Code :-
open command console and use the command `mvn clean package`
To copy build jars to executable directory execute `copy_build_jar_executable.bat`
