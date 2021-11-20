// Chris Hutcherson
import java.awt.BorderLayout;
import javax.swing.*;

public class GUIView {
	private LoginFrame loginFrame;
	private RegisterFrame registerFrame;
	private EmployeeFrame employeeFrame;
	private ManagerFrame managerFrame;
	private ProposalFrame proposalFrame;

	//  manager selection
	private JFrame managerSelectionFrame;
	private JButton selectManagerViewButton;
	private JButton selectEmployeeViewButton;
	private JButton selectLogoutButton;

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
		proposalFrame = new ProposalFrame(title);

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


	public ProposalFrame getProposalFrame()
	{
		return proposalFrame;
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
}
