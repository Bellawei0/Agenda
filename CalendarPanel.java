import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Inside Bottom Panel
 * Contains Date Picker
 */
public class CalendarPanel extends JPanel {
	private JButton[] button;
	private JLabel l;
	private int month;
	private int year;
	private String day;

	/**
	 * Constructor the date picker dialog
	 */
    public CalendarPanel() {
		button = new JButton[49];
		l = new JLabel("", JLabel.CENTER);
		month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
		year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
		day = "";

        setLayout(new BorderLayout());
        String[] header = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
        JPanel p1 = new JPanel(new GridLayout(7, 7));
        p1.setPreferredSize(new Dimension(430, 120));

        for (int x = 0; x < button.length; x++) {
            final int selection = x;
            button[x] = new JButton();
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.white);
            if (x > 6)
                button[x].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        day = button[selection].getActionCommand();
                        //d.dispose();
                    }
                });
            if (x < 7) {
                button[x].setText(header[x]);
                button[x].setForeground(Color.red);
            }

            p1.add(button[x]);
        }

		/**
		 * Two Layers BorderLayout
		 * Layer one: topPanel--> BorderLayout (Create button: , Previous/next/label)
		 * Layer Two: P1, topPanel
		 */
		JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        JButton createBtn = new JButton("Create");
		createBtn.setOpaque(true);
		createBtn.setBorderPainted(false);
		createBtn.setBackground(new Color(102,255,102));
		createBtn.addActionListener(e ->  {
			new CreateEventFrame();
		});


        JPanel p2 = new JPanel(new GridLayout(1, 3));
        JButton previous = new JButton("<< Previous");
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month--;
                displayDate();
            }
        });
        p2.add(previous);
        p2.add(l);
        topPanel.add(createBtn,BorderLayout.WEST);
        topPanel.add(p2,BorderLayout.SOUTH);
        JButton next = new JButton("Next >>");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month++;
                displayDate();
            }
        });
        p2.add(next);

        add(p1, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        displayDate();
        setVisible(true);
    }

    public void displayDate() {
        for (int x = 7; x < button.length; x++)
            button[x].setText("");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
            button[x].setText("" + day);
        l.setText(sdf.format(cal.getTime()));
        //d.setTitle("Date Picker");
    }

    /**
     * Models the date picker dialog
     *
     * @author [UNKNOWN]
     */
    public class DatePicker extends JPanel {


        public DatePicker() {
            //d.setModal(true);

        }

        public String setPickedDate() {
            if (day.equals(""))
                return day;
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                    "dd-MM-yyyy");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(year, month, Integer.parseInt(day));
            return sdf.format(cal.getTime());
        }
    }

}