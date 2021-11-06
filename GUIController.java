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
        view.getRegisterRegisterButton().addActionListener((e -> registerUser()));
        view.getExitButton().addActionListener((e -> exit()));
        view.getSelectManagerViewButton().addActionListener((e -> showManagerScreen()));
        view.getSelectEmployeeViewButton().addActionListener((e -> showEmployeeScreen()));
        view.getSelectLogoutButton().addActionListener((e -> logout()));
        view.getManagerBackButton().addActionListener((e -> managerToSelection()));
        view.getManagerAddButton().addActionListener((e -> addEmployee()));
        view.getEmployeeProposalButton().addActionListener((e -> gotoProposalScreen()));
        view.getEmployeeTripButton().addActionListener((e -> gotoTripScreen()));
        view.getEmployeeLogoutButton().addActionListener((e -> employeeLogout()));
        view.getProposalBackButton().addActionListener((e -> proposalToEmployeeScreen()));
        view.getProposalSubmitButton().addActionListener((e -> submitProposal()));
        view.getTripBackButton().addActionListener((e -> tripToEmployeeScreen()));
        view.getTripAddButton().addActionListener((e -> addItem()));
        view.getTripFinishButton().addActionListener((e -> finishTrip()));


    }


    private void getLoginData(){
        String id = view.getIdTextField().getText();
        String pass = view.getPasswordTextField().getText();
        model.getLoginController().getUserInput(id, pass);
    }



    private void registerUser() {
        // todo get the info and make user
        String id = view.getRegisterIdTextField().getText();
        String pass = view.getRegisterPasswordTextField().getText();
        model.getLoginController().getNewUserInput(id, pass);
        back();
    }

    private void finishTrip() {
        // todo archive trip details
        tripToEmployeeScreen();
    }

    private void addItem() {
        // todo add the item to data
        view.getTripExpenseModel().addElement(view.getTripItemTextField().getText() + ' ' + view.getTripCostTextField().getText());
        view.getTripItemTextField().setText("");
        view.getTripCostTextField().setText("");
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

    private void gotoTripScreen() {
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
        if(authenticator.authenticate(new DBManager())){ // if login was valid
            // todo check for manager vs employee
            // if employee
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

    private void register(){

        view.getLoginFrame().setVisible(false);
        view.getRegisterFrame().setVisible(true);

    }

    private void back(){
        view.getLoginFrame().setVisible(true);
        view.getRegisterFrame().setVisible(false);

    }

    private void loginEmployee(){
        view.getEmployeeScreenFrame().setVisible(true);
        view.getLoginFrame().setVisible(false);

    }

    private void loginManager(){
        view.getManagerSelectionFrame().setVisible(true);
        view.getLoginFrame().setVisible(false);
    }

    private void showEmployeeScreen() {
        view.getManagerSelectionFrame().setVisible(false);
        view.getEmployeeScreenFrame().setVisible(true);
    }
    private void showManagerScreen(){
        view.getManagerSelectionFrame().setVisible(false);
        view.getManagerScreenFrame().setVisible(true);
        //TODO fill lists with appropriate names / info
        view.getEmployeesListModel().addElement("jerry man");

    }

    private void managerToSelection(){
        view.getManagerSelectionFrame().setVisible(true);
        view.getManagerScreenFrame().setVisible(false);
    }

    private void addEmployee(){
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
    private void logout(){
        view.getManagerSelectionFrame().setVisible(false);
        view.getEmployeeScreenFrame().setVisible(false);

        // return to login screen
        view.getLoginFrame().setVisible(true);
    }

    private void exit(){
        System.exit(0);
    }


}
