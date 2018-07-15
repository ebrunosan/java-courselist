<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%
	//allow access only if session exists
	String user = null;
	if ( session.getAttribute( "userName" ) == null )
	{
		response.sendRedirect( "login.html" );
	} else 
	{
		userName = (String) session.getAttribute( "userName" );
	}
	
	String userName = null;
	String sessionID = null;
	
	Cookie[] cookies = request.getCookies();
	
	if ( cookies !=null )
	{
		for ( Cookie cookie : cookies )
		{
			if( cookie.getName().equals( "user" ) ) { user = cookie.getValue(); }
			if( cookie.getName().equals( "JSESSIONID" ) ) { sessionID = cookie.getValue(); }
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
	<h3>Hi <%=userName %>, Login successful. Your Session ID=<%=sessionID %></h3>
	<br>
	User=<%=user %>
	<br>

	<!-- need to encode all the URLs where we want session information to be passed -->
	<form action="<%=response.encodeURL("LogoutServlet") %>" method="post">
		<input type="submit" value="Logout" >
	</form>
</body>
</html>