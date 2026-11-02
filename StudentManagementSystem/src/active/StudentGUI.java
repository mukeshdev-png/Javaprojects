package active;

import javax.swing.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


public class StudentGUI {
    static JTable table;
    static String[] columns = {"ID", "Name", "Age"};
    DefaultTableModel tableModel;
    public void loadTableData() {
        System.out.println("Loading table...");
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            tableModel.setRowCount(0); // clear old data

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");


                tableModel.addRow(new Object[]{id, name, age});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StudentGUI(){









        JFrame frame = new JFrame("STUDENTDASHBOARD");

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(null);  // 🔥 VERY IMPORTANT

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 50, 100, 30);
        frame.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(150, 50, 150, 30);
        frame.add(idField);


// NAME
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 100, 100, 30);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 100, 150, 30);
        frame.add(nameField);

// AGE
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 150, 100, 30);
        frame.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(150, 150, 150, 30);
        frame.add(ageField);
        JTextArea textArea = new JTextArea();
        textArea.setBounds(50, 600, 400, 200);
        frame.add(textArea);

        // empty table

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30, 400, 350, 120);

        frame.add(scroll);





        JButton viewbutton = new JButton("viewstudent");
        viewbutton.setBounds(150, 200, 100, 30);
        frame.add(viewbutton);











        viewbutton.addActionListener(e -> {


            loadTableData();
        });








        frame.setVisible(true);
    }

    public static void main(String[] args) {

        new StudentGUI();


    }
}
