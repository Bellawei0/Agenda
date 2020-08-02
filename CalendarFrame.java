import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * View of the MVC Application
 * A class that represents Calendar Frame that contains two panel, the left side to display month calendar, the right
 * side to display event list based on day, week, month, agenda week
 *
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 2.0
 */
public class CalendarFrame extends JFrame implements ChangeListener {

    private DataModel model;
    private LocalDate firstDayOfMonth, currentDay;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private String eventInfo;
    private String viewType;

    private ArrayList<JButton> buttons;
    private JTextArea textArea;
    private JPanel p1, p2, p3;
    private JButton monthLabel;
    private JScrollPane scroll;

    public static String[] DAYS_OF_WEEK = {"Sat", "Fri", "Thu", "Wed", "Tue", "Mon", "Sun"};

    public CalendarFrame(DataModel model) {
        this.model = model;
    }

    public void setPanels(Controller controller) {
        controller.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        setLayout(new BorderLayout());
        setSize(1200, 400);

        currentDay = LocalDate.now();
        setLayout(new BorderLayout());
        firstDayOfMonth = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        monthLabel = new JButton();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();

        JButton todayBtn = new JButton("Today");
        JButton previousBtn = new JButton("<");
        JButton nextBtn = new JButton(">");

        p3.add(todayBtn);
        p3.add(previousBtn);
        p3.add(nextBtn);

        ActionListener todayListener = (e -> {
            firstDayOfMonth = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
            currentDay = LocalDate.now();
            displayDate();
            if (viewType.equals("DAY")) {
                displayDayView();
            } else if (viewType.equals("WEEK")) {
                displayWeekView();
            } else {
                displayMonthView();
            }
        });

        todayBtn.addActionListener(todayListener);

        ActionListener navigateListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton navigateBtn = (JButton) e.getSource();
                if (viewType.equals("DAY")) {
                    if (navigateBtn == previousBtn) {
                        currentDay = currentDay.minusDays(1);
                    } else if (navigateBtn == nextBtn) {
                        currentDay = currentDay.plusDays(1);
                    }
                    firstDayOfMonth = LocalDate.of(currentDay.getYear(), currentDay.getMonth(), 1);
                    displayDate();
                    displayDayView();
                } else if (viewType.equals("WEEK")) {
                    if (navigateBtn == previousBtn) {
                        currentDay = currentDay.minusWeeks(1);
                    } else if (((JButton) e.getSource()) == nextBtn) {
                        currentDay = currentDay.plusWeeks(1);
                    }
                    firstDayOfMonth = LocalDate.of(currentDay.getYear(), currentDay.getMonth(), 1);
                    displayDate();
                    displayWeekView();
                } else {
                    if (navigateBtn == previousBtn) {
                        currentDay = currentDay.minusMonths(1);
                    } else if (navigateBtn == nextBtn) {
                        currentDay = currentDay.plusMonths(1);
                    }
                    currentDay = LocalDate.of(currentDay.getYear(), currentDay.getMonth(), 1);
                    firstDayOfMonth = LocalDate.of(currentDay.getYear(), currentDay.getMonth(), 1);
                    displayDate();
                    displayMonthView();
                }
            }

        };
        previousBtn.addActionListener(navigateListener);
        nextBtn.addActionListener(navigateListener);

        JButton createBtn = new JButton("Create");
        Font newButtonFont = new Font(createBtn.getFont().getName(), Font.BOLD, createBtn.getFont().getSize());
        createBtn.setFont(newButtonFont);

        JButton previous = new JButton("<< Previous");
        JButton next = new JButton("Next >>");

        createBtn.setOpaque(true);
        createBtn.setBorderPainted(false);
        createBtn.setBackground(new Color(102, 255, 102));

        p2.add(previous);
        p2.add(monthLabel);
        p2.add(next);
        p2.add(createBtn);

        p1.setLayout(new GridLayout(7, 7));
        displayDate();

        // Add actionListener create /previous / next Month
        ActionListener listener = (e -> {
            if (e.getSource() == createBtn) {
                new CreateEventFrame(model);
            } else if (e.getSource() == previous) {
                currentDay = currentDay.minusMonths(1);
                firstDayOfMonth = LocalDate.of(currentDay.getYear(), currentDay.getMonth(), 1);
                displayDate();
            } else if (e.getSource() == next) {
                currentDay = currentDay.plusMonths(1);
                firstDayOfMonth = LocalDate.of(currentDay.getYear(), currentDay.getMonth(), 1);
                displayDate();
            }
        });

        createBtn.addActionListener(listener);
        previous.addActionListener(listener);
        next.addActionListener(listener);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(p3);
        panel.add(p2);
        panel.add(p1);

        textArea = new JTextArea(40, 40);
        textArea.setEditable(false);
        eventInfo = "";
        scroll = new JScrollPane(textArea);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(controller, new BorderLayout().NORTH);
        rightPanel.add(scroll, new BorderLayout().CENTER);
        
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.X_AXIS));
        finalPanel.add(panel);
        finalPanel.add(rightPanel);
        add(finalPanel);

        // Show panels
        displayDayView();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void displayDate() {
        // Reset panels
        p1.removeAll();
        int monthOfMonth = currentDay.getMonthValue();

        // Set up month label
        Formatter formatter = new EventFormatter();
        monthLabel.setText(formatter.formatDate(currentDay));

        JButton[] weekBtn = new JButton[7];
        for (int i = 0; i < weekBtn.length; i++) {
            weekBtn[i] = new JButton(DAYS_OF_WEEK[i]);
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
                  
                } else {
                    JButton currentDayBtn = new JButton(Integer.toString(firstDayOfMonth.getDayOfMonth()));
                    if (firstDayOfMonth.equals(LocalDate.now()) && monthOfMonth == LocalDate.now().getMonthValue()) {
                        currentDayBtn.setForeground(Color.RED);
                        currentDayBtn.setBorderPainted(true);
                        currentDayBtn.setVisible(true);
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

        firstDayOfMonth = firstDayOfMonth.minusMonths(1);
        // TODO: set BorderPainted true to button that not today
        for (JButton clickBtn : buttons) {
        
            clickBtn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    currentDay = LocalDate.of(firstDayOfMonth.getYear(), firstDayOfMonth.getMonth(), Integer.parseInt(clickBtn.getText()));
                    clickBtn.setBorderPainted(true);
                    
                    for (JButton btn: buttons)
                    	btn.setForeground(Color.BLACK);
                    
                    clickBtn.setForeground(Color.cyan);
                    clickBtn.setVisible(true);

                    // Display view based on the current event Type
                    if (viewType.equals("DAY")) {
                        displayDayView();
                    } else if (viewType.equals("WEEK")) {
                        displayWeekView();
                    } else {
                        displayMonthView();
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    clickBtn.setBorderPainted(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    clickBtn.setBorderPainted(false);
                }
            });
            p1.revalidate();
            p1.repaint();
        }
    }

    /**
     * Updates whenever there is event added into TreeMap or reset a new event view
     *
     * @param e changeEvent
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        TreeMap<LocalDateTime, ArrayList<Event>> eventMap = this.model.getData();

        // Get Keys from treeMap
        ArrayList<LocalDateTime> keyEventList = new ArrayList<>();
        for (Map.Entry<LocalDateTime, ArrayList<Event>> entry : eventMap.entrySet()) {
            LocalDateTime key = entry.getKey();
            keyEventList.add(key);
        }

        // Compare current Day and TreeMap keys, get the common day as new Keys and save to Arraylist
        ArrayList<LocalDateTime> currentEventkey = new ArrayList<>();
        for (int i = 0; i < keyEventList.size(); i++) {
            LocalDateTime localDate = keyEventList.get(i);
            int day = localDate.getDayOfMonth();
            int month = localDate.getMonthValue();
            if (this.currentDay.getMonthValue() == month && this.currentDay.getDayOfMonth() == day) {
                currentEventkey.add(localDate);
            }
        }

        // Use new Keys to access value from treeMap and put currentDay event to ArrayList
        ArrayList<Event> currentDayEvents = new ArrayList<>();
        for (int j = 0; j < currentEventkey.size(); j++) {
            Event event = eventMap.get(currentEventkey.get(j)).get(0);
            currentDayEvents.add(event);
        }
        this.eventInfo = this.formatEvent(currentDayEvents);
    }

    /**
     * The method to display DayView
     */
    public void displayDayView() {
        viewType = "DAY";
        stateChanged(null);
        textArea.setText(this.eventInfo);
    }

    /**
     * The method to display WeekView
     */
    public void displayWeekView() {
        viewType = "WEEK";
        // find position of current
        LocalDate currentClick = LocalDate.of(this.currentDay.getYear(), this.currentDay.getMonth(), this.currentDay.getDayOfMonth());
        int dayOfWeek = this.currentDay.getDayOfWeek().getValue();
        String weekEventInfo = "";

        // First Day of Month position is Not at 7
        if (dayOfWeek != 7) {
            for (int i = dayOfWeek; i > 1; i--) {
                this.currentDay = this.currentDay.minusDays(1);
            }
            this.currentDay = this.currentDay.minusDays(1);
        }
        // First Day of Month position is at 7
        for (int i = 0; i <= 6; i++) {
            stateChanged(null);
            weekEventInfo += this.eventInfo;
            this.currentDay = this.currentDay.plusDays(1);
        }
        this.eventInfo = weekEventInfo;
        this.currentDay = currentClick;
        textArea.setText(eventInfo);
    }

    /**
     * The method to display Month View
     */
    public void displayMonthView() {
        viewType = "MONTH";
        LocalDate firstDay = LocalDate.of(this.currentDay.getYear(), this.currentDay.getMonth(), 1);
        LocalDate currentClick = LocalDate.of(this.currentDay.getYear(), this.currentDay.getMonth(), this.currentDay.getDayOfMonth());
        String monthEventInfo = "";
        int monthLength = firstDay.getMonth().length(firstDay.isLeapYear());

        for (int i = 0; i < monthLength; i++) {
            this.currentDay = firstDay;
            stateChanged(null);
            monthEventInfo += eventInfo;
            firstDay = firstDay.plusDays(1);
        }
        eventInfo = monthEventInfo;
        textArea.setText(eventInfo);
        this.currentDay = currentClick;

    }

    /**
     * The method to display Agenda View
     */
    public void displayAgendaView() {
        viewType = "AGENDA";
        LocalDate startDate = LocalDate.of(startingDate.getYear(), startingDate.getMonth(), startingDate.getDayOfMonth());
        LocalDate endDate = LocalDate.of(endingDate.getYear(), endingDate.getMonth(), endingDate.getDayOfMonth());
        String eventsInfo = "";

        while( startDate.isBefore(endDate) || startDate.isEqual(endDate) ) {
            this.currentDay = startDate;
            stateChanged(null);
            eventsInfo += eventInfo;
            startDate = startDate.plusDays(1);
        }
        eventInfo = eventsInfo;
        textArea.setText(eventInfo);


    }

    /**
     * @param startingDate the starting date for Agenda view
     */
    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * @param endingDate the ending date for agenda View
     */
    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }
    
    private String formatEvent(ArrayList<Event> events) {
        Formatter formatter = new EventFormatter();
        String eventsInfo = formatter.formatHeader(this.currentDay);
        if (events.size() != 0) {
            for (Event event : events) {
                eventsInfo += formatter.formatEvent(event);
            }
        }
        eventsInfo += formatter.formatFooter();
        return eventsInfo;
    }
}
