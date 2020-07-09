package Task.StudentTask;

import java.util.*;

public class Main {

    public static void mainn(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.format("%-35s%-1c%-1c", "Enter the number of students ", ':',' ');
        int numberOfStudent = getIntegerInput(scan);
        Student[] students = new Student[numberOfStudent];
        List<Subject> subjects = null;

        for (int i = 0; i < numberOfStudent; ++i) {
            subjects = new ArrayList<>();
            System.out.format("%-35s%-1c%-1c", "Enter the student " + (i + 1) +  " name", ':',' ');
            scan.nextLine();
            String name = scan.nextLine();
            Student student = new Student(name);
            System.out.println("For student " + (i+1));

            System.out.format("%5s%-30s%-1c%-1c", "","Enter the Tamil mark ", ':',' ');
            int tamil = getIntegerInput(scan);
            subjects.add(new Subject(SubjectName.TAMIL, tamil));

            System.out.format("%5s%-30s%-1c%-1c", "","Enter the English mark ", ':',' ');
            int english = getIntegerInput(scan);
            subjects.add(new Subject(SubjectName.ENGLISH, english));

            System.out.format("%5s%-30s%-1c%-1c", "","Enter the Maths mark ", ':',' ');
            int maths = getIntegerInput(scan);
            subjects.add(new Subject(SubjectName.MATHS, maths));

            System.out.format("%5s%-30s%-1c%-1c", "","Enter the Science mark ", ':',' ');
            int science = getIntegerInput(scan);
            subjects.add(new Subject(SubjectName.SCIENCE, science));

            System.out.format("%5s%-30s%-1c%-1c", "","Enter the Social Science mark ", ':',' ');
            int socialScience = getIntegerInput(scan);
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
            int option = getIntegerInput(scan);;
            System.out.println("Your enter option is - " + option);
            switch (option) {
                case 1:
                    showTopRank(students);
                    break;
                case 2:
                    showEachSubjectAverageMarks(students);
                    break;
                case 3:
                    showStudentsAboveAverageMarks(students);
                    break;
                case 4:
                    showTopScoreEachSubject(students);
                    break;
                case 5:
                    getStudentDetails(students);
                    break;
                case 6:
                    showStudentsToGetAboveAverageMark(students);
                    break;
                case 7:
                    end = false;
                default:
                    System.out.println("Please enter the correct option :- ");
                    break;
            }
        } while (end);
    }

