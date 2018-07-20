package main.java.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
	name = "LoginServlet",
	urlPatterns = "/login"
)

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet 
{
	// TODO SELECT userName WHERE userID=? and password=? FROM DB
	private final String user = "admin@admin.com";
	private final String password = "admin";
	private final String userName = "Administrator Name";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		//TODO if user logged in THEN redirect to menu ELSE redirect to login.html
		
		// invalidate the session if exists
    	HttpSession session = req.getSession( false );
    	System.out.println( "UserName="+session.getAttribute( "userName" ) );

    	if ( session != null ) { session.invalidate(); }

    	// no need to encoding because the session is invalid
    	res.sendRedirect( "login.html" );
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		// get req parameters for userID and password
		String userID = req.getParameter( "userID" );
		String pwd = req.getParameter( "pwd" );
		
		// TODO if user NOT_FOUND at query
		if ( user.equals( userID ) && password.equals( pwd ) )
		{
			HttpSession session = req.getSession();
			session.setAttribute( "userName", userName );
			
			//setting session to expiry in 10 mins
			session.setMaxInactiveInterval( 10*60 );
			res.addCookie( new Cookie( "userID", userID ) );
			res.sendRedirect( res.encodeRedirectURL( "user" ) );
		} else
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher( "/login.html" );
			PrintWriter out = res.getWriter();
			out.println( "<font color=red>Either user name or password is wrong.</font>" );
			rd.include( req, res );
		}
	}
}