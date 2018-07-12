<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.User,java.util.*" %>

<!doctype html>
<html>
<head>
    <title>Users Application</title>
</head>
<body>
<% 
	List<User> listUsers = (ArrayList<User>) request.getAttribute ("listUsers");
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
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>User Name</th>
                <th>Password</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Actions</th>
            </tr>
			<% for(User user : listUsers) { %>
                <tr>
					
                    <td><%out.println(user.getUserName()); %></td>
                    <td><%out.println(user.getPass()); %></td>
                    <td><%out.println(user.getFirstName()); %></td>
                    <td><%out.println(user.getLastName()); %></td>
                    <td>
                        <a href="/user?action=edit&userId=<%out.println(user.getUserId()); %>" >Edit</a>
                        &nbsp;
                        <a href="/user?action=delete&userId=<%out.println(user.getUserId()); %>" >Delete</a>                     
                    </td>
                </tr>
            <% } %>
        </table>
    </div>   
</body>
</html>