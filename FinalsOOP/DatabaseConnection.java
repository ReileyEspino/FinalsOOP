import java.sql.*;

public class DatabaseConnection {
    public static Connection getConnection() {
        String url = "jdbc:sqlserver://COLLEGELAB-12:1433;databaseName=Company";
        String username = "yourUsername";  // Replace with your SQL Server username
        String password = "yourPassword";  // Replace with your SQL Server password

        try {
            // Load the driver (optional step)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Connect to the database
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            return null;
        }
    }
}
