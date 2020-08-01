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

		// Add actionListener
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



			TreeMap<LocalDateTime, ArrayList<Event>> eventMap = model.getData();
			for (Entry<LocalDateTime, ArrayList<Event>> entry : eventMap.entrySet()) {
				System.out.println("reached array of event loop");
				ArrayList<Event> events = entry.getValue();
				LocalDateTime t=  entry.getKey();

				int d;
				for (int i = 0; i < events.size(); i++) {
					System.out.println("reached event loop");
					d = events.get(i).getStartingMonth();
					System.out.println(d+" ? "+ month);
					if(t.toLocalDate().equals(dateTime.toLocalDate())) {
						boolean testConflict = events.get(i).getT().timeOverLap(timeInterval);
						System.out.println("reached valid month loop");
						System.out.println(events.get(i).getT().getStartingTime()+ " "+ events.get(i).getT().getEndingTime());
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

		cancelBtn.addActionListener(e -> {
			frame.dispose();
		});
	}

}