import java.lang.String;
import java.io.*;
import java.util.*;
import java.sql.*;

//Controller for user login and registration--Zachary Sedlacek
public class LoginController {
    String id, password, description, trips, firstName, lastName;

    LoginController() {
        id ="";
        firstName="";
        lastName="";
        password="";
        description="";
        trips="";
    }
	
    //Get user ID and password from GUI
    public void getUserInput(String i, String p) {
            id = i;
            password = p;
    }
    
    public void getNewUserInput(String i, String p) {
        //id = entered id password = entered password, from REGISTER FRAME
        id = i;
        password = p;
    }
     
     //Get new profile information from Edit GUI
     public void getProfileInput() {
           
            description = "New and improved description";
            trips = "lots of trips oh wow";
    }
    
    //Authenticate login with entered ID and Password. Display account information with successful login, display error with no matching account
    public void login(DBManager db) throws IOException {
        Authenticator auth = new Authenticator(id, password);
        if (auth.authenticate(db)){
        	id = db.id;
        	String get_user_query = String.format("SELECT First_Name, Last_Name FROM Users WHERE User_ID = '%d';", id);
        	ConnectedDBConnection connection = new ConnectedDBConnection();
		ResultSet user_results = connection.select(get_user_query);
		
        	firstName = user_result.getString("First_Name");
		lastName = user_result.getString("Last_Name");
        }
	  
       /* HashMap<String, HashMap<String, String>> employeeMap = db.getEmployeeInfoMap();
        HashMap<String, String> dataMap = employeeMap.get(id);
        description = dataMap.get("description");
        trips = dataMap.get("trips");
        return;
        */
    }

    //Create new account with user entered ID and password if ID is not taken
    public boolean register(DBManager db) throws IOException {
        if(db.addNewEmployee(id, password)) {
            System.out.println("Account created");
            return true;
        }
        
        else{
             System.out.println("Account not created--ID already in use");
             return false;
        }
    }


    //Change profile information to new user-entered information
    public void editProfile(DBManager db, Profile profile) throws IOException {
        getProfileInput();
        profile.edit(description, trips);
        profile.update(db);
        update(db);
        return;
    }

    //update login controller when profile info is updated
    public void update(DBManager db) throws IOException {
        HashMap<String, HashMap<String, String>> employeeMap = db.getEmployeeInfoMap();
        HashMap<String, String> dataMap = employeeMap.get(id);
        description = dataMap.get("description");
        trips = dataMap.get("trips");
    }
    public String getID() {
        return id;
    }

    public String getPassword() {return password; }

    public String getDescription() {
        return description;
    }

    public String getTrips() {
        return trips;
    }
}