import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Controllers of the MVC application
 *
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */
public class OptionsPanel extends JPanel {
    private JButton[] leftMenuBtn;
    private JButton[] rightMenuBtn;
    private Model model;
    private JPanel leftPanel, rightPanel;
    private EventsPanel eventsPanel;
    private CalendarPanel calendarPanel;
    private String viewType;
    LocalDate currentDay;


    /**
     * @param model
     */
    public OptionsPanel(Model model) {
        this.model = model;
        currentDay = LocalDate.now();
        viewType = "DAY";
        calendarPanel = new CalendarPanel();
        eventsPanel = new EventsPanel();
        setLayout(new BorderLayout());
        leftPanel = new JPanel();
        rightPanel = new JPanel();


        leftMenuBtn = new JButton[3];
        String[] leftHeader = {">", "<", "Today"};
        for (int i = 0; i < leftHeader.length; i++) {
            leftMenuBtn[i] = new JButton(leftHeader[i]);
            leftPanel.add(leftMenuBtn[i], SwingConstants.CENTER);

            leftMenuBtn[i].addActionListener(e -> {
                if (e.getSource() == leftMenuBtn[2]) {
                    calendarPanel.displayDate(currentDay);
                    if (viewType.equals("DAY")) {
                        eventsPanel.displayDayView();
                    } else if (viewType.equals("WEEK")) {
                        eventsPanel.displayWeekView();
                    } else {
                        eventsPanel.displayMonthView();
                    }
                    //TODO: Need GUI
                    System.out.println("Debug in OptionPanel" + currentDay);
                } else if (e.getSource() == leftMenuBtn[1]) {
                    //TODO: previousBtn bases on Day, Week, Month View
                    if (viewType.equals("DAY")) {
                        calendarPanel.displayDate(currentDay.minusDays(1));
                        eventsPanel.displayDayView();
                    } else if (viewType.equals("WEEK")) {
                        calendarPanel.displayDate(currentDay.minusWeeks(1));
                        eventsPanel.displayWeekView();
                    } else {
                        calendarPanel.displayDate(currentDay.minusMonths(1));
                        eventsPanel.displayMonthView();
                    }
                } else if (e.getSource() == leftMenuBtn[0]) {
                    //TODO: nextBtn bases on Day, Week, Month View
                }
            });
        }

        rightMenuBtn = new JButton[5];
        String[] rightHeader = {"From File", "Agenda", "Month", "Week", "Day"};
        for (int i = 0; i < rightMenuBtn.length; i++) {
            rightMenuBtn[i] = new JButton(rightHeader[i]);
            rightPanel.add(rightMenuBtn[i], SwingConstants.CENTER);

            rightMenuBtn[i].addActionListener(e -> {
                if (e.getSource() == rightMenuBtn[4]) {
                    //TODO: Diplay Day view based on ClickDay and CurrentDay
                    eventsPanel.displayDayView();
                    System.out.println("display day View");
                } else if (e.getSource() == rightMenuBtn[3]) {
                    System.out.println("display Week View");
                    //TODO: week view
                } else if (e.getSource() == rightMenuBtn[2]) {
                    System.out.println("display month view");
                    //TODO: Month View
                } else if (e.getSource() == rightMenuBtn[1]) {
                    System.out.println("display Agenda view");
                    //TODO: Agenda View
                } else if (e.getSource() == rightMenuBtn[0]) {
                    FileDialog fileDialog = new FileDialog(new JFrame());
                    fileDialog.setVisible(true);
                    File[] file = fileDialog.getFiles();
                    Scanner scanner;
                    if (file.length > 0) {
                        try {
                            scanner = new Scanner(file[0]);
                            model.loadFile(scanner);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                }
            });
        }
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }
}






