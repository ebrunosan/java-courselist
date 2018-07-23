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
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		ServletContext context = getServletContext();
		context.log( ">>> [ForgotPswdServlet | BEGIN]" );

		//if ( userDAO == null ) { userDAO = new UserDAO(); }

		String emailTo = req.getParameter( "user-email" );
        //User user = userDAO.selectUserByEmail( emailTo );
        User user = new User( "userName", "pass", "firstName", "lastName", emailTo );
		
		if ( user == null )
		{
			req.setAttribute( "message", "Email not found. Please try again!" );
			req.getRequestDispatcher( "/forgotpswd.jsp" ).forward( req, res );
			context.log( "<<< [ForgotPswdServlet | END] Email not found" );
		}
		else
		{
			//String resetToken = userDAO.getNewResetToken();
			String resetToken 	 = UUID.randomUUID().toString();
			String appUrl 		 = req.getScheme() + "://" + req.getServerName();
			String resetTokenUrl = appUrl + "/reset?token=" + resetToken;
			
			if ( sendResetEmail( emailTo, resetTokenUrl ) )
			{
				req.setAttribute( "message", "Email sent successfully. Check your mail box!" );
				req.getRequestDispatcher( "/forgotpswd.jsp" ).forward( req, res );
				context.log( "<<< [ForgotPswdServlet | END] Email sent successfully" );
			}
			else
			{
				req.setAttribute( "message", "Application fail when trying to send an email!" );
				req.getRequestDispatcher( "/forgotpswd.jsp" ).forward( req, res );
				context.log( "<<< [ForgotPswdServlet | END] Fail sent email" );
			}
		}
 	}

	
	
    private boolean sendResetEmail( String emailToAddr, String resetTokenUrl ) throws IOException 
	{
		try 
		{
			SendGrid sg = new SendGrid( System.getenv("SENDGRID_API_KEY") );
			
			Request request = new Request();
			request.setMethod( Method.POST );
			request.setEndpoint( "mail/send" );
			
			Email from 		= new Email( "do-not-reply@heroku.com" );
			Email to 		= new Email( emailToAddr );
			String subject 	= "Password reset request";
			Content content = new Content( "text/plain", 
					"To reset your password, click the link below:\n"+resetTokenUrl );
			
			Mail mail = new Mail( from, subject, to, content );
			
			request.setBody( mail.build() );
			
			Response response = sg.api( request );
			
			System.out.println("[::EMAIL::] Sent to [" + emailToAddr + "]. Status code [" + response.getStatusCode() + "]");
		} 
		catch ( IOException ex ) 
		{
			System.out.println( ex.getMessage() );
			return false;
		}
		
		return true;
    }
 

}