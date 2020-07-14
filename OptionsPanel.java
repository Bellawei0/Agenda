import java.awt.*;
import javax.swing.*;

/*
 * There are two panels in a View --> OptionsPanel and BottomPanel
 */
public class OptionsPanel extends JPanel{

	/*
	 * This constructor generates two buttons: Today and From file
	 */
	public OptionsPanel() {
		// TODO Auto-generated constructor stub
		todayBtn = new JButton("Today");
		fromFileBtn = new JButton("From file");
		
		setLayout(new GridLayout(1, 2));
		
		add(todayBtn);
		add(fromFileBtn);
	}
	
	private JButton todayBtn;
	private JButton fromFileBtn;

}
