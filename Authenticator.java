import java.lang.String;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

//Authenticates login with user-entered ID and password--Zachary Sedlacek
public class Authenticator {

    String id, password;
    String[] invalidChars = {};
    int maxLength = 30, minLength = 6;
    boolean valid;
    
    Pattern lowercaseLetter = Pattern.compile("[a-z]");
    Pattern uppercaseLetter = Pattern.compile("[A-Z]");
    Pattern digit = Pattern.compile("[0-9]");
    Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
    Matcher hasLowercase, hasUppercase, hasDigit, hasSpecial;
    
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
    	   
        hasLowercase = lowercaseLetter.matcher(password);
        hasUppercase = uppercaseLetter.matcher(password);
        hasDigit = digit.matcher(password);
        hasSpecial = special.matcher(password);
        
        if (!hasLowercase.find() || || !hasUppercase.find() || !hasDigit.find() || !hasSpecial.find()) {
        valid = false;
        }

    	   return;
    }
}
