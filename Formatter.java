import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Interface of Formatter
 * This applied to use the strategy pattern
 *
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 2.0
 */
public interface Formatter {
    /**
     * Return the  LocalDate of monthLabel
     * @param date the LocalDate
     * @return the current month, year
     */

    String formatDate(LocalDate date);

    /**
     * Return the header of the event.
     *
     * @param currentDay the current date
     * @return the current LocalDate, the Day of Week
     */
    String formatHeader(LocalDate currentDay);

    /**
     * Return the detail of the event
     *
     * @param event the current event
     * @return the Hour and the Event Name
     */
    String formatEvent(Event event);

    /**
     * Return the footer of the event.
     *
     * @return the next line of output
     */
    String formatFooter();



    String formatTime(LocalDateTime localDateTime);

}
