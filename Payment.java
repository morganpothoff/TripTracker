
/***********************************************************************************************************************
*                                                                                                                      *
*   created by: Morgan Pothoff                                                                                         *
*   on 2021.11.02                                                                                                      *
*                                                                                                                      *
*   DESCRIPTION: This file allows a user to add, edit, and delete a payment. It is not implemented, so functionality   *
*    beyond its current state is limited.                                                                              *
*                                                                                                                      *
***********************************************************************************************************************/

import java.lang.System;
import java.sql.*;


public class Payment {
	private int Payment_ID;
	private int account_number;
	private String payment_method;
	private int Expense_ID;
	private int User_ID;


	// TODO: Constructor
	Payment() {

	}


	// Adds Payment
	public static Boolean add(int account_number, String payment_method, int Expense_ID, int User_ID) {
		
		try {
			String form =	"INSERT INTO `Expenses` (`Account_number`, `Payment_method`, `Expense_ID`, `User_ID`) "
							+ "VALUES ('%s', %f, '%s', '%s', %d, %d);";
			String add_payment_query =	String.format(form, account_number, payment_method, Expense_ID, User_ID);

			ConnectedDBConnection connection = new ConnectedDBConnection();
			connection.insert(add_payment_query);

			return true;
		}
		catch(Exception error) {
			System.out.println(error.toString());
			return false;
		}
	}


	// Edits Payment and sets data to current data
	public Boolean edit(int account_number, String payment_method) {
		this.account_number = account_number;
		this.payment_method = payment_method;

		try {
			String form =	"UPDATE `Expenses` "
							+ "SET `Account_number` = '%d', `Payment_method` = %s WHERE `Payment_ID` = %d;";
			String edit_payment_query = String.format(form, account_number, payment_method, Payment_ID);

			ConnectedDBConnection connection = new ConnectedDBConnection();
			connection.update(edit_payment_query);

			return true;
		}
		catch(Exception error) {
			System.out.println(error.toString());
			return false;
		}
	}


	// Edits Payment
	public static Boolean edit(int id, int account_number, String payment_method) {
		try {
			String form =	"UPDATE `Expenses` "
							+ "SET `Account_number` = '%d', `Payment_method` = %s WHERE `Payment_ID` = %d;";
			String edit_payment_query = String.format(form, account_number, payment_method, id);

			ConnectedDBConnection connection = new ConnectedDBConnection();
			connection.update(edit_payment_query);

			return true;
		}
		catch(Exception error) {
			System.out.println(error.toString());
			return false;
		}
	}


	// Deletes Payment
	public static Boolean delete(int id) {
		try {
			String form = "DELETE FROM `Expenses` WHERE `Payment_ID` = %d;";
			String delete_payment_query = String.format(form, id);

			ConnectedDBConnection connection = new ConnectedDBConnection();
			connection.update(delete_payment_query);

			return true;
		}
		catch(Exception error) {
			System.out.println(error.toString());
			return false;
		}
	}
}
