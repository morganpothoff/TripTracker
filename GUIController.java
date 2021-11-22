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
        view.getSelectManagerViewButton().addActionListener((e -> {
            try {
                showManagerScreen();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));
        view.getSelectEmployeeViewButton().addActionListener((e -> showEmployeeScreen()));
        view.getSelectLogoutButton().addActionListener((e -> logout()));
        view.getManagerBackButton().addActionListener((e -> managerToSelection()));
        view.getEmployeeProposalButton().addActionListener((e -> {
            try {
                gotoProposalScreen();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));
        view.getEmployeeTripButton().addActionListener((e -> {
            try {
                gotoTripScreen();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }));
        view.getEmployeeLogoutButton().addActionListener((e -> employeeLogout()));
        view.getProposalBackButton().addActionListener((e -> proposalToEmployeeScreen()));
        view.getProposalSubmitButton().addActionListener((e -> {
            try {
                submitProposal();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));
        view.getTripBackButton().addActionListener((e -> tripToEmployeeScreen()));
        view.getTripAddButton().addActionListener((e -> addItem()));
        view.getTripFinishButton().addActionListener((e -> finishTrip()));
        view.getReviewBackButton().addActionListener((e -> reviewBack()));
        //view.getReviewSendButton().addActionListener((e -> reviewSend()));// todo update this and view
        view.getManagerSelectProposalButton().addActionListener((e -> {
            try {
                selectProposal();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));


    }

    private void selectProposal() throws Exception {
        String selected = view.getManagerPendingList().getSelectedValue();
        if(selected.equals("")){ // if nothing selected
           return;
        }

        view.getManagerScreenFrame().setVisible(false);
        view.getReviewFrame().setVisible(true);

        // fill in the text area with info from selected employee and trip
        int tripID = Integer.parseInt(selected.substring(selected.indexOf(' ')+1));
        int userID = Integer.parseInt(selected.substring(0, selected.indexOf(' ')));

        String get_user_query = String.format("SELECT `First_Name`, `Last_Name` FROM `Users` WHERE `User_ID` = '%d';", userID);
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet user_results = connection.select(get_user_query);
        user_results.next();
        String fname = user_results.getString("First_Name");
        String lname = user_results.getString("Last_Name");

        get_user_query = String.format("SELECT `Location`, `Start_Date`, `End_Date`, `Set_Budget`, `Description` FROM `Trip` WHERE `Trip_ID` = '%d';", tripID);
        connection = new ConnectedDBConnection();
        user_results = connection.select(get_user_query);
        user_results.next();

        String location = user_results.getString("Location");
        String sdate = user_results.getString("Start_Date");
        String edate = user_results.getString("End_Date");
        String bud = user_results.getString("Set_Budget");
        String desc = user_results.getString("Description");

        String buffer = String.format("Employee: %s %s\nLocation: %d\nTravel Date: %s - %s\nEst. Expenses: $%d\nDescription: %s",
                fname, lname, location, sdate, edate, bud, desc);
        view.getReviewTextArea().setText(buffer);

    }

    private void reviewApprove() {
        // todo

    }
    private void reviewReject() {
        // todo

    }

    private void reviewBack() {
        // todo

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

        String UserName = view.getRegisterIdTextField().getText();
        String pass = view.getRegisterPasswordTextField().getText();
        String email = view.getRegisterEmailTextField().getText();
        String First_Name = view.getRegisterFirstTextField().getText();
        String Last_Name = view.getRegisterLastTextField().getText();

        Authenticator auth = new Authenticator(UserName, pass);
        try {
            boolean validChecker = auth.checkValidInput();
            System.out.println(validChecker);      
              
            if(Users.username_exists(UserName)) {
                throw new Exception("Invalid ID - already exists");
            }
            else if(view.getIsManagerCheckBox().isSelected() && Users.addManager(First_Name, Last_Name, UserName, pass, email)) {
                String get_user_query = String.format("SELECT `User_ID` FROM `Users` WHERE `UserName` = '%s';", UserName);
                ConnectedDBConnection connection = new ConnectedDBConnection();
                ResultSet user_results = connection.select(get_user_query);
                user_results.next();
                int id = user_results.getInt("User_ID");
                new Trip(id,1);
                back();
            }
            else if(Users.add(First_Name, Last_Name, UserName, pass, email)){
                String get_user_query = String.format("SELECT `User_ID` FROM `Users` WHERE `UserName` = '%s';", UserName);
                ConnectedDBConnection connection = new ConnectedDBConnection();
                ResultSet user_results = connection.select(get_user_query);
                user_results.next();
                int id = user_results.getInt("User_ID");
                new Trip(id,1);
                back();
            }
            else {
                view.getRegisterIdTextField().setText("Failed to add User to DB");
            }
        }
        catch(Exception e) {
            view.getRegisterIdTextField().setText("Invalid ID - already exists");
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
            double cost;
            try{
                cost = Double.parseDouble(view.getTripCostTextField().getText());
            }
            catch(NumberFormatException e){
                return;
            }
            String tempInput = view.getTripItemTextField().getText();       // get text and parse ex: "Item,StarBucks,Austin"
            String[] tokens = tempInput.split(",");
            if(tokens.length != 3)
                return;

            String name = tokens[0];   // following lines parse the input string
            String company = tokens[1];
            String location = tokens[2];
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
    private void submitProposal() throws Exception {
        // todo actually submit the proposal
        String location = view.getProposalLocationTextField().getText();
        String sDate = view.getStartDateTextField().getText();
        String eDate = view.getEndDateTextField().getText();
        float budget = 0;
        try {budget = Float.parseFloat(view.getProposalEstimateTextField().getText());}
        catch (NumberFormatException e){view.getProposalEstimateTextField().setText("please enter a double value");}
        String desc = view.getProposalDescriptionTextField().getText();

        // todo make a trip to reference
        model.getCurrTrip().setNewBudget(budget);
        model.getCurrTrip().setLocation(location);
        model.getCurrTrip().setStartDate(sDate);
        model.getCurrTrip().setEndDate(eDate);
        model.getCurrTrip().setDescription(desc);
        model.getCurrTrip().setStatus(3);

        String temp = view.getProposalManagerList().getSelectedItem().toString();
        int managerID = Integer.parseInt(temp.substring(temp.indexOf(',')+1));
        model.getCurrTrip().setManager(managerID);

        proposalToEmployeeScreen();
    }


    private void proposalToEmployeeScreen() {
        view.getProposalFrame().setVisible(false);
        view.getEmployeeScreenFrame().setVisible(true);
    }

    private void gotoTripScreen() throws IOException {
        // if no trip in progress, don't switch
        if(model.getCurrTrip().getStatus() != 1){
            return;
        }

        // todo get trip data and add all expenses to the trip list


        view.getEmployeeScreenFrame().setVisible(false);
        view.getTripFrame().setVisible(true);
    }

    private void gotoProposalScreen() throws Exception {
        view.getProposalManagerList().removeAllItems();

        view.getEmployeeScreenFrame().setVisible(false);
        view.getProposalFrame().setVisible(true);

        // fill in the manager list
        String get_user_query = String.format("SELECT `First_Name`, `Last_Name` , `User_ID` FROM `Users` WHERE `isManager` = '%d';", 1);
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet user_results = connection.select(get_user_query);

        while(user_results.next() == true){
            String n = String.format("%s %s,%d", user_results.getString("First_Name"),
                    user_results.getString("Last_Name"), user_results.getInt("User_ID"));
            view.getProposalManagerList().addItem(n);
        }
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
            else{
                model.setUser(loginUser);

                model.setCurrTrip(new Trip(loginUser.getUserID()));
                System.out.println(String.format("User %s logged in", id));
                // login
                // check if manager
                String get_user_query = String.format("SELECT isManager FROM Users WHERE User_ID = %d;", loginUser.getUserID());
                ConnectedDBConnection connection = new ConnectedDBConnection();
                ResultSet user_results = connection.select(get_user_query);
                user_results.next();
                Boolean test = user_results.getBoolean("isManager");
                model.setManager(test);

                if(model.isManager())
                    loginManager();
                else
                    loginEmployee();
            }





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

        // fix note depending on trip status
        switch(model.getCurrTrip().getStatus()){
            case 3:
                view.getEmployeeNoteLabel().setText("Trip approval pending.");
                break;
            case 2:
                view.getEmployeeNoteLabel().setText("Your trip has been rejected; propose a new trip.\nManager note: ");
                break;
            case 1:
                view.getEmployeeNoteLabel().setText("Your trip has been approved.\nClick the trip tracker to record expenses.");
                break;
            default:
                break;

        }

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
        loginEmployee();
    }

    /**
     * switch from manager menu to manager view
     */
    private void showManagerScreen() throws Exception {
        view.getManagerSelectionFrame().setVisible(false);
        view.getManagerScreenFrame().setVisible(true);
        //TODO fill lists with appropriate names / info
        // put every employee in EL
        // all employee with pending and matching manager == USERNAME

        String get_user_query = String.format("SELECT `User_ID`, `Trip_ID` FROM `Trip` WHERE `Status` = '%d' AND `Manager_ID` = '%d';"
                , 3, model.getCurrUser().getUserID());
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet user_results = connection.select(get_user_query);

        while(user_results.next() == true){
            String n = String.format("User: %d Trip: %d", user_results.getInt("User_ID"), user_results.getInt("Trip_ID"));
            view.getPendingListModel().addElement(n);
        }

    }

    /**
     * switch from manager view to manager menu
     */
    private void managerToSelection() {
        view.getManagerSelectionFrame().setVisible(true);
        view.getManagerScreenFrame().setVisible(false);
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
