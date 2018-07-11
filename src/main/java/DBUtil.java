package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
        if (connection == null) {
            try {
				String dbUrl = System.getenv("JDBC_DATABASE_URL");
				connection = DriverManager.getConnection(dbUrl);
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
        return connection;
	}
}
