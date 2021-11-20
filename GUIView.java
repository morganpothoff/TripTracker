// Chris Hutcherson
import java.awt.BorderLayout;
import javax.swing.*;

public class GUIView {
	private LoginFrame loginFrame;
	private RegisterFrame registerFrame;
	private EmployeeFrame employeeFrame;
	private ManagerFrame managerFrame;

	//  manager selection
	private JFrame managerSelectionFrame;
	private JButton selectManagerViewButton;
	private JButton selectEmployeeViewButton;
	private JButton selectLogoutButton;

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



	public GUIView(String title) {
		loginFrame = new LoginFrame(title);
		registerFrame = new RegisterFrame(title);
		employeeFrame = new EmployeeFrame(title);
		managerFrame = new ManagerFrame(title);

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
		proposalLayout.linkSize(SwingConstants.HORIZONTAL, employeeFrame.getProposalButton(), employeeFrame.getCancelPropButton());
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

	}


	public LoginFrame getLoginFrame()
	{
		return loginFrame;
	}


	public RegisterFrame getRegisterFrame()
	{
		return registerFrame;
	}


	public EmployeeFrame getEmployeeFrame()
	{
		return employeeFrame;
	}


	public ManagerFrame getManagerFrame()
	{
		return managerFrame;
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

	public JComboBox getProposalManagerList() {
		return proposalManagerList;
	}

	public void setProposalManagerList(JComboBox proposalManagerList) {
		this.proposalManagerList = proposalManagerList;
	}
}
