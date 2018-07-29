package main.java;

import main.java.dao.UserDAO;
import main.java.model.User;

import java.io.IOException;

import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/auth/user" )

@SuppressWarnings( "serial" )
public class UserServlet extends HttpServlet {
	private UserDAO userDAO;
	
	@Override
	public void doPost( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		doGet(req, res);
	}
	
	@Override
	public void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		ServletContext context = getServletContext();
		context.log( ">>> [UserServlet | BEGIN]" );

		userDAO = new UserDAO();
		
		String action = req.getParameter( "action" );
		if (action == null) { action = "list"; }	// default action

		try {
            switch ( action ) {
				case "new":
					showNewUser( req, res );
					break;
				case "insert":
					insertUser( req, res );
					break;
				case "delete":
					deleteUser( req, res );
					break;
				case "update":
					updateUser( req, res );
					break;
				case "edit":
					showEditUser( req, res );
					break;
				default:
					listUser( req, res );
					break;
            }
			context.log( "<<< [UserServlet | END] Successfully action=" + action );
        } 
		catch ( Exception ex ) 
		{
			context.log( "<<< [UserServlet | END] Exception!");
            throw new ServletException( ex );
        }
    }
 
    private void listUser( HttpServletRequest req, HttpServletResponse res )
            throws SQLException, IOException, ServletException, Exception 
	{
        List<User> listUsers = userDAO.selectAllRecords();
        req.setAttribute( "listUsers", listUsers );
        req.getRequestDispatcher( "/auth/user-list.jsp" ).forward(req, res);
    }
 
    private void showNewUser( HttpServletRequest req, HttpServletResponse res )
            throws ServletException, IOException, Exception
	{
        req.getRequestDispatcher( "/auth/user-form.jsp" ).forward(req, res);
    }
 
    private void showEditUser( HttpServletRequest req, HttpServletResponse res )
            throws SQLException, ServletException, IOException, Exception
	{
		int userId 			= Integer.parseInt( req.getParameter( "userId" ) );
		User existingUser 	= userDAO.selectRecordByUser( userId );
        req.setAttribute( "user", existingUser );
        
        req.getRequestDispatcher( "/auth/user-form.jsp" ).forward(req, res);
    }
 
    private void insertUser( HttpServletRequest req, HttpServletResponse res )
            throws SQLException, IOException, Exception
	{
        String firstName 	= req.getParameter( "firstName" );
        String lastName 	= req.getParameter( "lastName" );
        String email 		= req.getParameter( "userEmail" );
        String pass 		= req.getParameter( "pass" );
 
		User newUser = new User( email, firstName, lastName, pass, null );
 
        if ( userDAO.insertRecord( newUser ) )
		{
			listUser( req, res );
		}
		else
		{
			req.setAttribute( "user", newUser );
			req.setAttribute( "message", "Email already exists in our database. Please, try another one." );
			req.getRequestDispatcher( "/auth/user-form.jsp" ).forward(req, res);
		}
    }
 
    private void updateUser( HttpServletRequest req, HttpServletResponse res )
            throws SQLException, IOException, Exception 
	{
		int userId 			= Integer.parseInt( req.getParameter( "userId" ) );
        String firstName 	= req.getParameter( "firstName" );
        String lastName 	= req.getParameter( "lastName" );
        String email 		= req.getParameter( "userEmail" );
        String pass 		= req.getParameter( "pass" );
 
		User user = new User( userId, email, firstName, lastName, pass, null );
 
        if ( userDAO.updateRecord( user ) )
		{
			listUser( req, res );
		}
		else
		{
			req.setAttribute( "user", user );
			req.setAttribute( "message", "Email already exists in our database. Please, try another one." );
			req.getRequestDispatcher( "/auth/user-form.jsp" ).forward(req, res);
		}
    }
 
    private void deleteUser( HttpServletRequest req, HttpServletResponse res )
            throws SQLException, IOException, Exception
	{
		int userId = Integer.parseInt( req.getParameter( "userId" ) );
        if ( userDAO.deleteRecord( userId ) )
		{
			listUser( req, res );
		}
		else
		{
	        throw new IOException( "Fail when deleting user" );
		}
    }
}