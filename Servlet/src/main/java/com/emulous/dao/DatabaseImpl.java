package com.emulous.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.emulous.model.Student;
import com.emulous.model.Subject;
import com.emulous.utils.ConfigUtil;

public class DatabaseImpl {

	//get   value ...    post -- create put--update   
	private static DatabaseImpl daoImpl;
    private Connection connection;

    DatabaseImpl() {
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

    public static DatabaseImpl getInstance() {
        if (daoImpl == null) {
            daoImpl = new DatabaseImpl();
        }
        return daoImpl;
    }
    
    public boolean isUserAvailable(String name, String password) {
    	
    	Properties properties = ConfigUtil.loadProperty();
    	String query = properties.getProperty("mysql.query.is_user");
    	try {
    		PreparedStatement pstmt = connection.prepareStatement(query);
    		pstmt.setString(1,  name);
    		pstmt.setString(2, password);
    		ResultSet rs = pstmt.executeQuery();
    		if (rs.next()) {    			
    			return true;
    		}
    	} catch(SQLException ex) {
    		ex.printStackTrace();
    		//add log file
    	}
    	return false;
    }
    
    public boolean isAdminAvailable(String name, String password) {
    	Properties properties = ConfigUtil.loadProperty();
    	String query = properties.getProperty("mysql.query.is_admin");
    	try {
    		PreparedStatement pstmt = connection.prepareStatement(query);
    		pstmt.setString(1,  name);
    		pstmt.setString(2, password);
    		ResultSet rs = pstmt.executeQuery();
    		if (rs.next()) {
    			return true;
    		}
    	} catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    	return false;
    }
	
    
    public List <Student> getStudentData(List<Integer> id) throws SQLException {
    	System.out.println(id);
    	List <Student> students = new ArrayList<>();
    	Properties properties = ConfigUtil.loadProperty();
    	String  query = properties.getProperty("mysql.query.get_student");
    	
    	for (int i  = 0; i < id.size(); ++i) {
    		Student student = new Student();
    		PreparedStatement pstmt = connection.prepareStatement(query);
    		pstmt.setInt(1,  id.get(i));
    		ResultSet rs = pstmt.executeQuery();
    		
    		while (rs.next()) {
    			Subject subject = new Subject();
    			student.setId(rs.getInt(1));
        		student.setName(rs.getString(2));
        		student.setDepartment(rs.getString(3));
    			subject.setName(rs.getString(4));
    			subject.setStaff(rs.getString(5));
    			subject.setMark(rs.getInt(6));
    			student.setSubjects(subject);
    		}
    		students.add(student);
    	}
    	return students;
    }
    
    public ResultSet getRow(int id) throws SQLException {
    	Properties properties = ConfigUtil.loadProperty();
    	String  query = properties.getProperty("mysql.query.get_student");
    	PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1,  id);
		ResultSet rs = pstmt.executeQuery();
		return rs;
    }
    
    public Map<String, Map<List<String>, List<Integer>>> getDataForAverage() throws SQLException {
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
        
    public ResultSet getSubjects() throws SQLException {
        Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.query.get_subjects_name");
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }
    
    public ResultSet getMarks() throws SQLException {
        Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.query.get_marks");
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();    
        return rs;
    }
    
    public ResultSet getData() throws SQLException {
        Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.query.get_result");
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
    	return rs;
    }
    
    public ResultSet getStudentMark() throws SQLException {
        Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.quety.get_staffs_and_marks");
	    PreparedStatement pstmt = connection.prepareStatement(query);
	    ResultSet rs = pstmt.executeQuery();
        return rs;
    }
    
    public void deleteRow(int id) throws SQLException {
    	Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.query.delete");
	    PreparedStatement pstmt = connection.prepareStatement(query);
	    pstmt.setInt(1, id);
	    pstmt.executeQuery();
    }
    
    public void updateRow(int studentId, int subjectId, int mark) throws SQLException {
    	Properties properties = ConfigUtil.loadProperty();
        String query = properties.getProperty("mysql.query.update");
	    PreparedStatement pstmt = connection.prepareStatement(query);
	    pstmt.setInt(1, mark);
	    pstmt.setInt(2, subjectId);
	    pstmt.setInt(3, studentId);
	    pstmt.execute();
    }
}
