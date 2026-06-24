package airline.managment.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends JFrame implements ActionListener {

    private JLabel bgLabel;
    private int currentImage = 0;
    private final String[] imagePaths = {
        "airline/managment/system/icons/SSS1.png",
        "airline/managment/system/icons/SSS2.png",
        "airline/managment/system/icons/SSS3.png",
        "airline/managment/system/icons/SSS4.png",
        "airline/managment/system/icons/SSS5.png"
    };

    private JButton flightDetails, addCustomer, bookFlight, journeyDetails, cancelTicket, boardingPass, exit;

    public Home() {
        setTitle("PIA Airline Management System");

        // ⛔ Removed fullscreen mode for cleaner layout
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, 1400, 800); // Set fixed bounds
        add(bgLabel);
        updateBackground();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                currentImage = (currentImage + 1) % imagePaths.length;
                updateBackground();
            }
        }, 3000, 3000);

        // Top Bar
        JPanel topBar = new JPanel(null);
        topBar.setBackground(new Color(0, 51, 0)); // PIA green
        topBar.setBounds(0, 0, 1900, 150);
        bgLabel.add(topBar);

        // Logo
        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("airline/managment/system/icons/pngwing.com.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoLabel.setBounds(10, 10, 120, 120);
        topBar.add(logoLabel);

        // Title and Shadow
        JLabel titleShadow = new JLabel("PAKISTAN INTERNATIONAL AIRLINES");
        titleShadow.setFont(new Font("Georgia", Font.BOLD, 30));
        titleShadow.setForeground(new Color(0, 0, 0, 150));
        titleShadow.setBounds(450 + 2, 30 + 2, 700, 40);
        topBar.add(titleShadow);

        JLabel title = new JLabel("PAKISTAN INTERNATIONAL AIRLINES");
        title.setFont(new Font("Georgia", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBounds(450, 30, 700, 40);
        topBar.add(title);

        // Slogan
        JLabel slogan = new JLabel("Great People to Fly With");
        slogan.setFont(new Font("Serif", Font.ITALIC, 18));
        slogan.setForeground(Color.WHITE);
        slogan.setBounds(450, 65, 400, 25);
        topBar.add(slogan);

        // Button Panel
        JPanel btnPanel = new JPanel(new GridLayout(3, 2, 40, 30));
        btnPanel.setOpaque(false);
        btnPanel.setBounds(500, 300, 600, 250);
        bgLabel.add(btnPanel);

        Font btnFont = new Font("Georgia", Font.BOLD, 18);

        flightDetails = createStyledButton("Flight Details", btnFont);
        addCustomer = createStyledButton("Add Customer", btnFont);
        bookFlight = createStyledButton("Book Flight", btnFont);
        journeyDetails = createStyledButton("Journey Details", btnFont);
        cancelTicket = createStyledButton("Ticket Cancellation", btnFont);
        boardingPass = createStyledButton("Boarding Pass", btnFont);
        exit = createStyledButton("Exit", btnFont);

        // Add buttons to the grid panel
        btnPanel.add(flightDetails);
        btnPanel.add(addCustomer);
        btnPanel.add(bookFlight);
        btnPanel.add(journeyDetails);
        btnPanel.add(cancelTicket);
        btnPanel.add(boardingPass);

        // Exit button separately
        // Exit button - styled same as other buttons
exit.setPreferredSize(new Dimension(200, 40)); // Same size as others

JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
exitPanel.setOpaque(false);
exitPanel.setBounds(850, 570, 200, 50); // Positioned just below main buttons
exitPanel.add(exit);
bgLabel.add(exitPanel);


        // Action Listeners
        flightDetails.addActionListener(this);
        addCustomer.addActionListener(this);
        bookFlight.addActionListener(this);
        journeyDetails.addActionListener(this);
        cancelTicket.addActionListener(this);
        boardingPass.addActionListener(this);
        exit.addActionListener(this);

        setVisible(true);
    }

    private JButton createStyledButton(String text, Font font) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 102, 0));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return btn;
    }

    private void updateBackground() {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(imagePaths[currentImage]));
        Image img = icon.getImage().getScaledInstance(1400, 800, Image.SCALE_SMOOTH);
        bgLabel.setIcon(new ImageIcon(img));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == flightDetails) {
            new Flightinfo().setVisible(true);
        } else if (e.getSource() == addCustomer) {
            new AddCustomer().setVisible(true);
        } else if (e.getSource() == bookFlight) {
            new Bookflight().setVisible(true);
        } else if (e.getSource() == journeyDetails) {
            new JourneyDetails().setVisible(true);
        } else if (e.getSource() == cancelTicket) {
            new CancelTicket().setVisible(true);
        } else if (e.getSource() == boardingPass) {
            new BoardingPass().setVisible(true);
        } else if (e.getSource() == exit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}



