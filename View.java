import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

/*
 * Main View in GUI
 * Contains OptionsPanel and Bottom Panel
 */
public class View extends JFrame{

	public View() {
		// TODO Auto-generated constructor stub
		GridBagConstraints c = new GridBagConstraints();
		optionsPanel = new OptionsPanel(null);
		bottomPanel = new BottomPanel(this);

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

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void render() {
		bottomPanel.render();
	}

	private Controller controller;
	private OptionsPanel optionsPanel;
	private BottomPanel bottomPanel;

	public static void main(String[] args) {
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(model, view);
	}

}
