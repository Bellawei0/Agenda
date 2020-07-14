import java.awt.*;
import javax.swing.*;

/*
 * Inside Bottom Panel
 * Contains day, week, etc buttons + Event List
 */
public class EventsPanel extends JPanel{
	public EventsPanel() {
		navigationPanel = new NavigationPanel();
		listPanel = new ListPanel();
		
		setLayout(new FlowLayout());
		//splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, navigationPanel, listPanel);
		
		
		add(new JLabel("This is where the events will be shown"));
	}
	
	private JSplitPane splitPane;
	private NavigationPanel navigationPanel;
	private ListPanel listPanel;
	
	private class NavigationPanel extends JPanel{
		private JButton dayBtn, weekBtn, monthBtn, agendaBtn;
		
		public NavigationPanel() {
			dayBtn = new JButton("Day");
			weekBtn = new JButton("Week");
			monthBtn = new JButton("Month");
			agendaBtn = new JButton("Agenda");
			
			
			setLayout(new GridLayout(1, 4));
			add(dayBtn);
			add(weekBtn);
			add(monthBtn);
			add(agendaBtn);
		}
	}
	
	private class ListPanel extends JPanel{
		
	}
}
