
package airline.managment.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.LineBorder;

public class BoardingPass extends JFrame implements ActionListener {
    JComboBox<String> cnicDropdown;
    JTextArea passArea;
    JButton generateBtn;
    
    public BoardingPass() {
        setTitle("Boarding Pass Generator");
        setLayout(null);
        getContentPane().setBackground(new Color(230, 230, 230)); // light grey background
        setBounds(250, 120, 800, 500);
        setResizable(false);
        
        // PIA Title (top center)
        JLabel piaTitle = new JLabel("PAKISTAN INTERNATIONAL AIRLINES");
        piaTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        piaTitle.setForeground(new Color(0, 100, 0)); // dark green
        piaTitle.setHorizontalAlignment(SwingConstants.CENTER);
        piaTitle.setBounds(0, 10, 800, 30);
        add(piaTitle);

        // Logo (top-right OUTSIDE green box)
        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("airline/managment/system/icons/pngwing.com.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoLabel.setBounds(690, 10, 80, 80);
        add(logoLabel);

        // Grey Panel with Dark Green Border
        JPanel greyPanel = new JPanel();
        greyPanel.setBackground(new Color(211, 211, 211)); // Light grey inside box
        greyPanel.setBounds(30, 80, 720, 350);
        greyPanel.setLayout(null);
        greyPanel.setBorder(new LineBorder(new Color(0, 100, 0), 4)); // Dark green border
        add(greyPanel);

        // CNIC dropdown
        JLabel cnicLabel = new JLabel("Select CNIC:");
        cnicLabel.setBounds(40, 60, 100, 25);
        greyPanel.add(cnicLabel);

        cnicDropdown = new JComboBox<>();
        cnicDropdown.setBounds(140, 60, 200, 25);
        greyPanel.add(cnicDropdown);

        // Fetch CNICs from reservations table
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT cnic FROM reservations");
            while (rs.next()) {
                cnicDropdown.addItem(rs.getString("cnic"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Generate button
        generateBtn = new JButton("Generate Pass");
        generateBtn.setBounds(360, 60, 150, 25);
        generateBtn.setBackground(Color.WHITE);
        generateBtn.addActionListener(this);
        greyPanel.add(generateBtn);

        // Text Area to display pass
        passArea = new JTextArea();
        passArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        passArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(passArea);
        scroll.setBounds(40, 100, 500, 200);
        greyPanel.add(scroll);

        // QR Code
        ImageIcon qrIcon = new ImageIcon(ClassLoader.getSystemResource("airline/managment/system/icons/QR.jpg"));
        Image qrImg = qrIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel qrLabel = new JLabel(new ImageIcon(qrImg));
        qrLabel.setBounds(600, 230, 100, 100); // Bottom-right inside box
        greyPanel.add(qrLabel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String selectedCnic = (String) cnicDropdown.getSelectedItem();
        if (selectedCnic == null) return;

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM reservations WHERE cnic = '" + selectedCnic + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                String name = rs.getString("name");
                String cnic = rs.getString("cnic");
                String source = rs.getString("source");
                String destination = rs.getString("destination");
                String fclass = rs.getString("class");
                String trip = rs.getString("trip_type");
                String ddate = rs.getString("departure_date");
                String rdate = rs.getString("return_date");

                StringBuilder sb = new StringBuilder();
                sb.append("****************************************\n");
                sb.append("         BOARDING PASS - PIA\n");
                sb.append("****************************************\n");
                sb.append("Passenger Name : ").append(name).append("\n");
                sb.append("CNIC           : ").append(cnic).append("\n");
                sb.append("From           : ").append(source).append("\n");
                sb.append("To             : ").append(destination).append("\n");
                sb.append("Trip Type      : ").append(trip).append("\n");
                sb.append("Class          : ").append(fclass).append("\n");
                sb.append("Departure Date : ").append(ddate).append("\n");
                if (trip.equalsIgnoreCase("round-trip")) {
                    sb.append("Return Date    : ").append(rdate).append("\n");
                }
                sb.append("****************************************\n");
                sb.append("      Have a Safe Journey with PIA!\n");

                passArea.setText(sb.toString());
            } else {
                passArea.setText("❌ No record found for CNIC: " + selectedCnic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BoardingPass();
    }
}



