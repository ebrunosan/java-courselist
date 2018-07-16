<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%
	String userID = null;
	String userName = null;
	String sessionID = null;

	if ( session.getAttribute( "userName" ) == null )
	{
		response.sendRedirect( "login.html" );
	} else 
	{
		userName = (String) session.getAttribute( "userName" );
	}
	
	Cookie[] cookies = request.getCookies();
	if ( cookies != null )
	{
		for ( Cookie cookie : cookies )
		{
			if ( cookie.getName().equals( "userID" ) ) { userID = cookie.getValue(); }
			if ( cookie.getName().equals( "JSESSIONID" ) ) { sessionID = cookie.getValue(); }
		}
	} else 
	{
		sessionID = session.getId();
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>Login Success Page</title>
</head>
<body>
	<h3>Hi <%=userName %>, Login successful.</h3> 
	<p>Your Session ID [<%= sessionID %>] </p>
	<p>Your UserID [<%= userID %>] </p>

	<!-- need to encode all the URLs where we want session information to be passed -->
	<form action="<%= response.encodeURL("LogoutServlet") %>" method="post">
		<input type="submit" value="Logout" >
	</form>
</body>
</html>