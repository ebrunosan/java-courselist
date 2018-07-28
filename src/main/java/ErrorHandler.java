package main.java;

import main.java.dao.UserDAO;
import main.java.model.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error")
@SuppressWarnings("serial")
public class ErrorHandler extends HttpServlet {
	private UserDAO userDAO;

	public void doGet( HttpServletRequest req, HttpServletResponse res ) 
		throws IOException, ServletException 
	{
		userDAO = new UserDAO();
		User newUser = new User();
		userDAO.insertRecord( newUser );
		//throw new ServletException( "Get method error testing" );
	}
}