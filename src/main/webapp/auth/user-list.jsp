<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.User,java.util.*" %>

<%
	List<User> listUsers = ( ArrayList<User> ) request.getAttribute ( "listUsers" );
%>

<jsp:include page="_top-page.jsp" />

<div class="container">
	<h1>Users Management</h1>
	<h2>
		<a href="<%= response.encodeURL( "/auth/user?action=new" ) %>" >Add New User</a>
		&nbsp;
		<a href="<%= response.encodeURL( "/auth/user?action=list" ) %>" >List All Users</a>
	</h2>

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
					<a href = "<%= response.encodeURL( "/auth/user?action=edit&userId=" + user.getUserId() ) %>" >Edit</a>
					&nbsp;
					<a href = "<%= response.encodeURL( "/auth/user?action=delete&userId=" + user.getUserId() ) %>" >Delete</a>                     
				</td>
			</tr>
		<% } %>
	</table>
</div>   

<jsp:include page="_botton-page.jsp" />
