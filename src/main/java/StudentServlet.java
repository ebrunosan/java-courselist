package main.java;

import main.java.dao.StudentDAO;
import main.java.model.Student;
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

@WebServlet( "/auth/student" )

@SuppressWarnings("serial")
public class StudentServlet extends HttpServlet {
	private StudentDAO studentDAO;
		
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		context.log( ">>> [StudentServlet | BEGIN]" );

		//if ( StudentDAO == null ) { StudentDAO = new StudentDAO(); }
		studentDAO = new StudentDAO();
		
		String action = req.getParameter( "action" );
		if (action == null) { action = "list"; }	// default action

		try {
            switch (action) {
            case "new":
                showNewStudent(req, res);
                break;
            case "insert":
                insertStudent(req, res);		// TODO check successful operation
				listStudent(req, res);
                break;
            case "delete":
                deleteStudent(req, res);		// TODO check successful operation
				listStudent(req, res);
                break;
            case "update":
                updateStudent(req, res);		// TODO check successful operation
				listStudent(req, res);
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
            throws SQLException, IOException, ServletException, Exception 
	{
        List<Student> listStudents = studentDAO.selectAllRecords();
        req.setAttribute("listStudents", listStudents);
		
        req.getRequestDispatcher( "/auth/student-list.jsp" ).forward(req, res);
    }
 
    private void showNewStudent(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, Exception
	{
        req.getRequestDispatcher( "/auth/student-form.jsp" ).forward(req, res);
    }
 
    private void showEditStudent(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, ServletException, IOException, Exception
	{
		int studentId 			= Integer.parseInt(req.getParameter("studentId"));
		Student existingStudent 	= studentDAO.selectRecordByStudent(studentId);
        req.setAttribute("student", existingStudent);
        
        req.getRequestDispatcher( "/auth/student-form.jsp" ).forward(req, res);
    }
 
    private void insertStudent(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, Exception
	{
        String name 	= req.getParameter("name");
        String age 		= req.getParameter("age");
        String gender 	= req.getParameter("gender");
        String country 	= req.getParameter("country");
		int courseId = Integer.parseInt(req.getParameter("course_id"));
		CourseProgram course = new CourseProgram(courseId); 
 
        studentDAO.insertRecord( new Student(name, age, gender, country, course) );
        //res.sendRedirect("student");
    }
 
    private void updateStudent(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, Exception 
	{
		int studentId 			= Integer.parseInt(req.getParameter("studentId"));
		String name 	= req.getParameter("name");
        String age 		= req.getParameter("age");
        String gender 	= req.getParameter("gender");
        String country 	= req.getParameter("country");
		int courseId = Integer.parseInt(req.getParameter("course_id"));
		CourseProgram course = new CourseProgram(courseId);
 
        studentDAO.updateRecord( new Student(studentId, name, age, gender, country, course ) );
        //res.sendRedirect("student");
    }
 
    private void deleteStudent(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, Exception
	{
		int studentId  = Integer.parseInt(req.getParameter("studentId"));

        studentDAO.deleteRecord( new Student(studentId));
        //res.sendRedirect("student");
    }
}
