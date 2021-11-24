import java.sql.*;
import java.util.ArrayList;

public class Trip {
	//Variables
	protected String myDescription;
	protected int tripID;
	protected float setBudget;
	protected String start_Date;
	protected String end_date;
	protected String location;
	protected boolean completed;	//True = trip finished, false = trip pending
	protected int status;		//1 = accepted, 2 = rejected, 3 = pending
	protected int userID;
	protected int managerID;
	protected String note;
	protected float totalExpenses;



	//Constructor
	Trip(int userID) throws Exception {
		String get_user_query = String.format("SELECT * FROM `Trip` WHERE `User_ID` = %d AND `Completed` = %d;", userID, 0);
       	ConnectedDBConnection connection = new ConnectedDBConnection();
		ResultSet trip_results = connection.select(get_user_query);
	    
	    // Check that atleast 1 row is returned
    	if(!trip_results.next()) {
    		// throw new Exception("No user results found for trip ID");
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
		note = trip_results.getString("Note");
		totalExpenses = trip_results.getFloat("TotalExpenses");
    	
	}
	
	//Generating a singular trip tied to user, linked to fake manager
	Trip(int userID, int managerID) throws Exception {
		if(!newTrip(userID, managerID))	{
			throw new Exception("New trip was not created in overloaded constructor");
		}
	}
	


	
	//Methods

	public int getUserID() {
		return userID;
	}//End of getUserID

	public int getManagerID() {
		return managerID;
	}//End of getManagerID

	public String getDescription()	{
		return myDescription;
	}//End of getDescription
		
	public int getTripID()	{
		return tripID;
	}//End of getTripID
	
	public double getBudget()	{
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
	
	public String getNote() {
		return note;
	}//End of getNote

	public float getTotalExpenses()	{return totalExpenses;}//End of totalExpenses
	
	public boolean setDescription(String newDescription) throws Exception{
		boolean retVal = false;
		myDescription = newDescription;
		//Update database
        	String get_user_query = String.format("UPDATE `Trip` SET `MyDescription` = '%s' WHERE `Trip_ID` = '%d';", newDescription, getTripID());
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
        	String get_user_query = String.format("UPDATE `Trip` SET `Set_Budget` = '%f' WHERE `Trip_ID` = '%d';", newBudget, getTripID());
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
	
	public boolean setStartDate(String newDate) throws Exception{
		boolean retVal = false;
		start_Date = newDate;
		//Update database
		System.out.println(newDate);
        	String get_user_query = String.format("UPDATE `Trip` SET `Start_Date` = \"%s\" WHERE `Trip_ID` = '%d';", newDate, getTripID());
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
        	String get_user_query = String.format("UPDATE `Trip` SET `End_Time` = \"%s\" WHERE `Trip_ID` = '%d';", newDate, getTripID());
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
        	String get_user_query = String.format("UPDATE `Trip` SET `Location` = '%s' WHERE `Trip_ID` = '%d';", newLocation, getTripID());
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
		String get_user_query = String.format("UPDATE `Trip` SET `Status` = '%d' WHERE `Trip_ID` = '%d';", newStatus, getTripID());
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
	public boolean setManager(int newManager) throws Exception{
		boolean retVal = false;
		managerID = newManager;
		//Update database
		String get_user_query = String.format("UPDATE `Trip` SET `Manager_ID` = '%d' WHERE `Trip_ID` = '%d';", newManager, getTripID());
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
		int sqlStatus = 0;
		if(newCompStatus == true) {
			sqlStatus = 1;
		}
        	String get_user_query = String.format("UPDATE `Trip` SET `Completed` = '%d' WHERE `Trip_ID` = '%d';", sqlStatus, getTripID());
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

	public boolean setNote(String newNote) throws Exception{
		boolean retVal = false;
		note = newNote;
		//Update database
        	String get_user_query = String.format("UPDATE `Trip` SET `Note` = '%s' WHERE `Trip_ID` = '%d';", newNote, getTripID());
        	ConnectedDBConnection connection = new ConnectedDBConnection();
       		int user_results = connection.update(get_user_query);
        	if(user_results == 1) {
           	retVal = true;
        	}
        	else {
        	throw new Exception("No changes were made to database.");
        	}
		return retVal;
	}//End of setNote

	public boolean addExpense(float expense) throws Exception{
		boolean retVal = false;
		totalExpenses += expense;
		//Update database
		String get_user_query = String.format("UPDATE `Trip` SET `TotalExpenses` = '%f' WHERE `Trip_ID` = '%d';", getTotalExpenses(), getTripID());
		ConnectedDBConnection connection = new ConnectedDBConnection();
		int user_results = connection.update(get_user_query);
		if(user_results == 1) {
			retVal = true;
		}
		else {
			throw new Exception("No changes were made to database.");
		}
		return retVal;
	}//End of addExpense

	public boolean newTrip(int userID, int managerID)	throws Exception{
		try {
			String form =   "INSERT INTO `Trip` (User_ID, Manager_ID, Set_Budget, MyDescription, Start_Date, End_Time, Location, Completed, Status, Note, TotalExpenses)"
					+ "VALUES ('%d', '%d', '%f', '%s', '%s', '%s', '%s', '%d', '%d', '%s');";
			String add_trip_query =  String.format(form, userID, managerID, 0.00, "TBD", "00/00/0000", "00/00/0000", "TBD", 0, 2, "TBD", 0.0);

			ConnectedDBConnection connection = new ConnectedDBConnection();
			connection.insert(add_trip_query);

			String get_user_query = String.format("SELECT Trip_ID FROM `Trip` WHERE `User_ID` = %d; AND `Completed` = %d", userID, 0);
			ResultSet trip_results = connection.select(get_user_query);
			if(!trip_results.next())	{
				return false;
			}

			this.userID = userID;
			this.managerID = managerID;
			myDescription = "TBD";
			tripID = trip_results.getInt("Trip_ID");
			setBudget = (float) 0.00;
			start_Date = "00/00/0000";
			end_date = "00/00/0000";;
			location = "TBD";
			completed = false;
			status = 3;
			note = "TBD";
			totalExpenses = (float)0.0;
		}
		catch(Exception error) {
			System.out.println(error.toString());
		}
		return true;
	}//End of newTrip
}//End of Trip
