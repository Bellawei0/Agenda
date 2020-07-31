/**
 * A class that represents TimeInterval for Event
 *
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 2.0
 */

public class TimeInterval implements Comparable<TimeInterval> {
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

    public boolean timeOverLap(TimeInterval newTi) {
        if (endingTime > (startingTime) || startingTime < (endingTime)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int compareTo(TimeInterval o) {
        if (endingTime > o.startingTime || o.startingTime < endingTime) {
            return 0;
        } else {
            return -1;
        }
    }
}
