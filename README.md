# TripTracker

## About


## Running
### Requirements
- JDK 16
- Unix/Linux Environment

Compile
```bash
make
```

Running
```bash
make run
```

## Structure
TripTracker
├── Build
│   ├── ConnectedDBConnection.class
│   ├── EmployeeFrame.class
│   ├── Expense.class
│   ├── GUI.class
│   ├── GUIController.class
│   ├── GUIView.class
│   ├── LoginFrame.class
│   ├── MFrame.class
│   ├── Manager.class
│   ├── ManagerFrame.class
│   ├── ManagerSelectionFrame.class
│   ├── Payment.class
│   ├── ProposalFrame.class
│   ├── RegisterFrame.class
│   ├── Trip.class
│   ├── TripFrame.class
│   ├── TripTracker.class
│   └── Users.class
├── DB
│   ├── CreateUser.sql
│   ├── FullSchema.sql
│   └── FullSchemaTest.sql
├── Makefile
├── README.md
└── Source
    ├── ConnectedDBConnection.java
    ├── DBClasses
    │   ├── Expense.java
    │   ├── Manager.java
    │   ├── Payment.java
    │   ├── Trip.java
    │   └── Users.java
    ├── GUI
    │   ├── EmployeeFrame.java
    │   ├── GUI.java
    │   ├── LoginFrame.java
    │   ├── MFrame.java
    │   ├── ManagerFrame.java
    │   ├── ManagerSelectionFrame.java
    │   ├── ProposalFrame.java
    │   ├── RegisterFrame.java
    │   └── TripFrame.java
    ├── TripTracker.java
    └── mysql-connector-java.jar


## Use Cases:
1. Login
    - Upon clicking login button on login page, the GUIController calls the LoginController to update with the user's data from the GUI.
    - It then creates an Authenticator which gets the user's input information from the LoginController.
    - The Authenticator calls the DBManager to check if the login is valid.
    - The DBManager checks for the login and returns true for valid or false for invalid login.
    - The Authenticator returns the results of the login.
    - The GUIController displays the profile of the employee on true or shows an error message and allows more login attempts on false.
2. Register
    - Upon clicking register button on register page, the GUIController calls the LoginController to update with the user's data from the GUI.
    - It then calls the LoginController's register function, which checks with the DBManager if the requested ID is already taken or not.
    - If the DBManager does not find the ID in the database, it inserts the new ID and password to create a new user and returns true.
    - If it finds the ID already in the database, it returns false.
    - The LoginController returns the information to the GUIController which either displays the new profile or an error message if an account was not created.
3. Add Item
    - Upon clicking the add button on the trip manager screen, content of the item and price textfields will be grabbed.<br>
    - The addItem() method in GUIController parses the content of the item line.<br>
    - The strings will be formatted and added to the item list for the user, then added to the database.<br>
    - The item is added to the Expense class along with the user that added the expense.<br>




## TODO
- [ ] Add field for Username in the GUI register user page
- [ ] Add method for the user to be saved in the DB
