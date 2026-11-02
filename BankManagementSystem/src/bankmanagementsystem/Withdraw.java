package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Withdraw extends JFrame implements ActionListener {

    String pinno;
    JTextField amountField;
    JButton withdrawBtn, backBtn;

    Withdraw(String pinno) {
        this.pinno = pinno;

        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setTitle("Withdraw");

        JLabel label = new JLabel("Enter the amount you want to Withdraw");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(100, 40, 350, 30);
        add(label);

        amountField = new JTextField();
        amountField.setBounds(100, 90, 300, 30);
        add(amountField);

        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(250, 150, 150, 30);
        withdrawBtn.addActionListener(this);
        add(withdrawBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(250, 190, 150, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        setSize(500, 300);
        setLocation(450, 200);
        getContentPane().setBackground(Color.BLACK);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == withdrawBtn) {

            String input = amountField.getText().trim();

            // ✅ Validation
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter amount");
                return;
            }

            int withdrawAmount;
            try {
                withdrawAmount = Integer.parseInt(input);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Enter valid number");
                return;
            }

            if (withdrawAmount <= 0) {
                JOptionPane.showMessageDialog(null, "Amount must be greater than 0");
                return;
            }

            try {
                Connection con = DBcon.getConnection();

                // ✅ Fetch balance
                String query1 = "SELECT amount FROM bank WHERE pinno = ?";
                PreparedStatement ps1 = con.prepareStatement(query1);
                ps1.setString(1, pinno);

                ResultSet rs = ps1.executeQuery();

                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Account not found");
                    return;
                }

                int currentBalance = rs.getInt("amount");

                // ✅ Check balance
                if (withdrawAmount > currentBalance) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                // ✅ Calculate new balance
                int newBalance = currentBalance - withdrawAmount;

                // ✅ Date Time
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String dateTime = now.format(formatter);

                // ✅ Update DB
                String updateQuery = "UPDATE bank SET amount = ?, date_time = ? WHERE pinno = ?";
                PreparedStatement ps2 = con.prepareStatement(updateQuery);

                ps2.setInt(1, newBalance);   // 🔥 FIXED (INT)
                ps2.setString(2, dateTime);
                ps2.setString(3, pinno);

                int rows = ps2.executeUpdate();

                if (rows > 0) {
                    JOptionPane.showMessageDialog(null, "Withdrawal Successful\nBalance: ₹ " + newBalance);
                    amountField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Transaction Failed");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new TransactionUI(pinno,"").setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Withdraw(""); // test pin
    }
}