
package airline.managment.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class CancelTicket extends JFrame implements ActionListener {

    JComboBox<String> cnicDropdown;
    JTable table;
    JButton showBtn, cancelBtn;
    Conn c;

    CancelTicket() {
        c = new Conn();

        setTitle("Cancel Ticket - PIA");
        setSize(850, 500);
        setLocation(300, 150);
        setLayout(null);
        getContentPane().setBackground(new Color(211, 211, 211)); // light grey

        // PIA Logo
        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("airline/managment/system/icons/pngwing.com.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoLabel.setBounds(20, 20, 100, 100);
        add(logoLabel);

        // Heading
JLabel heading = new JLabel("Reservation Cancellation – PIA Official Portal");
heading.setBounds(150, 10, 600, 40); // Adjusted width to fit full text
heading.setFont(new Font("Tahoma", Font.BOLD, 26));
heading.setForeground(new Color(0, 100, 0)); // Dark green
add(heading);


        // CNIC Dropdown
        JLabel lblCnic = new JLabel("Select CNIC:");
        lblCnic.setBounds(150, 100, 120, 25);
        lblCnic.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCnic.setForeground(Color.BLACK);
        add(lblCnic);

        cnicDropdown = new JComboBox<>();
        cnicDropdown.setBounds(270, 100, 200, 25);
        add(cnicDropdown);
        loadCnicDropdown(); // fetch all CNICs from DB

        // Show Booking Button
        showBtn = new JButton("Show Booking");
        showBtn.setBounds(490, 100, 140, 25);
        showBtn.setBackground(Color.BLACK);
        showBtn.setForeground(Color.WHITE);
        showBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
        showBtn.addActionListener(this);
        add(showBtn);

        // Table
        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(50, 150, 740, 200);
        add(jsp);

        // Cancel Button
        cancelBtn = new JButton("Cancel Ticket");
        cancelBtn.setBounds(320, 370, 180, 30);
        cancelBtn.setBackground(Color.RED);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        setVisible(true);
    }

    // Fetch CNICs from reservations table
    private void loadCnicDropdown() {
        try {
            String query = "SELECT DISTINCT cnic FROM reservations";
            ResultSet rs = c.s.executeQuery(query);
            cnicDropdown.removeAllItems();
            while (rs.next()) {
                cnicDropdown.addItem(rs.getString("cnic"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String cnic = (String) cnicDropdown.getSelectedItem();
        if (cnic == null || cnic.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a CNIC.");
            return;
        }

        try {
            if (ae.getSource() == showBtn) {
                String query = "SELECT id AS 'Booking ID', name AS 'Name', cnic AS 'CNIC', gender AS 'Gender', dob AS 'Date of Birth', source AS 'From', destination AS 'To', departure_date AS 'Departure', return_date AS 'Return', class AS 'Class', contact AS 'Contact' FROM reservations WHERE cnic = ?";
                PreparedStatement pstmt = c.conn.prepareStatement(query);
                pstmt.setString(1, cnic);
                ResultSet rs = pstmt.executeQuery();

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "No Booking Found for this CNIC.");
                    table.setModel(new javax.swing.table.DefaultTableModel());
                    return;
                }

                table.setModel(DbUtils.resultSetToTableModel(rs));
            } else if (ae.getSource() == cancelBtn) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this ticket?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String deleteQuery = "DELETE FROM reservations WHERE cnic = ?";
                    PreparedStatement pstmt = c.conn.prepareStatement(deleteQuery);
                    pstmt.setString(1, cnic);
                    int rows = pstmt.executeUpdate();

                    if (rows > 0) {
                        JOptionPane.showMessageDialog(null, "🎉 Booking Cancelled Successfully!");
                        table.setModel(new javax.swing.table.DefaultTableModel());
                        loadCnicDropdown(); // refresh dropdown
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ No booking found to cancel.");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while processing.");
        }
    }

    public static void main(String[] args) {
        new CancelTicket();
    }
}
