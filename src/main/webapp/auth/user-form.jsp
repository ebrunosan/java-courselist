<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.User,java.util.*" %>

<%
	User user = (User) request.getAttribute ("user");
%>

<jsp:include page="_top-page.jsp" />

<div class="container">
	<h1>User Sign up</h1>
	<h2>
		<a href="<%= response.encodeURL( "/auth/user?action=new" ) %>" >Add New User</a>
		&nbsp;
		<a href="<%= response.encodeURL( "/auth/user?action=list" ) %>" >List All Users</a>
	</h2>

	<% if (user != null) { %>
		<form action="<%= response.encodeURL( "/auth/user?action=update" ) %>" method="post">
	<% } else { %>
		<form action="<%= response.encodeURL( "/auth/user?action=insert" ) %>" method="post">
	<% } %>
	
	<table border="1" cellpadding="5">
		<caption>
			<h2>
				<% if (user != null) { %>Edit User<% } else { %>Add New User<% } %>
			</h2>
		</caption>
		<% if (user != null) { %>
			<input type="hidden" name="userId" value="<%= user.getUserId() %>" />
		<% } %>
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
			<th>User email: </th>
			<td>
				<input type="text" name="userEmail" size="50"
					<% if (user != null) { out.print( "value='" + user.getEmail() +"'" ); } %>
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
			<td colspan="2" align="center">
				<input type="reset" value="Reset" />
				&nbsp;
				<input type="submit" value="Save" />
			</td>
		</tr>
	</table>
	</form>
</div>
	
<jsp:include page="_botton-page.jsp" />
