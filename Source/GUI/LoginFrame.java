import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import java.util.List;
import java.util.Arrays;


class LoginFrame extends MFrame
{
	private JLabel idLabel;
	private JTextField idTextField;
	private JLabel passwordLabel;
	private JTextField passwordTextField;
	private JButton registerButton;
	private JButton loginButton;
	private JButton exitButton;


	public LoginFrame(GUI gui)
	{
		super(gui, "Login", true);
	   
		initialize_attributes();
		setup_frame_elements();
	}


	private void initialize_attributes()
	{
		idLabel = new JLabel("User ID");
		passwordLabel = new JLabel("Password");
		idTextField = new JTextField();
		passwordTextField = new JTextField();
		registerButton = new JButton("Register");
		loginButton = new JButton("Login");
		exitButton = new JButton("Exit");

		reset_components = Arrays.asList(idTextField, passwordTextField);
	}


	// Add UI element to frame
	private void setup_frame_elements()
	{
		SequentialGroup group;
		Alignment type;

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.LEADING;
		add_components(group, type, Arrays.asList(this.idLabel, this.passwordLabel));
		add_components(group, type, Arrays.asList(this.idTextField, this.passwordTextField));
		add_components(group, type, Arrays.asList(this.registerButton, this.loginButton, this.exitButton));
		layout.setHorizontalGroup(group);

		group = layout.createSequentialGroup();
		type = GroupLayout.Alignment.BASELINE;
		add_components(group, type, Arrays.asList(this.idLabel, this.idTextField, this.registerButton));
		add_components(group, type, Arrays.asList(this.passwordLabel, this.passwordTextField, this.loginButton));
		add_components(group, type, Arrays.asList(this.exitButton));
		layout.setVerticalGroup(group);

		layout.linkSize(SwingConstants.HORIZONTAL, this.registerButton, this.loginButton);
		this.getContentPane().setLayout(layout);

		exitButton.addActionListener(e -> gui.exit());
		loginButton.addActionListener(e -> login());
		registerButton.addActionListener(e -> gui.getRegisterFrame().focus());
	}


	// —————————————————————————————————————————————————— GETTERS —————————————————————————————————————————————————— //

	public JLabel getIdLabel()
	{
		return idLabel;
	}


	public JTextField getIdTextField()
	{
		return idTextField;
	}


	public JLabel getPasswordLabel()
	{
		return passwordLabel;
	}


	public JTextField getPasswordTextField()
	{
		return passwordTextField;
	}


	public JButton getRegisterButton()
	{
		return registerButton;
	}


	public JButton getLoginButton()
	{
		return loginButton;
	}


	public JButton getExitButton()
	{
		return exitButton;
	}


	// ————————————————————————————————————————————————— CALLBACKS ————————————————————————————————————————————————— //

	private void login()
	{
		String username = idTextField.getText();
		String password = passwordTextField.getText();
		System.out.println("LoginFrame::login::Username: " + idTextField);

		try {
			Users user = new Users(username);
			if(!user.getPassword().equals(password)) {
				throw new Exception("Invalid Password");
			}
	
			this.gui.setUser(user);
			System.out.println(String.format("User %s logged in", username));

			gui.getEmployeeFrame().focus();
		}
		catch(Exception e) {
			System.out.println(e.toString());
			idTextField.setText("invalid id or password");
		}
	}
}
