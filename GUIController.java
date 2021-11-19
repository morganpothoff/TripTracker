// Chris Hutcherson
import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class GUIController {
    private GUIModel model;
    private GUIView view;
    public GUIController(GUIModel m, GUIView v) {
        model = m;
        view = v;
    }

    /**
     * is executed during application startup
     * assigns action listener too all GUI buttons
     */
    public void initController() {
        view.getLoginButton().addActionListener(e -> {
            try {
                login();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        view.getRegisterButton().addActionListener(e -> register());
        view.getRegisterReturnButton().addActionListener((e -> back()));
        view.getRegisterRegisterButton().addActionListener((e -> {
            try {
                registerUser();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }));
        view.getExitButton().addActionListener((e -> exit()));
        view.getSelectManagerViewButton().addActionListener((e -> showManagerScreen()));
        view.getSelectEmployeeViewButton().addActionListener((e -> showEmployeeScreen()));
        view.getSelectLogoutButton().addActionListener((e -> logout()));
        view.getManagerBackButton().addActionListener((e -> managerToSelection()));
        view.getManagerAddButton().addActionListener((e -> addEmployee()));
        view.getEmployeeProposalButton().addActionListener((e -> gotoProposalScreen()));
        view.getEmployeeTripButton().addActionListener((e -> {
            try {
                gotoTripScreen();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }));
        view.getEmployeeLogoutButton().addActionListener((e -> employeeLogout()));
        view.getProposalBackButton().addActionListener((e -> proposalToEmployeeScreen()));
        view.getProposalSubmitButton().addActionListener((e -> submitProposal()));
        view.getTripBackButton().addActionListener((e -> tripToEmployeeScreen()));
        view.getTripAddButton().addActionListener((e -> addItem()));
        view.getTripFinishButton().addActionListener((e -> finishTrip()));


    }


    /**
     * gets user input from text fields and passes to the login controller
     */
    private void getLoginData() {

        String id = view.getIdTextField().getText();
        String pass = view.getPasswordTextField().getText();
        model.getLoginController().getUserInput(id, pass);
    }


    /**
     * when registration button is pressed
     * checks if username already exists, if not
     * register new user and password in database
     * @throws IOException
     */
    private void registerUser() throws IOException {
        //NOTE: how can we check something in the DB trying to put it in the DB? 
        if(view.getIsManagerCheckBox().isSelected()){ // if manager check box is selected
            // todo make sure they are a manager in database
        }
        String UserName = view.getRegisterIdTextField().getText();
        String pass = view.getRegisterPasswordTextField().getText();
        String email = view.getRegisterEmailTextField().getText();
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
                view.getRegisterIdTextField().setText("Failed to add User to DB");
            }
        }
        catch(Exception e) {
            view.getRegisterIdTextField().setText(e.toString());
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
            String tempInput = view.getTripItemTextField().getText();       // get text and parse ex: "Item,StarBucks,Austin"
            String name = tempInput.substring(0, tempInput.indexOf(','));   // following lines parse the input string
            tempInput = tempInput.substring(tempInput.indexOf(',')+1);
            String company = tempInput.substring(0, tempInput.indexOf(','));
            tempInput = tempInput.substring(tempInput.indexOf(',')+1);
            double cost = Double.parseDouble(view.getTripCostTextField().getText());
            String location = tempInput;
            int Trip_ID = 1;  //TODO: once trip select is implemented, get trip
            String User_ID = model.getLoginController().getID();

            view.getTripExpenseModel().addElement(name + " " + company + " " + location + " " + cost);  //todo delete once add is working

            // Set data
            if(Expense.add(company, cost, location, name, Trip_ID, new Users(User_ID).getUserID())) {
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
        view.getEmployeeScreenFrame().setVisible(true);
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
        view.getEmployeeScreenFrame().setVisible(true);
    }

    private void gotoTripScreen() throws IOException {
        //todo if no trip in progress, don't switch
        String name = view.getIdTextField().getText();
        //model.getEmployeeInfoMap().get(name);                   // todo get trip data and add all expenses to the trip list
        view.getEmployeeScreenFrame().setVisible(false);
        view.getTripFrame().setVisible(true);
    }

    private void gotoProposalScreen() {
        view.getEmployeeNoteLabel().setText("Note: Current trip in progress");
        view.getEmployeeScreenFrame().setVisible(false);
        view.getProposalFrame().setVisible(true);

        // fill in the manager list

        view.getProposalManagerList().addItem("hello");
        view.getProposalManagerList().setSelectedIndex(0);

    }

    /**
     * validates user input username and password against database
     * create a user class for reference based on login details (if valid) and switches screen
     * @throws IOException
     */
    private void login() throws Exception {
        String id = view.getIdTextField().getText();
        String pass = view.getPasswordTextField().getText();

        try {
            Users loginUser = new Users(id);
            if(!loginUser.getPassword().equals(pass)) {
                throw new Exception("Invalid Password");
            }
    
            model.setUser(loginUser);
            System.out.println(String.format("User %s logged in", id));

            //TODO: go to next page
        }
        catch(Exception e) {
            System.out.println(e.toString());
            view.getIdTextField().setText("invalid id or password");
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
        view.getRegisterIdTextField().setText("");
        view.getRegisterPasswordTextField().setText("");
        view.getRegisterEmailTextField().setText("");

        view.getLoginFrame().setVisible(true);
        view.getRegisterFrame().setVisible(false);

    }

    /**
     * switch from login frame to employee menu
     */
    private void loginEmployee() {
        view.getEmployeeScreenFrame().setVisible(true);
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
        view.getEmployeeScreenFrame().setVisible(true);
    }

    /**
     * switch from manager menu to manager view
     */
    private void showManagerScreen() {
        view.getManagerSelectionFrame().setVisible(false);
        view.getManagerScreenFrame().setVisible(true);
        //TODO fill lists with appropriate names / info
        view.getEmployeesListModel().addElement("jerry man");

    }

    /**
     * switch from manager view to manager menu
     */
    private void managerToSelection() {
        view.getManagerSelectionFrame().setVisible(true);
        view.getManagerScreenFrame().setVisible(false);
    }

    /**
     * search for existing employee in database, if they are
     * not tied to a manager, make the link to currently signed in manager
     */
    private void addEmployee() {
        // TODO if employee does not exist within company && is not tied to a manager
        if(view.getNewNameTextField().getText().equals(""))
            view.getNewNameTextField().setText("Invalid name");
        else{
            view.getEmployeesListModel().addElement(view.getNewNameTextField().getText());
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
        if(model.isManager()){
            view.getManagerSelectionFrame().setVisible(true);
            view.getEmployeeScreenFrame().setVisible(false);
        }
        else{
            logout();
        }

    }

    /**
     * clear login screen and only show login screen
     */
    private void logout() {
        view.getManagerSelectionFrame().setVisible(false);
        view.getEmployeeScreenFrame().setVisible(false);

        // return to login screen and clear login text fields
        view.getIdTextField().setText("");
        view.getPasswordTextField().setText("");
        view.getLoginFrame().setVisible(true);
    }

    /**
     * exit program
     */
    private void exit() {
        System.exit(0);
    }


}
