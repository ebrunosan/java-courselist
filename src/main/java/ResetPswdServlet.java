package main.java;

import main.java.dao.UserDAO;
import main.java.model.User;

import java.net.*;

import com.sendgrid.*;

import java.util.UUID;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		doPost( req, res );
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		ServletContext context = getServletContext();
		context.log( ">>> [ResetPswdServlet | BEGIN]" );

		//if ( userDAO == null ) { userDAO = new UserDAO(); }

		String token = req.getParameter( "token" );
		String newPswd = req.getParameter( "user-pwd" );
		String confPswd = req.getParameter( "conf-pwd" );
		if ( !newPswd.equals(confPswd) )
		{
			req.setAttribute( "message", "Both password MUST be equal. Try it again, please!" );
			req.getRequestDispatcher( "/resetpswd.jsp" ).forward( req, res );
			context.log( "<<< [ResetPswdServlet | END] Password not equal" );
		} 
		else 
		{
			//User user = userDAO.selectUserByToken( token );
			User user = new User( "userName", "pass", "firstName", "lastName", "emailTo" );
			
			if ( user == null )
			{
				req.setAttribute( "message", "Token not found! Please check your email again!" );
				req.getRequestDispatcher( "/resetpswd.jsp" ).forward( req, res );
				context.log( "<<< [ResetPswdServlet | END] Tokenn not found" );
			}
			else
			{
				userDAO.setNewPassword(newPswd);
				
				req.setAttribute( "message", "Password changed successfully!" );
				req.getRequestDispatcher( "/auth/login.jsp" ).forward( req, res );
				context.log( "<<< [ResetPswdServlet | END] Password changed successfully" );
			}
		}
 	}	
}
