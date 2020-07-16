import java.time.LocalDate;

/**
 * A Class represents an Event
 * @author
 * @version
 */
public class Event {
    private String eventName;
    private int year;
    private LocalDate startDate;
    private LocalDate endDate;
    private TimeInterval t;

    /**
     * Constructor of an Event
     * @param eventName the name of an event
     * @param year      the year of an event
     * @param startDate the starting date  of an event
     * @param endDate   the ending date of an event
     * @param t         the time interval of an event
     */
    public Event(String eventName, int year, LocalDate startDate, LocalDate endDate, TimeInterval t) {
        this.eventName = eventName;
        this.year = year;
        this.startDate = startDate;
        this.endDate = endDate;
        this.t = t;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public TimeInterval getT() {
        return t;
    }

    public void setT(TimeInterval t) {
        this.t = t;
    }
}
