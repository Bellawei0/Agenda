import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.event.ChangeListener;
/**
 * Model of the MVC application
 * @author jennifer yang
 *
 */
public class Model {
	TreeMap<LocalDateTime,Event>data;
	 ArrayList<ChangeListener> listeners;
	
	 //Data
	public Model(TreeMap<LocalDateTime,Event>d) {
		data=d;
		listeners= new ArrayList<ChangeListener>();
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
	
	 //add the Events to the data
	public void update(LocalDateTime t,Event s) {
		data.put(t, s);
	}
	

}
