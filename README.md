# TripTracker
#####
# Use Case: Login
#Upon clicking login button on login page, the GUIController calls the LoginController to update with the user's data from the GUI.<br>
#It then creates an Authenticator which gets the user's input information from the LoginController.<br>
#The Authenticator calls the DBManager to check if the login is valid.<br>
#The DBManager checks for the login and returns true for valid or false for invalid login.<br>
#The Authenticator returns the results of the login.<br>
#The GUIController displays the profile of the employee on true or shows an error message and allows more login attempts on false.<br>
#####
# Use Case: Register
#Upon clicking register button on rehister page, the GUIController calls the LoginController to update with the user's data from the GUI.<br>
#It then calls the LoginController's register function, which checks with the DBManager if the requested ID is already taken or not.<br>
#If the DBManager does not find the ID in the database, it inserts the new ID and password to create a new user and returns true.<br>
#If it finds the ID already in the database, it returns false.<br>
#The LoginController returns the information to the GUIController which either displays the new profile or an error message if an account was not created.<br>
#####
