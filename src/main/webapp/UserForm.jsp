<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.java.model.User" %>

<!doctype html>
<html>
<head>
    <title>Users Application</title>
</head>
<body>
<% 
	User user = (User) request.getAttribute ("user");
%>

    <center>
        <h1>Users Management</h1>
        <h2>
            <a href="/user?action=new">Add New User</a>
            &nbsp;
            <a href="/user?action=list">List All Users</a>
        </h2>
    </center>
	
    <div align="center">
        <% if (user != null) { %>
            <form action="/user?action=update" method="post">
        <% } else { %>
            <form action="/user?action=insert" method="post">
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
                    <input type="hidden" name="userId" value="<% out.println( user.getUserId() ); %>" />
                <% } %>
            <tr>
                <th>User name: </th>
                <td>
                    <input type="text" name="userName" size="50"
						<% if (user != null) { out.println( "value='" + user.getUserName() +"'" ); } %>
					/>
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="text" name="pass" size="50"
						<% if (user != null) { out.println( "value='" + user.getPass() + "'" ); } %>
					/>
                </td>
            </tr>
            <tr>
                <th>First Name: </th>
                <td>
                    <input type="text" name="firstName" size="50"
						<% if (user != null) { out.println( "value='" + user.getFirstName() + "'" ); } %>
					/>
                </td>
            </tr>
            <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="lastName" size="50"
						<% if (user != null) { out.println( "value='" + user.getLastName() + "'" ); } %>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>