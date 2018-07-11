<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="main.java.*"%>
<html>
<head>
    <title>Users Application</title>
</head>
<body>
    <center>
        <h1>Users Management</h1>
        <h2>
            <a href="/new">Add New User</a>
            &nbsp;
            <a href="/list">List All Users</a>
        </h2>
    </center>
	
    <div align="center">
        <c:if test="${user != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${user == null}">
            <form action="insert" method="post">
        </c:if>
		
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${user != null}">
                        Edit User
                    </c:if>
                    <c:if test="${user == null}">
                        Add New User
                    </c:if>
                </h2>
            </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="userId" value="<c:out value='${user.userId}' />" />
                </c:if>           
            <tr>
                <th>User name: </th>
                <td>
                    <input type="text" name="userName" size="50"
                            value="<c:out value='${user.userName}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="text" name="pass" size="50"
                            value="<c:out value='${user.pass}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>First Name: </th>
                <td>
                    <input type="text" name="firstName" size="50"
                            value="<c:out value='${user.firstName}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="lastName" size="50"
                            value="<c:out value='${user.lastName}' />"
                    />
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