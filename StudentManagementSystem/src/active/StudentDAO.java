package active;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public  class StudentDAO {
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student s = new Student(
                        rs.getString("id"),   // ✅ FIXED
                        rs.getString("name"),
                        rs.getInt("age")
                );

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }



    // ADD
    public static void addStudent(String id, String name, int age) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO students VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);

            ps.executeUpdate();
            System.out.println("active.Student Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public static void updateStudent(String id, String name, int age) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "UPDATE students SET name=?, age=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, id);

            ps.executeUpdate();
            System.out.println("active.Student Updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deleteStudent(String id) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, id);

            ps.executeUpdate();
            System.out.println("active.Student Deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void clearAllStudents() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "DELETE FROM students";
            PreparedStatement ps = con.prepareStatement(query);

            ps.executeUpdate();

            System.out.println("All students deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Student> sortByName() {
        ArrayList<Student> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM students ORDER BY name ASC";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public static ArrayList<Student> sortByAge() {
        ArrayList<Student> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM students ORDER BY age ASC";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

