import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Controllers of the MVC application
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 1.0
 */
public class OptionsPanel extends JPanel {

    private JButton todayBtn, dayBtn, weekBtn, monthBtn, agendaBtn, previousBtn, nextBtn, fromFileBtn;
    Model model;

    /**
     * @param model
     */
    public OptionsPanel(Model model) {
        this.model = model;
        // TODO Auto-generated constructor stub
        setLayout(new GridLayout(1, 6, 0, 0));

        todayBtn = new JButton("Today");
        previousBtn = new JButton("<");
        nextBtn = new JButton((">"));
        dayBtn = new JButton("Day");
        weekBtn = new JButton("Week");
        monthBtn = new JButton("Month");

        agendaBtn = new JButton("Agenda");
        agendaBtn.addActionListener(e -> {
            new AgendaFrame();
        });

        fromFileBtn = new JButton("From file");
        fromFileBtn.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(new JFrame());
            fileDialog.setVisible(true);
            File[] file = fileDialog.getFiles();
            Scanner scanner;
            if (file.length > 0) {
                try {
                    scanner = new Scanner(file[0]);
                    loadFile(scanner);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });

        add(todayBtn);
        add(previousBtn);
        add(nextBtn);
        add(dayBtn);
        add(weekBtn);
        add(monthBtn);
        add(agendaBtn);
        add(fromFileBtn);
    }

    /**
     * Method to load input file
     * @param scanner
     */
    private void loadFile(Scanner scanner) {
        Event event;

        while (scanner.hasNextLine()) {
            String eventInfo = scanner.nextLine();
            String eventToken[] = eventInfo.split(";");

            String eventName = eventToken[0];
            int startTime = Integer.parseInt(eventToken[5]);
            int endTime = Integer.parseInt(eventToken[6]);
            int year = Integer.parseInt(eventToken[1]);
            int startingMonth = Integer.parseInt(eventToken[2]);
            int endingMonth = Integer.parseInt(eventToken[3]);

            LocalDateTime startD = LocalDateTime.of(year, startingMonth, 1, startTime, 0);
            LocalDateTime endD = LocalDateTime.of(year, endingMonth, 31, startTime, 0);

            event = new Event(eventName, year, startingMonth, endingMonth, startTime, endTime);

            while (startD.isBefore(endD)) {
                System.out.println("debug StartD------" + startD);
                System.out.println("Debug EndD --------" + endD);
                for (char i : eventToken[4].toCharArray()) {
                    // for(int j= 0; j< eventToken.length; j++) {
                    int dayOfWeek = 0;
                    switch (i) {
                        case 'M':
                            dayOfWeek = 1;
                            break;
                        case 'T':
                            dayOfWeek = 2;
                            break;
                        case 'W':
                            dayOfWeek = 3;
                            break;
                        case 'R':
                            dayOfWeek = 4;
                            break;
                        case 'F':
                            dayOfWeek = 5;
                            break;
                        case 'A':
                            dayOfWeek = 6;
                            break;
                        case 'S':
                            dayOfWeek = 7;
                            break;
                    }
                    if (startD.getDayOfWeek().getValue() == dayOfWeek) {
                        model.update(startD, event);
                    }
                }
                startD = startD.plusDays(1);
            }
        }
        System.out.println("Loading is done!");
    }
}


