
package airline.managment.system;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Bookflight extends JFrame {

    private JRadioButton oneWay, roundTrip;
    private JDateChooser departureDateChooser, returnDateChooser, dobDateChooser;
    private JComboBox<String> from, to, travelClass, gender;
    private JTextField nameField, cnicField, contactField;
    private JButton bookButton;
    private JLabel image;

    public Bookflight() {
        setTitle("Flight Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600); // increased width
        setLocationRelativeTo(null);
        setLayout(null);

        // Background image
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("airline/managment/system/icons/PIA.png"));
        Image bg = bgIcon.getImage().getScaledInstance(1600, 800, Image.SCALE_SMOOTH);
        image = new JLabel(new ImageIcon(bg));
        image.setBounds(0, 0, 1600, 800);
        add(image);

        JLabel heading = new JLabel("PIA-FLIGHT BOOKING PORTAL");
heading.setFont(new Font("Tahoma", Font.BOLD, 26));

// Set dark green color
heading.setForeground(new Color(0, 100, 0));  // RGB for dark green

heading.setBounds(250, 10, 500, 30);
image.add(heading);



        ButtonGroup tripType = new ButtonGroup();
        oneWay = new JRadioButton("One-way");
        roundTrip = new JRadioButton("Round-trip");
        oneWay.setOpaque(false);
        roundTrip.setOpaque(false);
        oneWay.setForeground(Color.WHITE);
        roundTrip.setForeground(Color.WHITE);
        oneWay.setFont(new Font("Tahoma", Font.BOLD, 14));
        roundTrip.setFont(new Font("Tahoma", Font.BOLD, 14));
        tripType.add(oneWay);
        tripType.add(roundTrip);
        oneWay.setSelected(true);
        oneWay.setBounds(50, 60, 100, 25);
        roundTrip.setBounds(160, 60, 120, 25);
        image.add(oneWay);
        image.add(roundTrip);

        image.add(createLabel("From:", 50, 100));
        from = new JComboBox<>(new String[]{"Karachi", "Lahore", "Islamabad", "Quetta"});
        from.setBounds(150, 100, 150, 25);
        image.add(from);

        image.add(createLabel("To:", 320, 100));
        to = new JComboBox<>(new String[]{"London", "Jeddah", "Toronto", "Dubai", "Doha"});
        to.setBounds(370, 100, 150, 25);
        image.add(to);

        image.add(createLabel("Departure:", 50, 140));
        departureDateChooser = new JDateChooser();
        departureDateChooser.setBounds(150, 140, 150, 25);
        image.add(departureDateChooser);

        image.add(createLabel("Return:", 320, 140));
        returnDateChooser = new JDateChooser();
        returnDateChooser.setBounds(370, 140, 150, 25);
        image.add(returnDateChooser);

        returnDateChooser.getDateEditor().getUiComponent().setEnabled(false);

        oneWay.addActionListener(e -> {
            returnDateChooser.setDate(null);
            returnDateChooser.getDateEditor().getUiComponent().setEnabled(false);
        });

        roundTrip.addActionListener(e -> {
            returnDateChooser.getDateEditor().getUiComponent().setEnabled(true);
        });

        image.add(createLabel("Class:", 50, 180));
        travelClass = new JComboBox<>(new String[]{"Economy", "Business"});
        travelClass.setBounds(150, 180, 150, 25);
        image.add(travelClass);

        image.add(createLabel("Name:", 50, 220));
        nameField = new JTextField();
        nameField.setBounds(150, 220, 370, 25);
        image.add(nameField);

        image.add(createLabel("CNIC:", 50, 260));
        cnicField = new JTextField();
        cnicField.setBounds(150, 260, 370, 25);
        image.add(cnicField);

        image.add(createLabel("Gender:", 50, 300));
        gender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        gender.setBounds(150, 300, 150, 25);
        image.add(gender);

        image.add(createLabel("DOB:", 320, 300));
        dobDateChooser = new JDateChooser();
        dobDateChooser.setBounds(370, 300, 150, 25);
        image.add(dobDateChooser);

        image.add(createLabel("Contact:", 50, 340));
        contactField = new JTextField();
        contactField.setBounds(150, 340, 370, 25);
        image.add(contactField);

        bookButton = new JButton("Book Flight");
        bookButton.setBounds(300, 420, 200, 40);
        image.add(bookButton);

        bookButton.addActionListener(e -> bookFlight());

        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 100, 25);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        return label;
    }

    private void bookFlight() {
        try {
            String tripType = oneWay.isSelected() ? "One-way" : "Round-trip";
            String source = (String) from.getSelectedItem();
            String destination = (String) to.getSelectedItem();
            String travelClassType = (String) travelClass.getSelectedItem();
            String name = nameField.getText();
            String cnic = cnicField.getText();
            String genderType = (String) gender.getSelectedItem();
            String contact = contactField.getText();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String departureDate = sdf.format(departureDateChooser.getDate());
            String returnDate = (roundTrip.isSelected() && returnDateChooser.getDate() != null)
                    ? sdf.format(returnDateChooser.getDate()) : null;
            String dob = sdf.format(dobDateChooser.getDate());

            Conn c = new Conn();
            String sql = "INSERT INTO reservations (trip_type, source, destination, departure_date, return_date, class, name, cnic, gender, dob, contact) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = c.c.prepareStatement(sql);
            pstmt.setString(1, tripType);
            pstmt.setString(2, source);
            pstmt.setString(3, destination);
            pstmt.setString(4, departureDate);
            pstmt.setString(5, returnDate);
            pstmt.setString(6, travelClassType);
            pstmt.setString(7, name);
            pstmt.setString(8, cnic);
            pstmt.setString(9, genderType);
            pstmt.setString(10, dob);
            pstmt.setString(11, contact);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "🎉 FLIGHT BOOKED!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Error booking flight: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Bookflight();
    }
}
