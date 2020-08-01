/**
 * A class for testing the MVC Application which contains main method
 *
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 2.0
 */
public class CalendarTester {
    public static void main(String[] args) {
        DataModel model = new DataModel();
        CalendarFrame calendarFrame = new CalendarFrame(model);
        Controller controller = new Controller(model, calendarFrame);
        calendarFrame.setPanels(controller);
        model.attach(calendarFrame);
    }
}