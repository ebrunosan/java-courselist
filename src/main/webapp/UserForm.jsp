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

	User user = (User) request.getAttribute ("user");
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
        <% if (user != null) { %>
            <form action="<%= response.encodeURL( "/user?action=update" ) %>" method="post">
        <% } else { %>
            <form action="<%= response.encodeURL( "/user?action=insert" ) %>" method="post">
        <% } %>
		
        <table border="1" cellpadding="5">
            <caption>
                <h2>
					<% if (user != null) { %>
                        Edit User
					<% } else { %>
                        Add New User
                    <% } %>
                </h2>
            </caption>
				<% if (user != null) { %>
                    <input type="hidden" name="userId" value="<%= user.getUserId() %>" />
                <% } %>
            <tr>
                <th>User name: </th>
                <td>
                    <input type="text" name="userName" size="50"
						<% if (user != null) { out.print( "value='" + user.getUserName() +"'" ); } %>
					/>
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="text" name="pass" size="50"
						<% if (user != null) { out.print( "value='" + user.getPass() + "'" ); } %>
					/>
                </td>
            </tr>
            <tr>
                <th>First Name: </th>
                <td>
                    <input type="text" name="firstName" size="50"
						<% if (user != null) { out.print( "value='" + user.getFirstName() + "'" ); } %>
					/>
                </td>
            </tr>
            <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="lastName" size="50"
						<% if (user != null) { out.print( "value='" + user.getLastName() + "'" ); } %>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="reset" value="Reset" />
					&nbsp;
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>