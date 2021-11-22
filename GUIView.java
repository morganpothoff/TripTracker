// Chris Hutcherson
import java.awt.BorderLayout;
import javax.swing.*;

public class GUIView {
    // login
    private JFrame loginFrame;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JTextField passwordTextField;
    private JButton registerButton;
    private JButton loginButton;
    private JButton exitButton;

    // register
    private JFrame registerFrame;
    private JLabel registerIdLabel;
    private JTextField registerIdTextField;
    private JLabel registerPasswordLabel;
    private JTextField registerPasswordTextField;
    private JLabel registerEmailLabel;
    private JTextField registerEmailTextField;
    private JButton registerRegisterButton;
    private JButton registerReturnButton;
    private JCheckBox isManagerCheckBox;
    private JTextField registerFirstTextField;
    private JTextField registerLastTextField;

    //  manager selection
    private JFrame managerSelectionFrame;
    private JButton selectManagerViewButton;
    private JButton selectEmployeeViewButton;
    private JButton selectLogoutButton;

    // employee screen
    private JFrame employeeScreenFrame;
    private JButton employeeProposalButton;
    private JButton employeeTripButton;
    private JButton employeeLogoutButton;
    private JButton employeeCancelPropButton;
    private JLabel employeeNoteLabel;

    // manager screen
    private JFrame managerScreenFrame;
    private JLabel managerPendingLabel;
    private DefaultListModel pendingListModel;
    private JList managerPendingList;
    private JButton managerSelectProposalButton;
    private JLabel managerBudgetLabel;
    private JTextArea budgetTextArea;
    private JTextField managerBaseTextField;
    private JButton managerBaseButton;
    private JTextField startDateTextField;
    private JTextField endDateTextField;
    private JButton viewHistoryButton;
    private JButton generateReportButton;
    private JButton managerBackButton;

    // proposal screen
    private JFrame proposalFrame;
    private JLabel proposalLocationLabel;
    private JLabel proposalStartLabel;
    private JLabel proposalEndLabel;
    private JLabel proposalEstimateLabel;
    private JLabel proposalDescriptionLabel;
    private JTextField proposalLocationTextField;
    private JTextField proposalStartTextField;
    private JTextField proposalEndTextField;
    private JTextField proposalEstimateTextField;
    private JTextField proposalDescriptionTextField;
    private JButton proposalBackButton;
    private JButton proposalSubmitButton;
    private JComboBox proposalManagerList;

    // trip screen
    private JFrame tripFrame;
    private JLabel tripExpensesLabel;
    private JLabel tripItemLabel;
    private JLabel tripCostLabel;
    private JTextField tripItemTextField;
    private JTextField tripCostTextField;
    private JButton tripAddButton;
    private JButton tripBackButton;
    private JButton tripFinishButton;
    private DefaultListModel tripExpenseModel;
    private JList tripExpenseList;

    // review screen (manager review proposals)
    private JFrame reviewFrame;
    private JTextArea reviewTextArea;
    private JLabel reviewFeedbackLabel;
    private JTextField reviewFeedbackTextField;
    private JButton reviewBackButton;
    private JButton reviewRejectButton;
    private JButton reviewApproveButton;



