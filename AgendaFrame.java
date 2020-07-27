import javax.swing.*;
import java.awt.*;

/**
 * A class that represents Agenda Frame that allows user to check event based on a period time
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */
public class AgendaFrame extends JFrame {
    public AgendaFrame() throws HeadlessException {
        JFrame frame = new JFrame("Please Enter Starting Date And Ending Date:  ");
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3,2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,100);
        frame.setVisible(true);

        JLabel startDate = new JLabel("Starting Date: ");
        JTextField startDateInput= new JTextField();

        JLabel endDate = new JLabel("Ending Date: ");
        JTextField endDateInput = new JTextField();

        JButton confirmBtn = new JButton("Confirm");
        JButton cancelBtn = new JButton("Cancel");


        panel.add(startDate);
        panel.add(startDateInput);
        panel.add(endDate);
        panel.add(endDateInput);
        panel.add(confirmBtn);
        panel.add(cancelBtn);

        frame.add(panel);

        // Add actionListener
        confirmBtn.addActionListener(e -> {
            System.out.println("Debug: confirmBtn in AgendaFrame");
            // parse input
            // Fetch data
            // Print event

        });

        cancelBtn.addActionListener(e -> {
            frame.dispose();
        });
    }


}