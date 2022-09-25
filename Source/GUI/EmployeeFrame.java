import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import java.util.List;
import java.util.Arrays;


class EmployeeFrame extends MFrame
{
	private JButton proposalButton;
	private JButton tripButton;
	private JButton logoutButton;
	private JButton cancelPropButton;
	private JLabel noteLabel;


	public EmployeeFrame(GUI gui)
	{
		super(gui, "Employee", false);

		initialize_attributes();
		setup_frame_elements();
	}

	private void initialize_attributes()
	{
		this.proposalButton = new JButton("Make Proposal");
		this.tripButton = new JButton("Manage Trip");
		this.logoutButton = new JButton(("Logout"));
		this.cancelPropButton = new JButton(("Cancel Proposal"));
		this.noteLabel = new JLabel("NOTE: n/a");

		this.reset_components = Arrays.asList();
	}


	// Add UI element to frame
	private void setup_frame_elements()
	{
		SequentialGroup group;
		Alignment type;

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.LEADING;
		add_components(group, type, Arrays.asList(this.proposalButton, this.cancelPropButton, this.noteLabel));
		add_components(group, type, Arrays.asList(this.tripButton, this.logoutButton));
		layout.setHorizontalGroup(group);

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.BASELINE;
		add_components(group, type, Arrays.asList(this.proposalButton, this.tripButton));
		add_components(group, type, Arrays.asList(this.cancelPropButton, this.logoutButton));
		add_components(group, type, Arrays.asList(this.noteLabel));
		layout.setVerticalGroup(group);

		layout.linkSize(SwingConstants.HORIZONTAL, this.proposalButton, this.cancelPropButton);
		this.getContentPane().setLayout(layout);

		// CALLBACKS
		logoutButton.addActionListener(e -> gui.logout());
		proposalButton.addActionListener(e -> gui.hide_all_frames_except(gui.getProposalFrame()));
		tripButton.addActionListener(e -> gui.hide_all_frames_except(gui.getTripFrame()));
	}


	// —————————————————————————————————————————————————— GETTERS —————————————————————————————————————————————————— //

	public JButton getProposalButton()
	{
		return this.proposalButton;
	}


	public JButton getTripButton()
	{
		return this.tripButton;
	}


	public JButton getLogoutButton()
	{
		return this.logoutButton;
	}


	public JButton getCancelPropButton()
	{
		return this.cancelPropButton;
	}


	public JLabel getNoteLabel()
	{
		return this.noteLabel;
	}
}