    private static void showStudentsToGetAboveAverageMark(Student[] students) {
        Map<String, Integer> avrg = StudentUtil.getAverage(students);
        Map<String, List<String>> numberOfAboveAverageMark = StudentUtil.getNumberOfSubjectAboveAverageMark(students, avrg);
        System.out.println("-------------------------------------------------------------------------------------");
        for (Map.Entry<String, List<String>> entry : numberOfAboveAverageMark.entrySet()) {
            System.out.format("%1c%-20s%1c%-55s%1c%4d%2s%1c", '|',entry.getKey(),'|', entry.getValue(),'|', entry.getValue().size(), "",'|');
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    private static void showTopScoreEachSubject(Student[] students) {
        Map<String, Map<String, Integer>> highestMarkStudents = StudentUtil.getHighestMarkStudentName(students);
        for (Map.Entry<String, Map<String, Integer>> entry : highestMarkStudents.entrySet()) {
            System.out.println(entry.getKey() + ": ");
            System.out.println("-----------------------------");
            for (Map.Entry<String, Integer> innerEntry : entry.getValue().entrySet()) {
                System.out.format("%1c%-20s%1c%4d%2s%1c",'|',innerEntry.getKey(),'|', innerEntry.getValue(), "", '|');
                System.out.println();
            }
            System.out.println("-----------------------------");
        }
    }

    private static void showStudentsAboveAverageMarks(Student[] students) {
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
            if(!stdName.isEmpty()) {
                System.out.println("-----------------------------");
                for (int i = 0; i < stdMark.size(); ++i) {
                    System.out.format("%1c%-20s%1c%4d%2s%1c", '|', stdName.get(i), '|', stdMark.get(i), "", '|');
                    System.out.println();
                }
                System.out.println("-----------------------------");
            }else {
                System.out.println("No students got above average marks in this subject");
            }
        }
    }

    private static void showEachSubjectAverageMarks(Student[] students) {
        List<String> subName = new ArrayList<>();
        List<Integer> averageMark = new ArrayList<>();
        Map<String, Integer> subAverage = StudentUtil.getAverage(students);
        for (Map.Entry<String, Integer> entry : subAverage.entrySet()) {
            subName.add(entry.getKey());
            averageMark.add(entry.getValue());
        }
        StudentUtil.sort(averageMark, subName);
        System.out.println("Average marks for each subject: ");
        System.out.println("------------------------");
        for (int i = 0; i < averageMark.size(); ++i) {
            System.out.format("%1c%-15s%1c%4d%2s%1c", '|', subName.get(i), '|', averageMark.get(i),"" ,'|');
            System.out.println();
        }
        System.out.println("------------------------");
    }

    private static void showTopRank(Student[] students) {
        List<Integer> rank = new ArrayList<>();
        List<String> studentName = new ArrayList<>();
        Map<String, Integer> rankOrder = StudentUtil.getRankBasedOnTotal(students);
        List<String> failedStudent = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : rankOrder.entrySet()) {
            studentName.add(entry.getKey());
            rank.add(entry.getValue());
        }
        StudentUtil.sort(rank, studentName);
        System.out.println("Passed students: ");
        System.out.println("----------------------------------------");
        for (int i = rank.size() - 1; i >= 0; --i) {
            if (rank.get(i) != 0) {
                int total = StudentUtil.getStudentTotalMark(studentName.get(i), students);
                System.out.format("%1c%-20s%1c%4d%1s%1c%4d%1s%1c%1s%4s%1s%1c",'|',studentName.get(i),'|',total,"",'|',rank.get(i),"",'|',"","PASS","",'|');
                System.out.println();
            } else {
                failedStudent.add(studentName.get(i));
            }
        }
        System.out.println("----------------------------------------");
        if(failedStudent.size() > 0) {
            System.out.println("Failed students: ");
            System.out.println("-----------------------------------");
        for (int i = 0; i < failedStudent.size(); ++i) {
            int total = StudentUtil.getStudentTotalMark(failedStudent.get(i), students);
            System.out.format("%1c%-20s%1c%4d%1s%1c%4s%1s%1c",'|',failedStudent.get(i),'|',total,"",'|',"FAIL","",'|');
            System.out.println();
        }
            System.out.println("-----------------------------------");
        }
    }

    private static void getStudentDetails(Student[] students) {
        for (int i = 0; i < students[0].getSubjects().size(); ++i) {
            System.out.println("----------------------------------");
            System.out.println(students[0].getSubjects().get(i).getSubjectName() + ":");
            System.out.println("----------------------------------");
            System.out.format("%1c%-10s%1c%-10s%1c%-10s%1c",'|',"Name", '|', "Pass/fail", '|',"Marks",'|');
            System.out.println();
            System.out.println("----------------------------------");
            for (int j = 0; j < students.length; ++j) {
                String name = students[j].getName();
                int mark = students[j].getSubjects().get(i).getMarks();
                String status = mark >= 35 ? "PASS" : "FAIL";
                System.out.format("%1c%-10s%1c%-10s%1c%-10d%1c",'|',name, '|', status, '|',mark,'|');
                System.out.println();
            }
            System.out.println("----------------------------------");
        }
    }

    private static int getIntegerInput(Scanner scan) {
        int input = 0;
        while (true) {
            try {
                input = scan.nextInt();
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid Input, shoot again:");
                scan.nextLine();
            }
        }
        return input;
    }

    private static String getStringInput(Scanner  scan) {
        return scan.nextLine();
    }
}
