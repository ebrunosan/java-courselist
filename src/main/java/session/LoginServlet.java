package main.java.session;

import main.java.dao.UserDAO;
import main.java.model.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet( "/auth/login" )

@SuppressWarnings( "serial" )
public class LoginServlet extends HttpServlet 
{
	private UserDAO userDAO;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		req.getRequestDispatcher( "/auth/login.jsp" ).forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		ServletContext context = getServletContext();
		context.log( ">>> [LoginServlet | BEGIN]" );

		String userEmail 	= req.getParameter( "user-email" );		// Getting Form parameters
		String pwd 			= req.getParameter( "user-pwd" );

		try {
			userDAO 			= new UserDAO();
			User user 			= userDAO.selectRecordByEmail( userEmail );

			if ( user == null || user.getPass() == null || !pwd.equals( user.getPass() ))
			{
				req.setAttribute( "message", "Either email or password is wrong. Submit your regitration form and check your mail box." );

				req.getRequestDispatcher( "/auth/login.jsp" ).forward( req, res );
				context.log( "<<< [LoginServlet | END] Email or password incorret!" );
			}
			else
			{
				HttpSession session = req.getSession();			// Setting session to store user data
				session.setAttribute( "userName", user.getFirstName() );
				session.setAttribute( "userId", String.valueOf( user.getUserId() ) );
				session.setMaxInactiveInterval( 10*60 );		// setting session to expiry in 10 mins

				req.getRequestDispatcher( "/auth/welcome.jsp" ).forward(req, res);
				context.log( "<<< [LoginServlet | END] Successfully!" );
			}
        } catch ( Exception ex ) {
			context.log( "<<< [LoginServlet | END] Exception!");
            throw new ServletException(ex);
        }
	}
}