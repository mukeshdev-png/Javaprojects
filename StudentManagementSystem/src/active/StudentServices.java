package active;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



public class StudentServices {
    public  static final String file_name = "rangovaathiyar.txt";
    private static ArrayList<Student> students = new ArrayList<>();
    public static void displaymenu() {
        System.out.println("welcome to student management system");
        System.out.println("1.add student details");
        System.out.println("2.view student details");
        System.out.println("3.updatestudent");
        System.out.println("4.deletestudent");
        System.out.println("5.searchstudent");
        System.out.println("6.sortbyname");
        System.out.println("7.sortbyage");
        System.out.println("8.clearalldata");
        System.out.println("9.exit");

    }
    public static void addstudent(Scanner sc) {
        System.out.println("enter your student id");
        String id = sc.next();
        for (Student s : students) {
            if (s.id.equals(id)) {
                System.out.println("ID already exists!");
                return;
            }
        }
        System.out.println("enter your name");
        String name = sc.next();
        if (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }
        System.out.println("enter your age");
        int age = sc.nextInt();
        if (age <= 0) {
            System.out.println("Invalid age!");
            return;
        }

        Student stu = new Student(id, name, age);
        students.add(stu);
        savetofile();


    }

    public static void viewstudents() {
        for (Student a : students) {
            System.out.println(a);
        }
    }

    public static void updatestudent(Scanner sc) {
        System.out.println("enter student id to update");
        String ID = sc.next();
        for (Student i : students) {
            if (i.id.equals(ID)) {
                System.out.println("enter new name");
                i.name = sc.next();

                if (i.name.trim().isEmpty()) {
                    System.out.println("Name cannot be empty!");
                    return;
                }
                System.out.println("enter new age");
                i.age = sc.nextInt();
                if (i.age <= 0) {
                    System.out.println("Invalid age!");
                    return;
                }

                System.out.println("successfully updated");
                savetofile();
                return;
            }

        }
        System.out.println("id not found");

    }

    public static void deletestudent(Scanner sc) {
        System.out.println("enter id to delete the student");
        String ID = sc.next();
        for (int j = 0; j < students.size(); j++) {
            if (students.get(j).id.equals(ID)) {
                students.remove(j);
                System.out.println("successfully deleted");
                savetofile();
                return;

            }


        }
        System.out.println("id not found");

    }

    public static void searchstudent(Scanner sc) {
        System.out.println("enter student id to search");
        String ID = sc.next();
        for (Student k : students) {
            if (k.id.equals(ID)) {
                System.out.println("student found");
                System.out.println(k);
                return;
            }
        }
    }

    public static void savetofile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file_name));
            for (Student s : students) {
                writer.write(s.id + "," + s.name + "," + s.age);
                writer.newLine();

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("file does not exist");
        }

    }
    public static String sortByName() {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {

                if (students.get(i).name.compareToIgnoreCase(students.get(j).name) > 0) {
                    // swap
                    Student Temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, Temp);
                }
            }
        }

        System.out.println("Sorted by name:");
        String result = getAllStudents();
        return result;
    }
    public static String sortByAge() {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {

                if (students.get(i).age > students.get(j).age) {
                    // swap
                    Student temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, temp);
                }
            }
        }

        System.out.println("Sorted by age:");
        String result = getAllStudents();
        return result;
    }

    public static void loadfile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_name));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                int age = Integer.parseInt(data[2]);

                students.add(new Student(id,name,age));



            }
            reader.close();
        }
        catch (IOException e)
        {
            System.out.println("no prevoius data ");
        }

    }
    public static String addstudentFromGUI(String id, String name, int age) {

        for (Student s : students) {
            if (s.id.equals(id)) {
                System.out.println();
                return "ID already exists!";
            }
        }

         // create student
        Student m = new Student(id, name, age);

        students.add(m); // add to list

        savetofile();// save to file
        return "student added successfully";
    }
    public static String getAllStudents() {
        if (students.size() == 0) {
            return "No Data";
        }

        String result = "";


        for (Student s : students) {

            result += "ID: " + s.id + " | Name: " + s.name + " | Age: " + s.age + "\n";
        }

        return result;
    }

    public static void deleteFromGUI(String id) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).id.equals(id)) {

                students.remove(i);

                savetofile();

                return;
            }
        }

    }
    public static void clearAllData() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file_name));
            writer.write(""); // empty content
            writer.close();

            students.clear(); // also clear memory

            System.out.println("All data cleared!");

        } catch (IOException e) {
            System.out.println("Error clearing file");
        }
    }

    public static String updateFromGUI(String id, String name, int age) {

        for (Student s : students) {

            if (s.id.equals(id)) {

                s.name = name;
                s.age = age;

                savetofile();

                return "active.Student updated successfully!";
            }
        }

        return "ID not found!";
    }
    public static ArrayList<Student> getStudentList() {
        return students;
    }

}
