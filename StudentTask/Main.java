package Task.StudentTask;

import java.util.*;

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

        do {
            System.out.println("\n*------------------------------------------------*");
            System.out.println("1 ) Find top rank : ");
            System.out.println("2 ) Show each subject's average marks  : ");
            System.out.println("3 ) Show students with above-average marks : ");
            System.out.println("4 ) Show the top scorer for each subject : ");
            System.out.println("5 ) Show student details : ");
            System.out.println("6 ) Show number of subject is higher then average mark with subject name: ");
            System.out.println("7 ) Exit program : ");
            System.out.print("Enter your option - ");
            int option = scan.nextInt();
            switch (option) {
                case 1:
                    List<Integer> rank = new ArrayList<>();
                    List<String> studentName = new ArrayList<>();
                    Map<String, Integer> rankOrder = StudentUtil.getRankBasedOnTotal(students);
                    List<String> failedStudent = new ArrayList<>();
                    for (Map.Entry<String, Integer> entry : rankOrder.entrySet()) {
                            studentName.add(entry.getKey());
                            rank.add(entry.getValue());
                        }
                        StudentUtil.sort(rank, studentName);
                        for (int i = rank.size() - 1; i >= 0; --i) {
                            if (rank.get(i) != 0) {
                                int total = StudentUtil.getStudentTotalMark(studentName.get(i), students);
                                System.out.println(studentName.get(i) + " -> " + total + " " + rank.get(i) + " -> PASS");
                            } else {
                                failedStudent.add(studentName.get(i));
                            }
                        }
                        for (int i = 0; i < failedStudent.size(); ++i) {
                            int total = StudentUtil.getStudentTotalMark(studentName.get(i), students);
                            System.out.println(studentName.get(i) + " -> " + total + " " + rank.get(i) + " -> FAIL");
                        }
                    break;
                case 2:
                    List<String> subName = new ArrayList<>();
                    List<Integer> averageMark = new ArrayList<>();
                    Map<String, Integer> subAverage = StudentUtil.getAverage(students);
                    for (Map.Entry<String, Integer> entry : subAverage.entrySet()) {
                        subName.add(entry.getKey());
                        averageMark.add(entry.getValue());
                    }
                    StudentUtil.sort(averageMark, subName);
                    for (int i = 0; i < averageMark.size(); ++i) {
                        System.out.println("Average mark of  -> " + subName.get(i) + " - " + averageMark.get(i));
                    }
                    break;
                case 3:
                    List<Integer> stdMark = new ArrayList<>();
                    List<String> stdName = new ArrayList<>();
                    Map<String, Integer> average = StudentUtil.getAverage(students);
                    Map<String, Map<String, Integer>> aboveAverageMarks = StudentUtil.getAboveAverageMarkStudents(students, average);
                    for (Map.Entry<String, Map<String, Integer>> entry : aboveAverageMarks.entrySet()) {
                        stdMark = new ArrayList<>();
                        stdName = new ArrayList<>();
                        System.out.println("------  " + entry.getKey() + "  highest mark ------");
                        for (Map.Entry<String, Integer> innerEntry : entry.getValue().entrySet()) {
                            stdName.add(innerEntry.getKey());
                            stdMark.add(innerEntry.getValue());
                        }
                        StudentUtil.sort(stdMark, stdName);
                        for (int i = 0; i < stdMark.size(); ++i) {
                            System.out.println(stdName.get(i) + " - " + stdMark.get(i));
                        }
                    }
                    break;
                case 4:
                    Map<String, Map<String, Integer>> highestMarkStudents = StudentUtil.getHighestMarkStudentName(students);
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
                    Map<String, Integer> avrg = StudentUtil.getAverage(students);
                    Map<String, List<String>> numberOfAboveAverageMark = StudentUtil.getNumberOfSubjectAboveAverageMark(students, avrg);
                    System.out.println("--------------------------------------------------");
                    for (Map.Entry<String, List<String>> entry : numberOfAboveAverageMark.entrySet()) {
                        System.out.println(entry.getKey() + " " + entry.getValue() + " - " + entry.getValue().size());
                    }
                    System.out.println("--------------------------------------------------");
                    break;
                case 7:
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
}
