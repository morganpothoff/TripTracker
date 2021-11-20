import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import java.util.List;
import java.util.Arrays;


class ProposalFrame extends MFrame
{
	private JLabel locationLabel;
	private JLabel startLabel;
	private JLabel endLabel;
	private JLabel estimateLabel;
	private JLabel descriptionLabel;
	private JTextField locationTextField;
	private JTextField startTextField;
	private JTextField endTextField;
	private JTextField estimateTextField;
	private JTextField descriptionTextField;
	private JButton backButton;
	private JButton submitButton;
	private JComboBox managerList;


	public ProposalFrame(String title)
	{
		super(title, false);

		initialize_attributes();
		setup_frame_elements();
	}

	private void initialize_attributes()
	{
		locationLabel = new JLabel("Location");
		locationTextField = new JTextField("Ex: \"Austin, TX\"");
		startLabel = new JLabel("Start Date");
		startTextField = new JTextField("Ex: \"11/1/2021\"");
		endLabel = new JLabel("End Date");
		endTextField = new JTextField("Ex: \"11/8/2021\"");
		estimateLabel = new JLabel("Expense Estimate");
		estimateTextField = new JTextField("Ex: \"1250.75\"");
		descriptionLabel = new JLabel("Description");
		descriptionTextField = new JTextField("Ex: Conference with Mr. Doe about Project X");
		backButton = new JButton("Back");
		submitButton = new JButton("Submit");
		String[] managerStrings = { };
		managerList = new JComboBox(managerStrings);
	}


	// Add UI element to frame
	private void setup_frame_elements()
	{
		SequentialGroup group;
		Alignment type;

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.LEADING;
		add_components(group, type, Arrays.asList(this.locationLabel, this.locationTextField, this.startLabel,
		  this.startTextField, this.estimateLabel, this.estimateTextField, this.descriptionLabel,
		  this.descriptionTextField, this.submitButton, this.backButton));
		add_components(group, type, Arrays.asList(this.endLabel, this.endTextField, this.managerList));
		layout.setHorizontalGroup(group);

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.BASELINE;
		add_components(group, type, Arrays.asList(this.locationLabel));
		add_components(group, type, Arrays.asList(this.locationTextField));
		add_components(group, type, Arrays.asList(this.startLabel, this.endLabel));
		add_components(group, type, Arrays.asList(this.startTextField, this.endTextField));
		add_components(group, type, Arrays.asList(this.estimateLabel));
		add_components(group, type, Arrays.asList(this.estimateTextField));
		add_components(group, type, Arrays.asList(this.descriptionLabel));
		add_components(group, type, Arrays.asList(this.descriptionTextField));
		add_components(group, type, Arrays.asList(this.submitButton, this.managerList));
		add_components(group, type, Arrays.asList(this.backButton));
		layout.setVerticalGroup(group);

		//TODO: needs real values
		// layout.linkSize(SwingConstants.HORIZONTAL, this.employeeProposalButton, this.cancelPropButton);
		this.getContentPane().setLayout(layout);
	}


	// —————————————————————————————————————————————————— GETTERS —————————————————————————————————————————————————— //

	public JLabel getLocationLabel()
	{
		return this.locationLabel;
	}


	public JLabel getStartLabel()
	{
		return this.startLabel;
	}


	public JLabel getEndLabel()
	{
		return this.endLabel;
	}


	public JLabel getEstimateLabel()
	{
		return this.estimateLabel;
	}


	public JLabel getDescriptionLabel()
	{
		return this.descriptionLabel;
	}


	public JTextField getLocationTextField()
	{
		return this.locationTextField;
	}


	public JTextField getStartTextField()
	{
		return this.startTextField;
	}


	public JTextField getEndTextField()
	{
		return this.endTextField;
	}


	public JTextField getEstimateTextField()
	{
		return this.estimateTextField;
	}


	public JTextField getDescriptionTextField()
	{
		return this.descriptionTextField;
	}


	public JButton getBackButton()
	{
		return this.backButton;
	}


	public JButton getSubmitButton()
	{
		return this.submitButton;
	}


	public JComboBox getManagerList()
	{
		return this.managerList;
	}


}