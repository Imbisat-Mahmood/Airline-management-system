
package airline.managment.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener {
    
    JTable table;
    JComboBox<String> cnicDropdown;
    JButton show;

    public JourneyDetails() {
        
        getContentPane().setBackground(new Color(211, 211, 211)); // Light Grey

 // light mustard shade

        setLayout(null);

        // PIA Logo
        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("airline/managment/system/icons/pngwing.com.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoLabel.setBounds(20, 10, 80, 80);
        add(logoLabel);

        JLabel heading = new JLabel("Your Journey Details with PIA");
heading.setFont(new Font("Tahoma", Font.BOLD, 24));
heading.setForeground(new Color(0, 100, 0)); // Dark green
heading.setBounds(250, 10, 400, 30);
add(heading);


        JLabel lblCnic = new JLabel("Select CNIC:");
        lblCnic.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCnic.setBounds(100, 80, 120, 25);
        add(lblCnic);

        cnicDropdown = new JComboBox<>();
        cnicDropdown.setBounds(230, 80, 180, 25);
        add(cnicDropdown);
        populateCnicDropdown();

        show = new JButton("Show Booking");
        show.setBackground(new Color(0, 100, 0));
        show.setForeground(Color.WHITE);
        show.setFont(new Font("Tahoma", Font.BOLD, 14));
        show.setBounds(430, 80, 150, 25);
        show.addActionListener(this);
        add(show);

        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(50, 130, 700, 300);
        add(jsp);

        // Style table header
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBackground(new Color(0, 100, 0));

        setSize(800, 500); // same as BookFlight window
        setLocation(300, 150);
        setVisible(true);
    }

    private void populateCnicDropdown() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT DISTINCT cnic FROM reservations");
            while (rs.next()) {
                cnicDropdown.addItem(rs.getString("cnic"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching CNICs.");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();
            String cnic = (String) cnicDropdown.getSelectedItem();

            String sql = "SELECT id AS 'Booking ID', name AS 'Name', cnic AS 'CNIC', gender AS 'Gender', dob AS 'Date of Birth', "
                       + "source AS 'From', destination AS 'To', departure_date AS 'Departure', return_date AS 'Return', "
                       + "class AS 'Class', contact AS 'Contact' FROM reservations WHERE cnic = '" + cnic + "'";
            
            ResultSet rs = c.s.executeQuery(sql);

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Booking Found for selected CNIC.");
                return;
            }

            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching booking details.");
        }
    }

    public static void main(String[] args) {
        new JourneyDetails();
    }
}

