import java.util.Map;
import java.util.TreeMap;

public class Student {
    private String name;
    private String id;
    private Map<String, Double> grades;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.grades = new TreeMap<String, Double>();
    }

    public void addGrade(String course, double score) {
        if(grades.putIfAbsent(course, score) != null) {
            System.out.println("Course grade already exists.");
        }
    }

    private double avgGrade() {
        double avg = 0.0;
        for (Map.Entry<String,Double> entry : grades.entrySet()) {
            avg += entry.getValue();
        }
        return avg / grades.size();
    }

    public void printInfo() {

        System.out.printf("%-9s %-20s %-10s %n","ID","STUDENT", "AVG GRADE");
        Main.printLine();
        System.out.println("%-9s %-20s %-4.2f %n".formatted(id, name, avgGrade()));
        System.out.println();
        System.out.printf("%-20s %-6s %n","ID","COURSE ", "GRADE");
        Main.printLine();
        for (Map.Entry<String,Double> entry : grades.entrySet()) {
            System.out.printf("%-20s %-6s %n",
                    entry.getKey() + ": ", entry.getValue());
        }
        System.out.println();


    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%9s %-20s %-4.2f %n".formatted(id, name, avgGrade());
    }
}
