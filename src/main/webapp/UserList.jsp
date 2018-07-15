<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.User,java.util.*" %>

<%
//allow access only if session exists
	if ( session.getAttribute("user") == null )
	{
		response.sendRedirect("login.html");
	}
	
	String userName = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	
	if ( cookies != null )
	{
		for ( Cookie cookie : cookies )
		{
			if ( cookie.getName().equals("user") ) 
			{
				userName = cookie.getValue();
			}
		}
	}

	List<User> listUsers = (ArrayList<User>) request.getAttribute ("listUsers");
%>

<!doctype html>
<html>
<head>
    <title>Users Application</title>
</head>
<body>
    <center>
		<form action="logout" method="post">
			<h3>Hi <%= userName %>, welcome to our Java Lambton Project.</h3>
			<br>
			<input type="submit" value="Logout" >
		</form>

        <h1>Users Management</h1>
        <h2>
            <a href="/user?action=new">Add New User</a>
            &nbsp;
            <a href="/user?action=list">List All Users</a>
        </h2>
    </center>
	
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>User Name</th>
                <th>Password</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Actions</th>
            </tr>
			<% for ( User user : listUsers ) { %>
                <tr>
					
                    <td><%= user.getUserName() %></td>
                    <td><%out.print(user.getPass()); %></td>
                    <td><%out.print(user.getFirstName()); %></td>
                    <td><%out.print(user.getLastName()); %></td>
                    <td>
                        <a href="/user?action=edit&userId=<%out.print(user.getUserId()); %>" >Edit</a>
                        &nbsp;
                        <a href="/user?action=delete&userId=<%out.print(user.getUserId()); %>" >Delete</a>                     
                    </td>
                </tr>
            <% } %>
        </table>
    </div>   
</body>
</html>