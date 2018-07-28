<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.CourseProgram,java.util.*" %>

<%
	List<CourseProgram> listCourses = ( ArrayList<CourseProgram> ) request.getAttribute ( "listCourses" );
%>

<jsp:include page="_top-page.jsp" />

<div class="container">
	<h1>Courses Management</h1>
	<h2>
		<a href="<%= response.encodeURL( "/auth/course?action=new" ) %>" >Add New Course</a>
		&nbsp;
		<a href="<%= response.encodeURL( "/auth/course?action=list" ) %>" >List All Courses</a>
	</h2>

	<table border="1" cellpadding="5">
		<caption><h2>List of Courses</h2></caption>
		<tr>
			<th>Course Code</th>
			<th>Course Name</th>
			<th>Duration</th>
			<th>Description</th>
			<th>Actions</th>
		</tr>
		<% for ( CourseProgram course : listCourses ) { %>
			<tr>
				<td><%= course.getCourseCode() %></td>
				<td><%= course.getCourseName() %></td>
				<td><%= course.getDuration() %></td>
				<td><%= course.getDescription() %></td>
				<td>
					<a href = "<%= response.encodeURL( "/auth/course?action=edit&courseCode=" + course.getCourseCode() ) %>" >Edit</a>
					&nbsp;
					<a href = "<%= response.encodeURL( "/auth/course?action=delete&courseCode=" + course.getCourseCode() ) %>" >Delete</a>                     
				</td>
			</tr>
		<% } %>
	</table>
</div>   

<jsp:include page="_botton-page.jsp" />
