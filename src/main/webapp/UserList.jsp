<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.User,java.util.*" %>

<%
	String userID = null;
	String userName = null;
	String sessionID = null;

	if ( session.getAttribute( "userName" ) == null )
	{
		response.sendRedirect( "login.html" );
	} else 
	{
		userName = (String) session.getAttribute( "userName" );
	}
	
	Cookie[] cookies = request.getCookies();
	if ( cookies != null )
	{
		for ( Cookie cookie : cookies )
		{
			if ( cookie.getName().equals( "userID" ) ) { userID = cookie.getValue(); }
			if ( cookie.getName().equals( "JSESSIONID" ) ) { sessionID = cookie.getValue(); }
		}
	} else 
	{
		sessionID = session.getId();
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
            <a href="<%= response.encodeURL( "/user?action=new" ) %>" >Add New User</a>
            &nbsp;
            <a href="<%= response.encodeURL( "/user?action=list" ) %>" >List All Users</a>
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
                    <td><%= user.getPass() %></td>
                    <td><%= user.getFirstName() %></td>
                    <td><%= user.getLastName() %></td>
                    <td>
                        <a href = "<%= response.encodeURL( "/user?action=edit&userId=" + user.getUserId() ) %>" >Edit</a>
                        &nbsp;
                        <a href = "<%= response.encodeURL( "/user?action=delete&userId=" + user.getUserId() ) %>" >Delete</a>                     
                    </td>
                </tr>
            <% } %>
        </table>
    </div>   
</body>
</html>