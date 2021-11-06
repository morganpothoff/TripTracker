import java.lang.String;
import java.io.*;
import java.util.*;

public class LoginController {
    String id, password, description, trips;

    LoginController() {
        id ="";
        password="";
        description="";
        trips="";
    }

    public void getUserInput(String i, String p) {
            //id = entered id password = entered password, get from GUI later
            id = i;
            password = p;
    }

    public void getNewUserInput(String i, String p) {
        //id = entered id password = entered password, from REGISTER FRAME
        id = i;
        password = p;
    }

     public void getProfileInput() {
            //get from profile UI
            description = "New and improved description";
            trips = "lots of trips oh wow";
    }

    public void login(DBManager db) throws IOException {
        Authenticator auth = new Authenticator(id, password);
        auth.authenticate(db);

        HashMap<String, HashMap<String, String>> employeeMap = db.getEmployeeInfoMap();
        HashMap<String, String> dataMap = employeeMap.get(id);
        description = dataMap.get("description");
        trips = dataMap.get("trips");
        return;
    }

    public void register(DBManager db) throws IOException {
        if(db.addNewEmployee(id, password)) {
            System.out.println("Account created");
            return true;
        }
        
        else{
             System.out.println("Account not created--ID already in use");
             return false;
        }
    }



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