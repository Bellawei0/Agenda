/**
 * A Class represents TimeInterval of an Event
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */

public class TimeInterval implements Comparable<TimeInterval>{
    private int startingTime;
    private int endingTime;

    /**
     * Constructor of TimeInterval
     * @param startingTime the starting time of an event
     * @param endingTime   the ending time of an event
     */
    public TimeInterval(int startingTime, int endingTime) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    @Override
    public int compareTo(TimeInterval o) {
        return 0;
    }



}