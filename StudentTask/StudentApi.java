package Task.StudentTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentApi {

    private static StudentApi api = null;
    private StudentApi() {

    }

    public static StudentApi getInstance() {
        if (api == null) {
            api = new StudentApi();
        }
        return api;
    }

    public void insertStudent(Student student) {
        try {
            DaoImpl.getInstance().insertData(student);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void showTopRank() {
        int rank = 0;
        int count = 0;
        List<Integer> list = new ArrayList<>();
        Map<String, Integer> failStudent = new HashMap<>();
        try {
            ResultSet rs = DaoImpl.getInstance().getResults();
            while (rs.next()) {
                if  (list.contains(rs.getInt(2)) &&  rs.getString(3).equals("pass")) {
                    count++;
                } else if (rs.getString(3).equals("pass")) {
                    rank = ++rank + count;
                    System.out.format("%1c%-20s%1c%4d%1s%1c%4d%1s%1c%1s%4s%1s%1c",'|',rs.getString(1),'|',rs.getInt(2),"",'|',rank,"",'|',"","PASS","",'|');
                    System.out.println();
                    count = 0;
                } else {
                    failStudent.put(rs.getString(1), rs.getInt(2));
                }
               list.add(rs.getInt(2));
            }
            for (Map.Entry<String, Integer> entry : failStudent.entrySet()) {
                System.out.format("%1c%-20s%1c%4d%1s%1c%4s%1s%1c",'|',entry.getKey(),'|',entry.getValue(),"",'|',"FAIL","",'|');
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void showEachSubjectAverageMarks() {
        System.out.println("------------------------");
        System.out.println("Subject Average Marks :- ");
        System.out.println("------------------------");
        try {
            ResultSet rs = DaoImpl.getInstance().getAverage();
           while (rs.next()) {
               System.out.format("%1c%-15s%1c%4d%2s%1c", '|', rs.getString(2), '|', rs.getInt(1),"" ,'|');
               System.out.println();
           }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("------------------------");
    }

    public void showStudentsAboveAverageMarks() {
        try {
            Map<String, Map<List<String>, List<Integer>>> averageMarks = DaoImpl.getInstance().getAverageMarks();
            for (Map.Entry<String, Map<List<String>, List<Integer>>> entry : averageMarks.entrySet()) {
                System.out.println("------  " + entry.getKey() + "  -------");
                for (Map.Entry<List<String>, List<Integer>> innerEntry : entry.getValue().entrySet()) {
                    for (int i = 0; i < innerEntry.getValue().size(); ++i) {
                        System.out.format("%1c%-20s%1c%4d%2s%1c", '|', innerEntry.getKey().get(i), '|', innerEntry.getValue().get(i), "", '|');
                        System.out.println();
                    }
                }
                System.out.println("-----------------------------");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void showTopScoreEachSubject() {
        try {
            Map<String, Map<List<String>, List<Integer>>> highMark = DaoImpl.getInstance().getEachSubjectHighestMark();
            for (Map.Entry<String, Map<List<String>, List<Integer>>> entry : highMark.entrySet()) {
                System.out.println(entry.getKey() + ": ");
                System.out.println("-----------------------------");
                for (Map.Entry<List<String>, List<Integer>> innerEntry : entry.getValue().entrySet()) {
                    for (int i = 0; i < innerEntry.getValue().size(); ++i) {
                        System.out.format("%1c%-20s%1c%4d%2s%1c",'|',innerEntry.getKey().get(i),'|', innerEntry.getValue().get(i), "", '|');
                        System.out.println();
                    }
                }
                System.out.println("-----------------------------");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void showStudentDetails() {
        try {
            Map<String, Map<List<String>, List<Integer>>> highMark = DaoImpl.getInstance().getStudentStatus();
            for (Map.Entry<String, Map<List<String>, List<Integer>>> entry : highMark.entrySet()) {
                System.out.println("----------------------------------");
                System.out.println( entry.getKey() +  ":");
                System.out.println("----------------------------------");
                System.out.format("%1c%-10s%1c%-10s%1c%-10s%1c",'|',"Name", '|', "Pass/fail", '|',"Marks",'|');
                System.out.println();
                for (Map.Entry<List<String>, List<Integer>> innerEntry : entry.getValue().entrySet()) {
                    System.out.println("----------------------------------");
                    for (int i = 0; i < innerEntry.getValue().size(); ++i) {
                        String status = innerEntry.getValue().get(i) >= 35 ? "PASS" : "FAIL";
                        System.out.format("%1c%-10s%1c%-10s%1c%-10d%1c",'|',innerEntry.getKey().get(i), '|', status, '|',innerEntry.getValue().get(i),'|');
                        System.out.println();
                    }
                    System.out.println("----------------------------------");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void showStudentsSubjectsToGetAboveAverageMark() {

        try {
            Map<String, List<String>> subjects = DaoImpl.getInstance().showStudentsSubjectsToGetAboveAverageMark();
            System.out.println("-------------------------------------------------------------------------------------");
            for (Map.Entry<String, List<String>> entry : subjects.entrySet()) {
                System.out.format("%1c%-20s%1c%-55s%1c%4d%2s%1c", '|',entry.getKey(),'|', entry.getValue(),'|', entry.getValue().size(), "",'|');
                System.out.println();
            }
            System.out.println("-------------------------------------------------------------------------------------");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
