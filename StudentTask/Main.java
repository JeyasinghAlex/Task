package Task.StudentTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of student :- ");
        int numberOfStudent = scan.nextInt();
        Student[] students = new Student[numberOfStudent];
        List<Subject> subjects = null;

        for (int i = 0; i < numberOfStudent; ++i) {
            scan.nextLine();
            subjects = new ArrayList<>();
            System.out.print("Enter the " + (i + 1) + " student name - ");
            String name = scan.nextLine();
            Student student = new Student(name);

            System.out.print("Enter the tamil mark - ");
            int tamil = scan.nextInt();
            subjects.add(new Subject(SubjectName.TAMIL, tamil));

            System.out.print("Enter the english mark - ");
            int english = scan.nextInt();
            subjects.add(new Subject(SubjectName.ENGLISH, english));

            System.out.print("Enter the maths mark - ");
            int maths = scan.nextInt();
            subjects.add(new Subject(SubjectName.MATHS, maths));

            System.out.print("Enter the science mark - ");
            int science = scan.nextInt();
            subjects.add(new Subject(SubjectName.SCIENCE, science));

            System.out.print("Enter the social science mark - ");
            int socialScience = scan.nextInt();
            subjects.add(new Subject(SubjectName.SOCIAL_SCIENCE, socialScience));

            student.setSubjects(subjects);
            students[i] = student;
        }
        showStudentsStatus(students);
    }

    private static void showStudentsStatus(Student[] students) {
        boolean end = true;
        Map<String, Integer> rankOrder = StudentUtil.getRankBasedOnTotal(students);
        Map<String, Integer> subAverage = StudentUtil.getAverage(students);
        Map<String, Map<String, Integer>> aboveAverageMarks = StudentUtil.getAboveAverageMarkStudents(students, subAverage);
        Map<String, Map<String, Integer>> highestMarkStudents = StudentUtil.getHighestMarkStudentName(students);
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("\n*------------------------------------------------*");
            System.out.println("1 ) Find top rank : ");
            System.out.println("2 ) Show each subject's average marks  : ");
            System.out.println("3 ) Show students with above-average marks : ");
            System.out.println("4 ) Show the top scorer for each subject : ");
            System.out.println("5 ) Exit program : ");
            System.out.print("Enter your option - ");
            int option = scan.nextInt();
            switch (option) {
                case 1:
                    for (Map.Entry<String, Integer> entry : rankOrder.entrySet()) {
                        System.out.println(entry.getKey() + " ---> " + entry.getValue());
                    }
                    break;
                case 2:
                    for (Map.Entry<String, Integer> entry : subAverage.entrySet()) {
                        System.out.println("Average mark " + entry.getKey() + " " + entry.getValue());
                    }
                    break;
                case 3:
                    for (Map.Entry<String, Map<String, Integer>> entry : aboveAverageMarks.entrySet()) {
                        System.out.println("------  " + entry.getKey() + "  highest mak ------");
                        for (Map.Entry<String, Integer> innerEntry : entry.getValue().entrySet()) {
                            System.out.println(innerEntry.getKey() + " " + innerEntry.getValue());
                        }
                    }
                    break;
                case 4:
                    for (Map.Entry<String, Map<String, Integer>> entry : highestMarkStudents.entrySet()) {
                        System.out.println("----------  " + entry.getKey() + "  ----------");
                        for (Map.Entry<String, Integer> innerEntry : entry.getValue().entrySet()) {
                            System.out.println(innerEntry.getKey() + " " + innerEntry.getValue());
                        }
                    }
                    break;
                case 5:
                    end = false;
                    break;
                default:
                    System.out.println("Please enter the correct option :- ");
                    break;
            }
        } while (end);
    }
}
