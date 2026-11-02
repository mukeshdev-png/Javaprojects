package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Signupthree extends JFrame implements ActionListener {
    JRadioButton r1,r2,r3,r4;
    JCheckBox c1,c2,c3,c4,c5,c6,c7;
    JButton submit,cancel;
    String formno;

    Signupthree(String formno){
        this.formno = formno;
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setSize(800,800);
        frame.setTitle("page 3");
        frame.setLocation(560,340);
        frame.setBackground(Color.white);

        JLabel title = new JLabel("page 3 for account details" + formno);
        title.setBounds(300,50,400,60);
        frame.add(title);
        JLabel accty = new JLabel("ACCOUNT TYPE");
        accty.setBounds(300,100,200,40);
        frame.add(accty);

        r1 = new JRadioButton("savings account");
        r1.setBounds(300,150,150,40);
        frame.add(r1);
        r2 = new JRadioButton("fixed deposite account");
        r2.setBounds(470,150,150,40);
        frame.add(r2);
        r3 = new JRadioButton("current account");
        r3.setBounds(300,200,150,40);
        frame.add(r3);
        r4 = new JRadioButton("reccuring deposite account");
        r4.setBounds(470,200,150,40);
        frame.add(r4);

        ButtonGroup accg = new ButtonGroup();
        accg.add(r1);
        accg.add(r2);
        accg.add(r3);
        accg.add(r4);

        JLabel cardno = new JLabel("CARDNO :");
        cardno.setBounds(300,250,200,40);
        frame.add(cardno);
        JLabel cardnol = new JLabel("XXXX-XXXX-XXXX-4567");
        cardnol.setBounds(470,250,200,40);
        JLabel descard = new JLabel("your card no");
        descard.setBounds(300,280,200,40);
        frame.add(descard);
        frame.add(cardnol);
        JLabel pinno = new JLabel("PIN NO :");
        pinno.setBounds(300,330,200,40);
        frame.add(pinno);
        JLabel pinnodes = new JLabel("your card no");
        pinnodes.setBounds(300,360,200,40);
        frame.add(pinnodes);
        JLabel pinnol = new JLabel("XXXX");
        pinnol.setBounds(470,330,200,40);
        frame.add(pinnol);


        JLabel service = new JLabel("SERVICE REQUIRED");
        service.setBounds(300,410,200,40);
        frame.add(service);

        c1 = new JCheckBox("ATM CARD");
        c1.setBounds(300,450,200,40);
        frame.add(c1);

        c2 = new JCheckBox("INTERNET BANKING");
        c2.setBounds(500,450,200,40);
        frame.add(c2);

        c3 = new JCheckBox("MOBILE BANKING");
        c3.setBounds(300,480,200,40);
        frame.add(c3);

        c4 = new JCheckBox("EMAIL AND SMS ALERT");
        c4.setBounds(500,480,200,40);
        frame.add(c4);

        c5 = new JCheckBox("CHECK BOOK ");
        c5.setBounds(300,510,200,40);
        frame.add(c5);

        c6 = new JCheckBox("E- STATEMENT");
        c6.setBounds(500,510,200,40);
        frame.add(c6);

        c7 = new JCheckBox("i here by declare that the given info is correct and best of my knowledge");
        c7.setBounds(300,550,600,40);
        frame.add(c7);

        submit = new JButton("SUBMIT");
        submit.setBounds(300,600,100,40);
        submit.setBackground(Color.GREEN);
        submit.setForeground(Color.black);
        submit.addActionListener(this);
        frame.add(submit);

        cancel = new JButton("CANCEL");
        cancel.setBounds(500,600,100,40);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.black);
        cancel.addActionListener(this);
        frame.add(cancel);

        frame.setVisible(true);




    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String FORMNO = formno;
            String accountt = null;
            if (r1.isSelected()) {
                accountt = "saving account";

            } else if (r2.isSelected()) {
                accountt = "fixed deposite account";

            } else if (r3.isSelected()) {
                accountt = "current account";

            } else if (r4.isSelected()) {
                accountt = "reccuring deposite account";

            }

            Random rand = new Random();
            String cardnumber = "" + Math.abs((rand.nextLong() % 900000000L) + 5040936000000000L);
            System.out.println(cardnumber);
            String pinnumber = "" + Math.abs((rand.nextLong() % 9000L) + 1000L);

            String facility = "";
            if (c1.isSelected()) {
                facility = facility + " ATM Card ,";
            }if (c2.isSelected()) {
                facility = facility + " , Internet Banking ,";
            }if (c3.isSelected()) {
                facility = facility + " , Mobile Banking ,";
            }if (c4.isSelected()) {
                facility = facility + ", EMAIL & SMS Alerts ,";
            }if (c5.isSelected()) {
                facility = facility + " Cheque Book";
            }if (c6.isSelected()) {
                facility = facility + " E-Statement";
            }


            try {
                if (accountt == null)
                {
                    JOptionPane.showMessageDialog(null,"choose the required atm type");
                }
                else{
                    Connection con = DBcon.getConnection();
                    String query = "insert into signupthree values(?,?,?,?,?)";
                    String query2 = "insert into signupthreelogs values(?,?,?)";
                    String query3 = "UPDATE bank SET amount = 0, date_time = ? WHERE pinno = ?";
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String dateTime = now.format(formatter);
                    PreparedStatement ps = con.prepareStatement(query);
                    PreparedStatement pss = con.prepareStatement(query2);
                    PreparedStatement psss = con.prepareStatement(query3);
                    ps.setString(1,FORMNO);
                    ps.setString(2,accountt);
                    ps.setString(3,cardnumber);
                    ps.setString(4,pinnumber);
                    ps.setString(5,facility);
                    ps.executeUpdate();



                    pss.setString(1,FORMNO);
                    pss.setString(2,cardnumber);
                    pss.setString(3,pinnumber);

                    pss.executeUpdate();
                    psss.setString(1,dateTime);
                    psss.setString(2,pinnumber);
                    psss.executeUpdate();
                    JOptionPane.showMessageDialog(null,"CARDNO :" + cardnumber +"\n" +"PINNO : "+ pinnumber);
                    setVisible(false);
                    new TransactionUI(pinnumber,"").setVisible(true);



                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
                public static void main (String[]args){
                    new Signupthree("");

                }

            }