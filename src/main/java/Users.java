package jdbc;

import java.sql.*;
import java.net.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "Users",
        urlPatterns = "/users"
)

@SuppressWarnings("serial")
public class Users extends HttpServlet {
	public static String sql = "SELECT now()";
	
	public Users() {
		super();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;

		PrintWriter out = res.getWriter();
			
		try {
			res.setContentType("text/html");
			out.print("<html>");
			
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
		
			ResultSet result = stmt.executeQuery("SELECT now();");
			
			if(result.next()) {
			   out.write("<p>" + result.getString(1) + "</p>");
			}
		} catch (Exception e) {
			out.print("Error " + e);
		} finally {
			try {
				if ( stmt != null ) { stmt.close(); }
				if ( conn != null ) { conn.close(); }
			} catch (SQLException e) {
				out.print("Error - Finally " + e);
			}
		}
 	}
}
