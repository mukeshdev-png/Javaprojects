package active;

import javax.swing.*;

public class Mainfun {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        frame.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(120, 30, 120, 25);
        frame.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        frame.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(120, 70, 120, 25);
        frame.add(passField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(90, 110, 100, 30);
        frame.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if(username.equals("mukesh")&& password.equals("2712")){
                JOptionPane.showMessageDialog(frame,"loginsuccesfully");
                frame.dispose();
                new AdminGUI();
            } else if (username.equals("student")&& password.equals("studentpass")){
                JOptionPane.showMessageDialog(frame,"loginsuccessfully");
                frame.dispose();
                new StudentGUI();

            } else{
                JOptionPane.showMessageDialog(frame,"invalid username or password");
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
