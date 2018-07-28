package main.java;

import main.java.dao.UserDAO;
import main.java.model.User;

import java.io.IOException;

import java.sql.SQLException;

import com.sendgrid.*;

import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/newuser" )

@SuppressWarnings( "serial" )
public class NewUserServlet extends HttpServlet {
	private UserDAO userDAO;
	
	@Override
	public void doPost( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		doGet(req, res);
	}
	
	@Override
	public void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		ServletContext context = getServletContext();
		context.log( ">>> [NewUserServlet | BEGIN]" );

		User newUser = getValidUserFromRequest( req );
		if ( newUser == null )
		{
			req.getRequestDispatcher( "/newuser.jsp" ).forward(req, res);
			context.log( "<<< [NewUserServlet | END] Request form has invalid data" );
		}
		else
		{
			try {
				String msg = null;
				userDAO = new UserDAO();
				
				if ( !userDAO.insertRecord( newUser ) )
				{
					msg = "Fail to create new user! Perhaps your email already exists in our database.";
				}
				else
				{
					String appUrl 		 = req.getScheme() + "://" + req.getServerName();
					String resetTokenUrl = appUrl + "/reset?token=" + newUser.getToken();
					
					if ( sendResetEmail( newUser.getEmail(), resetTokenUrl ) )
					{
						msg = "Thanks for your registration. Check your email box to complete your authentication.";
					}
					else
					{
						msg = "Fail sending the email to complete your registration! Please try again later.";
					}
				}

				req.setAttribute( "message", msg );
				req.getRequestDispatcher( "/newuser.jsp" ).forward( req, res );
			} 
			catch ( Exception ex ) 
			{
				context.log( "<<< [NewUserServlet | END] Exception!");
				throw new ServletException( ex );
			}
			context.log( "<<< [NewUserServlet | END] User successfully created" );
		}
    }
 
    private User getValidUserFromRequest( HttpServletRequest req )
	{
        String msg = "";
		
		String email 		= req.getParameter( "userEmail" );
        String firstName 	= req.getParameter( "firstName" );
        String lastName 	= req.getParameter( "lastName" );
		
		if ( email == null || email.isEmpty() ) { msg += "Email MUST be informed!<br />"; }
		if ( firstName == null || firstName.isEmpty() ) { msg += "First name MUST be informed!<br />"; }
		if ( lastName == null || lastName.isEmpty() ) { msg += "Last name MUST be informed!<br />"; }
		
		if ( msg.isEmpty() )
		{
			String token 	 	= UUID.randomUUID().toString();
			return new User( email, firstName, lastName, null, token );
		}
		else
		{
			req.setAttribute( "message", msg );
			return null;
		}
    }
 
    private boolean sendResetEmail( String emailToAddr, String resetTokenUrl ) 
	{
		try 
		{
			SendGrid sg = new SendGrid( System.getenv("SENDGRID_API_KEY") );
			
			Request request = new Request();
			request.setMethod( Method.POST );
			request.setEndpoint( "mail/send" );

			Email from 		= new Email( "do-not-reply@java-prj-team-summer2018.ca" );
			Email to 		= new Email( emailToAddr );
			String subject 	= "Welcome to our website";
			Content content = new Content( "text/plain", 
				"To set your password, click the link below:<br/><a href='"
				+ resetTokenUrl + "'>Here</a><br/>OR copy/paste this link:" + resetTokenUrl );

			Mail mail = new Mail( from, subject, to, content );
			
			request.setBody( mail.build() );
			
			Response response = sg.api( request );
			
			System.out.println("[::EMAIL::] Sent to [" + emailToAddr + "]. Status code [" + response.getStatusCode() + "]");
		} 
		catch ( IOException ex ) 
		{
			System.err.println( ex.getMessage() );
			return false;
		}
		
		return true;
    }
}