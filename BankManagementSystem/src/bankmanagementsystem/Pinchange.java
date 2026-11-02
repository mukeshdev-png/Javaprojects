package bankmanagementsystem;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Pinchange extends JFrame implements ActionListener {

    JPasswordField newPinField, rePinField;
    JButton changeBtn, backBtn;
    String pinno;

    Pinchange(String pinno) {
        this.pinno = pinno;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setTitle("Change PIN");
        setSize(400, 300);
        setLocation(500, 200);
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("CHANGE YOUR PIN");
        heading.setBounds(110, 20, 200, 30);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        add(heading);

        // New PIN label
        JLabel newPin = new JLabel("New PIN:");
        newPin.setBounds(50, 80, 100, 25);
        add(newPin);

        // New PIN field
        newPinField = new JPasswordField();
        newPinField.setBounds(180, 80, 150, 25);
        add(newPinField);

        // Re-enter PIN label
        JLabel rePin = new JLabel("Re-Enter PIN:");
        rePin.setBounds(50, 120, 120, 25);
        add(rePin);

        // Re-enter field
        rePinField = new JPasswordField();
        rePinField.setBounds(180, 120, 150, 25);
        add(rePinField);

        // Change button
        changeBtn = new JButton("CHANGE");
        changeBtn.setBounds(80, 180, 100, 30);
        changeBtn.addActionListener(this);
        add(changeBtn);

        // Back button
        backBtn = new JButton("BACK");
        backBtn.setBounds(200, 180, 100, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeBtn) {
            String pin1 = new String(newPinField.getPassword());
            String repin = new String(rePinField.getPassword());

            if (!pin1.equals(repin)) {
                JOptionPane.showMessageDialog(null, "PINs do not match");
            } else if (pin1.equals("")) {
                JOptionPane.showMessageDialog(null, "pin is empty");

            } else if (repin.equals("")) {
                JOptionPane.showMessageDialog(null, "repin is empty");

            } else {
                try {
                    Connection con = DBcon.getConnection();
                    String query1 = "update bank set pinno = ? where pinno = ?";
                    String query2 = "update signupthree set pin = ? where pin = ?";
                    String query3 = "update signupthreelogs set pin = ? where pin = ?";
                    PreparedStatement ps = con.prepareStatement(query1);
                    PreparedStatement pss = con.prepareStatement(query2);
                    PreparedStatement psss = con.prepareStatement(query3);

                    ps.setString(1,repin);
                    ps.setString(2,pinno);

                    pss.setString(1,repin);
                    pss.setString(2,pinno);

                    psss.setString(1,repin);
                    psss.setString(2,pinno);



                    int row = ps.executeUpdate();
                    if(row>0){
                        JOptionPane.showMessageDialog(null,"updated in t1");
                    }
                    int row1 = pss.executeUpdate();
                    if(row1>0){
                        JOptionPane.showMessageDialog(null,"updated in t2");
                    }
                    int row2 = psss.executeUpdate();
                    if(row2>0){
                        JOptionPane.showMessageDialog(null,"updated in t3");
                    }



                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            setVisible(false);
            new Login().setVisible(true);

            if (e.getSource() == backBtn) {
                dispose(); // closes window
            }
        }
    }

    public static void main(String[] args) {
        new Pinchange("");
    }
}



