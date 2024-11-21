package Students;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>(List.of(
                new Student("Ivan", 15, 10.5),
                new Student("Anton", 20, 8),
                new Student("Anna", 25, 12),
                new Student("Maria", 23, 11.5),
                new Student("Viktoria", 22, 8.5)
        ));
        System.out.println(filterByAge(students, 22));
        System.out.println(getNames(students));
        System.out.println(sumAges(students));

    }

    static List<Student> filterByAge(List<Student> students, int age){
        return students.stream()
                .filter(student -> student.getAge() > age)
                .toList();
    }
    static List<String> getNames(List<Student> students){
        return students.stream()
                .map(Student::getName)
                .toList();
    }
    static int sumAges(List<Student> students){
        return students.stream()
                .reduce(0,(partialAgeResult, student) -> partialAgeResult + student.getAge(), Integer::sum);
    }
}
