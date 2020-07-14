package Task.Db;

import java.sql.*;
import java.util.*;

public class Dao {

    private static Dao daoImpl;
    private Connection connection;

    Dao() {
        try {
            Properties properties = ConfigUtil.loadProperty();
            String url = properties.getProperty("mysql.CONNECTION_URL");
            String userName = properties.getProperty("mysql.username");
            String password = properties.getProperty("mysql.password");
            String driverName = properties.getProperty("mysql.CONNECTION_DRIVER");
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public static Dao getInstance() {
        if (daoImpl == null) {
            daoImpl = new Dao();
        }
        return daoImpl;
    }

    public List<Student> getResult()  {
        Map<Integer, Student> map = new HashMap<>();
        List<Student> students = new ArrayList<>();
        Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.query.get_result");
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (!map.containsKey(rs.getInt(1))) {
                    Student student = new Student();
                    student.setId(rs.getInt(1));
                    student.setName(rs.getString(2));
                    student.setDepartment(rs.getString(3));
                    map.put(rs.getInt(1), student);
                }
                Subject subject = new Subject();
                subject.setName(rs.getString(4));
                subject.setStaff(rs.getString(5));
                subject.setMark(rs.getInt(6));
                map.get(rs.getInt(1)).setSubjects(subject);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (Map.Entry<Integer, Student> entry : map.entrySet()) {
            students.add(entry.getValue());
        }
        return students;
    }

    public List<String> getSubjectsName() {
        List<String> subName = new ArrayList<>();
        Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.query.get_subjects_name");
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                subName.add(rs.getString(1));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();;
        }
        return subName;
    }

    public Map<String, List<Integer>> getSubjectMark() {
        Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.query.get_marks");
        Map<String, List<Integer>> mark = new HashMap<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (!mark.containsKey(rs.getString(1))) {
                    mark.put(rs.getString(1), new ArrayList<>());
                }
                mark.get(rs.getString(1)).add(rs.getInt(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mark;
    }

    public Map<String, List<Integer>> getStudentMark() {
        Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.quety.get_staffs_and_marks");
        Map<String, List<Integer>> mark = new HashMap<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (!mark.containsKey(rs.getString(1))) {
                    mark.put(rs.getString(1), new ArrayList<>());
                }
                mark.get(rs.getString(1)).add(rs.getInt(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mark;
    }
}
