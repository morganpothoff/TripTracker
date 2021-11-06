
/***********************************************************************************************************************
*                                                                                                                      *
*   created by: Morgan Pothoff                                                                                         *
*   on 2021.11.02                                                                                                      *
*                                                                                                                      *
*   DESCRIPTION: TEMPLATE                                                                                              *
*   BUGS:                                                                                                              *
*   FUTURE:                                                                                                            *
*                                                                                                                      *
***********************************************************************************************************************/

import java.lang.System;
import java.util.ArrayList;


public class Test {
	public static void main(String[] argv) {
		System.out.println("Hello World");

		// Test whether able to INSERT into DB using Expense.
		Expense.add("Chipotle", 2.00, "Fort Worth", "Burrito Time", 1, 1);

		try {
			ArrayList<Expense> expenses = Expense.all_expenses_for_trip(1);
			for(int i = 0; i < expenses.size(); i++) {
				System.out.println(expenses.get(i).toString());
			}
		}
		catch(Exception error) {
			System.out.println(error.toString());
		}


		// Test whether able to SELECT from DB using Users.
		try {
			Users mergan = new Users("Morgan.Pothoff@aol.com");
			System.out.println(mergan.getName());
		}
		catch(Exception error) {
			System.out.println(error.toString());
		}
	}
}