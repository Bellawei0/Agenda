import javax.swing.*;
import java.awt.*;

/**
 * A class that represents a frame for creating a new Event
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */
public class CreateEventFrame extends JFrame{
    public CreateEventFrame() {
        JFrame frame = new JFrame("Create a New Event");
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(7,2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,200);
        frame.setVisible(true);

        JLabel eventName = new JLabel("Event Name: ");
        JTextField eventNameInput = new JTextField(15);

        JLabel startMonth = new JLabel("Starting Month: ");
        JTextField startMonthInput = new JTextField();

        JLabel endMonth = new JLabel("Ending Month: ");
        JTextField endMonthInput = new JTextField();

        JLabel startTime = new JLabel("Starting Time: ");
        JTextField startTimeInput= new JTextField();

        JLabel endTime = new JLabel("Ending Time: ");
        JTextField endTimeInput = new JTextField();

        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        panel.add(eventName);
        panel.add(eventNameInput);
        panel.add(startMonth);
        panel.add(startMonthInput);
        panel.add(endMonth);
        panel.add(endMonthInput);
        panel.add(startTime);
        panel.add(startTimeInput);
        panel.add(endTime);
        panel.add(endTimeInput);
        panel.add(saveBtn);
        panel.add(cancelBtn);

        frame.add(panel);

        // Add actionListener
        saveBtn.addActionListener(e -> {
            System.out.println("Debug: I'm here -> saveBtn");
            // parse input
            // Check time conflict
            // Save new event to Model

        });

        cancelBtn.addActionListener(e -> {
            frame.dispose();
        });
    }

}