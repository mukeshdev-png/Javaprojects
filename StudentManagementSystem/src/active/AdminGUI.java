package active;

import javax.swing.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class AdminGUI {
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

    public AdminGUI(){










        JFrame frame = new JFrame("admin dashboard");

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
        JLabel searchlabel = new JLabel("search");
        searchlabel.setBounds(50, 10, 100, 30);
        frame.add(searchlabel);
        JTextField searchfield = new JTextField();
        searchfield.setBounds(150, 10, 100, 30);
        frame.add(searchfield);

        // empty table

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30, 400, 350, 120);

        frame.add(scroll);


        JButton addButton = new JButton("add student");

        addButton.setBounds(30, 200, 100, 30);

        frame.add(addButton);
        JButton viewbutton = new JButton("viewstudent");
        viewbutton.setBounds(150, 200, 100, 30);
        frame.add(viewbutton);

        JButton deleteButton = new JButton("Delete");

        deleteButton.setBounds(270, 200, 100, 30);

        frame.add(deleteButton);

        JButton clearButton = new JButton("Clear All");

        clearButton.setBounds(270, 240, 120, 30);
        frame.add(clearButton);



        JButton updateButton = new JButton("Update");

        updateButton.setBounds(150, 240, 120, 30); // adjust if needed

        frame.add(updateButton);

        JButton sortname = new JButton("sortbyname");
        sortname.setBounds(300,300,120,30);
        frame.add(sortname);
        JButton sortage = new JButton("sortbyage");
        sortage.setBounds(30,240,120,30);
        frame.add(sortage);

        addButton.addActionListener(e -> {

            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            StudentDAO.addStudent(id, name, age);
            loadTableData();
            idField.setText("");
            nameField.setText("");
            ageField.setText("");
            idField.requestFocus();

        });





        viewbutton.addActionListener(e -> {


            loadTableData();
        });













        deleteButton.addActionListener(e -> {
            String id = idField.getText();

            StudentDAO.deleteStudent(id);


            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                StudentServices.deleteFromGUI(id);

                textArea.setText("active.Student Deleted!");
                loadTableData();
                idField.requestFocus();

            }
        });

        clearButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete?" +
                            "it clear the data in the database",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {

                StudentDAO.clearAllStudents();


            }



            textArea.setText("All data cleared!");
            loadTableData();
            idField.requestFocus();


        });

        updateButton.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());

            StudentDAO.updateStudent(id, name, age);



            loadTableData();
            idField.requestFocus();


        });
        sortname.addActionListener(e -> {


                ArrayList<Student> list = StudentDAO.sortByName();

                tableModel.setRowCount(0);

                for (Student s : list) {
                    tableModel.addRow(new Object[]{
                            s.id,
                            s.name,
                            s.age
                    });
                }

            });



        sortage.addActionListener(e -> {


                ArrayList<Student> list = StudentDAO.sortByAge();

                tableModel.setRowCount(0);

                for (Student s : list) {
                    tableModel.addRow(new Object[]{
                            s.id,
                            s.name,
                            s.age
                    });
                }


        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {

                int row = table.getSelectedRow();

                idField.setText(table.getValueAt(row, 0).toString());
                nameField.setText(table.getValueAt(row, 1).toString());
                ageField.setText(table.getValueAt(row, 2).toString());
            }
        });
        searchfield.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent e) {

                String text = searchfield.getText().toLowerCase();

                ArrayList<Student> list = StudentServices.getStudentList();

                String[][] data = new String[list.size()][3];

                int row = 0;
                for(int i=0;i< list.size();i++){
                    String id = list.get(i).id;
                    String name= list.get(i).name;

                    if(id.contains(text) || name.contains(text)){
                        data[row][0] = list.get(i).id;
                        data[row][1] = list.get(i).name;
                        data[row][2] = list.get(i).age + "";
                        row++;
                    }



                }
                table.setModel(new javax.swing.table.DefaultTableModel(data, columns));



            }

        });




        frame.setVisible(true);
        idField.requestFocus();
        idField.addActionListener(e -> nameField.requestFocus());
        nameField.addActionListener(e -> ageField.requestFocus());
        ageField.addActionListener(e -> idField.requestFocus());


    }

    public static void main(String[] args) {

        new AdminGUI();


    }
}
