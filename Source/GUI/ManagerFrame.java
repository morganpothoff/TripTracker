import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import java.util.List;
import java.util.Arrays;


class ManagerFrame extends MFrame
{
	private JFrame screenFrame;
	private JLabel employeesLabel;
	private DefaultListModel employeesListModel;
	private JList employeesList;
	private JButton removeButton;
	private JButton addButton;
	private JTextField newNameTextField;
	private JLabel pendingLabel;
	private DefaultListModel pendingListModel;
	private JList pendingList;
	private JButton selectEmployeeButton;
	private JButton selectProposalButton;
	private JLabel budgetLabel;
	private JTextArea budgetTextArea;
	private JTextField baseTextField;
	private JButton baseButton;
	private JTextField startDateTextField;
	private JTextField endDateTextField;
	private JButton viewHistoryButton;
	private JButton generateReportButton;
	private JButton backButton;
	private JScrollPane employeeScrollPane;
	private JScrollPane pendingScrollPane;


	public ManagerFrame(GUI gui)
	{
		super(gui, "Manager", false);

		initialize_attributes();
		setup_frame_elements();
	}


	private void initialize_attributes()
	{
		this.employeesListModel = new DefaultListModel();
		this.pendingListModel = new DefaultListModel();
		this.employeesLabel = new JLabel("Employees");
		this.employeesList = new JList<>(this.employeesListModel);
		this.removeButton = new JButton("Remove");
		this.addButton = new JButton("Add");
		this.selectEmployeeButton = new JButton("Select");
		this.selectProposalButton = new JButton("Select");
		this.viewHistoryButton = new JButton("View History");
		this.generateReportButton = new JButton("Generate Report");
		this.newNameTextField = new JTextField();
		this.startDateTextField = new JTextField();
		this.endDateTextField = new JTextField();
		this.baseTextField = new JTextField();
		this.baseButton = new JButton("Update Base");
		this.pendingLabel = new JLabel("Pending Requests");
		this.budgetLabel = new JLabel("Budget");
		this.pendingList = new JList<>(pendingListModel);
		this.budgetTextArea = new JTextArea();
		this.backButton = new JButton("Back");
		this.employeeScrollPane = new JScrollPane(this.employeesList);
		this.pendingScrollPane = new JScrollPane(this.pendingList);

		this.reset_components = Arrays.asList();  //TODO: populate
	}


	private void setup_frame_elements()
	{
		this.employeesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.employeesList.setSelectedIndex(0);
		this.employeesList.setVisibleRowCount(5);
		this.pendingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.pendingList.setSelectedIndex(0);
		this.pendingList.setVisibleRowCount(5);

		SequentialGroup group;
		Alignment type;

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.LEADING;

		add_components(group, type, Arrays.asList(this.employeesLabel, this.employeeScrollPane, 
		  this.selectEmployeeButton, this.pendingLabel, this.pendingScrollPane, this.selectProposalButton, 
		  this.backButton));
 		add_components(group, type, Arrays.asList(this.removeButton, this.addButton, this.newNameTextField));
 		add_components(group, type, Arrays.asList(this.budgetLabel, this.budgetTextArea, this.baseTextField, 
		  this.baseButton, this.startDateTextField, this.endDateTextField, this.viewHistoryButton,
		  this.generateReportButton));
		layout.setHorizontalGroup(group);

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.BASELINE;
		add_components(group, type, Arrays.asList(this.employeesLabel, this.budgetLabel));
 		add_components(group, type, Arrays.asList(this.employeeScrollPane, this.removeButton, this.budgetTextArea));
		add_components(group, type, Arrays.asList(this.newNameTextField, this.baseButton));
		add_components(group, type, Arrays.asList(this.pendingLabel));
		add_components(group, type, Arrays.asList(this.pendingScrollPane, this.startDateTextField));
		add_components(group, type, Arrays.asList(this.selectProposalButton, this.endDateTextField));
		add_components(group, type, Arrays.asList(this.backButton, this.viewHistoryButton));
		add_components(group, type, Arrays.asList(this.generateReportButton));
		layout.setVerticalGroup(group);

		// layout.linkSize(SwingConstants.HORIZONTAL, selectEmployeeViewButton, selectManagerViewButton, selectLogoutButton);
		this.getContentPane().setLayout(layout);

		// CALLBACKS
		backButton.addActionListener(e -> gui.hide_all_frames_except(gui.getManagerSelectionFrame()));
		addButton.addActionListener(e -> addEmployee());
	}


	// —————————————————————————————————————————————————— GETTERS —————————————————————————————————————————————————— //

	public JFrame getScreenFrame()
	{
		return this.screenFrame;
	}


	public JLabel getEmployeesLabel()
	{
		return this.employeesLabel;
	}


	public DefaultListModel getEmployeesListModel()
	{
		return this.employeesListModel;
	}


	public JList getEmployeesList()
	{
		return this.employeesList;
	}


	public JButton getRemoveButton()
	{
		return this.removeButton;
	}


	public JButton getAddButton()
	{
		return this.addButton;
	}


	public JTextField getNewNameTextField()
	{
		return this.newNameTextField;
	}


	public JLabel getPendingLabel()
	{
		return this.pendingLabel;
	}


	public DefaultListModel getPendingListModel()
	{
		return this.pendingListModel;
	}


	public JList getPendingList()
	{
		return this.pendingList;
	}


	public JButton getSelectEmployeeButton()
	{
		return this.selectEmployeeButton;
	}


	public JButton getSelectProposalButton()
	{
		return this.selectProposalButton;
	}


	public JLabel getBudgetLabel()
	{
		return this.budgetLabel;
	}


	public JTextArea getBudgetTextArea()
	{
		return this.budgetTextArea;
	}


	public JTextField getBaseTextField()
	{
		return this.baseTextField;
	}


	public JButton getBaseButton()
	{
		return this.baseButton;
	}


	public JTextField getStartDateTextField()
	{
		return this.startDateTextField;
	}


	public JTextField getEndDateTextField()
	{
		return this.endDateTextField;
	}


	public JButton getViewHistoryButton()
	{
		return this.viewHistoryButton;
	}


	public JButton getGenerateReportButton()
	{
		return this.generateReportButton;
	}


	public JButton getBackButton()
	{
		return this.backButton;
	}


	public JScrollPane getEmployeeScrollPane()
	{
		return this.employeeScrollPane;
	}


	public JScrollPane getPendingScrollPane()
	{
		return this.pendingScrollPane;
	}


	// ————————————————————————————————————————————————— CALLBACKS ————————————————————————————————————————————————— //

	private void addEmployee()
	{
		//TODO
	}
}