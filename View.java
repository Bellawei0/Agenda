import java.awt.*;
import javax.swing.*;

/*
 * Main View in GUI
 * Contains OptionsPanel and Bottom Panel
 */
public class View extends JFrame{

	public View() {
		// TODO Auto-generated constructor stub
		GridBagConstraints c = new GridBagConstraints();
		OptionsPanel optionsPanel = new OptionsPanel();
		BottomPanel bottomPanel = new BottomPanel();
		
		setLayout(new GridLayout(2, 1));
		
		/*
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.2;
		add(optionsPanel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.8;
		add(bottomPanel, c);
		*/
		
		add(optionsPanel);
		add(bottomPanel);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	public static void main(String[] args) {
		View frame = new View();
	}

}
