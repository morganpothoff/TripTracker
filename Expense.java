
/***********************************************************************************************************************
*                                                                                                                      *
*   created by: Morgan Pothoff                                                                                         *
*   on 2021.11.02                                                                                                      *
*                                                                                                                      *
*   DESCRIPTION: This file contains many getters and setters, and has the functionality to add, edit, and delete an    *
*    expense from the arraylist.                                                                                       *
*                                                                                                                      *
***********************************************************************************************************************/

import java.lang.System;
import java.util.ArrayList;
import java.sql.*;


public class Expense {
	private int Expense_ID;
	private String Company;
	private float Cost;
	private String Location;
	private String ExpenseName;
	private int Trip_ID;
	private int User_ID;


	Expense(int id) throws Exception {
        String get_expense_query = String.format("SELECT * FROM `Expenses` WHERE `Expense_ID` = %d;", id);
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet expense_results = connection.select(get_expense_query);

        // Check that atleast 1 row is returned
        if(!expense_results.next()) {
            throw new Exception("No user results found for user ID");
        }

        Expense_ID = id;
		Company = expense_results.getString("Company");
		Cost = expense_results.getFloat("Cost");
		Location = expense_results.getString("Location");
		ExpenseName = expense_results.getString("ExpenseName");
		Trip_ID = expense_results.getInt("Trip_ID");
		User_ID = expense_results.getInt("User_ID");
	}



	// ————————————————————————————————————————————————————— DB ————————————————————————————————————————————————————— //

	public static Boolean add(String company, double cost, String location, String name, int Trip_ID, int User_ID) {
		
		try {
			String form =	"INSERT INTO `Expenses` (`Company`, `Cost`, `Location`, `ExpenseName`, `Trip_ID`, `User_ID`) "
							+ "VALUES ('%s', %f, '%s', '%s', %d, %d);";
			String add_expense_query =	String.format(form, company, cost, location, name, Trip_ID, User_ID);

			ConnectedDBConnection connection = new ConnectedDBConnection();
			connection.insert(add_expense_query);

			return true;
		}
		catch(Exception error) {
			System.out.println(error.toString());
			return false;
		}
	}


	public static ArrayList<Expense> all_expenses_for_trip(int Trip_ID) throws Exception {
		ArrayList<Expense> expense_list = new ArrayList<Expense>();

		String form =	"SELECT `Expense_ID` FROM `Expenses` WHERE `Trip_ID` = %d;";
		String expense_query = String.format(form, Trip_ID);

		ConnectedDBConnection connection = new ConnectedDBConnection();
		ResultSet expenses = connection.select(expense_query);

		while(expenses.next()) {
			expense_list.add(new Expense(expenses.getInt("Expense_ID")));
		}

	return expense_list;
	}


	public Boolean edit(String company, float cost, String location, String name) {
		this.Company = company;
		this.Cost = cost;
		this.Location = location;
		this.ExpenseName = name;

		try {
			String form =	"UPDATE `Expenses` "
							+ "SET `Company` = '%s', `Cost` = %d, `Location` = '%s', `ExpenseName` = '%s' WHERE `Expense_ID` = %d;";
			String edit_expense_query = String.format(form, company, cost, location, name, Expense_ID);

			ConnectedDBConnection connection = new ConnectedDBConnection();
			connection.update(edit_expense_query);

			return true;
		}
		catch(Exception error) {
			System.out.println(error.toString());
			return false;
		}
	}


	public static Boolean edit(int id, String company, float cost, String location, String name) {
		try {
			String form =	"UPDATE `Expenses` "
							+ "SET `Company` = '%s', `Cost` = %d, `Location` = '%s', `ExpenseName` = '%s' WHERE `Expense_ID` = %d;";
			String edit_expense_query = String.format(form, company, cost, location, name, id);

			ConnectedDBConnection connection = new ConnectedDBConnection();
			connection.update(edit_expense_query);

			return true;
		}
		catch(Exception error) {
			System.out.println(error.toString());
			return false;
		}
	}


	public static Boolean delete(int id) {
		try {
			String form = "DELETE FROM `Expenses` WHERE `Expense_ID` = %d;";
			String delete_expense_query = String.format(form, id);

			ConnectedDBConnection connection = new ConnectedDBConnection();
			connection.update(delete_expense_query);

			return true;
		}
		catch(Exception error) {
			System.out.println(error.toString());
			return false;
		}
	}


	// —————————————————————————————————————————————————— GETTERS ——————————————————————————————————————————————————— //

	public int getExpense_ID() {
		return this.Expense_ID;
	}


	public String getCompany() {
		return this.Company;
	}


	public float getCost() {
		return this.Cost;
	}


	public String getLocation() {
		return this.Location;
	}


	public String getExpenseName() {
		return this.ExpenseName;
	}


	public int getTrip_ID() {
		return this.Trip_ID;
	}


	public int getUser_ID() {
		return this.User_ID;
	}


	// —————————————————————————————————————————————————— SETTERS ——————————————————————————————————————————————————— //

	public Boolean setCompany(String Company) {
		this.Company = Company;
		return this.edit(Company, Cost, Location, ExpenseName);
	}


	public Boolean setCost(float Cost) {
		this.Cost = Cost;
		return this.edit(Company, Cost, Location, ExpenseName);
	}


	public Boolean setLocation(String Location) {
		this.Location = Location;
		return this.edit(Company, Cost, Location, ExpenseName);
	}


	public Boolean setExpenseName(String ExpenseName) {
		this.ExpenseName = ExpenseName;
		return this.edit(Company, Cost, Location, ExpenseName);
	}


	// —————————————————————————————————————————————————— UTILITY ——————————————————————————————————————————————————— //

	public String toString() {
		String form = "Expense_ID: %d, Company: %s, Cost: %f, Location: %s, ExpenseName: %s, Trip_ID: %d, User_ID: %d";
		return String.format(form, Expense_ID, Company, Cost, Location, ExpenseName, Trip_ID, User_ID);
	}
}