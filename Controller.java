import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Controllers of the MVC application
 *
 * @author Jyoti Suri, Bella wei, Jennifer yang
 * @Version 2.0
 */
public class Controller extends JPanel {
    public static String[] MENU_HEADER = {"From File", "Agenda", "Month", "Week", "Day"};

    /**
     * Constructor of the Controller
     * @param model the model of MVC application
     * @param calendarFrame the frame to display calendar and events
     */
    public Controller(DataModel model, CalendarFrame calendarFrame) {
        JPanel rightPanel = new JPanel();
        JButton[] rightMenuBtn = new JButton[5];

        for (int i = 0; i < rightMenuBtn.length; i++) {
            rightMenuBtn[i] = new JButton(MENU_HEADER[i]);
            rightPanel.add(rightMenuBtn[i], SwingConstants.CENTER);

            rightMenuBtn[i].addActionListener(e -> {
                JButton menuBtn = (JButton) e.getSource();

                if (menuBtn == rightMenuBtn[4]) {
                    model.setViewType(DataModel.ViewType.DAY);
                    calendarFrame.displayDayView();

                } else if (menuBtn == rightMenuBtn[3]) {
                    model.setViewType(DataModel.ViewType.WEEK);
                    calendarFrame.displayWeekView();

                } else if (menuBtn == rightMenuBtn[2]) {
                    model.setViewType(DataModel.ViewType.MONTH);
                    calendarFrame.displayMonthView();

                } else if (menuBtn == rightMenuBtn[1]) {
                    new AgendaFrame(calendarFrame);
                    model.setViewType(DataModel.ViewType.AGENDA);

                } else if (menuBtn == rightMenuBtn[0]) {
                    FileDialog fileDialog = new FileDialog(new JFrame());
                    fileDialog.setVisible(true);
                    File[] file = fileDialog.getFiles();
                    Scanner scanner;
                    if (file.length > 0) {
                        try {
                            scanner = new Scanner(file[0]);
                            model.loadFile(scanner);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                }

                // Only set one button selected
                if (menuBtn != rightMenuBtn[4]) {
                    for (int j = 0; j < rightMenuBtn.length; j++) {
                        rightMenuBtn[j].setSelected(false);
                    }
                    menuBtn.setSelected(true);
                }
            });
        }
        add(rightPanel, BorderLayout.EAST);
    }
}