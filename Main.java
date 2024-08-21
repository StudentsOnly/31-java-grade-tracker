import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main{

    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Student> students = new TreeMap<>();

    public static void main(String[] args) {

        boolean flag = true;
        while(flag){

            printMenu();

            String operation = scanner.nextLine().trim().toUpperCase();
            System.out.println();

            switch(operation.charAt(0)){
                case 'A' -> addStudent();
                case 'I' -> inputGrades();
                case 'V' -> studentInfo();
                case 'E' -> flag = false;
                default -> System.out.println("Invalid input.");
            }
        }

        scanner.close();
    }

    private static void addStudent() {
        try {
            System.out.println("Enter student id, name (comma-separated): ");
            String[] student = scanner.nextLine().split(",");
            String id = student[0].trim();
            String name = student[1].trim();

            if(id.isEmpty() || name.isEmpty()){
                System.out.println("Invalid input.");
            }
            if(students.putIfAbsent(id, new Student(name, id)) != null) {
                System.out.println("Student with id " + id + " already exists.");

            } else {
                System.out.println("Student " + name + " with id " + id + " added.");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding a student: "
                    + e.getMessage());
        }
    }

    private static void inputGrades() {
        try {
            Student student = selectStudent();
            if (student == null) {
                return;
            }

            System.out.println("Enter course name, grade: \n" +
                    "e.g. course1, 3.12; course 2, 2.14; ...");
            String[] input = scanner.nextLine().trim().split(";");
            for (var s : input) {
                inputGrade(student, s);
            }

        } catch (Exception e) {
            System.out.println("An unexpected error occurred while inputting grades: " + e.getMessage());
        }
    }

    private static void inputGrade(Student student, String s) {
        String[] inputAr = s.split(",");

        if(inputAr.length < 2){
            System.out.println("Invalid input.");
        }

        String course = inputAr[0].trim();
        double grade;

        try {
            grade = Double.parseDouble(inputAr[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid grade: " + inputAr[1].trim() + " is not a valid number.");
            return;
        }

        if (course.isEmpty() || grade < 0 || grade > 100) {
            System.out.println("Invalid input.");
            return;
        }
        student.addGrade(course, grade);
    }

    private static Student selectStudent() {
        try {
            printStudents();
            System.out.println("Enter the ID of the student you want to select: ");
            String id = scanner.nextLine().trim();

            Student student = students.get(id);
            if (student == null) {
                System.out.println("Student with id " + id + " does not exist.");
                return null;
            } else {
                return student;
            }

        } catch (Exception e) {
            System.out.println("An unexpected error occurred while selecting a student.");
        }

        return null;
    }

    private static void printStudents() {
        System.out.printf("%-9s %-20s %n","ID","STUDENT");
        printLine();
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            System.out.printf("%-9s %-20s %n",entry.getKey(),entry.getValue().getName());
        }
        System.out.println();
    }

    static void printLine() {
        System.out.println("-".repeat(60));
    }

    private static void studentInfo() {

        Student student = selectStudent();
        if (student == null) {
            return;
        }

        student.printInfo();
    }

    private static void printMenu(){
        // textblock keeps formatting as shown
        String menu = """
                
                MENU
                -----------------------------------
                (A)dd Student
                (I)nput Grades for selected student
                (V)iew Student info
                
                Enter operation:\s""";
        System.out.print(menu);
    }

}
