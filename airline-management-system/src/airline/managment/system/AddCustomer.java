
package airline.managment.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {

    JTextField tfname, tfphone, tfCNIC, tfnationality, tfaddress;
    JRadioButton rbmale, rbfemale;

    public AddCustomer() {
        setLayout(null);

        // Load background image
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("airline/managment/system/icons/RE.jpeg"));
        Image bg = bgIcon.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(bg));
        bgLabel.setBounds(0, 0, 900, 600);
        add(bgLabel);

        // Heading
       JLabel heading = new JLabel("PIA – PASSENGER REGISTRATION");
heading.setBounds(220, 20, 500, 35);
heading.setFont(new Font("Tahoma", Font.BOLD, 28));
heading.setForeground(new Color(0, 102, 0)); // Dark green
bgLabel.add(heading);


        // PIA Logo (Top-Right Corner - Covering the blurred logo)

// PIA Logo (Exactly Over the Blurred Logo)
ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("airline/managment/system/icons/PIA (Pakistan).jpeg"));
Image logoImg = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH); // Adjust size as needed
JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
logoLabel.setBounds(760, -5, 120, 90); // Fine-tuned position
bgLabel.add(logoLabel);
        // Name
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 80, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblname.setForeground(Color.WHITE);
        bgLabel.add(lblname);

        tfname = new JTextField();
        tfname.setBounds(220, 80, 150, 25);
        bgLabel.add(tfname);

        // Nationality
        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 130, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblnationality.setForeground(Color.WHITE);
        bgLabel.add(lblnationality);

        tfnationality = new JTextField();
        tfnationality.setBounds(220, 130, 150, 25);
        bgLabel.add(tfnationality);

        // CNIC
        JLabel lblCNIC = new JLabel("CNIC Number");
        lblCNIC.setBounds(60, 180, 150, 25);
        lblCNIC.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCNIC.setForeground(Color.WHITE);
        bgLabel.add(lblCNIC);

        tfCNIC = new JTextField();
        tfCNIC.setBounds(220, 180, 150, 25);
        bgLabel.add(tfCNIC);

        // Gender
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 230, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblgender.setForeground(Color.WHITE);
        bgLabel.add(lblgender);

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(220, 230, 70, 25);
        rbmale.setOpaque(false);
        rbmale.setForeground(Color.WHITE);
        bgLabel.add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(300, 230, 80, 25);
        rbfemale.setOpaque(false);
        rbfemale.setForeground(Color.WHITE);
        bgLabel.add(rbfemale);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(rbmale);
        gendergroup.add(rbfemale);

        // Address
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 280, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbladdress.setForeground(Color.WHITE);
        bgLabel.add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(220, 280, 150, 25);
        bgLabel.add(tfaddress);

        // Phone
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(60, 330, 150, 25);
        lblphone.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblphone.setForeground(Color.WHITE);
        bgLabel.add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(220, 330, 150, 25);
        bgLabel.add(tfphone);

        // Save Button
        JButton save = new JButton("Save");
        save.setBounds(220, 380, 150, 30);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.addActionListener(this);
        bgLabel.add(save);

        // Frame settings
        setSize(900, 600);
        setLocation(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String name = tfname.getText();
        String phone = tfphone.getText();
        String nationality = tfnationality.getText();
        String address = tfaddress.getText();
        String CNIC = tfCNIC.getText();
        String gender = null;

        if (rbmale.isSelected()) gender = "Male";
        else if (rbfemale.isSelected()) gender = "Female";

        try {
            Conn conn = new Conn();
            String query = "INSERT INTO passenger VALUES('" + name + "','" + phone + "','" + nationality + "','" + CNIC + "','" + address + "','" + gender + "')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}

