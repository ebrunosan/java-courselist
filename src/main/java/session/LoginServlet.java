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
	private final String userID = "admin@admin.com";
	private final String password = "admin";
	private final String userName = "Administrator Name";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		// get req parameters for userID and password
		String user = req.getParameter( "userID" );
		String pwd = req.getParameter( "pwd" );
		
		// TODO if user NOT_FOUND at query
		if ( userID.equals( user ) && password.equals( pwd ) )
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
