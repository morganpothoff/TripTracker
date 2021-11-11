# TripTracker
#####
# Use Case: Login
#Upon clicking login button on login page, the GUIController calls the LoginController to update with the user's data from the GUI.
#It then creates an Authenticator which gets the user's input information from the LoginController.
#The Authenticator calls the DBManager to check if the login is valid.
#The DBManager checks for the login and returns true for valid or false for invalid login.
#The Authenticator returns the results of the login.
#The GUIController displays the profile of the employee on true or shows an error message and allows more login attempts on false.
#####
