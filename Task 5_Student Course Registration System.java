import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private List<String> registeredStudents;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public List<String> getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean registerStudent(String studentId) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(studentId);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeStudent(String studentId) {
        return registeredStudents.remove(studentId);
    }
}

public class CourseRegistrationApp {
    private List<Course> courses;
    private List<String> students;

    public CourseRegistrationApp() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(String studentId) {
        students.add(studentId);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Registered Students: " + course.getRegisteredStudents().size());
            System.out.println("-------------------------");
        }
    }

    public void registerStudentForCourse(String studentId, String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                if (course.registerStudent(studentId)) {
                    System.out.println("Student " + studentId + " registered successfully for course " + courseCode);
                } else {
                    System.out.println("Failed to register. Course " + courseCode + " is full.");
                }
                return;
            }
        }
        System.out.println("Course " + courseCode + " not found.");
    }

    public void removeStudentFromCourse(String studentId, String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                if (course.removeStudent(studentId)) {
                    System.out.println("Student " + studentId + " removed successfully from course " + courseCode);
                } else {
                    System.out.println("Failed to remove. Student " + studentId + " is not registered for course " + courseCode);
                }
                return;
            }
        }
        System.out.println("Course " + courseCode + " not found.");
    }

    public static void main(String[] args) {
        CourseRegistrationApp app = new CourseRegistrationApp();

        Course course1 = new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science", 30, List.of("Mon, Wed, Fri 10:00-11:00"));
        Course course2 = new Course("ENG201", "English Literature", "Survey of major works in English literature", 25, List.of("Tue, Thu 13:00-14:30"));
        Course course3 = new Course("MATH301", "Advanced Calculus", "Advanced topics in calculus", 20, List.of("Mon, Wed, Fri 14:00-15:00"));

        app.addCourse(course1);
        app.addCourse(course2);
        app.addCourse(course3);

        app.addStudent("1001");
        app.addStudent("1002");
        app.addStudent("1003");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Display Courses");
            System.out.println("2. Register Student for Course");
            System.out.println("3. Remove Student from Course");
            System.out.println("4. Exit");

            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    app.displayCourses();
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String courseCodeRegister = scanner.nextLine();
                    app.registerStudentForCourse(studentId, courseCodeRegister);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    String studentIdRemove = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String courseCodeRemove = scanner.nextLine();
                    app.removeStudentFromCourse(studentIdRemove, courseCodeRemove);
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}