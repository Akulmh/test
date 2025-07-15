import java.io.*;
import java.util.*;

/**
 * A student management system that demonstrates legacy Java practices.
 * This system manages student records, grades, and course enrollments.
 * Created in Java 6 style with room for modernization.
 * Using Github for version control
 */
public class StudentManagementSystem {
    private Vector<Student> students;
    private Hashtable<String, Course> courses;
    private static final String DATA_FILE = "students.dat";
    private static StudentManagementSystem instance = null;

    private StudentManagementSystem() {
        students = new Vector<Student>();
        courses = new Hashtable<String, Course>();
        initializeSampleData();
    }

    public static StudentManagementSystem getInstance() {
        if (instance == null) {
            instance = new StudentManagementSystem();
        }
        return instance;
    }

    private void initializeSampleData() {
        // Initialize with some sample courses
        Course java101 = new Course("CS101", "Introduction to Java", 30);
        Course python201 = new Course("CS201", "Advanced Python", 25);
        Course database301 = new Course("CS301", "Database Systems", 20);

        courses.put(java101.getCourseCode(), java101);
        courses.put(python201.getCourseCode(), python201);
        courses.put(database301.getCourseCode(), database301);
    }

    public void addStudent(String name, Integer id, String email) throws Exception {
        if (name == null || id == null || email == null) {
            throw new Exception("Invalid student data");
        }

        // Check if student already exists
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getId().equals(id)) {
                throw new Exception("Student with ID " + id + " already exists");
            }
        }

        students.add(new Student(name, id, email));
    }

    public Boolean enrollStudent(Integer studentId, String courseCode) throws Exception {
        Student student = null;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(studentId)) {
                student = students.get(i);
                break;
            }
        }

        if (student == null) {
            throw new Exception("Student not found");
        }

        Course course = courses.get(courseCode);
        if (course == null) {
            throw new Exception("Course not found");
        }

        if (course.getEnrolledStudents() >= course.getMaxCapacity()) {
            return Boolean.FALSE;
        }

        // Check if student is already enrolled
        Vector<String> enrolledCourses = student.getEnrolledCourses();
        for (int i = 0; i < enrolledCourses.size(); i++) {
            if (enrolledCourses.get(i).equals(courseCode)) {
                return Boolean.FALSE;
            }
        }

        student.enrollInCourse(courseCode);
        course.incrementEnrollment();
        return Boolean.TRUE;
    }

    public void saveToFile() throws Exception {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(DATA_FILE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(students);
        } catch (IOException e) {
            throw new Exception("Error saving data: " + e.getMessage());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    // Ignore close errors
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    // Ignore close errors
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() throws Exception {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(DATA_FILE);
            ois = new ObjectInputStream(fis);
            students = (Vector<Student>) ois.readObject();
        } catch (IOException e) {
            throw new Exception("Error loading data: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new Exception("Error loading data: Invalid format");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    // Ignore close errors
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // Ignore close errors
                }
            }
        }
    }

    public Vector<Student> getStudentsByGrade(Double minGrade) {
        Vector<Student> result = new Vector<Student>();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getAverageGrade() >= minGrade) {
                result.add(student);
            }
        }
        return result;
    }

    public void printEnrollmentReport() {
        for (Enumeration<Course> e = courses.elements(); e.hasMoreElements();) {
            Course course = e.nextElement();
            System.out.println("Course: " + course.getCourseCode() + " - " + 
                             course.getCourseName());
            System.out.println("Enrollment: " + course.getEnrolledStudents() + "/" + 
                             course.getMaxCapacity());
        }
    }

    public static void main(String args[]) {
        StudentManagementSystem system = StudentManagementSystem.getInstance();

        try {
            system.addStudent("John Doe", new Integer(1001), "john@example.com");
            system.addStudent("Jane Smith", new Integer(1002), "jane@example.com");
            system.enrollStudent(new Integer(1001), "CS101");
            system.enrollStudent(new Integer(1002), "CS201");
            system.printEnrollmentReport();
            system.saveToFile();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class Student implements Serializable {
    private String name;
    private Integer id;
    private String email;
    private Vector<String> enrolledCourses;
    private Vector<Double> grades;

    public Student(String name, Integer id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.enrolledCourses = new Vector<String>();
        this.grades = new Vector<Double>();
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Vector<String> getEnrolledCourses() { return enrolledCourses; }

    public void enrollInCourse(String courseCode) {
        enrolledCourses.add(courseCode);
    }

    public void addGrade(Double grade) {
        grades.add(grade);
    }

    public Double getAverageGrade() {
        if (grades.size() == 0) return 0.0;
        Double sum = new Double(0.0);
        for (int i = 0; i < grades.size(); i++) {
            sum += grades.get(i);
        }
        return sum / grades.size();
    }
}

class Course {
    private String courseCode;
    private String courseName;
    private Integer maxCapacity;
    private Integer enrolledStudents;

    public Course(String courseCode, String courseName, Integer maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new Integer(0);
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public Integer getMaxCapacity() { return maxCapacity; }
    public Integer getEnrolledStudents() { return enrolledStudents; }

    public void incrementEnrollment() {
        enrolledStudents = new Integer(enrolledStudents.intValue() + 1);
    }
}