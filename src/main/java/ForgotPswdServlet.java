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

@WebServlet( "/forgot" )

@SuppressWarnings("serial")
public class ForgotPswdServlet extends HttpServlet {
	private UserDAO userDAO = null;

	public ForgotPswdServlet() {
		super();
	}
	
	@Override
	public void doPost( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException 
	{
		ServletContext context = getServletContext();
		context.log( ">>> [ForgotPswdServlet | BEGIN]" );

		String emailTo 	= req.getParameter( "user-email" );
		try	{
			userDAO 		= new UserDAO();
			User user 		= userDAO.selectRecordByEmail( emailTo );
			
			if ( user == null )
			{
				req.setAttribute( "message", "Email not found. Please try again!" );
				context.log( "<<< [ForgotPswdServlet | END] Email not found" );
			}
			else
			{
				userDAO.setNewToken( user );
				String appUrl 		 = "https://" + req.getServerName();
				String resetTokenUrl = appUrl + "/reset?token=" + user.getToken();
				
				if ( sendResetEmail( user.getEmail(), resetTokenUrl ) )
				{
					req.setAttribute( "message", "Email sent successfully. Check your mail box!" );
					context.log( "<<< [ForgotPswdServlet | END] Email sent successfully" );
				}
				else
				{
					req.setAttribute( "message", "Application fail when trying to send an email!" );
					context.log( "<<< [ForgotPswdServlet | END] Fail when sending email" );
				}
			}
			req.getRequestDispatcher( "/forgotpswd.jsp" ).forward( req, res );
        } 
		catch ( Exception ex ) 
		{
			context.log( "<<< [ForgotPswdServlet | END] Exception!");
            throw new ServletException( ex );
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
			String subject 	= "Password reset request";
			Content content = new Content( "text/plain", "To reset your password, click the link below:");

			Mail mail = new Mail( from, subject, to, content );
			mail.personalization.get( 0 ).addSubstitution( "-resetTokenUrl-", resetTokenUrl );
			mail.setTemplateId( "790ff8ef-034c-467f-acf2-500fb3bf9920" );
	
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