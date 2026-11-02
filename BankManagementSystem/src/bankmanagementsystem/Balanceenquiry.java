package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Balanceenquiry extends JFrame implements ActionListener {

    int balance; // pass balance as string
    JButton backBtn;
    String pinno;

    Balanceenquiry(int balance,String pinno) {
        this.balance = balance;
        this.pinno = pinno;

        // Frame settings
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Balance Enquiry");
        setSize(500, 300);
        setLocation(400, 200);
        setLayout(null);

        // Background color (dark like your image)
        getContentPane().setBackground(Color.BLACK);

        // Label to show balance
        JLabel text = new JLabel("Your Current Account Balance is Rs " + balance);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.BOLD, 16));
        text.setBounds(50, 80, 400, 30);
        add(text);

        // Back Button
        backBtn = new JButton("Back");
        backBtn.setBounds(180, 180, 120, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        setVisible(true);
    }

    // Button Action
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            setVisible(false);
            new TransactionUI(pinno,"").setVisible(true);
            // You can open previous screen here
            // new Transactions(pinno);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        new Balanceenquiry(0,""); // example balance
    }
}
