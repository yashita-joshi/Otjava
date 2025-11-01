import java.io.*;
import java.util.*;

public class ClassManagementSystem implements Serializable {
    private static final String DATA_FILE = "classesData.ser";
    private ArrayList<ClassRoom> classes = new ArrayList<>();
    private ArrayList<Student> allStudents = new ArrayList<>();

    // Load existing data from file
    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            ClassManagementSystem loaded = (ClassManagementSystem) ois.readObject();
            this.classes = loaded.classes;
            this.allStudents = loaded.allStudents;
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            System.out.println("No saved data found, starting fresh.");
        }
    }

    // Save current data to file
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving data.");
        }
    }

    // Add a new class
    public void addClass(String className, String courseCode, String instructor, int maxStudents) {
        for (ClassRoom c : classes)
            if (c.getCourseCode().equals(courseCode)) {
                System.out.println("Class with this course code already exists.");
                return;
            }
        classes.add(new ClassRoom(className, courseCode, instructor, maxStudents));
        System.out.println("Class added successfully.");
    }

    // Register a new student globally
    public void registerStudent(String studentId, String name) {
        for (Student s : allStudents)
            if (s.getStudentId().equals(studentId)) {
                System.out.println("Student already registered.");
                return;
            }
        allStudents.add(new Student(studentId, name));
        System.out.println("Student registered successfully.");
    }

    // List all classes
    public void listClasses() {
        if (classes.isEmpty()) {
            System.out.println("No classes to display.");
            return;
        }
        for (ClassRoom c : classes)
            System.out.println(c);
    }

    // Enroll student in a class
    public void enrollStudent(String studentId, String courseCode) {
        Student student = getStudent(studentId);
        if (student == null) {
            System.out.println("Student not registered.");
            return;
        }
        ClassRoom classRoom = getClass(courseCode);
        if (classRoom == null) {
            System.out.println("Class not found.");
            return;
        }
        if (classRoom.enrollStudent(student))
            System.out.println("Student enrolled successfully.");
        else
            System.out.println("Enrollment failed. Class full or student already enrolled.");
    }

    // Remove student from a class
    public void removeStudent(String studentId, String courseCode) {
        ClassRoom classRoom = getClass(courseCode);
        if (classRoom == null) {
            System.out.println("Class not found.");
            return;
        }
        if (classRoom.removeStudent(studentId))
            System.out.println("Student removed from class.");
        else
            System.out.println("Student not enrolled in the class.");
    }

    // Display students in a class
    public void displayStudents(String courseCode) {
        ClassRoom classRoom = getClass(courseCode);
        if (classRoom == null) {
            System.out.println("Class not found.");
            return;
        }
        ArrayList<Student> students = classRoom.getEnrolledStudents();
        if (students.isEmpty())
            System.out.println("No students enrolled.");
        else {
            System.out.println("Students enrolled in " + classRoom.getClassName() + ":");
            for (Student s : students)
                System.out.println(s);
        }
    }

    // Helper to get student by ID
    private Student getStudent(String studentId) {
        for (Student s : allStudents)
            if (s.getStudentId().equals(studentId))
                return s;
        return null;
    }

    // Helper to get class by course code
    private ClassRoom getClass(String courseCode) {
        for (ClassRoom c : classes)
            if (c.getCourseCode().equals(courseCode))
                return c;
        return null;
    }

    // Command line interface
    public static void main(String[] args) {
        ClassManagementSystem cms = new ClassManagementSystem();
        cms.loadData();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Class Management System ---");
            System.out.println("1. Add a new class");
            System.out.println("2. Register a new student");
            System.out.println("3. List all classes");
            System.out.println("4. Enroll student in class");
            System.out.println("5. Remove student from class");
            System.out.println("6. Display students in a class");
            System.out.println("7. Save data");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Class Name: "); String cn = sc.nextLine();
                        System.out.print("Course Code: "); String cc = sc.nextLine();
                        System.out.print("Instructor: "); String inst = sc.nextLine();
                        System.out.print("Max Students: "); int max = sc.nextInt(); sc.nextLine();
                        cms.addClass(cn, cc, inst, max); break;
                    case 2:
                        System.out.print("Student ID: "); String sid = sc.nextLine();
                        System.out.print("Student Name: "); String sn = sc.nextLine();
                        cms.registerStudent(sid, sn); break;
                    case 3:
                        cms.listClasses(); break;
                    case 4:
                        System.out.print("Student ID: "); String eid = sc.nextLine();
                        System.out.print("Course Code: "); String ecc = sc.nextLine();
                        cms.enrollStudent(eid, ecc); break;
                    case 5:
                        System.out.print("Student ID: "); String rid = sc.nextLine();
                        System.out.print("Course Code: "); String rcc = sc.nextLine();
                        cms.removeStudent(rid, rcc); break;
                    case 6:
                        System.out.print("Course Code: "); String dcc = sc.nextLine();
                        cms.displayStudents(dcc); break;
                    case 7:
                        cms.saveData(); break;
                    case 8:
                        cms.saveData();
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                sc.nextLine();
            }
        }
    }
}
