package main.java;

import main.java.dao.StudentDAO;
import main.java.dao.CourseDAO;
import main.java.model.Student;
import main.java.model.Course;

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

@WebServlet( "/auth/student" )

@SuppressWarnings("serial")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;
		
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		context.log( ">>> [StudentServlet | BEGIN]" );

        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
		
		String action = req.getParameter( "action" );
		if (action == null) { action = "list"; }	// default action

		try {
            switch (action) {
            case "new":
                showNewStudent(req, res);
                break;
            case "insert":
                insertStudent(req, res);
                break;
            case "delete":
                deleteStudent(req, res);
                break;
            case "update":
                updateStudent(req, res);
                break;
            case "edit":
                showEditStudent(req, res);
                break;
            default:
                listStudent(req, res);
                break;
            }
        } catch (Exception ex) {
			context.log( "<<< [StudentServlet | END] Exception!");
            throw new ServletException(ex);
        }
		context.log( "<<< [StudentServlet | END] Successfully action=" + action );
    }
 
    private void listStudent(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException 
	{
        List<Student> listStudents = studentDAO.selectAllRecords();
        req.setAttribute("listStudents", listStudents);
		
        req.getRequestDispatcher( "/auth/student-list.jsp" ).forward(req, res);
    }
 
    private void showNewStudent(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, Exception
	{
        List<Course> listCourses = courseDAO.selectAllCourses();
        req.setAttribute( "listCourses", listCourses );
        req.getRequestDispatcher( "/auth/student-form.jsp" ).forward(req, res);
    }
 
    private void showEditStudent(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
	{
		int studentId 			= Integer.parseInt(req.getParameter("studentId"));
		Student existingStudent = studentDAO.selectRecordByStudent(studentId);
        req.setAttribute("student", existingStudent);

        List<Course> listCourses = courseDAO.selectAllCourses();
        req.setAttribute( "listCourses", listCourses );
        
        req.getRequestDispatcher( "/auth/student-form.jsp" ).forward(req, res);
    }
 
    private void insertStudent(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
	{
        String name 	= req.getParameter("name");
        String age 		= req.getParameter("age");
        String gender 	= req.getParameter("gender");
        String country 	= req.getParameter("country");

        String msg      = "";           // In case need to show message error
        Course course   = null;         // check if the course still exists before insert

        List<Course> listCourses = courseDAO.selectAllCourses();
        req.setAttribute( "listCourses", listCourses );

        try 
        {   
            // To validate if course_id is not null in case course table is empty
            int courseId = Integer.parseInt(req.getParameter("course_id"));

            // To make sure course still exists before insert. Avoid constraint exception
            course = courseDAO.selectRecordByCourse( courseId );

            if (course == null)         // The selected course no longer exists
            {
                msg = "The selected course no longer exists. Please select another course";
            }
        } 
        catch ( NumberFormatException e ) 
        {
            msg = "The selected course is invalid. You should try it again";
        }
        
        if ( msg.equals("") )           // No error regarding course
        {
            Student newStudent = new Student(name, age, gender, country, course);

            if ( studentDAO.insertRecord( newStudent ) )
            {
                listStudent(req, res);  // in case insert successfully, dispatch to list students
            }
            else
            {                           // Fail to insert student
                req.setAttribute( "student", newStudent );
                req.setAttribute( "message", "Name already exists in our database. Please, try another one." );
                req.getRequestDispatcher( "/auth/student-form.jsp" ).forward(req, res);
            }
        }
        else
        {                               // Display course error message
            req.setAttribute( "message", msg );
            req.getRequestDispatcher( "/auth/student-form.jsp" ).forward(req, res);
        }
    }
 
    private void updateStudent(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException 
	{
		int studentId 			= Integer.parseInt(req.getParameter("studentId"));
		String name 	= req.getParameter("name");
        String age 		= req.getParameter("age");
        String gender 	= req.getParameter("gender");
        String country 	= req.getParameter("country");
		int courseId = Integer.parseInt(req.getParameter("course_id"));
		Course course = new Course(courseId);
        Student newStudent = new Student(studentId, name, age, gender, country, course );
        
        if(studentDAO.updateRecord( newStudent ))
        {
            listStudent(req, res);
        }
        else
        {
            req.setAttribute( "student", newStudent );
            req.setAttribute( "message", "Name already exists in our database. Please, try another one." );
            req.getRequestDispatcher( "/auth/student-form.jsp" ).forward(req, res);
        };
    }
 
    private void deleteStudent(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
	{
		int studentId  = Integer.parseInt(req.getParameter("studentId"));

        if ( studentDAO.deleteRecord( new Student(studentId)) )
        {
            listStudent(req, res);
        }
		else
		{
	        throw new IOException( "Fail when deleting student" );
		}

    }
}
