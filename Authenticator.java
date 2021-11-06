import java.lang.String;
import java.io.*;
import java.util.*;

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
