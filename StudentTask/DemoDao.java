package Task.StudentTask;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DemoDao {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.format("%-35s%-1c%-1c", "Enter the number of students ", ':', ' ');
        int numberOfStudent = getIntegerInput();
        List<Subject> subjects = null;

        for (int i = 0; i < numberOfStudent; ++i) {
            subjects = new ArrayList<>();
            System.out.format("%-35s%-1c%-1c", "Enter the student " + (i + 1) + " name", ':', ' ');
            String name = scan.nextLine();
            Student student = new Student(name);
            System.out.println("For student " + (i + 1));

            System.out.format("%5s%-30s%-1c%-1c", "", "Enter the Tamil mark ", ':', ' ');
            int tamil = getIntegerInput();
            subjects.add(new Subject(SubjectName.TAMIL, tamil));

            System.out.format("%5s%-30s%-1c%-1c", "", "Enter the English mark ", ':', ' ');
            int english = getIntegerInput();
            subjects.add(new Subject(SubjectName.ENGLISH, english));

            System.out.format("%5s%-30s%-1c%-1c", "", "Enter the Maths mark ", ':', ' ');
            int maths = getIntegerInput();
            subjects.add(new Subject(SubjectName.MATHS, maths));

            System.out.format("%5s%-30s%-1c%-1c", "", "Enter the Science mark ", ':', ' ');
            int science = getIntegerInput();
            subjects.add(new Subject(SubjectName.SCIENCE, science));

            System.out.format("%5s%-30s%-1c%-1c", "", "Enter the Social Science mark ", ':', ' ');
            int socialScience = getIntegerInput();
            subjects.add(new Subject(SubjectName.SOCIAL_SCIENCE, socialScience));

            student.setSubjects(subjects);
            StudentApi.getInstance().insertStudent(student);
        }
        showStudentsStatus();
    }

    private static void showStudentsStatus() {
        Scanner scan = new Scanner(System.in);
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
            int option = getIntegerInput();
            System.out.println("Your enter option is - " + option);
            switch (option) {
                case 1:
                    StudentApi.getInstance().showTopRank();
                    break;
                case 2:
                    StudentApi.getInstance().showEachSubjectAverageMarks();
                    break;
                case 3:
                    StudentApi.getInstance().showStudentsAboveAverageMarks();
                    break;
                case 4:
                    StudentApi.getInstance().showTopScoreEachSubject();
                    break;
                case 5:
                    StudentApi.getInstance().showStudentDetails();
                    break;
                case 6:
                    StudentApi.getInstance().showStudentsSubjectsToGetAboveAverageMark();
                    break;
                case 7:
                    end = false;
                default:
                    System.out.println("Please enter the correct option :- ");
                    break;
            }
        } while (end);
    }

    private static int getIntegerInput() {
        Scanner scan = new Scanner(System.in);
        int input = 0;
        while (true) {
            try {
                input = scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input, shoot again:");
                scan.nextLine();
            }
        }
        return input;
    }
}
