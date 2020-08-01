import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Model of the MVC application
 *
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 2.0
 */
public class DataModel {
    TreeMap<LocalDateTime, ArrayList<Event>> data;
    ArrayList<ChangeListener> listeners;
    public static ViewType viewType;

    public enum ViewType {
        DAY, WEEK, MONTH, AGENDA
    }

    public DataModel() {
        data = new TreeMap<>();
        listeners = new ArrayList<>();
        viewType = ViewType.DAY;
    }

    public TreeMap<LocalDateTime, ArrayList<Event>> getData() {
        return (TreeMap<LocalDateTime, ArrayList<Event>>) data.clone();
    }

    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }

    public boolean hasEvent(LocalDateTime day) {
        ArrayList<Event> s = data.get(day);
        if (s == null)
            return false;
        else if (s.isEmpty())
            return false;
        return true;
    }

    // Need to be added to panels so model updates any changes
    public void attach(ChangeListener c) {
        listeners.add(c);
    }

    // add the Events to the data
    public void update(LocalDateTime t, Event s) {
        if (data.containsKey(t)) {
            ArrayList<Event> events = data.get(t);
            events.add(s);
        } else {
            ArrayList<Event> events = new ArrayList<Event>();
            events.add(s);
            data.put(t, events);
        }
        for (Map.Entry<LocalDateTime, ArrayList<Event>> entry : data.entrySet()) {
            //System.out.println("Key: " + entry.getKey() + "\n" + ". Value: " + entry.getValue().get(0).getEventName() + "\n");
        }

        for (ChangeListener l : listeners) {
            l.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Method to load input file
     *
     * @param scanner
     */
    public void loadFile(Scanner scanner) {
        Event event;
        TimeInterval timeInterval;

        while (scanner.hasNextLine()) {
            String eventInfo = scanner.nextLine();
            String eventToken[] = eventInfo.split(";");
            String eventName = eventToken[0];
            int startTime = Integer.parseInt(eventToken[5]);
            int endTime = Integer.parseInt(eventToken[6]);
            int year = Integer.parseInt(eventToken[1]);
            int startingMonth = Integer.parseInt(eventToken[2]);
            int endingMonth = Integer.parseInt(eventToken[3]);

            timeInterval = new TimeInterval(startTime, endTime);
            event = new Event(eventName, year, startingMonth, endingMonth, timeInterval);

            LocalDateTime startDate = LocalDateTime.of(year, startingMonth, 1, startTime, 0);
            LocalDateTime endDate = LocalDateTime.of(year, endingMonth, 31, startTime, 0);

            while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
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
                    if (startDate.getDayOfWeek().getValue() == dayOfWeek) {
                        update(startDate, event);

                    }
                }
                startDate = startDate.plusDays(1);
            }
        }
        System.out.println("Loading is done!");
    }
}