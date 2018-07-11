package jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "UsersServlet",
        urlPatterns = "/users"
)

@SuppressWarnings("serial")
public class UsersServlet extends HttpServlet {
	private UserDao userDao;
	
	public UsersServlet() {
		super();
		userDao = new UserDao();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getServletPath();
		try {
            switch (action) {
            case "/new":
                showNewUser(req, res);
                break;
            case "/insert":
                insertUser(req, res);
                break;
            case "/delete":
                deleteUser(req, res);
                break;
            case "/edit":
                showEditUser(req, res);
                break;
            case "/update":
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
        List<User> listUsers = userDao.selectAllRecords();
        req.setAttribute("listUser", listUsers);

        RequestDispatcher dispatcher = req.getRequestDispatcher("UserList.jsp");
        dispatcher.forward(req, res);
    }
 
    private void showNewUser(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, Exception
	{
        RequestDispatcher dispatcher = req.getRequestDispatcher("UserForm.jsp");
        dispatcher.forward(req, res);
    }
 
    private void showEditUser(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, ServletException, IOException, Exception
	{
		String username 	= req.getParameter("username");
		User existingUser 	= userDao.selectRecordByUser(username);
        req.setAttribute("user", existingUser);
        
		RequestDispatcher dispatcher = req.getRequestDispatcher("UserForm.jsp");
        dispatcher.forward(req, res);
    }
 
    private void insertUser(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, Exception
	{
        String username 	= req.getParameter("username");
        String pass 		= req.getParameter("pass");
        String firstName 	= req.getParameter("firstName");
        String lastName 	= req.getParameter("lastName");
 
        userDao.insertRecord( new User(username, pass, firstName, lastName) );
        res.sendRedirect("list");
    }
 
    private void updateUser(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, Exception 
	{
		String username 	= req.getParameter("username");
        String pass 		= req.getParameter("pass");
        String firstName 	= req.getParameter("firstName");
        String lastName 	= req.getParameter("lastName");
 
        userDao.updateRecord( new User(username, pass, firstName, lastName) );
        res.sendRedirect("list");
    }
 
    private void deleteUser(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, Exception
	{
		String username 	= req.getParameter("username");
 
        userDao.deleteRecord( new User(username) );
        res.sendRedirect("list");
    }
}
