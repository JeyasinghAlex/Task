package Task.Db;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        DbApi api = new DbApi();
        boolean isEnd = true;
        do {
            System.out.println();
            System.out.println("1 ) Print each student data : ");
            System.out.println("2 ) Find top 3 collage students : ");
            System.out.println("3 ) Print each department top 3 student :");
            System.out.println("4 ) Find the nth rand student details : ");
            System.out.println("5 ) Print pass and fail student data : ");
            System.out.println("6 ) Print each subject average mark : ");
            System.out.println("7 ) Print each subject highest mark ; ");
            System.out.println("8 ) Department wise pass percentage :");
            System.out.println("9 ) Teacher wise pass percentage : ");
            System.out.println("10 ) Exit : ");
            System.out.print("Enter your option : ");
            int option = scan.nextInt();
            switch (option) {
                case 1:
                    List<Student> students = api.getStudentData();
                    students.forEach(System.out::println);
                    break;
                case 2:
                    api.getTopThreeStudents();
                    break;
                case 3:
                    api.getEachDepartmentTopRanks();
                    break;
                case 4:
                    api.getNthRankStudent(6);
                    break;
                case 5:
                    api.getAllStudentDataRankWise();

                    break;
                case 6:
                    api.getEachSubjectAverage();
                    break;
                case 7:
                    api.getHighestMarkAndStudentNameTeacherName();

                    break;
                case 8:
                    api.getPassPercentageDeptWise();
                    break;
                case 9:
                    api.getTeacherWisePassPercentage();
                    break;
                case 10:
                    isEnd = false;
                    break;
                default:
                    System.out.println("please, enter valid option");
                    break;

            }
        } while (isEnd);

    }
}