import java.sql.*;


public class Users {
    //Variables
    protected String name;
    protected int userID;
    protected String password;
    protected String email;

    // Constructors added by Morgan Pothoff
    // Constructor throws an exception if there are no results found for expense ID.
    Users(int id) throws Exception {
        String get_user_query = String.format("SELECT * FROM `Users` WHERE `User_ID` = %d;", id);
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet user_results = connection.select(get_user_query);

        // Check that atleast 1 row is returned
        if(!user_results.next()) {
            throw new Exception("No user results found for user ID");
        }

        userID = id;
        name = user_results.getString("First_Name") + " " + user_results.getString("Last_Name");
        password = user_results.getString("Password");
        email = user_results.getString("Email");

    }

    // Constructor throws an exception if there are no results found for expense ID.
    Users(String username) throws Exception {
        String get_user_query = String.format("SELECT * FROM `Users` WHERE `UserName` = '%s';", username);
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet user_results = connection.select(get_user_query);

        // Check that atleast 1 row is returned
        if(!user_results.next()) {
            throw new Exception("No user results found for user ID");
        }

        email = user_results.getString("eMail");
        name = user_results.getString("First_Name") + " " + user_results.getString("Last_Name");
        password = user_results.getString("Password");
        userID = user_results.getInt("User_ID");
    }


    // Adds a user to the database using the connection insert method.
    // Returns T/F depending upon its success.
    public static Boolean add(String First_Name, String Last_Name, String UserName, String Password, String Email) {
        
        try {
            String form =   "INSERT INTO `Users` (`First_Name`, `Last_Name`, `UserName`, `Password`, `Email`, `isManager`) "
                            + "VALUES ('%s', '%s', '%s', '%s', '%s', '%d');";
            String add_user_query =  String.format(form, First_Name, Last_Name, UserName, Password, Email, 0);

            ConnectedDBConnection connection = new ConnectedDBConnection();
            connection.insert(add_user_query);


            
            return true;
        }
        catch(Exception error) {
            System.out.println(error.toString());
            return false;
        }
    }

    public static Boolean addManager(String First_Name, String Last_Name, String UserName, String Password, String Email) {

        try {
            String form =   "INSERT INTO `Users` (`First_Name`, `Last_Name`, `UserName`, `Password`, `Email`, `isManager`) "
                    + "VALUES ('%s', '%s', '%s', '%s', '%s', '%d');";
            String add_user_query =  String.format(form, First_Name, Last_Name, UserName, Password, Email, 1);

            ConnectedDBConnection connection = new ConnectedDBConnection();
            connection.insert(add_user_query);

            String get_user_query = String.format("SELECT `User_ID` FROM `Users` WHERE `UserName` = '%s';", UserName);
            ResultSet user_results = connection.select(get_user_query);

            if(!user_results.next()){
                System.out.println("didnt pull out lmfao");
            }
            int pulledID = user_results.getInt("User_ID");

            System.out.println(pulledID);

            add_user_query = String.format("INSERT INTO `Manager` (User_ID, TotalBudget) VALUES (%d, %f);", pulledID, 0.0);
            connection.insert(add_user_query);

            return true;
        }
        catch(Exception error) {
            System.out.println(error.toString());
            return false;
        }
    }


    public static Boolean username_exists(String username) throws Exception {
        String get_user_query = String.format("SELECT * FROM `Users` WHERE `UserName` = '%s';", username);
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet user_results = connection.select(get_user_query);

        // Check that atleast 1 row is returned
        return user_results.next();
    }


    //Methods
    public String getName() {
        return name;
    }//End of getName

    public int getUserID() {
        return userID;
    }//End of getUserID

    public String getPassword() {
        return password;
    }//End of getPassword

    public String getEmail() {
        return email;
    }//End of getEmail
    
    public void setPassword(String newPassword) {
         password = newPassword;  
    }//End of setPassword
    
    public boolean updatePassword(String newPassword, String currentPassword) throws Exception {
        boolean retVal = false;
        if(currentPassword.equals(password)) {
            password = newPassword;
            //Update database
            String get_user_query = String.format("UPDATE `Users` SET `Password` = '%s', WHERE `User_ID` = '%d';", newPassword, getUserID());
            ConnectedDBConnection connection = new ConnectedDBConnection();
            int user_results = connection.update(get_user_query);
            if(user_results == 1) {
            	retVal = true;
            }
        	else {
        		throw new Exception("No changes were made to database.");
        	}

        }
        return retVal;
    }//End of updatePassword

    public boolean setEmail(String newEmail, String currentEmail) throws Exception {
        boolean retVal = false;
        if(currentEmail.equals(email)) {
            email = newEmail;
            //Update database
            String get_user_query = String.format("UPDATE `Users` SET `eMail` = '%s', WHERE 'User_ID' = '%d';", newEmail, getUserID());
            ConnectedDBConnection connection = new ConnectedDBConnection();
            int user_results = connection.update(get_user_query);
            if(user_results == 1) {
               	retVal = true;
            }
            else {
            	throw new Exception("No changes were made to database.");
            	}
        }
        return retVal;
    }//End of setEmail

}//End of Users
