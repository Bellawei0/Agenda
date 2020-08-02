/**
 * A Class represents TimeInterval of an Event
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */

public class TimeInterval  {
    private int startingTime;
    private int endingTime;

    /**
     * TimeInterval Constructor 
     * @param startingTime
     * @param endingTime
     */
    public TimeInterval(int startingTime, int endingTime) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    /**
     * Get time interval staring time 
     * @return startingTime()
     */
    public int getStartingTime() {
        return startingTime;
    }

    /**
     * Set time interval starting time
     * @param startingTime
     */
    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }

    /**
     * Get time interval ending time
     * @return
     */
    public int getEndingTime() {
        return endingTime;
    }

    /**
     * Set time interval ending time
     * @param endingTime
     */
    public void setEndingTime(int endingTime) {
        this.endingTime = endingTime;
    }

    /**
     * Checks if existing time interval of a event overlaps with want-to-be added event time interval
     * @param o - new TimeInterval 
     * @return true if conflict
     */
    public boolean timeOverLap(TimeInterval o) {
        if ((o.startingTime>= startingTime && o.startingTime<= endingTime)==true ||
        		(o.endingTime>=startingTime && o.endingTime <= endingTime)==true) {
            return true;
        } else {
            return false;
        }
    }

   
}