import java.lang.String;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        LoginController lc = new LoginController();
        DBManager db = new DBManager();

        //when clicking the register button
        //lc.register(db);

        //when clicking login button
        lc.login(db);

        Profile profile = new Profile(lc.getID(), lc.getDescription(), lc.getTrips());

        //When clicking edit profile
        lc.editProfile(db, profile);
        
        
    }

}
