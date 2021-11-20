// Chris Hutcherson
import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class GUIController {
	private GUIView view;
	private Users user;


	public GUIController() {
		this.view = new GUIView("LoginPage");
		initController();
	}

	/**
	 * is executed during application startup
	 * assigns action listener too all GUI buttons
	 */
	public void initController() {
		view.getLoginFrame().getLoginButton().addActionListener(e -> {
			try {
				login();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});
		view.getLoginFrame().getRegisterButton().addActionListener(e -> register());
		view.getRegisterFrame().getReturnButton().addActionListener((e -> back()));
		view.getRegisterFrame().getRegisterButton().addActionListener((e -> {
			try {
				registerUser();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}));
		view.getLoginFrame().getExitButton().addActionListener((e -> exit()));
		view.getSelectManagerViewButton().addActionListener((e -> showManagerScreen()));
		view.getSelectEmployeeViewButton().addActionListener((e -> showEmployeeScreen()));
		view.getSelectLogoutButton().addActionListener((e -> logout()));
		view.getManagerFrame().getBackButton().addActionListener((e -> managerToSelection()));
		view.getManagerFrame().getAddButton().addActionListener((e -> addEmployee()));
		view.getEmployeeFrame().getProposalButton().addActionListener((e -> gotoProposalScreen()));
		view.getEmployeeFrame().getTripButton().addActionListener((e -> {
			try {
				gotoTripScreen();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}));
		view.getEmployeeFrame().getLogoutButton().addActionListener((e -> employeeLogout()));
		view.getProposalFrame().getBackButton().addActionListener((e -> proposalToEmployeeScreen()));
		view.getProposalFrame().getSubmitButton().addActionListener((e -> submitProposal()));
		view.getTripBackButton().addActionListener((e -> tripToEmployeeScreen()));
		view.getTripAddButton().addActionListener((e -> addItem()));
		view.getTripFinishButton().addActionListener((e -> finishTrip()));


	}


	/**
	 * when registration button is pressed
	 * checks if username already exists, if not
	 * register new user and password in database
	 * @throws IOException
	 */
	private void registerUser() throws IOException {
		//NOTE: how can we check something in the DB trying to put it in the DB? 
		if(view.getRegisterFrame().getIsManagerCheckBox().isSelected()){ // if manager check box is selected
			// todo make sure they are a manager in database
		}
		String UserName = view.getRegisterFrame().getIdTextField().getText();
		String pass = view.getRegisterFrame().getPasswordTextField().getText();
		String email = view.getRegisterFrame().getEmailTextField().getText();
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
				view.getRegisterFrame().getIdTextField().setText("Failed to add User to DB");
			}
		}
		catch(Exception e) {
			view.getRegisterFrame().getIdTextField().setText(e.toString());
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
			String tempInput = view.getTripItemTextField().getText();	  // get text and parse ex: "Item,StarBucks,Austin"
			String name = tempInput.substring(0, tempInput.indexOf(','));   // following lines parse the input string
			tempInput = tempInput.substring(tempInput.indexOf(',')+1);
			String company = tempInput.substring(0, tempInput.indexOf(','));
			tempInput = tempInput.substring(tempInput.indexOf(',')+1);
			double cost = Double.parseDouble(view.getTripCostTextField().getText());
			String location = tempInput;
			int Trip_ID = 1;  //TODO: once trip select is implemented, get trip
			int User_ID = user.getUserID();

			view.getTripExpenseModel().addElement(name + " " + company + " " + location + " " + cost);  //todo delete once add is working

			// Set data
			if(Expense.add(company, cost, location, name, Trip_ID, User_ID)) {
				System.out.println("Successfully added Expense");

				view.getTripExpenseModel().addElement(view.getTripItemTextField().getText() + ' ' + view.getTripCostTextField().getText());
				view.getTripItemTextField().setText("");
				view.getTripCostTextField().setText("");
				view.getTripExpenseModel().addElement(name + " " + company + " " + location + " " + cost);
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
		view.getTripFrame().setVisible(false);
		view.getEmployeeFrame().setVisible(true);
	}

	/**
	 * submit proposal details and switch screen back to menu
	 */
	private void submitProposal() {
		// todo actually submit the proposal

		proposalToEmployeeScreen();
	}


	private void proposalToEmployeeScreen() {
		view.getProposalFrame().setVisible(false);
		view.getEmployeeFrame().setVisible(true);
	}

	private void gotoTripScreen() throws IOException {
		//todo if no trip in progress, don't switch
		String name = view.getLoginFrame().getIdTextField().getText();
		//model.getEmployeeInfoMap().get(name);	   // todo get trip data and add all expenses to the trip list
		view.getEmployeeFrame().setVisible(false);
		view.getTripFrame().setVisible(true);
	}

	private void gotoProposalScreen() {
		view.getEmployeeFrame().getNoteLabel().setText("Note: Current trip in progress");
		view.getEmployeeFrame().setVisible(false);
		view.getProposalFrame().setVisible(true);

		// fill in the manager list

		view.getProposalFrame().getManagerList().addItem("hello");
		view.getProposalFrame().getManagerList().setSelectedIndex(0);

	}

	/**
	 * validates user input username and password against database
	 * create a user class for reference based on login details (if valid) and switches screen
	 * @throws IOException
	 */
	private void login() throws Exception {
		String id = view.getLoginFrame().getIdTextField().getText();
		String pass = view.getLoginFrame().getPasswordTextField().getText();

		try {
			Users loginUser = new Users(id);
			if(!loginUser.getPassword().equals(pass)) {
				throw new Exception("Invalid Password");
			}
	
			this.user = loginUser;
			System.out.println(String.format("User %s logged in", id));

			view.getLoginFrame().setVisible(false);
			view.getEmployeeFrame().setVisible(true);
		}
		catch(Exception e) {
			System.out.println(e.toString());
			view.getLoginFrame().getIdTextField().setText("invalid id or password");
		}
	}

	/**
	 * switch from login frame to register frame
	 */
	private void register() {

		view.getLoginFrame().setVisible(false);
		view.getRegisterFrame().setVisible(true);

	}

	/**
	 * switch from register frame to login frame
	 */
	private void back(){
		// clear registration textfields
		view.getRegisterFrame().getIdTextField().setText("");
		view.getRegisterFrame().getPasswordTextField().setText("");
		view.getRegisterFrame().getEmailTextField().setText("");

		view.getLoginFrame().setVisible(true);
		view.getRegisterFrame().setVisible(false);

	}

	/**
	 * switch from login frame to employee menu
	 */
	private void loginEmployee() {
		view.getEmployeeFrame().setVisible(true);
		view.getLoginFrame().setVisible(false);

	}

	/**
	 * switch from login frame to manager menu
	 */
	private void loginManager() {
		view.getManagerSelectionFrame().setVisible(true);
		view.getLoginFrame().setVisible(false);
	}

	/**
	 * switch from manger menu to employee view
	 */
	private void showEmployeeScreen() {
		view.getManagerSelectionFrame().setVisible(false);
		view.getEmployeeFrame().setVisible(true);
	}

	/**
	 * switch from manager menu to manager view
	 */
	private void showManagerScreen() {
		view.getManagerSelectionFrame().setVisible(false);
		view.getManagerFrame().setVisible(true);
		//TODO fill lists with appropriate names / info
		view.getManagerFrame().getEmployeesListModel().addElement("jerry man");

	}

	/**
	 * switch from manager view to manager menu
	 */
	private void managerToSelection() {
		view.getManagerSelectionFrame().setVisible(true);
		view.getManagerFrame().setVisible(false);
	}

	/**
	 * search for existing employee in database, if they are
	 * not tied to a manager, make the link to currently signed in manager
	 */
	private void addEmployee() {
		// TODO if employee does not exist within company && is not tied to a manager
		if(view.getManagerFrame().getNewNameTextField().getText().equals(""))
			view.getManagerFrame().getNewNameTextField().setText("Invalid name");
		else{
			view.getManagerFrame().getEmployeesListModel().addElement(view.getManagerFrame().getNewNameTextField().getText());
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
		// 	view.getManagerSelectionFrame().setVisible(true);
		// 	view.getEmployeeFrame().setVisible(false);
		// }
		// else{
			logout();
		// }

	}

	/**
	 * clear login screen and only show login screen
	 */
	private void logout() {
		view.getManagerSelectionFrame().setVisible(false);
		view.getEmployeeFrame().setVisible(false);

		// return to login screen and clear login text fields
		view.getLoginFrame().getIdTextField().setText("");
		view.getLoginFrame().getPasswordTextField().setText("");
		view.getLoginFrame().setVisible(true);
	}

	/**
	 * exit program
	 */
	private void exit() {
		System.exit(0);
	}


}