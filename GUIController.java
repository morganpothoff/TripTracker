// Chris Hutcherson
import java.io.IOException;

public class GUIController {
    private GUIModel model;
    private GUIView view;
    public GUIController(GUIModel m, GUIView v) {
        model = m;
        view = v;
    }

    public void initController() {
        view.getLoginButton().addActionListener(e -> {
            try {
                login();
            } catch (IOException ioException) {
                ioException.printStackTrace();
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


    private void getLoginData() {
        String id = view.getIdTextField().getText();
        String pass = view.getPasswordTextField().getText();
        model.getLoginController().getUserInput(id, pass);
    }



    private void registerUser() throws IOException {
        String id = view.getRegisterIdTextField().getText();
        String pass = view.getRegisterPasswordTextField().getText();
        model.getLoginController().getNewUserInput(id, pass);

        if(model.getLoginController().register(model.getDbManager())){  // if successful registration
            back();
        }
        else{
            // tell user if ID already exists
            view.getRegisterIdTextField().setText("Invalid ID - already exists");
        }

    }

    private void finishTrip() {
        // todo archive trip details
        tripToEmployeeScreen();
    }

    private void addItem() {
        try {
            // Get data
            String tempInput = view.getTripItemTextField().getText();       // get text and parse ex: "Item,StarBucks,Austin"
            String name = tempInput.substring(0, tempInput.indexOf(','));
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

    private void tripToEmployeeScreen() {
        view.getTripFrame().setVisible(false);
        view.getEmployeeScreenFrame().setVisible(true);
    }

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

    }

    private void login() throws IOException {
        getLoginData();
        // todo if name matches manager name
        Authenticator authenticator = new Authenticator(model.getLoginController().getID(), model.getLoginController().getPassword());
        if(authenticator.authenticate(model.getDbManager())){ // if login was valid
            // create user based from login ID
            // todo check for manager vs employee
            // if employee
            // todo fix once user info is found... model.setUser();
            loginEmployee();
            /* else
            loginManager();
             */
        }
        else{
            // tell user invalid
            view.getIdTextField().setText("invalid id or password");
        }

    }

    private void register() {

        view.getLoginFrame().setVisible(false);
        view.getRegisterFrame().setVisible(true);

    }

    private void back(){
        // clear registration textfields
        view.getRegisterIdTextField().setText("");
        view.getRegisterPasswordTextField().setText("");
        view.getRegisterEmailTextField().setText("");

        view.getLoginFrame().setVisible(true);
        view.getRegisterFrame().setVisible(false);

    }

    private void loginEmployee() {
        view.getEmployeeScreenFrame().setVisible(true);
        view.getLoginFrame().setVisible(false);

    }

    private void loginManager() {
        view.getManagerSelectionFrame().setVisible(true);
        view.getLoginFrame().setVisible(false);
    }

    private void showEmployeeScreen() {
        view.getManagerSelectionFrame().setVisible(false);
        view.getEmployeeScreenFrame().setVisible(true);
    }
    private void showManagerScreen() {
        view.getManagerSelectionFrame().setVisible(false);
        view.getManagerScreenFrame().setVisible(true);
        //TODO fill lists with appropriate names / info
        view.getEmployeesListModel().addElement("jerry man");

    }

    private void managerToSelection() {
        view.getManagerSelectionFrame().setVisible(true);
        view.getManagerScreenFrame().setVisible(false);
    }

    private void addEmployee() {
        // TODO if employee does not exist within company && is not tied to a manager
        if(view.getNewNameTextField().getText().equals(""))
            view.getNewNameTextField().setText("Invalid name");
        else{
            view.getEmployeesListModel().addElement(view.getNewNameTextField().getText());
            //TODO go to model to link employee to manager
        }
    }

    private void employeeLogout() {
        // todo if is a manager
        // return to selection screen
        view.getManagerSelectionFrame().setVisible(true);
        view.getEmployeeScreenFrame().setVisible(false);

        // else logout
    }
    private void logout() {
        view.getManagerSelectionFrame().setVisible(false);
        view.getEmployeeScreenFrame().setVisible(false);

        // return to login screen and clear login textfields
        view.getIdTextField().setText("");
        view.getPasswordTextField().setText("");
        view.getLoginFrame().setVisible(true);
    }

    private void exit() {
        System.exit(0);
    }


}
