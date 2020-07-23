import java.time.LocalTime;

/**
 * A Class represents TimeInterval of an Event
 * @author
 * @version
 */

public class TimeInterval implements Comparable<TimeInterval>{
    private LocalTime startingTime;
    private LocalTime endingTime;

    /**
     * Constructor of TimeInterval
     * @param startingTime the starting time of an event
     * @param endingTime   the ending time of an event
     */
    public TimeInterval(LocalTime startingTime, LocalTime endingTime) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    /**
     * The method is to check if two events are conflict
     * @param newTi  TimeInterval of a new Event
     * @return true
     */
    public boolean checkConflict(TimeInterval newTi){
        if(endingTime.isBefore(startingTime) || startingTime.isAfter(endingTime)){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public int compareTo(TimeInterval o) {
        return startingTime.compareTo(o.startingTime);
    }
}
