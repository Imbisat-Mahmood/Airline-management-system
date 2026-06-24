
package airline.managment.system;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class Flightinfo extends JFrame {

    public Flightinfo() {
        setTitle("Flight Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 550);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE); // Pure white background

        // ----- Heading -----
        JLabel heading = new JLabel("PIA FLIGHT STATUS & SCHEDULES");
        heading.setBounds(150, 20, 600, 35);
        heading.setFont(new Font("Tahoma", Font.BOLD, 26));
        heading.setForeground(new Color(0, 102, 0));
        add(heading);

        // ----- Logo -----
        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("airline/managment/system/icons/pngwing.com.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(740, 10, 70, 70);
        add(logoLabel);

        // ----- Table -----
        JTable table = new JTable();
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setBackground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0, 102, 0));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setReorderingAllowed(false);

        // ----- Scroll Pane for Table -----
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 120, 800, 0);
        scrollPane.setBorder(null);
        add(scrollPane);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM flight");
            table.setModel(DbUtils.resultSetToTableModel(rs));

            int rowCount = table.getRowCount();
            int tableHeight = rowCount * table.getRowHeight() + table.getTableHeader().getPreferredSize().height;

            // Limit max height
            int maxHeight = 300;
            if (tableHeight > maxHeight) {
                tableHeight = maxHeight;
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            } else {
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            }

            scrollPane.setBounds(25, 120, 800, tableHeight);
            scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 0), 2));

            // ----- Full Width Image Below Table -----
            ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("airline/managment/system/icons/R.jpeg"));
            
            // Calculate available space below table
            int availableHeight = getHeight() - (120 + tableHeight);
            
            // Stretch image to full width (same as table) and use all available height
            Image bgImage = bgIcon.getImage().getScaledInstance(800, availableHeight, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(bgImage));
            
            // Position image exactly below table, full width, no margins
            imageLabel.setBounds(25, 120 + tableHeight, 800, availableHeight);
            add(imageLabel);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading flight data: " + e.getMessage());
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Flightinfo();
        });
    }
}



