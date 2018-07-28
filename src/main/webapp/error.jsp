<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="/auth/_top-page.jsp" />

<div class="container-fluid">
<%
	Integer statusCode 	= ( Integer ) request.getAttribute( "javax.servlet.error.status_code" );
	String servletName 	= ( String ) request.getAttribute( "javax.servlet.error.servlet_name" );
	String reqUri 		= ( String ) request.getAttribute( "javax.servlet.error.req_uri" );
	Throwable throwable = ( Throwable ) request.getAttribute( "javax.servlet.error.exception" );

	if ( servletName == null ) { servletName = "Unknown"; }
	if ( reqUri == null ) { reqUri = "Unknown"; }

	if ( throwable == null && statusCode == null ) 
	{
		out.print( "<h3>Error Information Is Missing</h3>");			
	} 
	else if ( statusCode != 500 ) 
	{
		out.print( "<h3>Error Details</h3>");
		out.print( "<ul><li><strong>Status Code</strong> = "+ statusCode + "</li>");
		out.print( "<li><strong>Requested URI</strong> = "+ reqUri + "</li></ul>");
	} 
	else 
	{
		out.print( "<h3>Exception Details</h3>" );
		out.print( "<ul><li><strong>Servlet Name</strong> = " + servletName + "</li>" );
		out.print( "<li><strong>Exception Name</strong> = " + throwable.getClass( ).getName( ) + "</li>" );
		out.print( "<li><strong>Requested URI</strong> = " + reqUri + "</li>" );
		out.print( "<li><strong>Exception Message</strong> = " + throwable.getMessage( ) + "</li></ul>" );
	}
%>
</div>

<jsp:include page="/auth/_botton-page.jsp" />
