package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deposit extends JFrame implements ActionListener {

    String pinno;
    JTextField amountField;
    JButton depositBtn, backBtn;

    Deposit(String pinno) {
        this.pinno = pinno;
        setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen


        setLayout(null);
        setTitle("Deposit");
        setSize(800, 500);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Enter the amount you want to deposit");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(100, 40, 350, 30);
        add(label);

        amountField = new JTextField();
        amountField.setBounds(100, 90, 300, 30);
        add(amountField);

        depositBtn = new JButton("Deposit");
        depositBtn.setBounds(250, 150, 150, 30);
        depositBtn.addActionListener(this);
        add(depositBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(250, 190, 150, 30);
        backBtn.addActionListener(this);
        add(backBtn);


        setLocation(450, 200);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        // 🔵 Deposit Button
        if (ae.getSource() == depositBtn) {
            try {
                Connection con = DBcon.getConnection();

                String input = amountField.getText().trim();

                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter amount");
                    return;
                }

                int depositAmount;
                try {
                    depositAmount = Integer.parseInt(input);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Enter valid number");
                    return;
                }

                if (depositAmount <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid amount");
                    return;
                }

                // 🔍 Check if user exists
                String selectQuery = "SELECT * FROM bank WHERE pinno = ?";
                PreparedStatement ps1 = con.prepareStatement(selectQuery);
                ps1.setString(1, pinno);

                ResultSet rs = ps1.executeQuery();

                int currentBalance = 0;

                if (!rs.next()) {
                    // 🆕 New user → insert record
                    String insertQuery = "INSERT INTO bank (pinno, amount, date_time) VALUES (?, ?, ?)";
                    PreparedStatement psInsert = con.prepareStatement(insertQuery);

                    LocalDateTime now = LocalDateTime.now();
                    String dateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                    psInsert.setString(1, pinno);
                    psInsert.setInt(2, 0);
                    psInsert.setString(3, dateTime);

                    psInsert.executeUpdate();

                    currentBalance = 0;

                } else {
                    // 👤 Existing user
                    currentBalance = rs.getInt("amount");
                }

                // 💰 Deposit calculation
                int newBalance = currentBalance + depositAmount;

                LocalDateTime now = LocalDateTime.now();
                String dateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String updateQuery = "UPDATE bank SET amount = ?, date_time = ? WHERE pinno = ?";
                PreparedStatement ps2 = con.prepareStatement(updateQuery);

                ps2.setInt(1, newBalance);
                ps2.setString(2, dateTime);
                ps2.setString(3, pinno);

                int rows = ps2.executeUpdate();

                if (rows > 0) {
                    JOptionPane.showMessageDialog(null,
                            "₹ " + depositAmount + " Deposited\nBalance: ₹ " + newBalance);
                    amountField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Deposit failed");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 🔴 Back Button
        if (ae.getSource() == backBtn) {
            setVisible(false);
            new TransactionUI(pinno,"").setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Deposit(""); // test pin
    }
}