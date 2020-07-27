
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Model of the MVC application
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */
public class Model {
    TreeMap<LocalDateTime, TreeSet<Event>> data;
    ArrayList<ChangeListener> listeners;
    private ViewType viewType;
    private EventsPanel eventsPanel;

    public enum ViewType {
        DAY, WEEK, MONTH, AGENDA
    }

    /**
     * Clone data from Treemap
     * @return  key and value
     */
    public TreeMap<LocalDateTime, TreeSet<Event>> getData(){
        return (TreeMap<LocalDateTime, TreeSet<Event>>) data.clone();
    }
    public EventsPanel getEventsPanel() {
        return eventsPanel;
    }

    public void setEventsPanel(EventsPanel eventsPanel) {
        this.eventsPanel = eventsPanel;
    }



    //Data
    public Model() {
        data = new TreeMap<>();
        listeners = new ArrayList<>();
    }

    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }




    public TreeSet<Event> getEvents(LocalDateTime date){
        return data.get(date);
    }


    public String displayEvent(TreeSet<Event> events){
        String eventInfo = "";
        if(events.size() != 0 ){
            for(Event event:events){
                eventInfo = event.getEventName() + event.getYear()+event.getEndingMonth();
            }
        }
        return eventInfo;
    }



    public boolean hasEvent(LocalDateTime date){
        TreeSet<Event> s = data.get(date);
            if(s == null)
                return false;
            else if(s.isEmpty())
                return false;
            return true;
        }

    //need to be added to panels so model updates any changes
    public void attach(ChangeListener c)
    {
        listeners.add(c);
    }

   // add the Events to the data
    public void update(LocalDateTime startD,Event event) {
        if(!data.containsKey(startD)){
            TreeSet<Event> events = new TreeSet<Event>();
            data.put(startD, events);
        }
        data.get(startD).add(event);
       
        System.out.println("Model DEBUG---> Saved in the TreeMap. TreeMap Size: " + data.size());

        for (ChangeListener l : listeners) {
            l.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Method to load input file
     * @param scanner
     */
    void loadFile(Scanner scanner) {
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

            LocalDateTime startD = LocalDateTime.of(year, startingMonth, 1,0,0);
            LocalDateTime endD = LocalDateTime.of(year, endingMonth, 31,23,59);

            event = new Event(eventName, year, startingMonth, endingMonth, startTime, endTime);

            while (startD.isBefore(endD) || startD.isEqual(endD) ) {
                //System.out.println("debug StartD------" + startD);
                // System.out.println("Debug EndD --------" + endD);
                for (char i : eventToken[4].toCharArray()) {
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
                        update(startD, event);
                    }
                }
                startD = startD.plusDays(1);
            }
        }
        System.out.println("Loading is done!");
    }


}
