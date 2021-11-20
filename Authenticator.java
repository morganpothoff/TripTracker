import java.lang.String;
import java.io.*;
import java.util.*;

//Authenticates login with user-entered ID and password--Zachary Sedlacek
public class Authenticator {

    String id, password;
    String[] invalidChars = {};
    int maxLength = 30, minLength = 6;
    boolean valid;
    
    public Authenticator(String inputID, String inputPassword) {
        id = inputID;
        password = inputPassword;
    }

    public boolean authenticate(DBManager db) throws Exception {
    	   valid = true;
    	   
	   checkValidInput();
	       	   
    	   if (valid == true){
    	   	 if (!db.checkLogin(id, password)) {
    	   		valid = false;
    	  	 }
    	   }
    	   
        return valid;
    }
    
    public void checkValidInput() {
    	   for (String cha : invalidChars) {
    	   	if (id.contains(cha)){
    	   		valid = false;
    	   	}
    	   }
    	   
    	   if ((id.length() > maxLength) || (password.length() > maxLength) || (id.length() < minLength) || (password.length() < minLength)){
    	   	valid = false;
    	   }
    	   return;
    }
}
