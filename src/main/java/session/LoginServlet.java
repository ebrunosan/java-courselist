package main.java.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet( "/auth/login" )

@SuppressWarnings( "serial" )
public class LoginServlet extends HttpServlet 
{
	// TODO SELECT userName WHERE userID=? and password=? FROM DB
	private final String user = "admin@admin.com";
	private final String password = "admin";
	private final String userName = "Administrator Name";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		req.getRequestDispatcher( "/auth/login.jsp" ).forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		ServletContext context = getServletContext();
		context.log( ">>> [LoginServlet | BEGIN]" );

		String userEmail = req.getParameter( "user-email" );		// Getting Form parameters
		String pwd = req.getParameter( "user-pwd" );
		
		// TODO if user NOT_FOUND at query
		if ( user.equals( userEmail ) && password.equals( pwd ) )
		{
			int userId = 101;						// TODO
			
			HttpSession session = req.getSession();			// Setting session to store user data
			session.setAttribute( "userName", userName );
			session.setAttribute( "userId", String.valueOf(userId) );
			session.setMaxInactiveInterval( 10*60 );		// setting session to expiry in 10 mins

	        req.getRequestDispatcher( "/auth/welcome.jsp" ).forward(req, res);
			context.log( "<<< [LoginServlet | END] Success" );
		} 
		else
		{
			req.setAttribute( "message", "Either user name or password is wrong." );

			req.getRequestDispatcher( "/auth/login.jsp" ).forward( req, res );
			context.log( "<<< [LoginServlet | END] Login or password error" );
		}
	}
}