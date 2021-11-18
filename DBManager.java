import java.lang.String;
import java.io.*;
import java.util.*;
import java.sql.*;

//Database manager for getting/editing employee and trip information--Zachary Sedlacek
public class DBManager {

    String employeeDataPath = "employeeData.txt", firstName, lastName;
    int id;

    //Check database for matching ID and password
    public Boolean checkLogin(String username, String password) throws IOException {
        /*HashMap<String, HashMap<String, String>> employeeInfoMap = getEmployeeInfoMap();
        //System.out.println(employeeInfoMap.get(id));

        if (employeeInfoMap.get(id) == null)
            return false;
        if (employeeInfoMap.get(id).get("password").equals(password))
            return true;
        else 
            return false;
            */
       
        String get_user_query = String.format("SELECT User_ID, First_Name, Last_Name FROM Users WHERE UserName = '%s';", username);
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet user_results = connection.select(get_user_query);
	   id = user_results.getInt("User_ID");	   	
	   firstName = user_result.getString("First_Name");
	   lastName = user_result.getString("Last_Name");
	   
	   if(!user_results.next()) {
            return false;
        }
        else 
        	return true;
    } 
    
    //Parse database file for employee information. Return hash map of employee information.
    public HashMap<String, HashMap<String, String>> getEmployeeInfoMap() throws IOException {
        HashMap<String, HashMap<String, String>> employeeInfoMap = new HashMap<String, HashMap<String, String>>();
        BufferedReader br = null;
        File file = null;
        try {
            file = new File(employeeDataPath);
        }
        catch (Exception e) {
            System.out.println("File issue");
        }
        br = new BufferedReader(new FileReader(file));
        String line = null, id, field, data;

        //Read from file. Format: ID \n field:data \n (\n before each new employee) end at end of file
        while ((line = br.readLine()) != null) {
            if (line.trim().toString().equals("end"))
                break;

            //For each employee, add data to employeeInfoMap
            id = line.trim().toString();
            HashMap<String, String> employeeData = new HashMap<String, String>();
             while ((line = br.readLine()) != null) {
                 if (line.trim().toString().equals(""))
                 break;
                 //Split the data lines by : 
                String[] parts = line.split(":");
                field = parts[0].trim();
                data = parts[1].trim();

               employeeData.put(field, data);
             }  
             employeeInfoMap.put(id, employeeData);
        }
        br.close();
        return employeeInfoMap;
    }

    //Add new employee information to database if new ID does not yet exist
    public Boolean addNewEmployee(String id, String password) throws IOException {
        HashMap<String, HashMap<String, String>> employeeInfoMap = getEmployeeInfoMap();
        if (employeeInfoMap.get(id) == null) {
            HashMap<String, String> dataMap = new HashMap<>();
            dataMap.put("password", password);
            employeeInfoMap.put(id, dataMap);
            updateEmployeeData(employeeInfoMap);
            return true;
        }

        else
            return false;
    }

    //Write new employee information to database file
    public void updateEmployeeData(HashMap<String, HashMap<String, String>> newMap) throws IOException{
        
        File file = null;
        BufferedWriter bf = null;
        try {
            file = new File(employeeDataPath);
            bf = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, HashMap<String, String>> entry : newMap.entrySet()) {
                bf.write(entry.getKey());
                bf.newLine();
                HashMap<String, String> newData = entry.getValue();
                for (Map.Entry<String, String> dataEntry : newData.entrySet()) {
                    bf.write(dataEntry.getKey() + ":" + dataEntry.getValue());
                    bf.newLine();
                }
                bf.newLine();
            }
            bf.write("end");
            bf.close();
        }
        catch (Exception e) {
            System.out.println("File issue");
        }

    }

}