<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet" />
	<link href="https://cdnjs.cloudflare.com/ajax/libs/hyperform/0.9.9/hyperform.css" rel="stylesheet" />
	<!-- <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet" /> -->
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.js"></script>

	<title>:: SYSTEM NAME - To Be Changed ::</title>
</head>

<%
	String sessionID 	= session.getId();
	String userName 	= (String) session.getAttribute( "userName" );
	String userId 		= (String) session.getAttribute( "userId" );

	boolean isLogged 	= ( userName != null );
%>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<% if ( isLogged ) { %>
					<a class="navbar-brand" href="<%= response.encodeURL( "/auth/welcome.jsp" ) %>">:: WebSiteName ::</a>
				<% } else {%>
					<a class="navbar-brand" href="/auth/welcome.jsp">:: WebSiteName ::</a>
				<% } %>
			</div>

			<ul class="nav navbar-nav">
				<% if ( isLogged ) { %>
					<li class="active"><a href="<%= response.encodeURL( "/auth/welcome.jsp" ) %>" >Home</a></li>
					<li><a href="<%= response.encodeURL( "user" ) %>">User</a></li>
					<li><a href="<%= response.encodeURL( "student" ) %>">Student</a></li>
					<li><a href="<%= response.encodeURL( "course" ) %>">Course</a></li>
				<% } else {%>
					<li class="active"><a href="/welcome.jsp" >Home</a></li>
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
					<li><a href="/auth/user-form.jsp">
						<span class="glyphicon glyphicon-user"></span> Sign Up</a>
					</li>
					<li><a href="/auth/login.jsp">
						<span class="glyphicon glyphicon-log-in"></span> Login</a>
					</li>
				<% } %>
			</ul>
		</div>
	</nav>