import java.awt.*;
import javax.swing.*;

/*
 * Bottom Panel divided into two parts: Calendar Panel and events Panel
 */
public class BottomPanel extends JPanel{

	public BottomPanel() {
		calendarPanel = new CalendarPanel();
		eventsPanel = new EventsPanel();
		
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(1, 2));
		add(calendarPanel);
		add(eventsPanel);
	}

	
	private CalendarPanel calendarPanel;
	private EventsPanel eventsPanel;
}
