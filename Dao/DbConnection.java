package Task.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnection {

    private static DbConnection dbConnection;
    private Connection connection;

    private DbConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/task?allowPublicKeyRetrieval=true&useSSL=false";
            String userName = "user";
            String password = "Jsingh22.";
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public static DbConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }

    public void insert(String name, int age) {
        String query = "INSERT INTO students(name) VALUES (?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, name);
            int a = pstmt.executeUpdate();
            System.out.println(a + " row inserted");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
