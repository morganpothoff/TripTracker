import java.sql.*;

public class Trip {
	//Variables
	protected String myDescription;
	protected int tripID;
	protected float setBudget;
	protected String start_Date;
	protected String end_date;
	protected String location;
	
	//Constructor
	Trip(int userID) throws Exception {
		Trip(int userID) throws Exception {
		String get_user_query = String.format("SELECT * FROM `AllTrip` WHERE `User_ID` = %d;", userID);
		//String get_user_query = String.format("SELECT * FROM `Users` WHERE `User_ID` = %d;", userID);
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

	}
	
	//Methods
	public String getDescription()	{
		return myDescription;
	}//End of getDescription
		
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
	
	public boolean setDescription(String newDescription, String currentDescription) {
		boolean retVal = false;
		if(myDescription == currentDescription) {
			myDescription = newDescription;
			retVal = true;
		}
		return retVal;
	}//End of setDescription
	
	public boolean setNewBudget(float newBudget, float currentBudget) {
		boolean retVal = false;
		if(setBudget == currentBudget) {
			setBudget = newBudget;
			retVal = true;
		}
		return retVal;
	}//End of setNewBudget
	
	public boolean setStartDate(String newDate, String currentDate) {
		boolean retVal = false;
		if(start_Date.equals(currentDate)) {
			start_Date = newDate;
			retVal = true;
		}
		return retVal;
	}//End of setStartDate
	
	public boolean setEndDate(String newDate, String currentDate) {
		boolean retVal = false;
		if(end_date.equals(currentDate)) {
			end_date = newDate;
			retVal = true;
		}
		return retVal;
	}//End of setEndDate
	
	public boolean setLocation(String newLocation, String currentLocation) {
		boolean retVal = false;
		if(location.equals(currentLocation)) {
			location = newLocation;
			retVal = true;
		}
		return retVal;
	}//End of setLocation
}//End of Trip
