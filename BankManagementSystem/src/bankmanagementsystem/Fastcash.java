package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Fastcash extends JFrame implements ActionListener {
    String pinno ;

    JButton deposit, withdraw, fastcash, ministatement, pinchange, balance, exit;

    Fastcash(String pinno) {
        this.pinno = pinno;
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(800, 500);
        setLocation(300, 150);
        setTitle("ATM TRANSACTION");
        getContentPane().setBackground(Color.BLACK);

        JLabel title = new JLabel("Please select your withdraw");
        title.setBounds(250, 40, 400, 30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title);

        // LEFT SIDE BUTTONS
        deposit = new JButton("500");
        deposit.setBounds(200, 120, 150, 30);
        add(deposit);

        fastcash = new JButton("1000");
        fastcash.setBounds(200, 170, 150, 30);
        add(fastcash);

        pinchange = new JButton("1500");
        pinchange.setBounds(200, 220, 150, 30);
        add(pinchange);

        // RIGHT SIDE BUTTONS
        withdraw = new JButton("2000");
        withdraw.setBounds(400, 120, 150, 30);
        add(withdraw);

        ministatement = new JButton("2500");
        ministatement.setBounds(400, 170, 150, 30);
        add(ministatement);

        balance = new JButton("3000");
        balance.setBounds(400, 220, 150, 30);
        add(balance);

        exit = new JButton("back");
        exit.setBounds(400, 270, 150, 30);
        add(exit);

        // Add ActionListener
        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        fastcash.addActionListener(this);
        ministatement.addActionListener(this);
        pinchange.addActionListener(this);
        balance.addActionListener(this);
        exit.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String one = ((JButton)ae.getSource()).getText();
            int withdrawAmount = Integer.parseInt(one);
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

                } else {
                    JOptionPane.showMessageDialog(null, "Transaction Failed");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }





        } else if (ae.getSource() == withdraw) {
            String two = ((JButton)ae.getSource()).getText();
            int withdrawAmount = Integer.parseInt(two);
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

                } else {
                    JOptionPane.showMessageDialog(null, "Transaction Failed");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            JOptionPane.showMessageDialog(null, "Cash Withdrawal Clicked");
        }
        else if (ae.getSource() == fastcash) {
            String three = ((JButton)ae.getSource()).getText();
            int withdrawAmount = Integer.parseInt(three);
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

                } else {
                    JOptionPane.showMessageDialog(null, "Transaction Failed");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Fast Cash Clicked");

        } else if (ae.getSource() == ministatement) {
            String four = ((JButton)ae.getSource()).getText();
            int withdrawAmount = Integer.parseInt(four);
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

                } else {
                    JOptionPane.showMessageDialog(null, "Transaction Failed");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Mini Statement Clicked");
        } else if (ae.getSource() == pinchange) {
            String five = ((JButton)ae.getSource()).getText();
            int withdrawAmount = Integer.parseInt(five);
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

                } else {
                    JOptionPane.showMessageDialog(null, "Transaction Failed");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Pin Change Clicked");
        } else if (ae.getSource() == balance) {
            String six = ((JButton)ae.getSource()).getText();
            int withdrawAmount = Integer.parseInt(six);
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

                } else {
                    JOptionPane.showMessageDialog(null, "Transaction Failed");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Balance Enquiry Clicked");
        } else if (ae.getSource() == exit) {
            System.exit(0);
        }
        setVisible(false);
        new TransactionUI(pinno,"").setVisible(true);
    }

    public static void main(String[] args) {
        new Fastcash("");
    }
}
