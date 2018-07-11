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
	private Connection conn;
	
	public Users() {
		super();
		conn = DBUtil.getConnection();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		String sql = "SELECT now();";
			
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			res.setContentType("text/html");
			out.print("<html>");
			
			//Statement stmt = conn.createStatement();
		
			try (ResultSet result = stmt.executeQuery();) {
				while (result.next()) {
				   out.write("<p>" + result.getString(1) + "</p>");
				}
			}
			//stmt.close();
		} catch (Exception e) {
			out.print("Error " + e);
		}
 	}
}
