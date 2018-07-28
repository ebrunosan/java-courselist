package main.java;

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

	public void doGet( HttpServletRequest req, HttpServletResponse res ) 
		throws IOException, ServletException 
	{
		req.getRequestDispatcher( "/error.jsp" ).forward( req, res );
	}
}