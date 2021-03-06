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

	<title>BLB Company</title>
</head>

<%
	String sessionID 	= session.getId();
	String userName 	= (String) session.getAttribute( "userName" );
	String userId 		= (String) session.getAttribute( "userId" );

    boolean isLogged 	= ( userName != null );

    String homeMenuActive     = "";
    String userMenuActive     = "";
    String studentMenuActive  = "";
    String courseMenuActive   = "";
   
    String CLASS_ACTIVE       = " class='active' ";
   
	String uriName 		      = (String) session.getAttribute( "uri" );

    if (uriName == null) { uriName = ""; }
   
    switch ( uriName ) {
        case "/auth/user":
            userMenuActive = CLASS_ACTIVE;
            break;
        case "/auth/student":
            studentMenuActive = CLASS_ACTIVE;
            break;
        case "/auth/course":
            courseMenuActive = CLASS_ACTIVE;
            break;
        default:
            homeMenuActive = CLASS_ACTIVE;
            break;
    }
%>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<% if ( isLogged ) { %>
					<a class="navbar-brand" href="<%= response.encodeURL( "/auth/welcome.jsp" ) %>">BLB Company</a>
				<% } else {%>
					<a class="navbar-brand" href="/index.jsp">BLB Company</a>
				<% } %>
			</div>

			<ul class="nav navbar-nav">
				<% if ( isLogged ) { %>
					<li <%= homeMenuActive %>><a href="<%= response.encodeURL( "/auth/welcome.jsp" ) %>" >Home</a></li>
					<li <%= userMenuActive %>><a href="<%= response.encodeURL( "user" ) %>">User</a></li>
					<li <%= studentMenuActive %>><a href="<%= response.encodeURL( "student" ) %>">Student</a></li>
					<li <%= courseMenuActive %>><a href="<%= response.encodeURL( "course" ) %>">Course</a></li>
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