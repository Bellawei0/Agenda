import java.awt.*;
import javax.swing.*;
import java.util.*;

/*
 * Bottom Panel divided into two parts: Calendar Panel and events Panel
 */
public class BottomPanel extends JPanel{

	public BottomPanel(View parent) {
		calendarPanel = new CalendarPanel();
		this.parent = parent;
		ArrayList<Event> data = new ArrayList<>();
		if (parent.getController() != null)
			data = parent.getController().getDataFromModel();

		eventsPanel = new EventsPanel(data);

		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(1, 2));
		add(calendarPanel);
		add(eventsPanel);
	}

	public void render() {
		eventsPanel.render();
	}

	private CalendarPanel calendarPanel;
	private EventsPanel eventsPanel;
	private View parent;
}
