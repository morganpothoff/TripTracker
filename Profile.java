import java.lang.String;
import java.io.*;
import java.util.*;

public class Profile {
    String id, description, trips;

    public Profile(String inputID, String descriptionIn, String tripsIn) throws IOException{
        id = inputID;
        description = descriptionIn;
        trips = tripsIn;

    }

    //edit fields that user entered input for
    public void edit(String descriptionIn, String tripsIn) throws IOException{
        if (!(descriptionIn.isEmpty()))
            description = descriptionIn;
        if (!(tripsIn.isEmpty()))
            trips = tripsIn;
        return;
    }

    //update database based on new info
    public void update(DBManager db) throws IOException {
        HashMap<String, HashMap<String, String>> currentMap = db.getEmployeeInfoMap();
        HashMap<String, String> currentDataMap = currentMap.get(id);
        currentDataMap.put("description", description);
        currentDataMap.put("trips", trips);
        currentMap.put(id, currentDataMap);
        db.updateEmployeeData(currentMap);
        return;
    }

}