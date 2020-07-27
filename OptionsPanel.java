 import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;



/**
 * Controllers of the MVC application
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
    LocalDateTime currentDay;


    /**
     * @param model
     */
    public OptionsPanel(Model model) {
        this.model = model;
        currentDay = LocalDateTime.now();
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
                    calendarPanel.displayDate(currentDay.toLocalDate());
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
                        calendarPanel.displayDate(currentDay.toLocalDate().minusDays(1));
                        eventsPanel.displayDayView();
                    } else if (viewType.equals("WEEK")) {
                        calendarPanel.displayDate(currentDay.toLocalDate().minusWeeks(1));
                        eventsPanel.displayWeekView();
                    } else {
                        calendarPanel.displayDate(currentDay.toLocalDate().minusMonths(1));
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


/*
public class OptionsPanel extends JPanel {

    private static JButton todayBtn, dayBtn, weekBtn, monthBtn, agendaBtn, previousBtn, nextBtn, fromFileBtn;
    Model model;
    Controller c;

   
    public OptionsPanel(Model model) {
        this.model = model;
        c= new Controller (model);
        // TODO Auto-generated constructor stub
        setLayout(new GridLayout(1, 6, 0, 0));

        todayBtn = new JButton("Today");
        previousBtn = new JButton("<");
        nextBtn = new JButton((">"));
      
        weekBtn = new JButton("Week");
        monthBtn = new JButton("Month");
        
        dayBtn = new JButton("Day");
        
        
        agendaBtn = new JButton("Agenda");
      

        fromFileBtn = new JButton("From file");
        fromFileBtn.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(new JFrame());
            fileDialog.setVisible(true);
            File[] file = fileDialog.getFiles();
            Scanner scanner;
            if (file.length > 0) {
                try {
                    scanner = new Scanner(file[0]);
                    loadFile(scanner);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });

        add(todayBtn);
        add(previousBtn);
        add(nextBtn);
        add(dayBtn);
        add(weekBtn);
        add(monthBtn);
        add(agendaBtn);
        add(fromFileBtn);
        
      
        
        setActionListener(c); //link buttons to controller
    }

   
    private void loadFile(Scanner scanner) {
        Event event;

        while (scanner.hasNextLine()) {
            String eventInfo = scanner.nextLine();
            String eventToken[] = eventInfo.split(";");

            String eventName = eventToken[0];
            int startTime = Integer.parseInt(eventToken[5]);
            int endTime = Integer.parseInt(eventToken[6]);
            int year = Integer.parseInt(eventToken[1]);
            int startingMonth = Integer.parseInt(eventToken[2]);
            int endingMonth = Integer.parseInt(eventToken[3]);

            LocalDateTime startD = LocalDateTime.of(year, startingMonth, 1, startTime, 0);
            LocalDateTime endD = LocalDateTime.of(year, endingMonth, 31, startTime, 0);

            event = new Event(eventName, year, startingMonth, endingMonth, startTime, endTime);

            while (startD.isBefore(endD)) {
                System.out.println("debug StartD------" + startD);
                System.out.println("Debug EndD --------" + endD);
                for (char i : eventToken[4].toCharArray()) {
                    // for(int j= 0; j< eventToken.length; j++) {
                    int dayOfWeek = 0;
                    switch (i) {
                        case 'M':
                            dayOfWeek = 1;
                            break;
                        case 'T':
                            dayOfWeek = 2;
                            break;
                        case 'W':
                            dayOfWeek = 3;
                            break;
                        case 'R':
                            dayOfWeek = 4;
                            break;
                        case 'F':
                            dayOfWeek = 5;
                            break;
                        case 'A':
                            dayOfWeek = 6;
                            break;
                        case 'S':
                            dayOfWeek = 7;
                            break;
                    }
                    if (startD.getDayOfWeek().getValue() == dayOfWeek) {
                        model.update(startD, event);
                    }
                }
                startD = startD.plusDays(1);
            }
        }
        System.out.println("Loading is done!");
    }
    
    //set action listener
    private void setActionListener(ActionListener l) {
    	todayBtn.addActionListener(l);
    	dayBtn.addActionListener(l);
    	weekBtn.addActionListener(l);
    	monthBtn.addActionListener(l); 
    	agendaBtn.addActionListener(l); 
    	previousBtn.addActionListener(l); 
    	nextBtn.addActionListener(l); 
    	fromFileBtn.addActionListener(l);
    	
    }
}

*/