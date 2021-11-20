// Chris Hutcherson
import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class GUI {
	private LoginFrame loginFrame;
	private EmployeeFrame employeeFrame;
	private ManagerFrame managerFrame;
	private ManagerSelectionFrame managerSelectionFrame;
	private ProposalFrame proposalFrame;
	private RegisterFrame registerFrame;
	private TripFrame tripFrame;

	// DATA
	private Users user;


	public GUI() {
		loginFrame = new LoginFrame();
		employeeFrame = new EmployeeFrame();
		managerFrame = new ManagerFrame();
		managerSelectionFrame = new ManagerSelectionFrame();
		proposalFrame = new ProposalFrame();
		registerFrame = new RegisterFrame();
		tripFrame = new TripFrame();

		initialize_callbacks();
	}


	// ———————————————————————————————————————————————— INITIALIZERS ———————————————————————————————————————————————— //

	/**
	 * is executed during application startup
	 * assigns action listener too all GUI buttons
	 */
	public void initialize_callbacks() {
		initialize_employee_callbacks();
		initialize_login_callbacks();
		initialize_manager_callbacks();
		initialize_managerSelection_callbacks();
		initialize_proposal_callbacks();
		initialize_register_callbacks();
		initialize_trip_callbacks();
	}


	private void initialize_employee_callbacks()
	{
		employeeFrame.getProposalButton().addActionListener((e -> gotoProposalScreen()));
		employeeFrame.getLogoutButton().addActionListener((e -> employeeLogout()));
		employeeFrame.getTripButton().addActionListener((e -> {
			try {
				gotoTripScreen();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}));
	}


	private void initialize_login_callbacks()
	{
		loginFrame.getRegisterButton().addActionListener(e -> register());
		loginFrame.getExitButton().addActionListener((e -> exit()));
		loginFrame.getLoginButton().addActionListener(e ->
			{
				try {
					login();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		);
	}


	private void initialize_manager_callbacks()
	{
		managerFrame.getBackButton().addActionListener((e -> managerToSelection()));
		managerFrame.getAddButton().addActionListener((e -> addEmployee()));
	}


	private void initialize_managerSelection_callbacks()
	{
		managerSelectionFrame.getManagerViewButton().addActionListener((e -> showManagerScreen()));
		managerSelectionFrame.getEmployeeViewButton().addActionListener((e -> showEmployeeScreen()));
		managerSelectionFrame.getLogoutButton().addActionListener((e -> logout()));
	}


	private void initialize_register_callbacks()
	{
		registerFrame.getReturnButton().addActionListener((e -> back()));
		registerFrame.getRegisterButton().addActionListener((e -> {
			try {
				registerUser();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}));
	}


	private void initialize_proposal_callbacks()
	{
		proposalFrame.getBackButton().addActionListener((e -> proposalToEmployeeScreen()));
		proposalFrame.getSubmitButton().addActionListener((e -> submitProposal()));
	}


	private void initialize_trip_callbacks()
	{
		tripFrame.getBackButton().addActionListener((e -> tripToEmployeeScreen()));
		tripFrame.getAddButton().addActionListener((e -> addItem()));
		tripFrame.getFinishButton().addActionListener((e -> finishTrip()));
	}


	// ————————————————————————————————————————————————— CALLBACKS ————————————————————————————————————————————————— //

	/**
	 * when registration button is pressed
	 * checks if username already exists, if not
	 * register new user and password in database
	 * @throws IOException
	 */
	private void registerUser() throws IOException {
		//NOTE: how can we check something in the DB trying to put it in the DB? 
		if(registerFrame.getIsManagerCheckBox().isSelected()){ // if manager check box is selected
			// todo make sure they are a manager in database
		}
		String UserName = registerFrame.getIdTextField().getText();
		String pass = registerFrame.getPasswordTextField().getText();
		String email = registerFrame.getEmailTextField().getText();
		//TODO: add field for First and Last names
		String First_Name = "Test";
		String Last_Name = "User";

		try {
			if(Users.username_exists(UserName)) {
				throw new Exception("Invalid ID - already exists");
			}

			if(Users.add(First_Name, Last_Name, UserName, pass, email)) {
				back();
			}
			else {
				registerFrame.getIdTextField().setText("Failed to add User to DB");
			}
		}
		catch(Exception e) {
			registerFrame.getIdTextField().setText(e.toString());
		}
	}

	private void finishTrip() {
		// todo archive trip details
		tripToEmployeeScreen();
	}

	/**
	 * add a new item to the expense list based on user inputs
	 */
	private void addItem() {
		try {
			// Get data
			String tempInput = tripFrame.getItemTextField().getText();	  // get text and parse ex: "Item,StarBucks,Austin"
			String name = tempInput.substring(0, tempInput.indexOf(','));   // following lines parse the input string
			tempInput = tempInput.substring(tempInput.indexOf(',')+1);
			String company = tempInput.substring(0, tempInput.indexOf(','));
			tempInput = tempInput.substring(tempInput.indexOf(',')+1);
			double cost = Double.parseDouble(tripFrame.getCostTextField().getText());
			String location = tempInput;
			int Trip_ID = 1;  //TODO: once trip select is implemented, get trip
			int User_ID = user.getUserID();

			tripFrame.getExpenseModel().addElement(name + " " + company + " " + location + " " + cost);  //todo delete once add is working

			// Set data
			if(Expense.add(company, cost, location, name, Trip_ID, User_ID)) {
				System.out.println("Successfully added Expense");

				tripFrame.getExpenseModel().addElement(tripFrame.getItemTextField().getText() + ' ' + tripFrame.getCostTextField().getText());
				tripFrame.getItemTextField().setText("");
				tripFrame.getCostTextField().setText("");
				tripFrame.getExpenseModel().addElement(name + " " + company + " " + location + " " + cost);
			}
			else {
				System.out.println("Try again");
			}
		}
		catch(Exception error) {
			System.out.println(error.toString());
		}
	}

	/**
	 * switch frames from trip manager to employee main menu
	 */
	private void tripToEmployeeScreen() {
		tripFrame.setVisible(false);
		employeeFrame.setVisible(true);
	}

	/**
	 * submit proposal details and switch screen back to menu
	 */
	private void submitProposal() {
		// todo actually submit the proposal
		proposalToEmployeeScreen();
	}


	private void proposalToEmployeeScreen() {
		proposalFrame.setVisible(false);
		employeeFrame.setVisible(true);
	}

	private void gotoTripScreen() throws IOException {
		//todo if no trip in progress, don't switch
		String name = loginFrame.getIdTextField().getText();
		//model.getEmployeeInfoMap().get(name);	   // todo get trip data and add all expenses to the trip list
		employeeFrame.setVisible(false);
		tripFrame.setVisible(true);
	}

	private void gotoProposalScreen() {
		employeeFrame.getNoteLabel().setText("Note: Current trip in progress");
		employeeFrame.setVisible(false);
		proposalFrame.setVisible(true);

		// fill in the manager list

		proposalFrame.getManagerList().addItem("hello");
		proposalFrame.getManagerList().setSelectedIndex(0);
	}

	/**
	 * validates user input username and password against database
	 * create a user class for reference based on login details (if valid) and switches screen
	 * @throws IOException
	 */
	private void login() throws Exception {
		String id = loginFrame.getIdTextField().getText();
		String pass = loginFrame.getPasswordTextField().getText();

		try {
			Users loginUser = new Users(id);
			if(!loginUser.getPassword().equals(pass)) {
				throw new Exception("Invalid Password");
			}
	
			this.user = loginUser;
			System.out.println(String.format("User %s logged in", id));

			loginFrame.setVisible(false);
			employeeFrame.setVisible(true);
		}
		catch(Exception e) {
			System.out.println(e.toString());
			loginFrame.getIdTextField().setText("invalid id or password");
		}
	}

	/**
	 * switch from login frame to register frame
	 */
	private void register() {
		loginFrame.setVisible(false);
		registerFrame.setVisible(true);
	}

	/**
	 * switch from register frame to login frame
	 */
	private void back(){
		// clear registration textfields
		registerFrame.getIdTextField().setText("");
		registerFrame.getPasswordTextField().setText("");
		registerFrame.getEmailTextField().setText("");

		loginFrame.setVisible(true);
		registerFrame.setVisible(false);
	}

	/**
	 * switch from login frame to employee menu
	 */
	private void loginEmployee() {
		employeeFrame.setVisible(true);
		loginFrame.setVisible(false);
	}

	/**
	 * switch from login frame to manager menu
	 */
	private void loginManager() {
		managerSelectionFrame.setVisible(true);
		loginFrame.setVisible(false);
	}

	/**
	 * switch from manger menu to employee view
	 */
	private void showEmployeeScreen() {
		managerSelectionFrame.setVisible(false);
		employeeFrame.setVisible(true);
	}

	/**
	 * switch from manager menu to manager view
	 */
	private void showManagerScreen() {
		managerSelectionFrame.setVisible(false);
		managerFrame.setVisible(true);
		//TODO fill lists with appropriate names / info
		managerFrame.getEmployeesListModel().addElement("jerry man");
	}

	/**
	 * switch from manager view to manager menu
	 */
	private void managerToSelection() {
		managerSelectionFrame.setVisible(true);
		managerFrame.setVisible(false);
	}

	/**
	 * search for existing employee in database, if they are
	 * not tied to a manager, make the link to currently signed in manager
	 */
	private void addEmployee() {
		// TODO if employee does not exist within company && is not tied to a manager
		if(managerFrame.getNewNameTextField().getText().equals(""))
			managerFrame.getNewNameTextField().setText("Invalid name");
		else{
			managerFrame.getEmployeesListModel().addElement(managerFrame.getNewNameTextField().getText());
			//TODO go to model to link employee to manager
		}
	}

	/**
	 * switch from employee screen to login, and delete currently signed in user
	 * if manager, only go back to manager menu
	 */
	private void employeeLogout() {
		// if is a manager
		// return to selection screen
		// if(model.isManager()){
		// 	managerSelectionFrame.setVisible(true);
		// 	employeeFrame.setVisible(false);
		// }
		// else{
			logout();
		// }
		}

	/**
	 * clear login screen and only show login screen
	 */
	private void logout() {
		managerSelectionFrame.setVisible(false);
		employeeFrame.setVisible(false);

		// return to login screen and clear login text fields
		loginFrame.getIdTextField().setText("");
		loginFrame.getPasswordTextField().setText("");
		loginFrame.setVisible(true);
	}

	/**
	 * exit program
	 */
	private void exit() {
		System.exit(0);
	}


}
