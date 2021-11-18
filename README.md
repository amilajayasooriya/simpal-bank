# Simple Bank Springboot App 
Simple Account Transaction Application

### Content :-
1. Source code
2. Executables
   1. Executable jar and extendable jar file to attached as a dependency
   2. Postman collection to test APIs
   3. Config folder to set configurations such as profile to use and database details

### How to Start Executables :-

Inside "executable" directory double-click on "`start-simple-bank-app.bat`" in Windows machine start the app with default
configurations. Any changes to configurations can be done at configuration files inside the "config" directory.
Logs will be placed inside the "logs" directory and the default h2 database will be created under the "data" directory.

Application support MySQL database as well. In order to use MySQL please change the active profile as "mysql" and change the database
details inside the "`application-mysql.yml`" file.

### API Documentation :-

Once the application is started, go to the "[http://localhost:10444/swagger-ui/#/]()" URL to access API documentation.

### Postman Collection of All APIs :-

Import "SimpleBank.postman_collection.json" which is inside the "executable" directory to Postman.

1. Use the "Create User" request to create a new user
2. Get the id of the created user, then append in `"user" :{"id": user_id}` of the "Create Account" request to create an account for the user.
3. To create a transaction, use the "Create Transaction" request with account id.
   1. If the transaction type is Debit, please set `"transactionType": "Debit"` and set `"debitAmount": "1000"`
   2. If the transaction type is Debit, please set `"transactionType": "Credit"` and set `"creditAmount": "1000`"
   3. An initial transaction will be added when a new account is created.

### Build Source Code :-
Open a command prompt console and use the command `mvn clean package`
To copy build jars to executable directory, execute `copy_build_jar_executable.bat`
