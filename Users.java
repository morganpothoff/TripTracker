import java.sql.*;


public class Users {
    //Variables
    protected String name;
    protected int userID;
    protected String password;
    protected String email;


    Users(int id) throws Exception {
        String get_user_query = String.format("SELECT * FROM `Users` WHERE `User_ID` = %d;", id);
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet user_results = connection.select(get_user_query);

        // Check that atleast 1 row is returned
        if(!user_results.next())
        {
            throw new Exception("No user results found for user ID");
        }

        userID = id;
        name = user_results.getString("First_Name") + " " + user_results.getString("Last_Name");
        password = user_results.getString("Password");
        email = user_results.getString("Email");
        
        //Generate Trip Object
        myTrip = new Trip(id)
    }


    Users(String email) throws Exception {
        String get_user_query = String.format("SELECT * FROM `Users` WHERE `Email` = '%s';", email);
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet user_results = connection.select(get_user_query);

        // Check that atleast 1 row is returned
        if(!user_results.next())
        {
            throw new Exception("No user results found for user ID");
        }

        email = email;
        name = user_results.getString("First_Name") + " " + user_results.getString("Last_Name");
        password = user_results.getString("Password");
        userID = user_results.getInt("User_ID");
    }


    //Methods
    public String getName()    {
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

    public boolean setPassword(String newPassword, String currentPassword) {
        boolean retVal = false;
        if(currentPassword.equals(password)) {
            password = newPassword;
            retVal = true;
        }
        return retVal;
    }//End of setPassword

    public boolean setEmail(String newEmail, String currentEmail) {
        boolean retVal = false;
        if(currentEmail.equals(email)) {
            email = newEmail;
            retVal = true;
        }
        return retVal;
    }//End of setEmail

    // Edited by: MorganPotttt-hofff

}//End of Users

