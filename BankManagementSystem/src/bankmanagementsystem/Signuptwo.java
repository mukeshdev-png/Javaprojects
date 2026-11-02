package bankmanagementsystem;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.toedter.calendar.JDateChooser;

public class Signuptwo extends JFrame implements ActionListener {
        JComboBox rfield,cfield,incofield, edufield,occfield ;
        JTextField pann,adharf;
        JDateChooser datechooser;
        JRadioButton eyes,eno, syes, sno ;
        JButton next;
        ButtonGroup seniorg, eaccountg;
        String formno;
        public Signuptwo(String formno){
            this.formno = formno;
            JFrame frame = new JFrame();
            frame.setLayout(null);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            frame.setSize(800,500);
            frame.setTitle("SIGNUP PAGE 2");
            frame.setLocation(300,150);
            frame.setBackground(Color.white);


            JLabel title = new JLabel("application form 2 for additional information : " +formno);
            title.setBounds(250, 10, 300, 40);
            frame.add(title);
            JLabel religion = new JLabel("RELIGION :");
            religion.setBounds(150,40,270,50);
            frame.add(religion);
            String[] relivalue = {"hindhu","muslim","christian","others"};
            rfield = new JComboBox<>(relivalue);
            rfield.setBounds(300,40,350,30);
            frame.add(rfield);

            JLabel category = new JLabel("CATEGORY :");
            category.setBounds(150,70,270,50);
            frame.add(category);
            String[] catovalue = {"GENERAL","MBC","SC","OTHERS"};
            cfield = new JComboBox<>(catovalue);
            cfield.setBounds(300,70,350,30);
            frame.add(cfield);
            JLabel income = new JLabel("INCOME :");
            income.setBounds(150,100,270,50);
            frame.add(income);
            String[] incvalue = {"1 lakhs <","2 lakhs < "," 3 lakhs < ","upto 5 lakhs"};
            incofield = new JComboBox<>(incvalue);
            incofield.setBounds(300,100,350,30);
            frame.add(incofield);


            JLabel educational = new JLabel("EDU.QUALIFICATION :");
            educational.setBounds(150,130,270,50);
            frame.add(educational);
            String[] eduvalue = {"graduate","non-graduate","post-graduate","others"};
            edufield = new JComboBox<>(eduvalue);
            edufield.setBounds(300,130,350,30);
            frame.add(edufield);

            JLabel occupation = new JLabel("OCCUPATION :");
            occupation.setBounds(150,160,270,50);
            frame.add(occupation);
            String[] occvalue = {"salaried","unsalaried","selfemployee","others"};
            occfield = new JComboBox<>(occvalue);
            occfield.setBounds(300,160,350,30);
            frame.add(occfield);

            JLabel pan= new JLabel("PAN NUMBER :");
            pan.setBounds(150,190,270,50);
            frame.add(pan);

            pann = new JTextField();
            pann.setBounds(300,190,350,30);
            frame.add(pann);



            JLabel adhar = new JLabel("ADHAR NUMBER :");
            adhar.setBounds(150,220,270,50);
            frame.add(adhar);
            adharf = new JTextField();
            adharf.setBounds(300,220,350,30);
            frame.add(adharf);

            JLabel senoir = new JLabel("SENIOR CITIZEN :");
            senoir.setBounds(150,250,270,50);
            frame.add(senoir);
            syes = new JRadioButton("YES");
            syes.setBounds(300,250,70,30);
            frame.add(syes);
            sno = new JRadioButton("NO");
            sno.setBounds(380,250,70,30);
            frame.add(sno);




            seniorg = new ButtonGroup();
            seniorg.add(syes);
            seniorg.add(sno);



            JLabel eacc = new JLabel("EXISTING ACCOUNT :");
            eacc.setBounds(150,280,270,50);
            frame.add(eacc);

            eyes = new JRadioButton("YES");
            eyes.setBounds(300,280,70,30);
            frame.add(eyes);
            eno = new JRadioButton("NO");
            eno.setBounds(380,280,70,30);
            frame.add(eno);




            eaccountg = new ButtonGroup();
            eaccountg.add(eyes);
            eaccountg.add(eno);


            next = new JButton("NEXT");
            next.setBounds(510,370,70,30);
            next.setBackground(Color.black);
            next.setForeground(Color.ORANGE);
            next.addActionListener(this);
            frame.add(next);

            frame.setVisible(true);

        }
        public void actionPerformed(ActionEvent e) {
            String FORMNO = formno;

            String Religion = (String) rfield.getSelectedItem();
            String cat = (String) cfield.getSelectedItem();
            String incom = (String) incofield.getSelectedItem();
            String senio = null;
            if (syes.isSelected()) {
                senio = "yes";

            } else if (sno.isSelected()) {
                senio = "no";
            }

            String education = (String) edufield.getSelectedItem();
            String eaccoun = null;
            if (eyes.isSelected()) {
                eaccoun = "yes";

            } else if (eno.isSelected()) {
                eaccoun = "no";

            }
            String occupatio =(String) occfield.getSelectedItem();
            String pannn = pann.getText();
            String adha = adharf.getText();


            try {
                if (Religion.equals("")) {
                    JOptionPane.showMessageDialog(null, "enter required religion");
                } else {

                    Signupinfotwo s = new Signupinfotwo(FORMNO,Religion, cat, incom, education, senio, eaccoun, occupatio, pannn, adha);
                    Connection con = DBcon.getConnection();
                    String query = "INSERT INTO signuptwo VALUES (?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(query);


                    ps.setString(1, FORMNO);

                    ps.setString(2, Religion);
                    ps.setString(3, cat);
                    ps.setString(4, incom);
                    ps.setString(5, education);
                    ps.setString(6, senio);
                    ps.setString(7, eaccoun);
                    ps.setString(8, occupatio);
                    ps.setString(9, pannn);
                    ps.setString(10, adha);

                    ps.executeUpdate();
                    System.out.println("Student Added! for2");

                    setVisible(false);
                    new Signupthree(formno).setVisible(true);


                }

            } catch (Exception r) {
                r.printStackTrace();
            }
        }

        public static void main() {
            new Signuptwo("");

        }
    }