    public GUIView(String title) {
        // login
        // frame setups
        loginFrame = new JFrame(title);
        loginFrame.getContentPane().setLayout(new BorderLayout());
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(500, 350);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        // Create UI elements
        idLabel = new JLabel("User ID");
        passwordLabel = new JLabel("Password");
        idTextField = new JTextField();
        passwordTextField = new JTextField();
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");

        // Add UI element to frame
        GroupLayout loginLayout = new GroupLayout(loginFrame.getContentPane());
        loginLayout.setAutoCreateGaps(true);
        loginLayout.setAutoCreateContainerGaps(true);

        loginLayout.setHorizontalGroup(loginLayout.createSequentialGroup()
                .addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(idLabel)
                        .addComponent(passwordLabel))
                .addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(idTextField)
                        .addComponent(passwordTextField))
                .addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(registerButton)
                        .addComponent(loginButton).addComponent(exitButton)));
        loginLayout.setVerticalGroup(loginLayout.createSequentialGroup()
                .addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(idLabel)
                        .addComponent(idTextField).addComponent(registerButton))
                .addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(passwordLabel)
                        .addComponent(passwordTextField).addComponent(loginButton))
                .addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(exitButton)));
        loginLayout.linkSize(SwingConstants.HORIZONTAL, registerButton, loginButton);
        loginFrame.getContentPane().setLayout(loginLayout);


        // register
        // frame setups
        registerFrame = new JFrame("Register");
        registerFrame.getContentPane().setLayout(new BorderLayout());
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setSize(500, 350);
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setVisible(false);

        // Create UI elements
        registerIdLabel = new JLabel("User ID");
        registerPasswordLabel = new JLabel("Password");
        registerIdTextField = new JTextField();
        registerPasswordTextField = new JTextField();
        registerEmailLabel = new JLabel("Email");
        registerEmailTextField = new JTextField();
        registerRegisterButton = new JButton("Register");
        registerReturnButton = new JButton("Back");
        isManagerCheckBox = new JCheckBox("Check if Manager");
        registerFirstTextField = new JTextField("First Name");
        registerLastTextField = new JTextField("Last Name");

        // Add UI element to frame
        GroupLayout registerLayout = new GroupLayout(registerFrame.getContentPane());
        registerLayout.setAutoCreateGaps(true);
        registerLayout.setAutoCreateContainerGaps(true);

        registerLayout.setHorizontalGroup(registerLayout.createSequentialGroup()
                .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(registerIdLabel)
                        .addComponent(registerPasswordLabel).addComponent(registerEmailLabel).addComponent(registerFirstTextField).addComponent(registerReturnButton))
                .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(registerIdTextField)
                        .addComponent(registerPasswordTextField).addComponent(registerEmailTextField).addComponent(registerLastTextField).addComponent(registerRegisterButton))
                .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(isManagerCheckBox)));
        registerLayout.setVerticalGroup(registerLayout.createSequentialGroup()
                .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(registerIdLabel)
                        .addComponent(registerIdTextField).addComponent(isManagerCheckBox))
                .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(registerPasswordLabel)
                        .addComponent(registerPasswordTextField))
                .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(registerEmailLabel)
                        .addComponent(registerEmailTextField))
                .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(registerFirstTextField)
                        .addComponent(registerLastTextField))
                .addGroup(registerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(registerReturnButton)
                        .addComponent(registerRegisterButton)));
        registerLayout.linkSize(SwingConstants.HORIZONTAL, registerRegisterButton, registerReturnButton);
        registerFrame.getContentPane().setLayout(registerLayout);


        // manager selection
        // frame setups
        managerSelectionFrame = new JFrame("Manager Selection");
        managerSelectionFrame.getContentPane().setLayout(new BorderLayout());
        managerSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerSelectionFrame.setSize(500, 350);
        managerSelectionFrame.setLocationRelativeTo(null);
        managerSelectionFrame.setVisible(false);

        // Create UI elements
        selectManagerViewButton = new JButton("Manager Menu");
        selectEmployeeViewButton = new JButton("Employee Menu");
        selectLogoutButton = new JButton("Logout");


        // Add UI element to frame
        GroupLayout selectionLayout = new GroupLayout(managerSelectionFrame.getContentPane());
        selectionLayout.setAutoCreateGaps(true);
        selectionLayout.setAutoCreateContainerGaps(true);

        selectionLayout.setHorizontalGroup(selectionLayout.createSequentialGroup()
                .addGroup(selectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(selectEmployeeViewButton)
                        .addComponent(selectLogoutButton))
                .addGroup(selectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(selectManagerViewButton)));
        selectionLayout.setVerticalGroup(selectionLayout.createSequentialGroup()
                .addGroup(selectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(selectEmployeeViewButton)
                        .addComponent(selectManagerViewButton))
                .addGroup(selectionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(selectLogoutButton)));
        selectionLayout.linkSize(SwingConstants.HORIZONTAL, selectEmployeeViewButton, selectManagerViewButton, selectLogoutButton);
        managerSelectionFrame.getContentPane().setLayout(selectionLayout);


        // employee screen
        // frame setups
        employeeScreenFrame = new JFrame("Employee Menu");
        employeeScreenFrame.getContentPane().setLayout(new BorderLayout());
        employeeScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeScreenFrame.setSize(500, 350);
        employeeScreenFrame.setLocationRelativeTo(null);
        employeeScreenFrame.setVisible(false);

        // Create UI elements
        employeeProposalButton = new JButton("Make Proposal");
        employeeTripButton = new JButton("Manage Trip");
        employeeLogoutButton = new JButton(("Logout"));
        employeeCancelPropButton = new JButton(("Cancel Proposal"));
        employeeNoteLabel = new JLabel("NOTE: n/a");

        // Add UI element to frame
        GroupLayout employeeScreenLayout = new GroupLayout(employeeScreenFrame.getContentPane());
        employeeScreenLayout.setAutoCreateGaps(true);
        employeeScreenLayout.setAutoCreateContainerGaps(true);

        employeeScreenLayout.setHorizontalGroup(employeeScreenLayout.createSequentialGroup()
                .addGroup(employeeScreenLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(employeeProposalButton)
                        .addComponent(employeeCancelPropButton).addComponent(employeeNoteLabel))
                .addGroup(employeeScreenLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(employeeTripButton)
                        .addComponent(employeeLogoutButton)));
        employeeScreenLayout.setVerticalGroup(employeeScreenLayout.createSequentialGroup()
                .addGroup(employeeScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(employeeProposalButton)
                        .addComponent(employeeTripButton))
                .addGroup(employeeScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(employeeCancelPropButton)
                        .addComponent(employeeLogoutButton))
                .addGroup(employeeScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(employeeNoteLabel)));
        employeeScreenLayout.linkSize(SwingConstants.HORIZONTAL, employeeProposalButton, employeeCancelPropButton);
        employeeScreenFrame.getContentPane().setLayout(employeeScreenLayout);

        // manager screen
        // frame setups
        managerScreenFrame = new JFrame("Manager Home");
        managerScreenFrame.getContentPane().setLayout(new BorderLayout());
        managerScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerScreenFrame.setSize(500, 350);
        managerScreenFrame.setLocationRelativeTo(null);
        managerScreenFrame.setVisible(false);

        // Create UI elements
        pendingListModel = new DefaultListModel();
        managerSelectProposalButton = new JButton("Select");
        viewHistoryButton = new JButton("View History");
        generateReportButton = new JButton("Generate Report");
        startDateTextField = new JTextField();
        endDateTextField = new JTextField();
        managerBaseTextField = new JTextField();
        managerBaseButton = new JButton("Update Base");
        managerPendingLabel = new JLabel("Pending Requests");
        managerBudgetLabel = new JLabel("Budget");
        managerPendingList = new JList<>(pendingListModel);
        budgetTextArea = new JTextArea();
        managerBackButton = new JButton("Back");


        managerPendingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        managerPendingList.setSelectedIndex(0);
        managerPendingList.setVisibleRowCount(5);
        JScrollPane pendingScrollPane = new JScrollPane(managerPendingList);

        // Add UI element to frame
        GroupLayout managerScreenLayout = new GroupLayout(managerScreenFrame.getContentPane());
        managerScreenLayout.setAutoCreateGaps(true);
        managerScreenLayout.setAutoCreateContainerGaps(true);

        managerScreenLayout.setHorizontalGroup(managerScreenLayout.createSequentialGroup()
                .addGroup(managerScreenLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(managerPendingLabel)
                        .addComponent(pendingScrollPane).addComponent(managerSelectProposalButton).addComponent(managerBackButton))
                .addGroup(managerScreenLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(managerBudgetLabel)
                        .addComponent(budgetTextArea).addComponent(managerBaseTextField).addComponent(managerBaseButton)
                        .addComponent(startDateTextField).addComponent(endDateTextField).addComponent(viewHistoryButton)
                        .addComponent(generateReportButton)));
        managerScreenLayout.setVerticalGroup(managerScreenLayout.createSequentialGroup()
                .addGroup(managerScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(managerPendingLabel)
                        .addComponent(managerBudgetLabel))
                .addGroup(managerScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(pendingScrollPane)
                        .addComponent(budgetTextArea))
                .addGroup(managerScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(managerSelectProposalButton)
                        .addComponent(managerBaseTextField))
                .addGroup(managerScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(managerBaseButton))
                .addGroup(managerScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(startDateTextField))
                .addGroup(managerScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(endDateTextField))
                .addGroup(managerScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(managerBackButton).addComponent(viewHistoryButton))
                .addGroup(managerScreenLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(generateReportButton)));
        managerScreenFrame.getContentPane().setLayout(managerScreenLayout);

        // Proposal screen
        // frame setups
        proposalFrame = new JFrame("Proposal");
        proposalFrame.getContentPane().setLayout(new BorderLayout());
        proposalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        proposalFrame.setSize(500, 700);
        proposalFrame.setLocationRelativeTo(null);
        proposalFrame.setVisible(false);

        // Create UI elements
        proposalLocationLabel = new JLabel("Location");
        proposalLocationTextField = new JTextField("Ex: \"Austin, TX\"");
        proposalStartLabel = new JLabel("Start Date");
        proposalStartTextField = new JTextField("Ex: \"11/1/2021\"");
        proposalEndLabel = new JLabel("End Date");
        proposalEndTextField = new JTextField("Ex: \"11/8/2021\"");
        proposalEstimateLabel = new JLabel("Expense Estimate");
        proposalEstimateTextField = new JTextField("Ex: \"1250.75\"");
        proposalDescriptionLabel = new JLabel("Description");
        proposalDescriptionTextField = new JTextField("Ex: Conference with Mr. Doe about Project X");
        proposalBackButton = new JButton("Back");
        proposalSubmitButton = new JButton("Submit");
        String[] managerStrings = { };
        proposalManagerList = new JComboBox(managerStrings);

        // Add UI element to frame
        GroupLayout proposalLayout = new GroupLayout(proposalFrame.getContentPane());
        proposalLayout.setAutoCreateGaps(true);
        proposalLayout.setAutoCreateContainerGaps(true);

        proposalLayout.setHorizontalGroup(proposalLayout.createSequentialGroup()
                .addGroup(proposalLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(proposalLocationLabel)
                        .addComponent(proposalLocationTextField).addComponent(proposalStartLabel).addComponent(proposalStartTextField)
                        .addComponent(proposalEstimateLabel).addComponent(proposalEstimateTextField).addComponent(proposalDescriptionLabel)
                        .addComponent(proposalDescriptionTextField).addComponent(proposalSubmitButton).addComponent(proposalBackButton))
                .addGroup(proposalLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(proposalEndLabel)
                        .addComponent(proposalEndTextField).addComponent(proposalManagerList)));
        proposalLayout.setVerticalGroup(proposalLayout.createSequentialGroup()
                .addGroup(proposalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(proposalLocationLabel))
                .addGroup(proposalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(proposalLocationTextField))
                .addGroup(proposalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(proposalStartLabel).addComponent(proposalEndLabel))
                .addGroup(proposalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(proposalStartTextField).addComponent(proposalEndTextField))
                .addGroup(proposalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(proposalEstimateLabel))
                .addGroup(proposalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(proposalEstimateTextField))
                .addGroup(proposalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(proposalDescriptionLabel))
                .addGroup(proposalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(proposalDescriptionTextField))
                .addGroup(proposalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(proposalSubmitButton).addComponent(proposalManagerList))
                .addGroup(proposalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(proposalBackButton)));
        proposalLayout.linkSize(SwingConstants.HORIZONTAL, employeeProposalButton, employeeCancelPropButton);
        proposalFrame.getContentPane().setLayout(proposalLayout);

        // trip screen
        // frame setups
        tripFrame = new JFrame("Expense Tracker");
        tripFrame.getContentPane().setLayout(new BorderLayout());
        tripFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tripFrame.setSize(500, 350);
        tripFrame.setLocationRelativeTo(null);
        tripFrame.setVisible(false);

        // Create UI elements
        tripExpenseModel = new DefaultListModel();
        tripExpenseList = new JList<>(tripExpenseModel);
        tripExpensesLabel = new JLabel("Expenses");
        tripItemLabel = new JLabel("Item");
        tripCostLabel = new JLabel("Cost");
        tripItemTextField = new JTextField("Ex: \"Coffee,Starbucks,Austin\"");
        tripCostTextField = new JTextField("Ex: \"5.89\"");
        tripAddButton = new JButton("Add");
        tripBackButton = new JButton("Back");
        tripFinishButton = new JButton("Finish");

        tripExpenseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tripExpenseList.setSelectedIndex(0);
        tripExpenseList.setVisibleRowCount(10);
        JScrollPane tripExpenseScrollPane = new JScrollPane(tripExpenseList);

        // Add UI element to frame
        GroupLayout tripLayout = new GroupLayout(tripFrame.getContentPane());
        tripLayout.setAutoCreateGaps(true);
        tripLayout.setAutoCreateContainerGaps(true);

        tripLayout.setHorizontalGroup(tripLayout.createSequentialGroup()
                .addGroup(tripLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(tripExpensesLabel)
                        .addComponent(tripExpenseScrollPane).addComponent(tripItemLabel).addComponent(tripItemTextField)
                        .addComponent(tripAddButton).addComponent(tripBackButton))
                .addGroup(tripLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(tripCostLabel)
                        .addComponent(tripCostTextField).addComponent(tripFinishButton)));
        tripLayout.setVerticalGroup(tripLayout.createSequentialGroup()
                .addGroup(tripLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(tripExpensesLabel))
                .addGroup(tripLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(tripExpenseScrollPane))
                .addGroup(tripLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(tripItemLabel).addComponent(tripCostLabel))
                .addGroup(tripLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(tripItemTextField).addComponent(tripCostTextField))
                .addGroup(tripLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(tripAddButton))
                .addGroup(tripLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(tripBackButton).addComponent(tripFinishButton)));
        tripFrame.getContentPane().setLayout(tripLayout);


        // review screen
        // frame setups
        reviewFrame = new JFrame("Expense Tracker");
        reviewFrame.getContentPane().setLayout(new BorderLayout());
        reviewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reviewFrame.setSize(500, 350);
        reviewFrame.setLocationRelativeTo(null);
        reviewFrame.setVisible(false);

        // Create UI elements
        reviewTextArea = new JTextArea();
        reviewTextArea.setEditable(false);
        reviewFeedbackLabel = new JLabel("Feedback:");
        reviewFeedbackTextField = new JTextField("n/a");
        reviewBackButton = new JButton("Back");
        reviewApproveButton = new JButton("Approve");
        reviewRejectButton = new JButton("Reject");

        // Add UI element to frame
        GroupLayout reviewLayout = new GroupLayout(reviewFrame.getContentPane());
        reviewLayout.setAutoCreateGaps(true);
        reviewLayout.setAutoCreateContainerGaps(true);

        reviewLayout.setHorizontalGroup(reviewLayout.createSequentialGroup()
                .addGroup(reviewLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(reviewTextArea)
                        .addComponent(reviewFeedbackLabel).addComponent(reviewFeedbackTextField).addComponent(reviewBackButton))
                .addGroup(reviewLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(reviewRejectButton).addComponent(reviewApproveButton)));
        reviewLayout.setVerticalGroup(reviewLayout.createSequentialGroup()
                .addGroup(reviewLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(reviewTextArea))
                .addGroup(reviewLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(reviewFeedbackLabel))
                .addGroup(reviewLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(reviewFeedbackTextField).addComponent(reviewRejectButton))
                .addGroup(reviewLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(reviewBackButton).addComponent(reviewApproveButton)));
        reviewFrame.getContentPane().setLayout(reviewLayout);

    }




    public JFrame getLoginFrame() {
        return loginFrame;
    }

    public void setLoginFrame(JFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public JLabel getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(JLabel idLabel) {
        this.idLabel = idLabel;
    }

    public JTextField getIdTextField() {
        return idTextField;
    }

    public void setIdTextField(JTextField idTextField) {
        this.idTextField = idTextField;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(JTextField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public JFrame getRegisterFrame() {
        return registerFrame;
    }

    public void setRegisterFrame(JFrame registerFrame) {
        this.registerFrame = registerFrame;
    }

    public JLabel getRegisterIdLabel() {
        return registerIdLabel;
    }

    public void setRegisterIdLabel(JLabel registerIdLabel) {
        this.registerIdLabel = registerIdLabel;
    }

    public JTextField getRegisterIdTextField() {
        return registerIdTextField;
    }

    public void setRegisterIdTextField(JTextField registerIdTextField) {
        this.registerIdTextField = registerIdTextField;
    }

    public JLabel getRegisterPasswordLabel() {
        return registerPasswordLabel;
    }

    public void setRegisterPasswordLabel(JLabel registerPasswordLabel) {
        this.registerPasswordLabel = registerPasswordLabel;
    }

    public JTextField getRegisterPasswordTextField() {
        return registerPasswordTextField;
    }

    public void setRegisterPasswordTextField(JTextField registerPasswordTextField) {
        this.registerPasswordTextField = registerPasswordTextField;
    }

    public JLabel getRegisterEmailLabel() {
        return registerEmailLabel;
    }

    public void setRegisterEmailLabel(JLabel registerEmailLabel) {
        this.registerEmailLabel = registerEmailLabel;
    }

    public JTextField getRegisterEmailTextField() {
        return registerEmailTextField;
    }

    public void setRegisterEmailTextField(JTextField registerEmailTextField) {
        this.registerEmailTextField = registerEmailTextField;
    }

    public JButton getRegisterRegisterButton() {
        return registerRegisterButton;
    }

    public void setRegisterRegisterButton(JButton registerRegisterButton) {
        this.registerRegisterButton = registerRegisterButton;
    }

    public JButton getRegisterReturnButton() {
        return registerReturnButton;
    }

    public void setRegisterReturnButton(JButton registerReturnButton) {
        this.registerReturnButton = registerReturnButton;
    }

    public JFrame getManagerSelectionFrame() {
        return managerSelectionFrame;
    }

    public void setManagerSelectionFrame(JFrame managerSelectionFrame) {
        this.managerSelectionFrame = managerSelectionFrame;
    }

    public JButton getSelectManagerViewButton() {
        return selectManagerViewButton;
    }

    public void setSelectManagerViewButton(JButton selectManagerViewButton) {
        this.selectManagerViewButton = selectManagerViewButton;
    }

    public JButton getSelectEmployeeViewButton() {
        return selectEmployeeViewButton;
    }

    public void setSelectEmployeeViewButton(JButton selectEmployeeViewButton) {
        this.selectEmployeeViewButton = selectEmployeeViewButton;
    }

    public JButton getSelectLogoutButton() {
        return selectLogoutButton;
    }

    public void setSelectLogoutButton(JButton selectLogoutButton) {
        this.selectLogoutButton = selectLogoutButton;
    }

    public JFrame getEmployeeScreenFrame() {
        return employeeScreenFrame;
    }

    public void setEmployeeScreenFrame(JFrame employeeScreenFrame) {
        this.employeeScreenFrame = employeeScreenFrame;
    }

    public JFrame getManagerScreenFrame() {
        return managerScreenFrame;
    }

    public void setManagerScreenFrame(JFrame managerScreenFrame) {
        this.managerScreenFrame = managerScreenFrame;
    }

    public JLabel getManagerPendingLabel() {
        return managerPendingLabel;
    }

    public void setManagerPendingLabel(JLabel managerPendingLabel) {
        this.managerPendingLabel = managerPendingLabel;
    }

    public JList<String> getManagerPendingList() {
        return managerPendingList;
    }


    public JButton getManagerSelectProposalButton() {
        return managerSelectProposalButton;
    }

    public void setManagerSelectProposalButton(JButton managerSelectProposalButton) {
        this.managerSelectProposalButton = managerSelectProposalButton;
    }

    public JLabel getManagerBudgetLabel() {
        return managerBudgetLabel;
    }

    public void setManagerBudgetLabel(JLabel managerBudgetLabel) {
        this.managerBudgetLabel = managerBudgetLabel;
    }

    public JTextArea getBudgetTextArea() {
        return budgetTextArea;
    }

    public void setBudgetTextArea(JTextArea budgetTextArea) {
        this.budgetTextArea = budgetTextArea;
    }

    public JTextField getManagerBaseTextField() {
        return managerBaseTextField;
    }

    public void setManagerBaseTextField(JTextField managerBaseTextField) {
        this.managerBaseTextField = managerBaseTextField;
    }

    public JButton getManagerBaseButton() {
        return managerBaseButton;
    }

    public void setManagerBaseButton(JButton managerBaseButton) {
        this.managerBaseButton = managerBaseButton;
    }

    public JTextField getStartDateTextField() {
        return startDateTextField;
    }

    public void setStartDateTextField(JTextField startDateTextField) {
        this.startDateTextField = startDateTextField;
    }

    public JTextField getEndDateTextField() {
        return endDateTextField;
    }

    public void setEndDateTextField(JTextField endDateTextField) {
        this.endDateTextField = endDateTextField;
    }

    public JButton getViewHistoryButton() {
        return viewHistoryButton;
    }

    public void setViewHistoryButton(JButton viewHistoryButton) {
        this.viewHistoryButton = viewHistoryButton;
    }

    public JButton getGenerateReportButton() {
        return generateReportButton;
    }

    public void setGenerateReportButton(JButton generateReportButton) {
        this.generateReportButton = generateReportButton;
    }

    public JButton getManagerBackButton() {
        return managerBackButton;
    }

    public void setManagerBackButton(JButton managerBackButton) {
        this.managerBackButton = managerBackButton;
    }


    public DefaultListModel getPendingListModel() {
        return pendingListModel;
    }

    public void setPendingListModel(DefaultListModel pendingListModel) {
        this.pendingListModel = pendingListModel;
    }

    public void setManagerPendingList(JList managerPendingList) {
        this.managerPendingList = managerPendingList;
    }

    public JButton getEmployeeProposalButton() {
        return employeeProposalButton;
    }

    public void setEmployeeProposalButton(JButton employeeProposalButton) {
        this.employeeProposalButton = employeeProposalButton;
    }

    public JButton getEmployeeTripButton() {
        return employeeTripButton;
    }

    public void setEmployeeTripButton(JButton employeeTripButton) {
        this.employeeTripButton = employeeTripButton;
    }

    public JLabel getEmployeeNoteLabel() {
        return employeeNoteLabel;
    }

    public void setEmployeeNoteLabel(JLabel employeeNoteLabel) {
        this.employeeNoteLabel = employeeNoteLabel;
    }



    public JButton getEmployeeLogoutButton() {
        return employeeLogoutButton;
    }

    public void setEmployeeLogoutButton(JButton employeeLogoutButton) {
        this.employeeLogoutButton = employeeLogoutButton;
    }

    public JButton getEmployeeCancelPropButton() {
        return employeeCancelPropButton;
    }

    public void setEmployeeCancelPropButton(JButton employeeCancelPropButton) {
        this.employeeCancelPropButton = employeeCancelPropButton;
    }

    public JFrame getProposalFrame() {
        return proposalFrame;
    }

    public void setProposalFrame(JFrame proposalFrame) {
        this.proposalFrame = proposalFrame;
    }

    public JLabel getProposalLocationLabel() {
        return proposalLocationLabel;
    }

    public void setProposalLocationLabel(JLabel proposalLocationLabel) {
        this.proposalLocationLabel = proposalLocationLabel;
    }

    public JLabel getProposalStartLabel() {
        return proposalStartLabel;
    }

    public void setProposalStartLabel(JLabel proposalStartLabel) {
        this.proposalStartLabel = proposalStartLabel;
    }

    public JLabel getProposalEndLabel() {
        return proposalEndLabel;
    }

    public void setProposalEndLabel(JLabel proposalEndLabel) {
        this.proposalEndLabel = proposalEndLabel;
    }

    public JLabel getProposalEstimateLabel() {
        return proposalEstimateLabel;
    }

    public void setProposalEstimateLabel(JLabel proposalEstimateLabel) {
        this.proposalEstimateLabel = proposalEstimateLabel;
    }

    public JLabel getProposalDescriptionLabel() {
        return proposalDescriptionLabel;
    }

    public void setProposalDescriptionLabel(JLabel proposalDescriptionLabel) {
        this.proposalDescriptionLabel = proposalDescriptionLabel;
    }

    public JTextField getProposalLocationTextField() {
        return proposalLocationTextField;
    }

    public void setProposalLocationTextField(JTextField proposalLocationTextField) {
        this.proposalLocationTextField = proposalLocationTextField;
    }

    public JTextField getProposalStartTextField() {
        return proposalStartTextField;
    }

    public void setProposalStartTextField(JTextField proposalStartTextField) {
        this.proposalStartTextField = proposalStartTextField;
    }

    public JTextField getProposalEndTextField() {
        return proposalEndTextField;
    }

    public void setProposalEndTextField(JTextField proposalEndTextField) {
        this.proposalEndTextField = proposalEndTextField;
    }

    public JTextField getProposalEstimateTextField() {
        return proposalEstimateTextField;
    }

    public void setProposalEstimateTextField(JTextField proposalEstimateTextField) {
        this.proposalEstimateTextField = proposalEstimateTextField;
    }

    public JTextField getProposalDescriptionTextField() {
        return proposalDescriptionTextField;
    }

    public void setProposalDescriptionTextField(JTextField proposalDescriptionTextField) {
        this.proposalDescriptionTextField = proposalDescriptionTextField;
    }

    public JButton getProposalBackButton() {
        return proposalBackButton;
    }

    public void setProposalBackButton(JButton proposalBackButton) {
        this.proposalBackButton = proposalBackButton;
    }

    public JButton getProposalSubmitButton() {
        return proposalSubmitButton;
    }

    public void setProposalSubmitButton(JButton proposalSubmitButton) {
        this.proposalSubmitButton = proposalSubmitButton;
    }

    public JFrame getTripFrame() {
        return tripFrame;
    }

    public void setTripFrame(JFrame tripFrame) {
        this.tripFrame = tripFrame;
    }

    public JLabel getTripExpensesLabel() {
        return tripExpensesLabel;
    }

    public void setTripExpensesLabel(JLabel tripExpensesLabel) {
        this.tripExpensesLabel = tripExpensesLabel;
    }

    public JLabel getTripItemLabel() {
        return tripItemLabel;
    }

    public void setTripItemLabel(JLabel tripItemLabel) {
        this.tripItemLabel = tripItemLabel;
    }

    public JLabel getTripCostLabel() {
        return tripCostLabel;
    }

    public void setTripCostLabel(JLabel tripCostLabel) {
        this.tripCostLabel = tripCostLabel;
    }

    public JTextField getTripItemTextField() {
        return tripItemTextField;
    }

    public void setTripItemTextField(JTextField tripItemTextField) {
        this.tripItemTextField = tripItemTextField;
    }

    public JTextField getTripCostTextField() {
        return tripCostTextField;
    }

    public void setTripCostTextField(JTextField tripCostTextField) {
        this.tripCostTextField = tripCostTextField;
    }

    public JButton getTripAddButton() {
        return tripAddButton;
    }

    public void setTripAddButton(JButton tripAddButton) {
        this.tripAddButton = tripAddButton;
    }

    public JButton getTripBackButton() {
        return tripBackButton;
    }

    public void setTripBackButton(JButton tripBackButton) {
        this.tripBackButton = tripBackButton;
    }

    public JButton getTripFinishButton() {
        return tripFinishButton;
    }

    public void setTripFinishButton(JButton tripFinishButton) {
        this.tripFinishButton = tripFinishButton;
    }

    public DefaultListModel getTripExpenseModel() {
        return tripExpenseModel;
    }

    public void setTripExpenseModel(DefaultListModel tripExpenseModel) {
        this.tripExpenseModel = tripExpenseModel;
    }

    public JList getTripExpenseList() {
        return tripExpenseList;
    }

    public void setTripExpenseList(JList tripExpenseList) {
        this.tripExpenseList = tripExpenseList;
    }

    public JCheckBox getIsManagerCheckBox() {
        return isManagerCheckBox;
    }

    public JComboBox getProposalManagerList() {
        return proposalManagerList;
    }

    public void setProposalManagerList(JComboBox proposalManagerList) {
        this.proposalManagerList = proposalManagerList;
    }

    public void setIsManagerCheckBox(JCheckBox isManagerCheckBox) {
        this.isManagerCheckBox = isManagerCheckBox;
    }

    public JTextField getRegisterFirstTextField() {
        return registerFirstTextField;
    }

    public void setRegisterFirstTextField(JTextField registerFirstTextField) {
        this.registerFirstTextField = registerFirstTextField;
    }

    public JTextField getRegisterLastTextField() {
        return registerLastTextField;
    }

    public void setRegisterLastTextField(JTextField registerLastTextField) {
        this.registerLastTextField = registerLastTextField;
    }

    public JFrame getReviewFrame() {
        return reviewFrame;
    }

    public void setReviewFrame(JFrame reviewFrame) {
        this.reviewFrame = reviewFrame;
    }

    public JTextArea getReviewTextArea() {
        return reviewTextArea;
    }

    public void setReviewTextArea(JTextArea reviewTextArea) {
        this.reviewTextArea = reviewTextArea;
    }

    public JLabel getReviewFeedbackLabel() {
        return reviewFeedbackLabel;
    }

    public void setReviewFeedbackLabel(JLabel reviewFeedbackLabel) {
        this.reviewFeedbackLabel = reviewFeedbackLabel;
    }

    public JTextField getReviewFeedbackTextField() {
        return reviewFeedbackTextField;
    }

    public void setReviewFeedbackTextField(JTextField reviewFeedbackTextField) {
        this.reviewFeedbackTextField = reviewFeedbackTextField;
    }

    public JButton getReviewBackButton() {
        return reviewBackButton;
    }

    public void setReviewBackButton(JButton reviewBackButton) {
        this.reviewBackButton = reviewBackButton;
    }

    public JButton getReviewRejectButton() {
        return reviewRejectButton;
    }

    public void setReviewRejectButton(JButton reviewRejectButton) {
        this.reviewRejectButton = reviewRejectButton;
    }

    public JButton getReviewApproveButton() {
        return reviewApproveButton;
    }

    public void setReviewApproveButton(JButton reviewApproveButton) {
        this.reviewApproveButton = reviewApproveButton;
    }
}
