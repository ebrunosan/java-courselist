<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.CourseProgram,java.util.*" %>

<%
	CourseProgram course = (CourseProgram) request.getAttribute ("course");
%>

<jsp:include page="_top-page.jsp" />

<div class="container">
	<h1>Courses/Programs</h1>
	<h2>
		<a href="<%= response.encodeURL( "/auth/course?action=new" ) %>" >Add New Course/Program</a>
		&nbsp;
		<a href="<%= response.encodeURL( "/auth/course?action=list" ) %>" >List All Courses/Programs</a>
	</h2>

	<% if (course != null) { %>
		<form action="<%= response.encodeURL( "/auth/course?action=update" ) %>" method="post">
	<% } else { %>
		<form action="<%= response.encodeURL( "/auth/course?action=insert" ) %>" method="post">
	<% } %>
	
	<table border="1" cellpadding="5">
		<caption>
			<h2>
				<% if (course != null) { %>Edit Course<% } else { %>Add New Course<% } %>
			</h2>
		</caption>
		<% if (course != null) { %>
			<input type="hidden" name="courseCode" value="<%= course.getCourseCode() %>" />
		<% } %>
		<tr>
			<th>Course name: </th>
			<td>
				<input type="text" name="courseName" size="50"
					<% if (course != null) { out.print( "value='" + course.getCourseName() +"'" ); } %>
				/>
			</td>
		</tr>
		<tr>
			<th>Duration: </th>
			<td>
				<input type="text" name="duration" size="50"
					<% if (course != null) { out.print( "value='" + course.getDuration() + "'" ); } %>
				/>
			</td>
		</tr>
		<tr>
			<th>Description: </th>
			<td>
				<input type="text" name="description" size="50"
					<% if (course != null) { out.print( "value='" + course.getDescription() + "'" ); } %>
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
