/**
 * A Class represents an Event
 *
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 2.0
 */
public class Event {
    private String eventName;
    private int year;
    private int startingMonth;
    private int endingMonth;
    private TimeInterval t;

   /**
    * Constructor of Event
    * @param eventName
    * @param year
    * @param startingMonth
    * @param endingMonth
    * @param t
    */
    public Event(String eventName, int year, int startingMonth, int endingMonth, TimeInterval t) {
        this.eventName = eventName;
        this.year = year;
        this.startingMonth = startingMonth;
        this.endingMonth = endingMonth;
        this.t = t;
    }

    /**
     * Get time interval of Event
     * @return TimeInterval
     */
    public TimeInterval getT() {
        return t;
    }

    /**
     * Set time interval of Event
     * @param t
     */
    public void setT(TimeInterval t) {
        this.t = t;
    }

    /**
     * Get event name of Event
     * @return EventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Set event name of Event
     * @param eventName
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Get year of Event
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Set year of Event
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get the starting month of Event
     * @return
     */
    public int getStartingMonth() {
        return startingMonth;
    }

    /**
     * Set the starting month of Event
     * @param startingMonth
     */
    public void setStartingMonth(int startingMonth) {
        this.startingMonth = startingMonth;
    }

    /**
     * Get the ending month of event
     * @return endingMonth
     */
    public int getEndingMonth() {
        return endingMonth;
    }

    /**
     * Set the ending month of event
     * @param endingMonth
     */
    public void setEndingMonth(int endingMonth) {
        this.endingMonth = endingMonth;
    }

 
}