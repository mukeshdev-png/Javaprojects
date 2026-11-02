package active;

public class Student {
    String id;
    String name;
    int age;
    String Temp;
    int temp;


    Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return  "ID: " + id + " | Name: " + name + " | Age: " + age ;
    }
}
