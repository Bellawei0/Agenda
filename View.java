import javax.swing.*;
import java.awt.*;

/**
 * Main View of the MVC application: Contains OptionsPane and Bottom Panel
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */
public class View extends JFrame{

	public View() {
		// TODO Auto-generated constructor stub
		GridBagConstraints c = new GridBagConstraints();
		Model model = new Model();
		OptionsPanel optionsPanel = new OptionsPanel(model);

		BottomPanel bottomPanel = new BottomPanel();
		EventsPanel eventsPanel = new EventsPanel(model);
		model.attach(eventsPanel);
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1000,600));
		
		add(optionsPanel,BorderLayout.NORTH);
		add(bottomPanel,BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	public static void main(String[] args) {
		View frame = new View();

	}

}
