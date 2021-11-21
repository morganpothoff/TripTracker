import java.sql.*;

public class Trip {
	//Variables
	protected String myDescription;
	protected int tripID;
	protected float setBudget;
	protected String start_Date;
	protected String end_date;
	protected String location;
	protected boolean completed;	//True = trip finished, false = trip pending
	protected int status;		//1 = accepted, 2 = rejected, 3 = undetermined
	protected int userID;
	protected int managerID;



	//Constructor
	Trip(int userID) throws Exception {
		String get_user_query = String.format("SELECT * FROM `Trip` WHERE `User_ID` = %d;", userID);
       	ConnectedDBConnection connection = new ConnectedDBConnection();
		ResultSet trip_results = connection.select(get_user_query);
	    
	    // Check that atleast 1 row is returned
    	if(!trip_results.next()) {
    		throw new Exception("No user results found for trip ID");
    	}

		userID = trip_results.getInt("User_ID");
		managerID = trip_results.getInt("Manager_ID");
		tripID = trip_results.getInt("Trip_ID");
		myDescription = trip_results.getString("MyDescription");
		start_Date = trip_results.getString("Start_Date");
		end_date = trip_results.getString("End_Time");
		location = trip_results.getString("Location");
		setBudget = trip_results.getFloat("Set_Budget");
		completed = trip_results.getBoolean("Completed");
		status = trip_results.getInt("Status");
    	
	}
	Trip(int userID, int status) throws Exception {
		String get_user_query = String.format("SELECT * FROM `AllTrips` WHERE `User_ID` = %d;", userID);
		ConnectedDBConnection connection = new ConnectedDBConnection();
		ResultSet trip_results = connection.select(get_user_query);

		// Check that atleast 1 row is returned
		if(!trip_results.next()) {
			throw new Exception("No user results found for trip ID");
		}

		userID = trip_results.getInt("User_ID");
		managerID = trip_results.getInt("Manager_ID");
		tripID = trip_results.getInt("Trip_ID");
		myDescription = trip_results.getString("MyDescription");
		start_Date = trip_results.getString("Start_Date");
		end_date = trip_results.getString("End_Time");
		location = trip_results.getString("Location");
		setBudget = trip_results.getFloat("Set_Budget");
		completed = trip_results.getBoolean("Completed");
		this.status = trip_results.getInt(status);

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
	
	public boolean setDescription(String newDescription) throws Exception{
		boolean retVal = false;
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
		return retVal;
	}//End of setDescription
	
	public boolean setNewBudget(float newBudget) throws Exception{
		boolean retVal = false;
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
		return retVal;
	}//End of setNewBudget
	
	public boolean setStartDate(String newDate, String currentDate) throws Exception{
		boolean retVal = false;
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
		return retVal;
	}//End of setStartDate
	
	public boolean setEndDate(String newDate) throws Exception{
		boolean retVal = false;
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
		return retVal;
	}//End of setEndDate
	
	public boolean setLocation(String newLocation) throws Exception{
		boolean retVal = false;
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
		return retVal;
	}//End of setLocation
	
	public boolean setStatus(int newStatus) throws Exception{
		boolean retVal = false;
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
		return retVal;
	}//End of setStatus
	
	public boolean setCompletion(boolean newCompStatus) throws Exception{
		boolean retVal = false;
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
		return retVal;
	}//End of setCompletion
}//End of Trip
