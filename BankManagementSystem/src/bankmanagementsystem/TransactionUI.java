package bankmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransactionUI extends JFrame implements ActionListener {
    String pinno ;
    String cardnumber;

    JButton deposit, withdraw, fastcash, ministatement, pinchange, Balance, exit;

        TransactionUI(String pinno,String cardnumber) {
            this.pinno = pinno;
            this.cardnumber = cardnumber;
            setLayout(null);
            setExtendedState(JFrame.MAXIMIZED_BOTH);

            setSize(800, 500);
            setLocation(300, 150);
            setTitle("ATM TRANSACTION");
            getContentPane().setBackground(Color.BLACK);

            JLabel title = new JLabel("Please select your Transaction");
            title.setBounds(250, 40, 400, 30);
            title.setForeground(Color.WHITE);
            title.setFont(new Font("Arial", Font.BOLD, 18));
            add(title);

            // LEFT SIDE BUTTONS
            deposit = new JButton("Deposit");
            deposit.setBounds(200, 120, 150, 30);
            add(deposit);

            fastcash = new JButton("Fast Cash");
            fastcash.setBounds(200, 170, 150, 30);
            add(fastcash);

            pinchange = new JButton("Pin Change");
            pinchange.setBounds(200, 220, 150, 30);
            add(pinchange);

            // RIGHT SIDE BUTTONS
            withdraw = new JButton("Cash Withdrawal");
            withdraw.setBounds(400, 120, 150, 30);
            add(withdraw);

            ministatement = new JButton("Mini Statement");
            ministatement.setBounds(400, 170, 150, 30);
            add(ministatement);

            Balance = new JButton("Balance Enquiry");
            Balance.setBounds(400, 220, 150, 30);
            add(Balance);

            exit = new JButton("Exit");
            exit.setBounds(400, 270, 150, 30);
            add(exit);

            // Add ActionListener
            deposit.addActionListener(this);
            withdraw.addActionListener(this);
            fastcash.addActionListener(this);
            ministatement.addActionListener(this);
            pinchange.addActionListener(this);
            Balance.addActionListener(this);
            exit.addActionListener(this);

            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == deposit) {
                new Deposit(pinno);
                JOptionPane.showMessageDialog(null, "Deposit Clicked");

            } else if (e.getSource() == withdraw) {
                new Withdraw(pinno);

                JOptionPane.showMessageDialog(null, "Cash Withdrawal Clicked");
            } else if (e.getSource() == fastcash) {
                new Fastcash(pinno);
                JOptionPane.showMessageDialog(null, "Fast Cash Clicked");
            } else if (e.getSource() == ministatement) {
                setVisible(false);
                new MiniStatement(pinno,cardnumber).setVisible(true);
                JOptionPane.showMessageDialog(null, "Mini Statement Clicked");
            } else if (e.getSource() == pinchange) {
                setVisible(false);
                new Pinchange(pinno).setVisible(true);
                JOptionPane.showMessageDialog(null, "Pin Change Clicked");
            } else if (e.getSource() == Balance) {
                int balance=0;

                try{
                    Connection con = DBcon.getConnection();
                    String query = "select amount from bank where pinno = ?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1,pinno);
                    ResultSet rs = ps.executeQuery();

                    if(rs.next()){
                      balance=rs.getInt("amount");
                    }
                }catch (Exception e1){
                    System.out.println(e1);
                }
                JOptionPane.showMessageDialog(null, "Balance Enquiry Clicked");

                setVisible(false);
                new Balanceenquiry(balance,pinno).setVisible(true);
            } else if (e.getSource() == exit) {
                System.exit(0);
            }
        }

        public static void main(String[] args) {
            new TransactionUI("","");
        }
    }

