import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class that represents formatting event and LocalDate
 *
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 2.0
 */
public class EventFormatter implements Formatter {

    /**
     * Return the string representation of the header of the event.
     *
     * @param currentDay the current date
     * @return the header for the current event
     */
    @Override
    public String formatHeader(LocalDate currentDay) {
        return "--------->" + currentDay.toString() + " " + currentDay.getDayOfWeek().toString() + "<---------" + '\n' + '\n';
    }

    /**
     * Return the string representation of the body of the event.
     *
     * @param event the current event
     * @return the string representation of the current event
     */
    @Override
    public String formatEvent(Event event) {
        return event.getT().getStartingTime() + ":00 - "
                + event.getT().getEndingTime() + ":00: " + event.getEventName() + '\n' + '\n';
    }

    /**
     * Return the string representation of the footer of the event.
     *
     * @return the footer of the current event
     */
    @Override
    public String formatFooter() {
        return "\n";
    }

    /**
     * Return the LocalDate representation of the pattern MM/DD/YYYY
     *
     * @param date the local date
     * @return the LocalDate of the desired Events
     */
    @Override
    public String formatDate(LocalDate date) {
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MMMM yyyy");
     //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return f1.format(date);
    }

    /**
     * Unused
     *
     * @param localDateTime
     * @return LocalDateTime
     */
    @Override
    public String formatTime(LocalDateTime localDateTime) {
        DateTimeFormatter eventTimeFormatter = DateTimeFormatter.ofPattern("H:mm");
        return eventTimeFormatter.format(localDateTime);
    }


}
