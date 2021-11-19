import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.GroupLayout.ParallelGroup;
import java.util.List;
import java.util.Arrays;


class LoginFrame extends JFrame
{
	GroupLayout loginLayout;
	
	private JLabel idLabel;
	private JTextField idTextField;
	private JLabel passwordLabel;
	private JTextField passwordTextField;
	private JButton registerButton;
	private JButton loginButton;
	private JButton exitButton;


	public LoginFrame(String title)
	{
		super(title);

		// frame setups
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 350);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	   
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

		loginLayout = new GroupLayout(this.getContentPane());
	}


	// Add UI element to frame
	private void setup_frame_elements()
	{
		loginLayout.setAutoCreateGaps(true);
		loginLayout.setAutoCreateContainerGaps(true);

		SequentialGroup horizontal_group = loginLayout.createSequentialGroup();
		add_components(horizontal_group, Arrays.asList(this.idLabel, this.passwordLabel));
		add_components(horizontal_group, Arrays.asList(this.idTextField, this.passwordTextField));
		add_components(horizontal_group, Arrays.asList(this.registerButton, this.loginButton, this.exitButton));
		loginLayout.setHorizontalGroup(horizontal_group);

		SequentialGroup vertical_group = loginLayout.createSequentialGroup();
		add_components(vertical_group, Arrays.asList(this.idLabel, this.idTextField, this.registerButton));
		add_components(vertical_group, Arrays.asList(this.passwordLabel, this.passwordTextField, this.loginButton));
		add_components(vertical_group, Arrays.asList(this.exitButton));
		loginLayout.setVerticalGroup(vertical_group);

		loginLayout.linkSize(SwingConstants.HORIZONTAL, this.registerButton, this.loginButton);
		this.getContentPane().setLayout(loginLayout);
	}


	private void add_components(SequentialGroup sequential_group, List<JComponent> components)
	{
		ParallelGroup parallel_group = loginLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
		for(int x = 0; x < components.size(); x++)
		{
			parallel_group.addComponent(components.get(x));
		}
		sequential_group.addGroup(parallel_group);
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
}