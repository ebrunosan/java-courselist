package main.java;

import main.java.dao.CourseProgramDAO;
import main.java.model.CourseProgram;

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

@WebServlet( "/auth/course" )

@SuppressWarnings( "serial" )
public class CourseServlet extends HttpServlet {
	private CourseProgramDAO courseDAO;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
		@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		context.log( ">>> [CourseServlet | BEGIN]" );

		//if ( userDAO == null ) { userDAO = new UserDAO(); }
		courseDAO = new CourseProgramDAO();
		
		String action = req.getParameter( "action" );
		if (action == null) { action = "list"; }	// default action

		try {
            switch (action) {
            case "new":
                showNewCourse(req, res);
                break;
            case "insert":
                insertCourse(req, res);		// TODO check successful operation
				listCourse(req, res);
                break;
            case "delete":
                deleteCourse(req, res);		// TODO check successful operation
				listCourse(req, res);
                break;
            case "update":
                updateCourse(req, res);		// TODO check successful operation
				listCourse(req, res);
                break;
            case "edit":
                showEditCourse(req, res);
                break;
            default:
                listCourse(req, res);
                break;
            }
        } catch (Exception ex) {
			context.log( "<<< [CourseServlet | END] Exception!");
            throw new ServletException(ex);
        }
		context.log( "<<< [CourseServlet | END] Successfully action=" + action );
    }
 
    private void listCourse(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, ServletException, Exception 
	{
        List<CourseProgram> listCourses = courseDAO.selectAllCourses();
        req.setAttribute( "listCourses", listCourses );
        req.getRequestDispatcher( "/auth/course-list.jsp" ).forward(req, res);
    }
 
    private void showNewCourse(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, Exception
	{
        req.getRequestDispatcher( "/auth/course-form.jsp" ).forward(req, res);
    }
 
    private void showEditCourse(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, ServletException, IOException, Exception
	{
		int courseCode 			= Integer.parseInt( req.getParameter( "courseCode" ) );
		CourseProgram existingCourse 	= courseDAO.selectRecordByCourse( courseCode );
        req.setAttribute( "course", existingCourse );
        
        req.getRequestDispatcher( "/auth/course-form.jsp" ).forward(req, res);
    }
 
    private void insertCourse(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, Exception
	{
        String courseName	= req.getParameter( "courseName" );
        String duration 	= req.getParameter( "duration" );
        String description 	= req.getParameter( "description" );
 
        courseDAO.insertCourse( new CourseProgram( courseName, duration, description) );
//        res.sendRedirect( "course" );
    }
 
    private void updateCourse(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, Exception 
	{
		int courseCode 		= Integer.parseInt( req.getParameter( "courseCode" ) );
		String courseName 	= req.getParameter( "courseName" );
        String duration		= req.getParameter( "duration" );
        String description 	= req.getParameter( "description" );
         
        courseDAO.updateCourse( new CourseProgram( courseCode, courseName, duration, description ) );
//        res.sendRedirect( "user" );
    }
 
    private void deleteCourse(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, Exception
	{
		int courseCode 			= Integer.parseInt( req.getParameter( "courseCode" ) );

        courseDAO.deleteCourse( new CourseProgram( courseCode ) );
//        res.sendRedirect( "course" );
    }
}
