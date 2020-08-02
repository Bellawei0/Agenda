/**
 * A Class represents TimeInterval of an Event
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */

public class TimeInterval  {
    private int startingTime;
    private int endingTime;

    public TimeInterval(int startingTime, int endingTime) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
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

    public boolean timeOverLap(TimeInterval o) {
        if ((o.startingTime>= startingTime && o.startingTime<= endingTime)==true ||
        		(o.endingTime>=startingTime && o.endingTime <= endingTime)==true) {
            return true;
        } else {
            return false;
        }
    }

   
}