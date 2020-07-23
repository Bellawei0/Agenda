/**
 * A Class represents an Event
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */
public class Event {
    String eventName;
    int year;
    int startingMonth;
    int endingMonth;
    int startingTime;
    int endingTime;

    public Event(String eventName, int year, int startingMonth, int endingMonth, int startingTime, int endingTime) {
        this.eventName = eventName;
        this.year = year;
        this.startingMonth = startingMonth;
        this.endingMonth = endingMonth;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
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

    public int getStartingMonth() {
        return startingMonth;
    }

    public void setStartingMonth(int startingMonth) {
        this.startingMonth = startingMonth;
    }

    public int getEndingMonth() {
        return endingMonth;
    }

    public void setEndingMonth(int endingMonth) {
        this.endingMonth = endingMonth;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }

    public int getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(int endingTime) {
        this.endingTime = endingTime;
    }
}
