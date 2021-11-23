// Chris Hutcherson
import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

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
        view.getSelectEmployeeViewButton().addActionListener((e -> {
            try {
                showEmployeeScreen();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));
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
        view.getProposalBackButton().addActionListener((e -> {
            try {
                proposalToEmployeeScreen();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));
        view.getProposalSubmitButton().addActionListener((e -> {
            try {
                submitProposal();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));
        view.getTripBackButton().addActionListener((e -> tripToEmployeeScreen()));
        view.getEmployeeCancelPropButton().addActionListener((e -> {
            try {
                cancelProposal();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));
        view.getTripAddButton().addActionListener((e -> addItem()));
        view.getTripFinishButton().addActionListener((e -> finishTrip()));
        view.getReviewBackButton().addActionListener((e -> {
            try {
                reviewBack();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));
        view.getReviewApproveButton().addActionListener((e -> {
            try {
                reviewApprove();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));
        view.getReviewRejectButton().addActionListener((e -> {
            try {
                reviewReject();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));
        view.getManagerSelectProposalButton().addActionListener((e -> {
            try {
                selectProposal();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));


    }

    private void cancelProposal() throws Exception {
        view.getEmployeeNoteLabel().setText("Proposal canceled.");
        view.getEmployeeManagerNote().setText("Manager note: n/a");
        model.getCurrTrip().setStatus(2);
        model.getCurrTrip().setNote("");

    }

    private void selectProposal() throws Exception {
        String selected = view.getManagerPendingList().getSelectedValue();
        if(selected.equals("")){ // if nothing selected
           return;
        }

        view.getManagerScreenFrame().setVisible(false);
        view.getReviewFrame().setVisible(true);
        view.getReviewFeedbackTextField().setText("");
        selected = selected.substring(selected.indexOf(' ')+1);
        // fill in the text area with info from selected employee and trip
        int userID = Integer.parseInt(selected.substring(0, selected.indexOf(' ')));
        int tripID = Integer.parseInt(selected.substring(selected.indexOf(':')+2));



        String get_user_query = String.format("SELECT `First_Name`, `Last_Name` FROM `Users` WHERE `User_ID` = '%d';", userID);
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet user_results = connection.select(get_user_query);
        user_results.next();
        String fname = user_results.getString("First_Name");
        String lname = user_results.getString("Last_Name");

        get_user_query = String.format("SELECT `Location`, `Start_Date`, `End_Time`, `Set_Budget`, `MyDescription` FROM `Trip` WHERE `Trip_ID` = '%d';", tripID);
        connection = new ConnectedDBConnection();
        user_results = connection.select(get_user_query);
        user_results.next();

        String location = user_results.getString("Location");
        String sdate = user_results.getString("Start_Date");
        String edate = user_results.getString("End_Time");
        String bud = user_results.getString("Set_Budget");
        String desc = user_results.getString("MyDescription");

        String buffer = String.format("Employee: %s %s\nLocation: %s\nTravel Date: %s - %s\nEst. Expenses: $%s\nDescription: %s",
                fname, lname, location, sdate, edate, bud, desc);
        view.getReviewTextArea().setText(buffer);

    }

    private void reviewApprove() throws Exception {

        String selected = view.getManagerPendingList().getSelectedValue();
        int tripID = Integer.parseInt(selected.substring(selected.indexOf('p')+3));

        String get_user_query = String.format("UPDATE `Trip` SET `Status` = '%d', `Note` = '%s' WHERE `Trip_ID` = '%d';",
                1, view.getReviewFeedbackTextField().getText(), tripID);
        ConnectedDBConnection connection = new ConnectedDBConnection();

        int user_results = connection.update(get_user_query);           // check that database was updated
        if(user_results != 1) {
            throw new Exception("No changes were made to database.");
        }
        reviewBack();

    }
    private void reviewReject() throws Exception {

        String selected = view.getManagerPendingList().getSelectedValue();
        int tripID = Integer.parseInt(selected.substring(selected.indexOf('p')+3));

        String get_user_query = String.format("UPDATE `Trip` SET `Status` = '%d', `Note` = '%s' WHERE `Trip_ID` = '%d';",
                2, view.getReviewFeedbackTextField().getText(), tripID);
        ConnectedDBConnection connection = new ConnectedDBConnection();

        int user_results = connection.update(get_user_query);           // check that database was updated
        if(user_results != 1) {
            throw new Exception("No changes were made to database.");
        }
        reviewBack();

    }

    private void reviewBack() throws Exception {
        showManagerScreen();
        view.getReviewFrame().setVisible(false);
        view.getManagerScreenFrame().setVisible(true);
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
                
            if (!validChecker){
            	throw new Exception("Invalid information--Refer to ID/Password rules in README");
            }
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
            view.getRegisterIdTextField().setText(e.getMessage());
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
            int Trip_ID = model.getCurrTrip().getTripID();
            int User_ID = model.getCurrUser().getUserID();

            // Set data
            if(Expense.add(company, cost, location, name, Trip_ID, User_ID)) {
                view.getTripItemTextField().setText("");
                view.getTripCostTextField().setText("");
                view.getTripExpenseModel().addElement(name + ' ' + cost);

                System.out.println("Successfully added Expense");
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
        String sDate = view.getProposalStartTextField().getText();
        String eDate = view.getProposalEndTextField().getText();
        float budget = 0;
        try {budget = Float.parseFloat(view.getProposalEstimateTextField().getText());}
        catch (NumberFormatException e){view.getProposalEstimateTextField().setText("please enter a double value");}
        String desc = view.getProposalDescriptionTextField().getText();

        //Split for sDate and eDate
        String[] splitSDate = sDate.split("/");
        String[] splitEDate = eDate.split("/");
        //Checks if sDate and eDate are length 3 (3 date fields) and checks if each field for sDate and eDate are integer or not
        if(!(splitSDate.length == 3 && isInteger(splitSDate[0]) && isInteger(splitSDate[1]) && isInteger(splitSDate[2])))	{
        	view.getProposalStartTextField().setText("Invalid date input");
        	return;
        }
        //Make sure month is between 1 and 12
        else if(!(Integer.parseInt(splitSDate[0]) >= 1 && Integer.parseInt(splitSDate[0]) <= 12)) {
        	view.getProposalStartTextField().setText("Invalid month input");
            return;
        }
        //Make sure day is between 1 and 31
        else if(!(Integer.parseInt(splitSDate[1]) >= 1 && Integer.parseInt(splitSDate[1]) <= 31)) {
        	view.getProposalStartTextField().setText("Invalid day input");
            return;
        }
        //Make sure year is at least 2021
        else if(!(Integer.parseInt(splitSDate[2]) >= 2021)) {
        	view.getProposalStartTextField().setText("Invalid year input");
            return;
        }
        if(!(splitEDate.length == 3 && isInteger(splitEDate[0]) && isInteger(splitEDate[1]) && isInteger(splitEDate[2]))) {
        	view.getProposalEndTextField().setText("Invalid date input");
            return;
        }
        //Make sure month is between 1 and 12
        else if(!(Integer.parseInt(splitEDate[0]) >= 1 && Integer.parseInt(splitEDate[0]) <= 12)) {
        	view.getProposalEndTextField().setText("Invalid month input");
            return;
        }
        //Make sure day is between 1 and 31
        else if(!(Integer.parseInt(splitEDate[1]) >= 1 && Integer.parseInt(splitEDate[1]) <= 31)) {
        	view.getProposalEndTextField().setText("Invalid day input");
            return;
        }
        //Make sure year is at least 2021
        else if(!(Integer.parseInt(splitEDate[2]) >= 2021)) {
        	view.getProposalEndTextField().setText("Invalid year input");
            return;
        }
        
        
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


    private void proposalToEmployeeScreen() throws Exception {
        loginEmployee();
        view.getProposalFrame().setVisible(false);
        view.getEmployeeScreenFrame().setVisible(true);
    }

    private void gotoTripScreen() throws IOException {
        // if no trip in progress, don't switch
        if(model.getCurrTrip().getStatus() != 1){
            System.out.println("Here");
            return;
        }

        try {
            view.getTripExpenseModel().removeAllElements();
            ArrayList<Expense> expenses_for_trip = Expense.all_expenses_for_trip(model.getCurrTrip().getTripID());  // Get the list of expenses for a trip

            for(int i = 0; i < expenses_for_trip.size(); i++) {  // Loop through expenses
                Expense expense = expenses_for_trip.get(i);  // Get expense for current index
                view.getTripExpenseModel().addElement(expense.getExpenseName() + ' ' + expense.getCost());  // Display expense in GUI
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }

        view.getEmployeeScreenFrame().setVisible(false);
        view.getTripFrame().setVisible(true);
    }

    private void gotoProposalScreen() throws Exception {
        view.getProposalManagerList().removeAllItems();
        view.getEmployeeScreenFrame().setVisible(false);
        view.getProposalFrame().setVisible(true);

        //reset all textfields
        view.getProposalLocationTextField().setText("");
        view.getProposalStartTextField().setText("");
        view.getProposalEndTextField().setText("");
        view.getProposalEstimateTextField().setText("");
        view.getProposalDescriptionTextField().setText("");

        // fill in the manager list
        String get_user_query = String.format("SELECT u.First_Name, u.Last_Name, m.Manager_ID FROM Users AS u NATURAL JOIN Manager AS m WHERE isManager = '%d';", 1);
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet user_results = connection.select(get_user_query);

        while(user_results.next() == true){
            String n = String.format("%s %s,%d", user_results.getString("u.First_Name"),
                    user_results.getString("u.Last_Name"), user_results.getInt("m.Manager_ID"));
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
                throw new Exception("Invalid Password or Username is not in use");
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
    private void loginEmployee() throws Exception {
        view.getEmployeeScreenFrame().setVisible(true);
        view.getLoginFrame().setVisible(false);

        // fix note depending on trip status
        switch(model.getCurrTrip().getStatus()){
            case 3:
                view.getEmployeeNoteLabel().setText("Trip approval pending.");
                view.getEmployeeManagerNote().setText("Manager note: n/a");
                break;
            case 2:
                view.getEmployeeNoteLabel().setText("Your trip has been rejected; propose a new trip.");
                view.getEmployeeManagerNote().setText("Manager note: " + model.getCurrTrip().getNote());
                break;
            case 1:
                view.getEmployeeNoteLabel().setText("Your trip has been approved.\nClick the trip tracker to record expenses.");
                view.getEmployeeManagerNote().setText("Manager note: n/a");
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
    private void showEmployeeScreen() throws Exception {
        view.getManagerSelectionFrame().setVisible(false);
        loginEmployee();
    }

    /**
     * switch from manager menu to manager view
     */
    private void showManagerScreen() throws Exception {
        view.getManagerSelectionFrame().setVisible(false);
        view.getManagerScreenFrame().setVisible(true);

        //clear pending list
        view.getPendingListModel().clear();

        // get manager ID using user ID
        String get_user_query = String.format("SELECT `Manager_ID` FROM `Manager` WHERE `User_ID` = '%d';", model.getCurrUser().getUserID());
        ConnectedDBConnection connection = new ConnectedDBConnection();
        ResultSet user_results = connection.select(get_user_query);
        user_results.next();
        int managerID = user_results.getInt("Manager_ID");

        get_user_query = String.format("SELECT `User_ID`, `Trip_ID` FROM `Trip` WHERE `Status` = '%d' AND `Manager_ID` = '%d';"
                , 3, managerID);
        user_results = connection.select(get_user_query);

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
    
    //Helper Function
    public boolean isInteger(String input)	{
    	try { 
            Integer.parseInt(input); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }//End of isInteger

}
