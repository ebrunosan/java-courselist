package main.java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error")
@SuppressWarnings("serial")
public class ErrorHandler extends HttpServlet {

	/***** This Method Is Called By The Servlet Container To Process A 'GET' Request *****/
	public void doGet( HttpServletRequest req, HttpServletResponse res ) 
		throws IOException, ServletException 
	{
		handleRequest( req, res );
	}

	private void handleRequest( HttpServletRequest req, HttpServletResponse res ) 
			throws IOException, ServletException 
	{
		/***** Analyze The Servlet Exception *****/    		
		Integer statusCode 	= ( Integer ) req.getAttribute( "javax.servlet.error.status_code" );
		String servletName 	= ( String ) req.getAttribute( "javax.servlet.error.servlet_name" );
		String reqUri 		= ( String ) req.getAttribute( "javax.servlet.error.req_uri" );
		Throwable throwable = ( Throwable ) req.getAttribute( "javax.servlet.error.exception" );

		if ( servletName == null ) { servletName = "Unknown"; }
		if ( reqUri == null ) { reqUri = "Unknown"; }

		req.getRequestDispatcher( "/auth/_top-page.jsp" ).include( req, res );
		
		PrintWriter out = res.getWriter();
		
		if ( throwable == null && statusCode == null ) 
		{
			out.println( "<h3>Error Information Is Missing</h3>");			
		} 
		else if ( statusCode != 500 ) 
		{
			out.write( "<h3>Error Details</h3>");
			out.write( "<ul><li><strong>Status Code</strong>?= "+ statusCode + "</li>");
			out.write( "<li><strong>Requested URI</strong>?= "+ reqUri + "</li></ul>");
		} 
		else 
		{
			out.println( "<h3>Exception Details</h3>" );
			out.println( "<ul><li><strong>Servlet Name</strong>?= " + servletName + "</li>" );
			out.println( "<li><strong>Exception Name</strong>?= " + throwable.getClass( ).getName( ) + "</li>" );
			out.println( "<li><strong>Requested URI</strong>?= " + reqUri + "</li>" );
			out.println( "<li><strong>Exception Message</strong>?= " + throwable.getMessage( ) + "</li></ul>" );
		}

		out.close();
		req.getRequestDispatcher( "/auth/_botton-page.jsp" ).include( req, res );
	}
}