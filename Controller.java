import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Controller implements ActionListener{
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		//this.view.setController(this);
	}
	
	public Controller(Model m) {
		this.model= m;
	}
	
	public Model getModel() {
		return model;
	}
	
	public View getView() {
		return view;
	}
	
	public TreeMap<LocalDateTime, TreeSet<Event>> getDataFromModel(){
		return model.getData();
	}
	
	
	
	private Model model;
	private View view;
	@Override
	
	//action perform button actions
	public void actionPerformed(ActionEvent e) {
		String type = e.getActionCommand();
		System.out.println("reached controller");
		if(type.equalsIgnoreCase("day")) {
			
			System.out.println("reached button type");
		}
		
		
	}

}