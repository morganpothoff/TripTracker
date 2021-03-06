import java.lang.String;
import java.io.*;
import java.util.*;

//Controller for user login and registration--Zachary Sedlacek
public class LoginController {
    String id, password, description, trips, firstName, lastName, username;

    LoginController() {
    	   username="";
        id ="";
        firstName="";
        lastName="";
        password="";
        description="";
        trips="";
    }
	
    //Get user ID and password from GUI
    public void getUserInput(String u, String p) {
            username = u;
            password = p;
    }
    
    public void getNewUserInput(String u, String p) {
        //id = entered id password = entered password, from REGISTER FRAME
        username = u;
        password = p;
    }
     
     //Get new profile information from Edit GUI
     public void getProfileInput() {
           
            description = "New and improved description";
            trips = "lots of trips oh wow";
    }
    
    //Authenticate login with entered ID and Password. Display account information with successful login, display error with no matching account
    public void login(DBManager db) throws Exception {
        Authenticator auth = new Authenticator(username, password);
        if (auth.authenticate(db)){
        	id = Integer.toString(db.id);
        	firstName = db.firstName;
        	lastName = db.lastName;
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

    //TODO: FIX (ID IS AN INT, NOT A STRING)
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
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public int getUserID() {
        try{
            return Integer.parseInt(id);
        }
        catch(Exception e) {
            System.out.println(e.toString());
            return -1;
        }
    }

}