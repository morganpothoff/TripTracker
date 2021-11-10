import java.lang.String;
import java.io.*;
import java.util.*;

//Authenticates login with user-entered ID and password--Zachary Sedlacek
public class Authenticator {

    String id, password;
    
    public Authenticator(String inputID, String inputPassword) {
        id = inputID;
        password = inputPassword;
    }

    public boolean authenticate(DBManager db) throws IOException {
        return db.checkLogin(id, password);
    }
}
