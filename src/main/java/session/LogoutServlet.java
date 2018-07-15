package main.java.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
	name = "LogoutServlet",
	urlPatterns = "/logout"
)

@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet 
{
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
    	res.setContentType( "text/html" );
    	Cookie[] cookies = req.getCookies();

    	if ( cookies != null )
		{
			for ( Cookie cookie : cookies )
			{
				if ( cookie.getName().equals( "JSESSIONID" ) )
				{
					System.out.println( "JSESSIONID="+cookie.getValue() );
				}
				cookie.setMaxAge( 0 );
				res.addCookie( cookie );
			}
    	}

    	// invalidate the session if exists
    	HttpSession session = req.getSession( false );
    	System.out.println( "User="+session.getAttribute( "user" ) );

    	if ( session != null ) { session.invalidate(); }

    	// no need to encoding because the session is invalid
    	res.sendRedirect( "login.html" );
    }
}
