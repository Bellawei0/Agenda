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
	TreeMap<LocalDateTime,String>data;
	 ArrayList<ChangeListener> listeners;
	
	public Model(TreeMap<LocalDateTime,String>d) {
		data=d;
		listeners= new ArrayList<ChangeListener>();
	}

	public ArrayList<String> getData(){
		return (ArrayList<String>) (data.clone());
	}
	
	 public void attach(ChangeListener c)
	   {
	      listeners.add(c);
	   }
	
	public void update(LocalDateTime t,String s) {
		data.put(t, s);
	}
}
