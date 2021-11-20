import java.sql.*;

public class Trip {
	//Variables
	protected String myDescription;
	protected int tripID;
	protected float setBudget;
	protected String start_Date;
	protected String end_date;
	protected String location;
	protected boolean completed;	//True = trip finished, false = trip not completed
	protected int status;		//1 = accepted, 2 = rejected, 3 = undetermined
	
	
	//Constructor
	Trip(int userID) throws Exception {
		String get_user_query = String.format("SELECT * FROM `AllTrip` WHERE `User_ID` = %d;", userID);
       	ConnectedDBConnection connection = new ConnectedDBConnection();
		ResultSet trip_results = connection.select(get_user_query);
	    
	    // Check that atleast 1 row is returned
    	if(!trip_results.next()) {
    		throw new Exception("No user results found for trip ID");
    	}

    	tripID = trip_results.getInt("Trip_ID");
    	myDescription = trip_results.getString("First_Name");
    	start_Date = trip_results.getString("Last_Name");
    	end_date = trip_results.getString("Password");
    	location = trip_results.getString("Email");
    	setBudget = trip_results.getFloat("Set_Budget");
    	completed = trip_results.getBoolean("Completed");
    	status = trip_results.getInt("Status");
    	
	}
	
	//Methods
	public String getDescription()	{
		return myDescription;
	}//End of getDescription
		
	public int getTripID()	{
		return tripID;
	}//End of getTripID
	
	public float getBudget()	{
		return setBudget;
	}//End of getBudget
		
	public String getStartDate()	{
		return start_Date;
	}//End of getStartDate
		
	public String getEndDate()	{
		return end_date;
	}//End of getEndDate
		
	public String getLocation()	{
		return location;
	}//End of getLocation
	
	public int getStatus()	{
		return status;
	}//End of getStatus
	
	public Boolean getCompletion()	{
		return completed;
	}//End of getCompletion
	
	public boolean setDescription(String newDescription, String currentDescription) throws Exception{
		boolean retVal = false;
		if(myDescription == currentDescription) {
			myDescription = newDescription;
			//Update database
            String get_user_query = String.format("UPDATE `Trip` SET `Set_Budget` = '%s', WHERE 'Trip_ID' = '%d';", newDescription, getTripID());
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
	}//End of setDescription
	
	public boolean setNewBudget(float newBudget, float currentBudget) throws Exception{
		boolean retVal = false;
		if(setBudget == currentBudget) {
			setBudget = newBudget;
			//Update database
            String get_user_query = String.format("UPDATE `Trip` SET `Set_Budget` = '%f', WHERE 'Trip_ID' = '%d';", newBudget, getTripID());
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
	}//End of setNewBudget
	
	public boolean setStartDate(String newDate, String currentDate) throws Exception{
		boolean retVal = false;
		if(start_Date.equals(currentDate)) {
			start_Date = newDate;
			//Update database
            String get_user_query = String.format("UPDATE `Trip` SET `Start_Date` = '%s', WHERE 'Trip_ID' = '%d';", newDate, getTripID());
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
	}//End of setStartDate
	
	public boolean setEndDate(String newDate, String currentDate) throws Exception{
		boolean retVal = false;
		if(end_date.equals(currentDate)) {
			end_date = newDate;
			//Update database
            String get_user_query = String.format("UPDATE `Trip` SET `End_Time` = '%s', WHERE 'Trip_ID' = '%d';", newDate, getTripID());
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
	}//End of setEndDate
	
	public boolean setLocation(String newLocation, String currentLocation) throws Exception{
		boolean retVal = false;
		if(location.equals(currentLocation)) {
			location = newLocation;
			//Update database
            String get_user_query = String.format("UPDATE `Trip` SET `Location` = '%s', WHERE 'Trip_ID' = '%d';", newLocation, getTripID());
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
	}//End of setLocation
	
	public boolean setStatus(int newStatus, int currentStatus) throws Exception{
		boolean retVal = false;
		if(status == currentStatus) {
			status = newStatus;
			//Update database
            String get_user_query = String.format("UPDATE `Trip` SET `Status` = '%d', WHERE 'Trip_ID' = '%d';", newStatus, getTripID());
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
	}//End of setStatus
	
	public boolean setCompletion(boolean newCompStatus, boolean currentCompStatus) throws Exception{
		boolean retVal = false;
		if(completed == currentCompStatus) {
			completed = newCompStatus;
			//Update database
            String get_user_query = String.format("UPDATE `Users` SET `Completed` = '%d', WHERE 'Trip_ID' = '%d';", newCompStatus, getTripID());
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
	}//End of setCompletion
}//End of Trip
