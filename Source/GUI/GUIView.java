// Chris Hutcherson
import java.awt.BorderLayout;
import javax.swing.*;

public class GUIView {
	private LoginFrame loginFrame;
	private RegisterFrame registerFrame;
	private EmployeeFrame employeeFrame;
	private ManagerFrame managerFrame;
	private ProposalFrame proposalFrame;
	private TripFrame tripFrame;

	//  manager selection
	private JFrame managerSelectionFrame;
	private JButton selectManagerViewButton;
	private JButton selectEmployeeViewButton;
	private JButton selectLogoutButton;



	public GUIView(String title) {
		loginFrame = new LoginFrame(title);
		registerFrame = new RegisterFrame(title);
		employeeFrame = new EmployeeFrame(title);
		managerFrame = new ManagerFrame(title);
		proposalFrame = new ProposalFrame(title);
		tripFrame = new TripFrame("Expense Tracker");

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


	public TripFrame getTripFrame()
	{
		return tripFrame;
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



}
