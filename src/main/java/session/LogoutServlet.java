package main.java.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/auth/logout" )

@SuppressWarnings( "serial" )
public class LogoutServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		doPost(req, res);
	}
	
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		// ----------------------------- invalidate the session if exists
    	HttpSession session = req.getSession( false );
    	if ( session != null ) 
		{ 
			session.invalidate(); 
		}
    	req.getRequestDispatcher( "/index.jsp" ).forward(req, res);
    }
}