import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import java.util.List;
import java.util.Arrays;


class RegisterFrame extends MFrame
{
	private JLabel idLabel;
	private JTextField idTextField;
	private JLabel passwordLabel;
	private JTextField passwordTextField;
	private JLabel emailLabel;
	private JTextField emailTextField;
	private JButton registerButton;
	private JButton returnButton;
	private JCheckBox isManagerCheckBox;


	public RegisterFrame(String title)
	{
		super(title);

		this.initialize_attributes();
		this.setup_frame_elements();
	}


	private void initialize_attributes()
	{
		idLabel = new JLabel("User ID");
		passwordLabel = new JLabel("Password");
		idTextField = new JTextField();
		passwordTextField = new JTextField();
		emailLabel = new JLabel("Email");
		emailTextField = new JTextField();
		registerButton = new JButton("Register");
		returnButton = new JButton("Back");
		isManagerCheckBox = new JCheckBox("Check if Manager");
	}


	private void setup_frame_elements()
	{
		SequentialGroup group;
		Alignment type;

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.LEADING;
		add_components(group, type, Arrays.asList(this.idLabel, this.passwordLabel, this.emailLabel, this.returnButton));
		add_components(group, type, Arrays.asList(this.idTextField, this.passwordTextField, this.emailTextField, this.registerButton));
		add_components(group, type, Arrays.asList(this.isManagerCheckBox));
		layout.setHorizontalGroup(group);

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.BASELINE;
		add_components(group, type, Arrays.asList(this.idLabel, this.idTextField, this.isManagerCheckBox));
		add_components(group, type, Arrays.asList(this.passwordLabel, this.passwordTextField));
		add_components(group, type, Arrays.asList(this.emailLabel, this.emailTextField));
		add_components(group, type, Arrays.asList(this.returnButton, this.registerButton));
		layout.setVerticalGroup(group);

		layout.linkSize(SwingConstants.HORIZONTAL, this.registerButton, this.returnButton);
		this.getContentPane().setLayout(layout);
	}


	// —————————————————————————————————————————————————— GETTERS —————————————————————————————————————————————————— //

	public JTextField getIdTextField()
	{
		return this.idTextField;
	}


	public JTextField getPasswordTextField() {
		return this.passwordTextField;
	}


	public JTextField getEmailTextField() {
		return this.emailTextField;
	}


	public JButton getRegisterButton() {
		return this.registerButton;
	}


	public JButton getReturnButton() {
		return this.returnButton;
	}


	public JCheckBox getIsManagerCheckBox() {
		return this.isManagerCheckBox;
	}
}