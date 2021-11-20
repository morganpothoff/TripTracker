import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import java.util.List;
import java.util.Arrays;


class ManagerSelectionFrame extends MFrame
{
	private JButton managerViewButton;
	private JButton employeeViewButton;
	private JButton logoutButton;


	public ManagerSelectionFrame()
	{
		super("Manager Select", false);
	   
		initialize_attributes();
		setup_frame_elements();
	}


	private void initialize_attributes()
	{
		this.managerViewButton = new JButton("Manager Menu");
		this.employeeViewButton = new JButton("Employee Menu");
		this.logoutButton = new JButton("Logout");
	}


	// Add UI element to frame
	private void setup_frame_elements()
	{
		SequentialGroup group;
		Alignment type;

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.LEADING;
		add_components(group, type, Arrays.asList(this.employeeViewButton, this.logoutButton));
		add_components(group, type, Arrays.asList(this.managerViewButton));
		layout.setHorizontalGroup(group);

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.BASELINE;
		add_components(group, type, Arrays.asList(this.employeeViewButton, this.managerViewButton));
		add_components(group, type, Arrays.asList(this.logoutButton));
		layout.setVerticalGroup(group);

		layout.linkSize(SwingConstants.HORIZONTAL, this.employeeViewButton, this.managerViewButton, this.logoutButton);
		this.getContentPane().setLayout(layout);
	}


	// —————————————————————————————————————————————————— GETTERS —————————————————————————————————————————————————— //
	
	public JButton getManagerViewButton()
	{
		return this.managerViewButton;
	}


	public JButton getEmployeeViewButton()
	{
		return 	this.employeeViewButton;
	}


	public JButton getLogoutButton()
	{
		return 	this.logoutButton;
	}
}