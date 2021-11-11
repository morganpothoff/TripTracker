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
