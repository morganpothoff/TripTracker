import java.lang.String;
import java.io.*;
import java.util.*;

//Database manager for getting/editing employee and trip information--Zachary Sedlacek
public class DBManager {

    String employeeDataPath = "employeeData.txt";

    //Check database for matching ID and password
    public Boolean checkLogin(String id, String password) throws IOException {
        HashMap<String, HashMap<String, String>> employeeInfoMap = getEmployeeInfoMap();
        //System.out.println(employeeInfoMap.get(id));

        if (employeeInfoMap.get(id) == null)
            return false;
        if (employeeInfoMap.get(id).get("password").equals(password))
            return true;
        else 
            return false;
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