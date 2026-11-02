package bankmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBcon {
    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/signup_db",
                    "root",
                    "root2712"
            );
            System.out.println("Connected to DB!");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}

