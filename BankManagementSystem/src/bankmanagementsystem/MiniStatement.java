package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    String pinno;
    String cardnumber;

    MiniStatement(String pinno,String cardnumber) {
        this.pinno = pinno;
        this.cardnumber = cardnumber;

        setTitle("Mini Statement");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLocation(400, 200);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        // Title
        JLabel title = new JLabel("Mini Statement");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(170, 20, 200, 30);
        add(title);

        // Masked Card Number
        String masked = cardnumber.substring(0, 4) + "XXXXX";
        JLabel card = new JLabel("Card Number: " + masked);
        card.setForeground(Color.WHITE);
        card.setBounds(50, 70, 300, 25);
        add(card);

        // Text Area for transactions
        JTextArea area = new JTextArea();
        area.setBounds(50, 110, 380, 180);
        area.setBackground(Color.BLACK);
        area.setForeground(Color.GREEN);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setEditable(false);
        add(area);

        // Fetch data from DB
        try {
            Connection con = DBcon.getConnection();
            String query = "SELECT amount, date_time FROM bank WHERE pinno = ? ORDER BY date_time DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pinno);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String amount = rs.getString("amount");
                String date = rs.getString("date_time");

                area.append(date + "   Rs " + amount + "\n");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement("","1234");
    }
}
