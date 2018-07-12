<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="main.java.model.User" %>

<!doctype html>
<html>
<head>
    <title>Users Application</title>
</head>
<body>
<%
	final String bruno = (String) request.getAttribute ("bruno");
	final String x = (String) request.getAttribute ("bruno");
    out.write("<h3> 4 " + x + "</h3>");
%>
<h3> 1 My name is <c:out value="${bruno}" /></h3>
<h3> 2 My name with requestScope <c:out value="${requestScope.bruno}" /></h3>
<h3> 3 My name with param <c:out value="${param.bruno}" /></h3>
<h3> 5 <%=x %> </h3>

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
            <c:forEach var="user" items="${listUsers}">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                    <td><c:out value="${user.pass}" /></td>
                    <td><c:out value="${user.firstName}" /></td>
                    <td><c:out value="${user.lastName}" /></td>
                    <td>
                        <a href="/user?action=edit&userId=<c:out value='${user.userId}' />">Edit</a>
                        &nbsp;
                        <a href="/user?action=delete&userId=<c:out value='${user.userId}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>