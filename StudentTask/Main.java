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
        showStudentsStatus(students, scan);
    }

    private static void showStudentsStatus(Student[] students, Scanner scan) {
        boolean end = true;
        int maxRank = students.length;
        Map<String, Integer> rankOrder = StudentUtil.getRankBasedOnTotal(students);
        Map<String, Integer> subAverage = StudentUtil.getAverage(students);
        Map<String, Map<String, Integer>> aboveAverageMarks = StudentUtil.getAboveAverageMarkStudents(students, subAverage);
        Map<String, Map<String, Integer>> highestMarkStudents = StudentUtil.getHighestMarkStudentName(students);
        List<Integer> averageMark = new ArrayList<>();
        List<String> subName = new ArrayList<>();
        List<Integer> rank = new ArrayList<>();
        List<String> studentName = new ArrayList<>();
        List<Integer> mark = new ArrayList<>();
//        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("\n*------------------------------------------------*");
            System.out.println("1 ) Find top rank : ");
            System.out.println("2 ) Show each subject's average marks  : ");
            System.out.println("3 ) Show students with above-average marks : ");
            System.out.println("4 ) Show the top scorer for each subject : ");
            System.out.println("5 ) Show student details : ");
            System.out.println("6 ) Exit program : ");
            System.out.print("Enter your option - ");
            int option = scan.nextInt();
            switch (option) {
                case 1:
//                    for (int i = 1; i <= maxRank; ++i) {
                        for (Map.Entry<String, Integer> entry : rankOrder.entrySet()) {
//                            if (entry.getValue() == i) {
//                                System.out.println(entry.getKey() + " ---> " + entry.getValue());
//                            }
                            studentName.add(entry.getKey());
                            rank.add(entry.getValue());
                        }
//                    }
//                    for (Map.Entry<String, Integer> entry : rankOrder.entrySet()) {
//                        if (entry.getValue() == 0) {
//                            System.out.println(entry.getKey() + " ---> " + entry.getValue());
//                        }
//                    }
                    break;
                case 2:
                    for (Map.Entry<String, Integer> entry : subAverage.entrySet()) {
                        subName.add(entry.getKey());
                        averageMark.add(entry.getValue());
                    }
                    StudentUtil.sort(averageMark, subName);
                    for (int i = 0; i < averageMark.size(); ++i) {
                        System.out.println("Average mark of  -> " + subName.get(i) + " - " + averageMark.get(i));
                    }
//                    for (int i = 0; i < averageMark.size(); ++i) {
//                        for (Map.Entry<String, Integer> entry : subAverage.entrySet()) {
//                            if (averageMark.get(i).equals(entry.getValue())) {
//                                System.out.println("Average mark " + entry.getKey() + " " + entry.getValue());
//                            }
//                        }
//                    }
                    break;
                case 3:
                    for (Map.Entry<String, Map<String, Integer>> entry : aboveAverageMarks.entrySet()) {
                        mark = new ArrayList<>();
                        System.out.println("------  " + entry.getKey() + "  highest mark ------");
                        for (Map.Entry<String, Integer> innerEntry : entry.getValue().entrySet()) {
                            mark.add(innerEntry.getValue());
                        }
                        sort(mark);
                        for (int i = 0; i < mark.size(); ++i) {
                            for (Map.Entry<String, Integer> innerEntry : entry.getValue().entrySet()) {
                                if (mark.get(i).equals(innerEntry.getValue())) {
                                    System.out.println(innerEntry.getKey() + " " + innerEntry.getValue());
                                }
                            }
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
                    getStudentDetails(students);
                    break;
                case 6:
                    end = false;
                default:
                    System.out.println("Please enter the correct option :- ");
                    break;
            }
        } while (end);
    }

    private static void getStudentDetails(Student[] students) {
        for (int i = 0; i < students[0].getSubjects().size(); ++i) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("| " + students[0].getSubjects().get(i).getSubjectName() + " |");
            for (int j = 0; j < students.length; ++j) {
                String name = students[j].getName();
                String status = students[j].getSubjects().get(i).getMarks() >= 35 ? "PASS" : "FAIL";
                    System.out.println("|          | " + name + "          |" + status + "          |");
            }
        }
    }

    private static void sort(List<Integer> averageMark) {
        for  (int i = 0; i < averageMark.size(); ++i) {
            for (int j = i + 1; j < averageMark.size(); ++j) {
                if (averageMark.get(i) < averageMark.get(j)) {
                    int temp = averageMark.get(i);
                    averageMark.set(i, averageMark.get(j));
                    averageMark.set(j, temp);
                }
            }
        }
    }
}
