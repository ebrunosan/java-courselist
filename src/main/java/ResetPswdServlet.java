package main.java;

import main.java.dao.UserDAO;
import main.java.model.User;

import java.net.*;

import com.sendgrid.*;

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

@WebServlet( "/reset" )

@SuppressWarnings("serial")
public class ResetPswdServlet extends HttpServlet {
	private UserDAO userDAO = null;

	public ResetPswdServlet() {
		super();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		ServletContext context = getServletContext();
		context.log( ">>> [ResetPswdServlet | BEGIN] Get" );

		String token 	= req.getParameter( "token" );
		try {
			userDAO 	= new UserDAO();
			User user 	= userDAO.selectRecordByToken( token );

			if ( user == null  )
			{
				req.setAttribute( "message", "Please request a new token for reseting your password!" );
				req.getRequestDispatcher( "/forgotpswd.jsp" ).forward( req, res );
				context.log( "<<< [ResetPswdServlet | END] Token invalid" );
			}
			else
				req.getRequestDispatcher( "/resetpswd.jsp" ).forward(req, res);
				context.log( "<<< [ResetPswdServlet | END] Token valid" );
		} 
		catch ( Exception ex ) 
		{
			context.log( "<<< [ResetPswdServlet | END] Exception!");
			throw new ServletException( ex );
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		ServletContext context = getServletContext();
		context.log( ">>> [ResetPswdServlet | BEGIN] Post" );

		String token 	= req.getParameter( "token" );
		String newPswd 	= req.getParameter( "user-pwd" );
		String confPswd = req.getParameter( "conf-pwd" );
		
		if ( !newPswd.equals( confPswd ) )
		{
			req.setAttribute( "message", "Both password MUST be equal. Try it again, please!" );
			req.getRequestDispatcher( "/resetpswd.jsp" ).forward( req, res );
			context.log( "<<< [ResetPswdServlet | END] Password not equal" );
		}
		else
		{
			try {
				userDAO 	= new UserDAO();
				User user 	= userDAO.selectRecordByToken( token );
				
				if ( user == null )
				{
					req.setAttribute( "message", "Token not found! Please check your email again!" );
					req.getRequestDispatcher( "/resetpswd.jsp" ).forward( req, res );
					context.log( "<<< [ResetPswdServlet | END] Token not found" );
				}
				else
				{
					user.setPass( newPswd );
					userDAO.setNewPassword( user );

					HttpSession session = req.getSession();			// Setting session to store user data
					session.setAttribute( "userName", user.getFirstName() );
					session.setAttribute( "userId", String.valueOf( user.getUserId() ) );
					session.setMaxInactiveInterval( 10*60 );		// setting session to expiry in 10 mins

					res.sendRedirect( "/auth/welcome.jsp" );		// Redirects to authenticated area
					context.log( "<<< [ResetPswdServlet | END] Password changed successfully" );
				}
			} catch ( Exception ex ) {
				context.log( "<<< [ResetPswdServlet | END] Exception!");
				throw new ServletException( ex );
			}
		}
 	}	
}
