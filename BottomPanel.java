import javax.swing.*;
import java.awt.*;

/**
 * A class that represents Bottom Panel Divided into Two parts: Calendar Panel and Events Panel
 *  * @author Jyoti Suri, Bella wei, Jennifer yang
 *  * @Version 1.0
 */
public class BottomPanel extends JPanel{

	public BottomPanel() {
		calendarPanel = new CalendarPanel();
		Model model = new Model();
		eventsPanel = new EventsPanel(model);
		
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(1, 2));
		add(calendarPanel);
		add(eventsPanel);
	}
	private CalendarPanel calendarPanel;
	private EventsPanel eventsPanel;
}