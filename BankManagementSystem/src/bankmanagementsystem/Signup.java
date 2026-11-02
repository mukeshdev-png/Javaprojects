package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import com.toedter.calendar.JDateChooser;

public class Signup extends JFrame implements ActionListener {
    JTextField namet,fnamet,emailt,addresst,cityt,statet,pincodet;
    JDateChooser datechooser;
    JRadioButton radiobm,radiobf,maritalm,maritalum,maritalo;
    JButton next;
    ButtonGroup genderg,maritalg;
    long random;
    String formno;
    public Signup(){
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setSize(800,500);
        frame.setTitle("SIGNUP PAGE");
        frame.setLocation(300,150);
        frame.setBackground(Color.white);

        Random rand = new Random();
        random =(Math.abs(rand.nextLong()%9000L)+1000L);
        JLabel title = new JLabel("application formm 1 : " +random);
        formno =String.valueOf(random);
        title.setBounds(250, 10, 300, 40);
        frame.add(title);
        JLabel name = new JLabel("NAME :");
        name.setBounds(150,40,270,50);
        frame.add(name);
        namet = new JTextField();
        namet.setBounds(300,40,350,30);
        frame.add(namet);

        JLabel fname = new JLabel("FATHERNAME :");
        fname.setBounds(150,70,270,50);
        frame.add(fname);
        fnamet = new JTextField();
        fnamet.setBounds(300,70,350,30);
        frame.add(fnamet);
        JLabel dob = new JLabel("DOB :");
        dob.setBounds(150,100,270,50);
        frame.add(dob);
        datechooser = new JDateChooser();
        datechooser.setBounds(300,100,350,30);
        frame.add(datechooser);


        JLabel gender = new JLabel("GENDER :");
        gender.setBounds(150,130,270,50);
        frame.add(gender);
        radiobm = new JRadioButton("male");
        radiobm.setBounds(300,130,70,30);
        frame.add(radiobm);
        radiobf = new JRadioButton("female");
        radiobf.setBounds(400,130,70,30);
        frame.add(radiobf);

        genderg = new ButtonGroup();
        genderg.add(radiobm);
        genderg.add(radiobf);





        JLabel email = new JLabel("EMAILADDRESS :");
        email.setBounds(150,160,270,50);
        frame.add(email);
        emailt = new JTextField();
        emailt.setBounds(300,160,350,30);
        frame.add(emailt);
        JLabel marital = new JLabel("MARITALSTATUS :");
        marital.setBounds(150,190,270,50);
        frame.add(marital);
        maritalm = new JRadioButton("married");
        maritalm.setBounds(300,190,70,30);
        frame.add(maritalm);
        maritalum = new JRadioButton("unmarried");
        maritalum.setBounds(380,190,70,30);
        frame.add(maritalum);
        maritalo = new JRadioButton("others");
        maritalo.setBounds(500,190,70,30);
        frame.add(maritalo);



        maritalg = new ButtonGroup();
        maritalg.add(maritalm);
        maritalg.add(maritalum);
        maritalg.add(maritalo);




        JLabel address = new JLabel("ADDRESS :");
        address.setBounds(150,220,270,50);
        frame.add(address);
        addresst = new JTextField();
        addresst.setBounds(300,220,350,30);
        frame.add(addresst);

        JLabel city = new JLabel("CITY :");
        city.setBounds(150,250,270,50);
        frame.add(city);
        cityt = new JTextField();
        cityt.setBounds(300,250,350,30);
        frame.add(cityt);
        JLabel state = new JLabel("STATE :");
        state.setBounds(150,280,270,50);
        frame.add(state);
        statet = new JTextField();
        statet.setBounds(300,280,350,30);
        frame.add(statet);

        JLabel pincode = new JLabel("PINCODE :");
        pincode.setBounds(150,310,270,50);
        frame.add(pincode);
        pincodet = new JTextField();
        pincodet.setBounds(300,310,350,30);
        frame.add(pincodet);
        next = new JButton("NEXT");
        next.setBounds(510,370,70,30);
        next.setBackground(Color.black);
        next.setForeground(Color.ORANGE);
        next.addActionListener(this);
        frame.add(next);

        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {

        String name = namet.getText();
        String fname = fnamet.getText();
        String dob = ((JTextField) datechooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (radiobm.isSelected()) {
            gender = "male";

        } else if (radiobf.isSelected()) {
            gender = "female";
        }
        String email = emailt.getText();
        String marital = null;
        if (maritalm.isSelected()) {
            marital = "married";

        } else if (maritalum.isSelected()) {
            marital = "unmarried";

        } else if (maritalo.isSelected()) {
            marital = "others";
        }
        String address = addresst.getText();
        String city = cityt.getText();
        String state = statet.getText();
        String pincode = pincodet.getText();

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "enter required name");
            } else {

                Signupinfo s = new Signupinfo(name, fname, dob, email, gender, marital, address, city, state, pincode);
                Connection con = DBcon.getConnection();
                String query = "INSERT INTO signup VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, formno);

                ps.setString(2, name);
                ps.setString(3, fname);
                ps.setString(4, dob);
                ps.setString(5, email);
                ps.setString(6, gender);
                ps.setString(7, marital);
                ps.setString(8, address);
                ps.setString(9, city);
                ps.setString(10, state);
                ps.setString(11, pincode);
                ps.executeUpdate();
                System.out.println("Student Added!");

                setVisible(false);
                new Signuptwo(formno).setVisible(true);




            }

        } catch (Exception r) {
            r.printStackTrace();
        }
    }

    public static void main() {
        new Signup();

    }
}
