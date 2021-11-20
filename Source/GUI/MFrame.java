import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import java.util.List;
import java.util.Arrays;


class MFrame extends JFrame
{
	protected GUI gui;
	protected GroupLayout layout;
	protected List<JTextField> reset_components;  // components to reset when focus is regained


	public MFrame(GUI gui, String title, Boolean visible)
	{
		super(title);
		this.gui = gui;

		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 350);
		this.setLocationRelativeTo(null);
		this.setVisible(visible);

		this.layout = new GroupLayout(this.getContentPane());
		this.layout.setAutoCreateGaps(true);
		this.layout.setAutoCreateContainerGaps(true);
	}


	protected void add_components(SequentialGroup group, Alignment group_type, List<JComponent> components)
	{
		ParallelGroup parallel_group = layout.createParallelGroup(group_type);
		for(int x = 0; x < components.size(); x++)
		{
			parallel_group.addComponent(components.get(x));
		}
		group.addGroup(parallel_group);
	}


	public void focus()
	{
		reset();
		gui.hide_all_frames_except(this);
	}


	public void reset()
	{
		for(int x = 0; x < reset_components.size(); x++)
		{
			reset_components.get(x).setText("");
		}
	}
}
