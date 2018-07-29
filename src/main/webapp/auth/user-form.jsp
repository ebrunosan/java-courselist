<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.User,java.util.*" %>

<%
	String message 	= ( String ) request.getAttribute ( "message" );
	User user 		= ( User ) request.getAttribute ( "user" );
	
	boolean editMode;
	if ( user != null && user.getUserId() > 0 )
	{
		editMode = true;
	}
	else
	{
		editMode = false;
	}
%>

<jsp:include page="_top-page.jsp" />

<div class="container-fluid">
	<h1>User Sign up</h1>
	<h2>
		<a href="<%= response.encodeURL( "/auth/user?action=new" ) %>" >Add New User</a>
		&nbsp;
		<a href="<%= response.encodeURL( "/auth/user?action=list" ) %>" >List All Users</a>
	</h2>

	<% if ( user != null && user.getUserId() != null && user.getUserId() != 0 ) { %>
		<form action="<%= response.encodeURL( "/auth/user?action=update" ) %>" method="post">
	<% } else { %>
		<form action="<%= response.encodeURL( "/auth/user?action=insert" ) %>" method="post">
	<% } %>
	
	<% if ( message != null && !message.isEmpty() ) { %>
		<p><font color=red><%= message %></font></p>
	<% } %>

	<table border="1" cellpadding="5">
		<caption>
			<h2>
				<% if ( editMode ) { %>Edit User<% } else { %>Add New User<% } %>
			</h2>
		</caption>
		<% if ( editMode ) { %>
			<input type="hidden" name="userId" value="<%= user.getUserId() %>" />
		<% } %>
		<tr>
			<th>First Name: </th>
			<td>
				<input type="text" name="firstName" size="50"
					<% if ( editMode ) { out.print( "value='" + user.getFirstName() + "'" ); } %>
				/>
			</td>
		</tr>
		<tr>
			<th>Last Name: </th>
			<td>
				<input type="text" name="lastName" size="50"
					<% if ( editMode ) { out.print( "value='" + user.getLastName() + "'" ); } %>
			</td>
		</tr>
		<tr>
			<th>User email: </th>
			<td>
				<input type="text" name="userEmail" size="50"
					<% if ( editMode ) { out.print( "value='" + user.getEmail() +"'" ); } %>
				/>
			</td>
		</tr>
		<tr>
			<th>Password: </th>
			<td>
				<input type="text" name="pass" size="50"
					<% if ( editMode ) { out.print( "value='" + user.getPass() + "'" ); } %>
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
