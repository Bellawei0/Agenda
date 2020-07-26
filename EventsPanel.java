import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * A Panel that represents Event List as day View, Week View, Month View, Agenda View
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */
public class EventsPanel extends JPanel implements ChangeListener {
    private JTextArea textArea;
    private String viewType;
    private String eventInfo;
    private CalendarPanel calendarPanel;
    private LocalDate currentDay;
    private JScrollPane scrollPane;
    private Model model;

    public EventsPanel(Model model) {
        calendarPanel = new CalendarPanel();
        setLayout(new BorderLayout());
        setSize(800, 700);
        this.model = model;

        //--------------------------TopPanel for display Menu---------------
//        topPanel = new JPanel();
//        rightMenuBtn = new JButton[5];
//        String[] rightHeader = {"From File", "Agenda", "Month", "Week", "Day"};
//        for (int i = 0; i < rightMenuBtn.length; i++) {
//            rightMenuBtn[i] = new JButton(rightHeader[i]);
//            topPanel.add(rightMenuBtn[i], SwingConstants.CENTER);
//
//            rightMenuBtn[i].addActionListener(e -> {
//                if (e.getSource() == rightMenuBtn[4]) {
//                    //TODO: Display Day view based on ClickDay and CurrentDay
//                    displayDayView();
//
//                } else if (e.getSource() == rightMenuBtn[3]) {
//                    //TODO: week view
//                    //displayWeekView();
//                    TreeMap<LocalDateTime, ArrayList<Event>> eventMap = model.getData();
//                    System.out.println("Debug in stateChanged" +eventMap);
//                } else if (e.getSource() == rightMenuBtn[2]) {
//                    //TODO: Month View
//                    displayMonthView();
//                } else if (e.getSource() == rightMenuBtn[1]) {
//                    //TODO: Agenda View
//                    displayAgendaView();
//                } else if (e.getSource() == rightMenuBtn[0]) {
//                    FileDialog fileDialog = new FileDialog(new JFrame());
//                    fileDialog.setVisible(true);
//                    File[] file = fileDialog.getFiles();
//                    Scanner scanner;
//                    if (file.length > 0) {
//                        try {
//                            scanner = new Scanner(file[0]);
//                            model.loadFile(scanner);
//                        } catch (FileNotFoundException fileNotFoundException) {
//                            fileNotFoundException.printStackTrace();
//                        }
//                    }
//                }
//            });
//        }

        //----------------------------scrollPane for display events-------------------------------------------
        textArea = new JTextArea(30, 30);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setBounds(20, 20, 600, 600);
        textArea.setBorder(new LineBorder(Color.BLACK));
        textArea.setText("Hello from other side");
        scrollPane.setBounds(20, 20, 600, 600);
        scrollPane.getViewport().setBackground(Color.WHITE);

        //add(topPanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        repaint();
        setVisible(true);
    }

    public EventsPanel() {

    }

    public void setCalendarPanel(CalendarPanel calendarPanel) {
        this.calendarPanel = calendarPanel;
        currentDay = calendarPanel.getClickDay();
    }

    /**
     * Update model and view when the new event adds
     *
     * @param e
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        currentDay = calendarPanel.getClickDay();
         System.out.println("Debug in EventsPanel CurrentDay" + currentDay);

        //Clone from treeMap
        TreeMap<LocalDateTime, ArrayList<Event>> eventMap = model.getData();

        // Get Keys from treeMap
        ArrayList<LocalDateTime> keyEventList = new ArrayList<>();
        for (Map.Entry<LocalDateTime, ArrayList<Event>> entry : eventMap.entrySet()) {
            LocalDateTime key = entry.getKey();
            keyEventList.add(key);
        }
        // System.out.println("KeyEventList" + keyEventList.size());

        // Compare currentDay and keys, get the common day as new Keys and save to Arraylist
        ArrayList<LocalDateTime> currentEventkey = new ArrayList<>();
        for (int i = 0; i < keyEventList.size(); i++) {
            LocalDateTime localDateTime = keyEventList.get(i);
            int day = localDateTime.getDayOfMonth();
            int month = localDateTime.getMonthValue();
            if (currentDay.getMonthValue() == month && currentDay.getDayOfMonth() == day) {
                currentEventkey.add(localDateTime);
            }
        }

        // Use new Keys to access value from treeMap
        ArrayList<Event> currentDayEvent = new ArrayList<>();
        for (int j = 0; j < currentEventkey.size(); j++) {
            Event event = eventMap.get(currentEventkey.get(j)).get(0);
            currentDayEvent.add(event);
        }
        System.out.println("Debug in EventsPane-------> currentDayEvent" + currentDayEvent.size());


        // ----------------------This is only for Debug-------------------------------------
//        for(int i = 0; i < currentDayEvent.size(); i++){
//            eventInfo = currentDayEvent.get(0).getEventName();
//        }

        // ---------------------------------------------------------------------------------
      //  eventInfo = model.displayEvent(currentDayEvent);
      //  System.out.println("Debug in EventsPane-------> EventInfo:     " + eventInfo);
    }


//    public void setTextArea() {
//        textArea.setText(eventInfo);
//        System.out.println("Debug ===========" +eventInfo);
//    }

    public String getViewType() {
        return viewType;
    }
//
//    public void setEventInfo() {
//        eventInfo = "This is just for testing";
//
//    }

    public String getEventInfo() {
        return eventInfo;
    }


    //TODO: set Day View
    public void displayDayView() {
        viewType = "DAY";
//        System.out.println("Debug:EventPanel DisplayDayView before" + eventInfo);
//        setEventInfo();
//        setTextArea();
//        System.out.println("");
        eventInfo = "This is Day view ";
        textArea.setText(eventInfo);

        System.out.println("This is Day View");
        // eventsPanel.displayEvent();
    }

    //TODO: set Week View
    public void displayWeekView() {
        viewType = "WEEK";
        eventInfo = "This is Week view ";
        textArea.setText(eventInfo);


        System.out.println("Debug:EventPanel DisplayDayView");
    }
    //TODO: set Month View

    public void displayMonthView() {
        viewType = "MONTH";
        eventInfo = "This is Month view ";
        textArea.setText(eventInfo);
        System.out.println("Debug:EventPanel displayMonthView");
    }

    //TODO: Set Agenda View
    public void displayAgendaView() {
        viewType = "AGENDA";
        eventInfo = "This is Agenda view ";
        textArea.setText(eventInfo);
        System.out.println("Debug:EventPanel displayAgendaView");

    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }


}







