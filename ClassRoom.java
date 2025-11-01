import java.io.Serializable;
import java.util.ArrayList;

public class ClassRoom implements Serializable {
    private String className;
    private String courseCode;
    private String instructor;
    private int maxStudents;
    private ArrayList<Student> enrolledStudents;

    public ClassRoom(String className, String courseCode, String instructor, int maxStudents) {
        this.className = className;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.maxStudents = maxStudents;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getClassName() { return className; }

    public String getCourseCode() { return courseCode; }

    public String getInstructor() { return instructor; }

    public int getMaxStudents() { return maxStudents; }

    public ArrayList<Student> getEnrolledStudents() { return enrolledStudents; }

    public boolean enrollStudent(Student student) {
        if (enrolledStudents.size() >= maxStudents) return false;
        for (Student s : enrolledStudents)
            if (s.getStudentId().equals(student.getStudentId()))
                return false;
        enrolledStudents.add(student);
        return true;
    }

    public boolean removeStudent(String studentId) {
        return enrolledStudents.removeIf(s -> s.getStudentId().equals(studentId));
    }

    public boolean hasStudent(String studentId) {
        for (Student s : enrolledStudents)
            if (s.getStudentId().equals(studentId))
                return true;
        return false;
    }

    @Override
    public String toString() {
        return "Class: " + className + " | Code: " + courseCode + " | Instructor: " + instructor +
               " | Max Students: " + maxStudents + " | Enrolled: " + enrolledStudents.size();
    }
}
