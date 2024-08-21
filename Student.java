import java.util.Map;
import java.util.TreeMap;

public class Student implements Comparable<Student>{

    String name;
    Integer id;
    Map<String, Integer> courses;

    public Student(String name, Integer id){
        this.name = name;
        this.id = id;
        courses = new TreeMap<>();
    }
    public Student(String name, Integer id, Map<String, Integer> courses){
        this.name = name;
        this.id = id;
        this.courses = courses; // store <courseName, grade>
    }

    @Override
    public int compareTo(Student student) {
        return id.compareTo(student.id);
    }

    @Override
    public String toString() {
        return name + " [id = " + id + "]";
    }

    public double average(){
        if(courses.isEmpty()){
            return 0;
        }
        double sum = 0;
        int count = 0;
        for(Map.Entry<String, Integer> entry: courses.entrySet()){
            if(entry.getValue() != null) {
                sum += entry.getValue();
                count++;
            }
        }
        if(count == 0){
            return 0;
        }
        return sum / count;
    }

    public void displayGrades(){
        System.out.println("\n" + this + ":");
        if(courses.isEmpty()){
            System.out.println(" - no courses yet");
            return;
        }

        for(Map.Entry<String, Integer> entry: courses.entrySet()){
            Integer grade = entry.getValue();
            if(grade == null){
                System.out.println(entry.getKey() + ": - ;");
            }else{
                System.out.println(entry.getKey() + ": " + entry.getValue() + ";");
            }
        }
    }

    public void addCourse(String courseName){
        if (courses.putIfAbsent(courseName, null) == null){
            System.out.println("\nCourse '" + courseName + "' was added");
        }else{
            System.out.println("\nCourse '" + courseName + "' is already exist");
        }
    }
    public void addCourse(String courseName, int grade){
        if (courses.putIfAbsent(courseName, grade) == null){
            System.out.println("\nCourse '" + courseName + "' was added with the grade -> " + grade);
        }else{
            System.out.println("\nCourse '" + courseName + "' is already exist");
            if(!courses.get(courseName).equals(grade)){
                courses.put(courseName, grade);
                System.out.println("\nGrade '" + grade + "' was updated to -> " + grade);
            }
        }
    }

}
