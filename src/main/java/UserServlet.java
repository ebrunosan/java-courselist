package main.java;

import main.java.dao.UserDAO;
import main.java.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	name = "UserServlet",
	urlPatterns = "/user"
)

@SuppressWarnings("serial")
public class UserServlet extends HttpServlet {
	private UserDAO userDAO;
	
	@Override
	public void init() {
		userDAO = new UserDAO();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) { action = "list"; }	// default action

		try {
            switch (action) {
            case "new":
                showNewUser(req, res);
                break;
            case "insert":
                insertUser(req, res);
                break;
            case "delete":
                deleteUser(req, res);
                break;
            case "edit":
                showEditUser(req, res);
                break;
            case "update":
                updateUser(req, res);
                break;
            default:
                listUser(req, res);
                break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listUser(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, ServletException, Exception 
	{
        List<User> listUsers = userDAO.selectAllRecords();
        req.setAttribute("listUsers", listUsers);
		
//List<User> listUsers = new ArrayList<User>();
//listUsers.add(new User(1, "username1","pass1", "firstName1", "lastName1"));
//listUsers.add(new User(2, "username2","pass2", "firstName2", "lastName2"));
//listUsers.add(new User(3, "username3","pass3", "firstName3", "lastName3"));
//req.setAttribute("listUsers", listUsers);

//req.setAttribute("bruno", "Bruno Santos");

//System.out.println("bruno SET");

        req.getRequestDispatcher("UserList.jsp").forward(req, res);
    }
 
    private void showNewUser(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, Exception
	{
//req.setAttribute("bruno", "Bruno Santos");
        req.getRequestDispatcher("UserForm.jsp").forward(req, res);
    }
 
    private void showEditUser(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, ServletException, IOException, Exception
	{
		int userId 			= Integer.parseInt(req.getParameter("userId"));
		User existingUser 	= userDAO.selectRecordByUser(userId);
        req.setAttribute("user", existingUser);
        
//req.setAttribute("bruno", "Bruno Santos");
        req.getRequestDispatcher("UserForm.jsp").forward(req, res);
    }
 
    private void insertUser(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, Exception
	{
        String username 	= req.getParameter("userName");
        String pass 		= req.getParameter("pass");
        String firstName 	= req.getParameter("firstName");
        String lastName 	= req.getParameter("lastName");
 
        userDAO.insertRecord( new User(username, pass, firstName, lastName) );
        res.sendRedirect("user");
    }
 
    private void updateUser(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, Exception 
	{
		int userId 			= Integer.parseInt(req.getParameter("userId"));
		String username 	= req.getParameter("userName");
        String pass 		= req.getParameter("pass");
        String firstName 	= req.getParameter("firstName");
        String lastName 	= req.getParameter("lastName");
 
        userDAO.updateRecord( new User(userId, username, pass, firstName, lastName) );
        res.sendRedirect("user");
    }
 
    private void deleteUser(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, Exception
	{
		int userId 			= Integer.parseInt(req.getParameter("userId"));
System.out.println("UserID=" + userId); 
        userDAO.deleteRecord( new User(userId) );
        res.sendRedirect("user");
    }
}
