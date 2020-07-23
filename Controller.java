import java.util.ArrayList;

public class Controller {
	
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
	
	private Model model;
	private View view;

}
