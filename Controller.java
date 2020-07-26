import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener{
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		this.view.setController(this);
	}
	
	public Model getModel() {
		return model;
	}
	
	public View getView() {
		return view;
	}
	
	public ArrayList<Event> getDataFromModel(){
		return model.getData();
	}
	
	public void passType(Model m, String t) {
		model.setType(t);
	}
	
	private Model model;
	private View view;
	@Override
	
	//action perform button actions
	public void actionPerformed(ActionEvent e) {
		
		
	}

}