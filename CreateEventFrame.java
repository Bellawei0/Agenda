import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * A class that represents a frame for creating a new Event
 *
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 2.0
 */
public class CreateEventFrame extends JFrame {
	private DataModel model;
	private boolean conflict = false;

	/**
	 * Constructor of createEventFrame
	 * Creates a create event frame
	 * @param model
	 */
	public CreateEventFrame(DataModel model) {
		JFrame frame = new JFrame("Create a New Event");
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(5, 2));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setVisible(true);
		frame.setBackground(Color.PINK);

		JLabel eventName = new JLabel(" Enter an Event Name: ");
		JTextField eventNameInput = new JTextField(30);

		JLabel date = new JLabel(" Date (MM/DD/YYYY): ");
		JTextField dateInput = new JTextField();


		JLabel startTime = new JLabel(" Starting Time: ");
		JTextField startTimeInput = new JTextField();

		JLabel endTime = new JLabel(" Ending Time: ");
		JTextField endTimeInput = new JTextField();

		JButton saveBtn = new JButton("Save");
		saveBtn.setForeground(Color.BLUE);
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setForeground(Color.BLUE);

		panel.add(eventName);
		panel.add(eventNameInput);
		panel.add(date);
		panel.add(dateInput);
		panel.add(startTime);
		panel.add(startTimeInput);
		panel.add(endTime);
		panel.add(endTimeInput);
		panel.add(cancelBtn);
		panel.add(saveBtn);
		frame.add(panel);

		/**
		 * Save event into data model
		 */
		saveBtn.addActionListener(e -> {
			Event event;
			TimeInterval timeInterval;
			System.out.println("Debug in createEventFrame" + model);
			System.out.println("Debug: I'm here -> saveBtn");
			String eventN = eventNameInput.getText();
			String eventDate = dateInput.getText();
			String startT = startTimeInput.getText();
			String endT = endTimeInput.getText();


			String[] dateArray = eventDate.split("/");
			int year = Integer.parseInt(dateArray[2]);
			int month = Integer.parseInt(dateArray[0]);
			int day = Integer.parseInt(dateArray[1]);
			int eventStartTime = Integer.parseInt(startT);
			int eventEndTime = Integer.parseInt(endT);


			LocalDateTime dateTime = LocalDateTime.of(year, month, day, eventStartTime, 0);
			timeInterval = new TimeInterval(eventStartTime, eventEndTime);
			event = new Event(eventN, year, month, month, timeInterval);


			/**
			 * Checks if there is a conflict
			 */
			TreeMap<LocalDateTime, ArrayList<Event>> eventMap = model.getData();
			for (Entry<LocalDateTime, ArrayList<Event>> entry : eventMap.entrySet()) {
				ArrayList<Event> events = entry.getValue();
				LocalDateTime t=  entry.getKey();
				for (int i = 0; i < events.size(); i++) {	
					if(t.toLocalDate().equals(dateTime.toLocalDate())) {
						boolean testConflict = events.get(i).getT().timeOverLap(timeInterval);
						if (testConflict == true) {
							JOptionPane.showMessageDialog(frame, "Time Conflict");
							conflict = true;
							frame.dispose();
						}

					}

				}
				
			}
			if (conflict==false) {
				model.update(dateTime, event);
				frame.dispose();

			}

		});

		/**
		 * Exits create event frame 
		 */
		cancelBtn.addActionListener(e -> {
			frame.dispose();
		});
	}

}