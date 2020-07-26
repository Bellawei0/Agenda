import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A class that represents CalendarPanel which displays month, navigates to previous and next month
 * and Create a new Event
 *
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */
public class CalendarPanel extends JPanel {
    private ArrayList<JButton> buttons;
    private EventsPanel eventsPanel;
    private LocalDate firstDayOfMonth;
    private LocalDate currentDay;
    private LocalDate clickDay;
    private JButton[] weekBtn;
    private JButton[] leftMenuBtn;
    private JLabel monthLabel;
    private JPanel p1, p2, topPanel;

    /**
     * Constructor the CalendarPanel
     */
    public CalendarPanel() {

        p1 = new JPanel(new GridLayout(7, 7));
        p2 = new JPanel();
        monthLabel = new JLabel();
        currentDay = LocalDate.now();
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        topPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);


        JButton createBtn = new JButton("Create");
        JButton previous = new JButton("<< Previous");
        JButton next = new JButton("Next >>");

        createBtn.setOpaque(true);
        createBtn.setBorderPainted(false);
        createBtn.setBackground(new Color(102, 255, 102));

        p2.add(previous);
        p2.add(monthLabel);
        p2.add(next);
        p2.add(createBtn);

        displayDate(currentDay);

        // Add actionListener
        ActionListener listener = (e -> {
            if (e.getSource() == createBtn) {
                new CreateEventFrame();
            } else if (e.getSource() == previous) {
                currentDay = currentDay.minusMonths(1);
                displayDate(currentDay);
            } else if (e.getSource() == next) {
                currentDay = currentDay.plusMonths(1);
                displayDate(currentDay);
            }
        });

        createBtn.addActionListener(listener);
        previous.addActionListener(listener);
        next.addActionListener(listener);

        panel.add(topPanel);
        panel.add(p2);

        add(panel, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Set the new interface for the calendar panel based on Days
     *
     * @param currentDay the day to display calendar panel
     */
    public void displayDate(LocalDate currentDay) {
        //  Reset panel whenever calling this method
        clickDay = LocalDate.now();
        p1.removeAll();
        firstDayOfMonth = LocalDate.of(currentDay.getYear(), currentDay.getMonth(), 1);
        int monthOfMonth = currentDay.getMonthValue();

        // Set up month label
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        monthLabel.setText(formatter.format(currentDay));

        //  Add header
        weekBtn = new JButton[7];
        String[] header = {"Sat", "Fri", "Thu", "Wed", "Tue", "Mon", "Sun",};
        for (int i = 0; i < header.length; i++) {
            weekBtn[i] = new JButton(header[i]);
            p1.add(weekBtn[i], SwingConstants.CENTER);
            weekBtn[i].setForeground(Color.red);
            weekBtn[i].setBorderPainted(false);
        }

        /**
         *  Display the month Calendar and set the current Day to red
         *
         * July 2020
         *  *      *     *      1      2      3       4
         *  5     6      7      8      9      10      11
         *  12    13     14    15      16     17      18
         *  19    20     21    22     [23]    24      25
         *  26    27     28    29      30     31
         */
        buttons = new ArrayList<>();
        int firstDayPosition = firstDayOfMonth.getDayOfWeek().getValue();
        JButton[] dayBtn = new JButton[currentDay.lengthOfMonth()];

        // If: to display Calendar when firstDayPosition is not 7
        int restDays = 0;
        if (firstDayPosition != 7) {
            for (int i = 0; i < dayBtn.length + firstDayPosition; i++) {
                if (i < firstDayPosition) {
                    final JButton emptyBtn = new JButton("");
                    buttons.add(emptyBtn);
                    emptyBtn.setBorderPainted(false);
                    p1.add(emptyBtn);
                    // System.out.println("Debug d " + firstDayPosition);
                } else {
                    JButton currentDayBtn = new JButton(Integer.toString(firstDayOfMonth.getDayOfMonth()));
                    if (firstDayOfMonth.equals(LocalDate.now()) && monthOfMonth == LocalDate.now().getMonthValue()) {
                        currentDayBtn.setForeground(Color.RED);
                        currentDayBtn.setBorderPainted(true);
                    } else {
                        currentDayBtn.setBorderPainted(false);
                    }
                    p1.add(currentDayBtn);
                    firstDayOfMonth = firstDayOfMonth.plusDays(1);
                    buttons.add(currentDayBtn);
                }
            }

            restDays = 49 - firstDayPosition - currentDay.lengthOfMonth() - 7;
            for (int i = 0; i < restDays; i++) {
                JButton restBtn = new JButton(Integer.toString(firstDayOfMonth.getDayOfMonth()));
                restBtn.setVisible(false);
                p1.add(restBtn);
                firstDayOfMonth = firstDayOfMonth.plusDays(1);
                buttons.add(restBtn);
            }
        } else {
            // else: to display Calendar when firstDayPosition is 7
            for (int i = 0; i < dayBtn.length; i++) {
                JButton currentDayBtn = new JButton(Integer.toString(firstDayOfMonth.getDayOfMonth()));
                if (firstDayOfMonth.equals(LocalDate.now()) && monthOfMonth == LocalDate.now().getMonthValue()) {
                    currentDayBtn.setForeground(Color.RED);
                    currentDayBtn.setBorderPainted(true);
                } else {
                    currentDayBtn.setBorderPainted(false);
                }
                p1.add(currentDayBtn);
                firstDayOfMonth = firstDayOfMonth.plusDays(1);
                buttons.add(currentDayBtn);
            }
            restDays = 49 - currentDay.lengthOfMonth() - 7;
            for (int i = 0; i < restDays; i++) {
                JButton restBtn = new JButton(Integer.toString(firstDayOfMonth.getDayOfMonth()));
                restBtn.setVisible(false);
                p1.add(restBtn);
                buttons.add(restBtn);
            }
        }
        //Note: Here is already in next month after display all the buttons in current month

        // Find clicked Day when user clicks on the certain button
        firstDayOfMonth = firstDayOfMonth.minusMonths(1);
        System.out.println("Debug in CalendarPanel: Current month----->" + firstDayOfMonth);
        for (JButton button : buttons) {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    button.setForeground(Color.blue);
                    button.setBorderPainted(true);
                    firstDayOfMonth = LocalDate.of(firstDayOfMonth.getYear(), firstDayOfMonth.getMonth(), 1);
                    clickDay = LocalDate.of(firstDayOfMonth.getYear(), firstDayOfMonth.getMonth(), Integer.parseInt(button.getText()));
                    System.out.println("Clicked day " + clickDay);
                }
            });
        }
        p1.revalidate();
        p1.repaint();

        System.out.println("Debug in calendarPanel click day in displayDate----- " + clickDay);
    }

    public EventsPanel getEventsPanel() {
        return eventsPanel;
    }

    public void setEventsPanel(EventsPanel eventsPanel) {
        this.eventsPanel = eventsPanel;
        eventsPanel.displayDayView();
    }

    /**
     * Return the LocalDate object when users click on certain button
     *
     * @return the LocalDate Object
     */
    public LocalDate getClickDay() {
        return clickDay;
    }

    public void setClickDay(LocalDate clickDay) {
        this.clickDay = clickDay;
    }
}


