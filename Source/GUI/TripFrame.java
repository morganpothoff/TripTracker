import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import java.util.List;
import java.util.Arrays;


class TripFrame extends MFrame
{
	private JLabel expensesLabel;
	private JLabel itemLabel;
	private JLabel costLabel;
	private JTextField itemTextField;
	private JTextField costTextField;
	private JButton addButton;
	private JButton backButton;
	private JButton finishButton;
	private DefaultListModel expenseModel;
	private JList expenseList;
	JScrollPane expenseScrollPane;


	public TripFrame()
	{
		super("Trip", false);
	   
		initialize_attributes();
		setup_frame_elements();
	}


	private void initialize_attributes()
	{
		this.expenseModel = new DefaultListModel();
		this.expenseList = new JList<>(this.expenseModel);
		this.expensesLabel = new JLabel("Expenses");
		this.itemLabel = new JLabel("Item");
		this.costLabel = new JLabel("Cost");
		this.itemTextField = new JTextField("Ex: \"Coffee,Starbucks,Austin\"");
		this.costTextField = new JTextField("Ex: \"5.89\"");
		this.addButton = new JButton("Add");
		this.backButton = new JButton("Back");
		this.finishButton = new JButton("Finish");
		this.expenseScrollPane = new JScrollPane(this.expenseList);
	}


	// Add UI element to frame
	private void setup_frame_elements()
	{
		SequentialGroup group;
		Alignment type;

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.LEADING;
		add_components(group, type, Arrays.asList(this.expensesLabel, this.expenseScrollPane, this.itemLabel,
		  this.itemTextField, this.addButton, this.backButton));
		add_components(group, type, Arrays.asList(this.costLabel, this.costTextField, this.finishButton));
		layout.setHorizontalGroup(group);

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.BASELINE;
		add_components(group, type, Arrays.asList(this.expensesLabel));
		add_components(group, type, Arrays.asList(this.expenseScrollPane));
		add_components(group, type, Arrays.asList(this.itemLabel, this.costLabel));
		add_components(group, type, Arrays.asList(this.itemTextField, this.costTextField));
		add_components(group, type, Arrays.asList(this.addButton));
		add_components(group, type, Arrays.asList(this.backButton, this.finishButton));
		layout.setVerticalGroup(group);

		this.getContentPane().setLayout(layout);

		expenseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		expenseList.setSelectedIndex(0);
		expenseList.setVisibleRowCount(10);
	}


	// —————————————————————————————————————————————————— GETTERS —————————————————————————————————————————————————— //

	public JLabel getExpensesLabel()
	{
		return this.expensesLabel;
	}


	public JLabel getItemLabel()
	{
		return this.itemLabel;
	}


	public JLabel getCostLabel()
	{
		return this.costLabel;
	}


	public JTextField getItemTextField()
	{
		return this.itemTextField;
	}


	public JTextField getCostTextField()
	{
		return this.costTextField;
	}


	public JButton getAddButton()
	{
		return this.addButton;
	}


	public JButton getBackButton()
	{
		return this.backButton;
	}


	public JButton getFinishButton()
	{
		return this.finishButton;
	}


	public DefaultListModel getExpenseModel()
	{
		return this.expenseModel;
	}


	public JList getExpenseList()
	{
		return this.expenseList;
	}
}