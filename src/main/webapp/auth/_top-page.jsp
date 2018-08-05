<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet" />
	<link href="https://cdnjs.cloudflare.com/ajax/libs/hyperform/0.9.9/hyperform.css" rel="stylesheet" />
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.js"></script>

	<title>BLB Website</title>
</head>

<%
	String sessionID 	= session.getId();
	String userName 	= (String) session.getAttribute( "userName" );
	String userId 		= (String) session.getAttribute( "userId" );

	String uriName 		= (String) session.getAttribute( "uri" );
	
	boolean isLogged 	= ( userName != null );
%>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<% if ( isLogged ) { %>
					<a class="navbar-brand" href="<%= response.encodeURL( "/auth/welcome.jsp" ) %>">BLB Website</a>
				<% } else {%>
					<a class="navbar-brand" href="/index.jsp">BLB Website</a>
				<% } %>
			</div>

			<ul class="nav navbar-nav">
				<% if ( isLogged ) { %>
					<li><a href="<%= response.encodeURL( "/auth/welcome.jsp" ) %>" >Home</a></li>
					<li <%if (uriName.equals("/auth/user")) { out.print(" class='active' "); }%>><a href="<%= response.encodeURL( "user" ) %>">User</a></li>
					<li <%if (uriName.equals("/auth/student")) { out.print(" class='active' "); }%>><a href="<%= response.encodeURL( "student" ) %>">Student</a></li>
					<li <%if (uriName.equals("/auth/course")) { out.print(" class='active' "); }%>><a href="<%= response.encodeURL( "course" ) %>">Course</a></li>
				<% } else {%>
					<li class="active"><a href="/index.jsp" >Home</a></li>
				<% } %>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<% if ( isLogged ) { %>
					<li><a href="<%= response.encodeURL( "/auth/user?action=edit&userId=" + userId ) %>">
						<span class="glyphicon glyphicon-cog"></span> Hi <%= userName %></a>
					</li>
					<li><a href="<%= response.encodeURL( "logout" ) %>">
						<span class="glyphicon glyphicon-log-out"></span> Logout</a>
					</li>
				<% } else { %>
					<li><a href="/newuser.jsp">
						<span class="glyphicon glyphicon-user"></span> Sign Up</a>
					</li>
					<li><a href="/auth/login.jsp">
						<span class="glyphicon glyphicon-log-in"></span> Login</a>
					</li>
				<% } %>
			</ul>
		</div>
	</nav>
    <% out.print(uriName + " | isUser=" + uriName.equals("/auth/user") + " | isStudent=" + uriName.equals("/auth/student")); %>