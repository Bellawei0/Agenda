import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * A class that represents Agenda Frame that allows user to check event based on a period time
 *
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 2.0
 */
public class AgendaFrame extends JFrame {
    private LocalDate startLocalDate;
    private LocalDate endLocalDate;

    public AgendaFrame(CalendarFrame calendarFrame) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 100);
        setVisible(true);

        JLabel startDate = new JLabel("Start Date(MM/DD/YYYY): ");
        JTextField startDateInput = new JTextField();
        JLabel endDate = new JLabel("End Date(MM/DD/YYYY): ");
        JTextField endDateInput = new JTextField();
        JButton confirmBtn = new JButton("Confirm");
        JButton cancelBtn = new JButton("Cancel");

        panel.add(startDate);
        panel.add(startDateInput);
        panel.add(endDate);
        panel.add(endDateInput);
        panel.add(confirmBtn);
        panel.add(cancelBtn);
        add(panel);

        // Add actionListener
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startArray[] = startDateInput.getText().split("/");
                String endArray[] = endDateInput.getText().split("/");
                int startYear = Integer.parseInt(startArray[2]);
                int startMonth = Integer.parseInt(startArray[0]);
                int startDay = Integer.parseInt(startArray[1]);
                int endYear = Integer.parseInt(endArray[2]);
                int endMonth = Integer.parseInt(endArray[0]);
                int endDay = Integer.parseInt(endArray[1]);

                calendarFrame.setStartingDate(LocalDate.of(startYear, startMonth, startDay));
                calendarFrame.setEndingDate(LocalDate.of(endYear, endMonth, endDay));
                calendarFrame.displayAgendaView();
                dispose();
            }
        });
    }

    public LocalDate getStartLocalDate() {
        return startLocalDate;
    }

    public LocalDate getEndLocalDate() {
        return endLocalDate;
    }
}