import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;


public class StudentManager{

    Map<Integer, Student> students;

    public StudentManager(){
        students = new TreeMap<>();
    }

    public StudentManager(Map<Integer, Student> students){
        this.students = students;
    }

    public void addStudent(Scanner scan){
        System.out.println("Enter name:");
        String name = scan.nextLine();
        System.out.println("Enter id:");
        int id = Integer.valueOf(scan.nextLine());
        Student student = new Student(name, id);
        if(students.putIfAbsent(id, student) == null){
            System.out.println("\nStudent '" + student + "' was added");
        }else{
            System.err.println("\nID '" + id + "' is already taken!");
        }
    }
    public void addStudentCourse(Scanner scan){
        Student student = getStudent(scan);
        if(student != null)
        {
            System.out.println("Enter course name:");
            String course = scan.nextLine();
            student.addCourse(course);
        }
    }

    public void addStudentGrade(Scanner scan){
        Student student = getStudent(scan);
        if(student != null)
        {
            System.out.println("Enter course name:");
            String course = scan.nextLine();
            System.out.println("Enter grade:");
            int grade = Integer.valueOf(scan.nextLine());
            student.addCourse(course, grade);
        }
        }
        public void displayStudentInfo(Scanner scan){
        Student student = getStudent(scan);
            if(student != null) {
                student.displayGrades();
            }
        }



    public void displayStudents(){
        System.out.println("\nList of students: ");
        if(students.isEmpty()){
            System.out.println("- no students yet");
            return;
        }
        for(Map.Entry<Integer, Student> entry: students.entrySet()){
            System.out.println("*\t" + entry.getValue());
        }
    }

    public void displayAverageGrades(){
        System.out.println("\nAverage grades: ");
        if(students.isEmpty()){
            System.out.println("- no grades yet");
            return;
        }
        for(Map.Entry<Integer, Student> entry: students.entrySet()){
            System.out.println("*\t" + entry.getValue() + " -> " + entry.getValue().average());
        }
    }

    public Student getStudent(Scanner scan){
        System.out.println("Enter id:");
        int id = Integer.valueOf(scan.nextLine());
        if(!students.containsKey(id)) {
            System.err.println("\nID " + id + " doesn't exist!");
            return null;
        }
        return students.get(id);
    }

    public void menu(){
        Scanner scan = new Scanner(System.in);

        while(true) {
            displayMenu();
            switch(Integer.valueOf(scan.nextLine())){
                case 1:
                    addStudent(scan);
                    break;
                case 2:
                    addStudentCourse(scan);
                    break;
                case 3:
                    addStudentGrade(scan);
                    break;
                case 4:
                    displayStudentInfo(scan);
                    break;
                case 5:
                    displayStudents();
                    break;
                case 6:
                    displayAverageGrades();
                    break;
                case 7:
                    scan.close();
                    System.exit(0);
                default:
                    System.err.println("\n Invalid input");
                    break;
            }

        }

    }

    public void displayMenu(){
        System.out.println("\nMenu: ");
        System.out.println("* Add a student:          -> '1'");
        System.out.println("* Add a course:           -> '2'");
        System.out.println("* Add a grade:            -> '3'");
        System.out.println("* View student info:      -> '4'");
        System.out.println("* View list of students:  -> '5'");
        System.out.println("* Display average grade:  -> '6'");
        System.out.println("* Exit:                   -> '7'");
    }

}
