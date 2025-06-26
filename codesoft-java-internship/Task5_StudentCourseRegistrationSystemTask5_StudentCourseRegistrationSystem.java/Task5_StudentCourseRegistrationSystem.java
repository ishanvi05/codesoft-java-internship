import java.util.*;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    List<Student> enrolledStudents = new ArrayList<>();

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public boolean registerStudent(Student student) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public void displayCourse() {
        System.out.println("\nCourse Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Available Slots: " + (capacity - enrolledStudents.size()));
    }
}

class Student {
    String id;
    String name;
    List<Course> registeredCourses = new ArrayList<>();

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void registerCourse(Course course) {
        if (course.registerStudent(this)) {
            registeredCourses.add(course);
            System.out.println("✅ Registered for course: " + course.title);
        } else {
            System.out.println("❌ Course is full. Cannot register.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.removeStudent(this);
            registeredCourses.remove(course);
            System.out.println("🗑️ Dropped course: " + course.title);
        } else {
            System.out.println("⚠️ You are not registered in this course.");
        }
    }

    public void viewRegisteredCourses() {
        System.out.println("\n📚 " + name + "'s Registered Courses:");
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println("- " + course.title + " (" + course.code + ")");
            }
        }
    }
}

public class Task5_StudentCourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample Courses
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Intro to Programming", "Basics of Java", 2, "Mon-Wed"));
        courses.add(new Course("MATH201", "Calculus I", "Differentiation and Integration", 2, "Tue-Thu"));
        courses.add(new Course("ENG105", "English Communication", "Professional writing", 2, "Fri"));

        // Student setup
        System.out.print("Enter your Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your Name: ");
        String name = scanner.nextLine();
        Student student = new Student(id, name);

        int choice;
        do {
            System.out.println("\n=== STUDENT COURSE REGISTRATION MENU ===");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear input buffer

            switch (choice) {
                case 1:
                    for (Course c : courses) {
                        c.displayCourse();
                    }
                    break;

                case 2:
                    System.out.print("Enter course code to register: ");
                    String codeToRegister = scanner.nextLine();
                    Course courseToRegister = findCourseByCode(courses, codeToRegister);
                    if (courseToRegister != null)
                        student.registerCourse(courseToRegister);
                    else
                        System.out.println("❌ Course not found.");
                    break;

                case 3:
                    System.out.print("Enter course code to drop: ");
                    String codeToDrop = scanner.nextLine();
                    Course courseToDrop = findCourseByCode(courses, codeToDrop);
                    if (courseToDrop != null)
                        student.dropCourse(courseToDrop);
                    else
                        System.out.println("❌ Course not found.");
                    break;

                case 4:
                    student.viewRegisteredCourses();
                    break;

                case 5:
                    System.out.println("👋 Exiting... Thank you!");
                    break;

                default:
                    System.out.println("⚠️ Invalid choice!");
            }

        } while (choice != 5);

        scanner.close();
    }

    public static Course findCourseByCode(List<Course> courses, String code) {
        for (Course c : courses) {
            if (c.code.equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }
}