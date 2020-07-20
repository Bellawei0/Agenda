import javax.swing.event.ChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Model of the MVC application
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */
public class Model {
    TreeMap<LocalDateTime, ArrayList<Event>> data;
    ArrayList<ChangeListener> listeners;

    //Data
    public Model() {
        data = new TreeMap<LocalDateTime, ArrayList<Event>>();
        listeners = new ArrayList<ChangeListener>();
    }

    //returns the event
    public ArrayList<Event> getData(){
        return (ArrayList<Event>) (data.clone());
    }

    //need to be added to panels so model updates any changes
    public void attach(ChangeListener c)
    {
        listeners.add(c);
    }

   // add the Events to the data
    public void update(LocalDateTime t,Event s) {
        if(data.containsKey(t)){
            ArrayList<Event> events = data.get(t);
            events.add(s);
        }
        else {
            ArrayList<Event> events = new ArrayList<Event>();
            events.add(s);
            data.put(t,events);
        }
       // System.out.println("Model DEBUG" + data.size());
    }

}
