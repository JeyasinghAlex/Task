package Task.StudentTask;

import java.sql.*;
import java.util.*;

public class DaoImpl {

    private static DaoImpl daoImpl;
    private Connection connection;

    private DaoImpl() {
        try {
            Properties properties = ConfigUtil.loadProperty();
            String url = properties.getProperty("mysql.CONNECTION_URL");
            String userName = properties.getProperty("mysql.username");
            String password = properties.getProperty("mysql.passwork");
            String driverName = properties.getProperty("mysql.CONNECTION_DRIVER");
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public static DaoImpl getInstance() {
        if (daoImpl == null) {
            daoImpl = new DaoImpl();
        }
        return daoImpl;
    }

    public void insertData(Student student) throws SQLException {
        Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.query.insert_student");
        String markInsertQuery = properties.getProperty("mysql.query.insert_mark");
        String resultInsertQuery = properties.getProperty("mysql.query.insert_result");
        ResultSet rs = null;
        int studentId = 0;
        PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, student.getName());
        int n = pstmt.executeUpdate();
        System.out.println(n + " student row inserted");
        rs = pstmt.getGeneratedKeys();
        if (rs.next())
            studentId = rs.getInt(1);

        pstmt = connection.prepareStatement(markInsertQuery);
        int total = 0;
        String isPass = "pass";
        List<Subject> subjects = student.getSubjects();
        for (int i = 0; i < subjects.size(); ++i) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, subjects.get(i).getSubjectName().toString());
            pstmt.setInt(3, subjects.get(i).getMarks());
            total += subjects.get(i).getMarks();
            if (subjects.get(i).getMarks() < 35) {
                isPass = "fail";
            }
            pstmt.addBatch();
        }
        pstmt.executeBatch();
        pstmt = connection.prepareStatement(resultInsertQuery);
        pstmt.setInt(1, studentId);
        pstmt.setInt(2, total);
        pstmt.setString(3, isPass);
        pstmt.executeUpdate();
    }


    public ResultSet getAll(int id) throws SQLException {
        String query = "SELECT * FROM marks where student_id = ?";
        ResultSet resultSet = null;
        PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, id);
        resultSet = pstmt.executeQuery();
        return resultSet;
    }

    public ResultSet getAverage() throws SQLException {
        Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.query.get_average");
        Map<String, Integer> average = new HashMap<>();
        ResultSet resultSet = null;
        PreparedStatement pstmt = connection.prepareStatement(query);
        resultSet = pstmt.executeQuery();
        return resultSet;
    }

    public ResultSet getResults() throws SQLException {
        Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.query.get_result");
        Map<String, Integer> average = new HashMap<>();
        ResultSet resultSet = null;
        PreparedStatement pstmt = connection.prepareStatement(query);
        resultSet = pstmt.executeQuery();
        return resultSet;
    }

    public Map<String, Map<List<String>, List<Integer>>> getAverageMarks() throws SQLException {
        Properties properties = ConfigUtil.loadProperty();
        String subjectsNameQuery = properties.getProperty("mysql.query.get_subjects_name");
        String averageMarkQuery = properties.getProperty("mysql.query.get_mark_above_average");

        Map<String, Map<List<String>, List<Integer>>> aboveAverageMarks = new HashMap<>();
        List<String> name = new ArrayList<>();
        List<Integer> mark = new ArrayList<>();
        Map<List<String>, List<Integer>> innerMap = new HashMap<>();
        PreparedStatement pstmt = connection.prepareStatement(subjectsNameQuery);
        ResultSet resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            name = new ArrayList<>();
            mark = new ArrayList<>();
            innerMap = new HashMap<>();
            pstmt = connection.prepareStatement(averageMarkQuery);
            pstmt.setString(1, resultSet.getString(1));
            pstmt.setString(2, resultSet.getString(1));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                name.add(rs.getString(3));
                mark.add(rs.getInt(2));
            }
            innerMap.put(name, mark);
            aboveAverageMarks.put(resultSet.getString(1), innerMap);
        }
        return aboveAverageMarks;
    }

    public Map<String, Map<List<String>, List<Integer>>> getEachSubjectHighestMark() throws SQLException {
        Properties properties = ConfigUtil.loadProperty();
        String subjectsNameQuery = properties.getProperty("mysql.query.get_subjects_name");
        String topScoreQuery = properties.getProperty("mysql.query.get_top_mark");

        Map<String, Map<List<String>, List<Integer>>> highMark = new HashMap<>();
        Map<List<String>, List<Integer>> innerMap = new HashMap<>();
        List<String> name = new ArrayList<>();
        List<Integer> mark = new ArrayList<>();

        PreparedStatement pstmt = connection.prepareStatement(subjectsNameQuery);
        ResultSet resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            name = new ArrayList<>();
            mark = new ArrayList<>();
            innerMap = new HashMap<>();
            pstmt = connection.prepareStatement(topScoreQuery);
            pstmt.setString(1, resultSet.getString(1));
            ResultSet rs = pstmt.executeQuery();
            boolean isHigh = true;
            while (rs.next()) {
                if (isHigh || mark.contains(rs.getInt(1))) {
                    mark.add(rs.getInt(1));
                    name.add(rs.getString(3));
                    isHigh = false;
                } else {
                    break;
                }
            }
            innerMap.put(name, mark);
            highMark.put(resultSet.getString(1), innerMap);
        }
        return highMark;
    }


    public Map<String, Map<List<String>, List<Integer>>> getStudentStatus() throws SQLException {
        Properties properties = ConfigUtil.loadProperty();
        String studentStatus = properties.getProperty("mysql.query.get_student_status");
        String subjectsNameQuery = properties.getProperty("mysql.query.get_subjects_name");
        Map<String, Map<List<String>, List<Integer>>> status = new HashMap<>();
        Map<List<String>, List<Integer>> innerMap = new HashMap<>();
        List<String> name = new ArrayList<>();
        List<Integer> mark = new ArrayList<>();
        PreparedStatement pstmt = connection.prepareStatement(subjectsNameQuery);
        ResultSet subjectsName = pstmt.executeQuery();
        while (subjectsName.next()) {
            innerMap = new HashMap<>();
            name = new ArrayList<>();
            mark = new ArrayList<>();
            pstmt = connection.prepareStatement(studentStatus);
            pstmt.setString(1, subjectsName.getString(1));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                name.add(rs.getString(1));
                mark.add(rs.getInt(2));
            }
            innerMap.put(name, mark);
            status.put(subjectsName.getString(1), innerMap);
        }
        return status;
    }

    public Map<String, List<String>> showStudentsSubjectsToGetAboveAverageMark() throws SQLException {
        Properties properties = ConfigUtil.loadProperty();
        String studentNameQuery = properties.getProperty("mysql.query.get_students");
        String averageMarkQuery = properties.getProperty("mysql.query.get_average_mark");
        String studentMarkQuery = properties.getProperty("mysql.query.student_mark");
        PreparedStatement pstmt = connection.prepareStatement(studentNameQuery);
        ResultSet names = pstmt.executeQuery();
        Map<String, List<String>> subjects = new HashMap<>();
        List<String> aboveAverageMarkSubjects = new ArrayList<>();
        while (names.next()) {
            aboveAverageMarkSubjects = new ArrayList<>();
            pstmt = connection.prepareStatement(averageMarkQuery);
            ResultSet average = pstmt.executeQuery();
            pstmt = connection.prepareStatement(studentMarkQuery);
            pstmt.setString(1, names.getString(2));
            ResultSet mark = pstmt.executeQuery();
            while (average.next() && mark.next()) {
                if (mark.getInt(1) > average.getInt(1)) {
                    aboveAverageMarkSubjects.add(mark.getString(2));
                }
            }
            subjects.put(names.getString(2), aboveAverageMarkSubjects);
        }
        return subjects;
    }
}