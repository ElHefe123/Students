import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Student implements Comparable<Student> {
    private String name;
    private String address;
    private double GPA;

    public Student(String name, String address, double GPA) {
        this.name = name;
        this.address = address;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getGPA() {
        return GPA;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.getName());
    }
}

public class StudentDataEntry {
    public static void main(String[] args) {
        List<Student> studentList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        int numStudents;
        do {
            System.out.print("Enter the number of students: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // consume the invalid input
            }
            numStudents = scanner.nextInt();
        } while (numStudents <= 0);

        for (int i = 1; i <= numStudents; i++) {
            System.out.println("\nEnter data for student " + i + ":");
            System.out.print("Name: ");
            String name = scanner.next();

            System.out.print("Address: ");
            String address = scanner.next();

            double gpa;
            do {
                System.out.print("GPA: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input. Please enter a valid GPA.");
                    scanner.next(); // consume the invalid input
                }
                gpa = scanner.nextDouble();
            } while (gpa < 0 || gpa > 4); // Validate GPA within the range [0, 4]

            studentList.add(new Student(name, address, gpa));
        }

        // Sort the student list in ascending order by name
        Collections.sort(studentList);

        // Output the contents to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("student_data.txt"))) {
            for (Student student : studentList) {
                writer.write("Name: " + student.getName() + "\tAddress: " + student.getAddress() + "\tGPA: " + student.getGPA());
                writer.newLine();
            }
            System.out.println("Student data has been written to student_data.txt.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
