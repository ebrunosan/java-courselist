package jdbc;

import java.sql.*;
import java.net.*;

public class DBUtil {
	public static Connection getConnection() throws URISyntaxException, SQLException {
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		return DriverManager.getConnection(dbUrl);
	}
}
