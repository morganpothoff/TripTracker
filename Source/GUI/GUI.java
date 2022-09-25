// Chris Hutcherson
import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Arrays;


public class GUI {
	private EmployeeFrame employeeFrame;
	private LoginFrame loginFrame;
	private ManagerFrame managerFrame;
	private ManagerSelectionFrame managerSelectionFrame;
	private ProposalFrame proposalFrame;
	private RegisterFrame registerFrame;
	private TripFrame tripFrame;
	private List<MFrame> frames;

	// DATA
	private Users user;


	public GUI() {
		employeeFrame = new EmployeeFrame(this);
		loginFrame = new LoginFrame(this);
		managerFrame = new ManagerFrame(this);
		managerSelectionFrame = new ManagerSelectionFrame(this);
		proposalFrame = new ProposalFrame(this);
		registerFrame = new RegisterFrame(this);
		tripFrame = new TripFrame(this);

		frames = Arrays.asList(loginFrame, employeeFrame, managerFrame, managerSelectionFrame, proposalFrame,
		  registerFrame, tripFrame);
	}

	// ————————————————————————————————————————————————— CALLBACKS ————————————————————————————————————————————————— //
	// ————————————————————————————————————————————————————————————————————————————————————————————————————————————— //

	/**
	 * switch from manager menu to manager view
	 */
	private void showManagerScreen() {
		hide_all_frames_except(managerFrame);
		//TODO fill lists with appropriate names / info
		managerFrame.getEmployeesListModel().addElement("jerry man");
	}


	// ————————————————————————————————————————————————————————————————————————————————————————————————————————————— //

	/**
	 * clear login screen and only show login screen
	 */
	public void logout() {
		loginFrame.getIdTextField().setText("");
		loginFrame.getPasswordTextField().setText("");
		user = null;
		hide_all_frames_except(loginFrame);
	}

	/**
	 * exit program
	 */
	public void exit() {
		System.exit(0);
	}


	// —————————————————————————————————————————————————— GETTERS —————————————————————————————————————————————————— //

	public EmployeeFrame getEmployeeFrame()
	{
		return this.employeeFrame;
	}


	public LoginFrame getLoginFrame()
	{
		return this.loginFrame;
	}


	public ManagerFrame getManagerFrame()
	{
		return this.managerFrame;
	}


	public ManagerSelectionFrame getManagerSelectionFrame()
	{
		return this.managerSelectionFrame;
	}


	public ProposalFrame getProposalFrame()
	{
		return this.proposalFrame;
	}


	public RegisterFrame getRegisterFrame()
	{
		return this.registerFrame;
	}


	public TripFrame getTripFrame()
	{
		return this.tripFrame;
	}


	public Users getUser()
	{
		return this.user;
	}


	// —————————————————————————————————————————————————— GETTERS —————————————————————————————————————————————————— //

	public void setUser(Users user)
	{
		this.user = user;
	}


	// —————————————————————————————————————————————————— UTILITY —————————————————————————————————————————————————— //

	public void hide_all_frames_except(MFrame visible_screen)
	{
		for(int x = 0; x < frames.size(); x++)
		{
			frames.get(x).setVisible(frames.get(x) == visible_screen);
		}
	}
}
