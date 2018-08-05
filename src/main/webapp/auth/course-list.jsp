<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.CourseProgram,java.util.*" %>

<%
	List<CourseProgram> listCourses = ( ArrayList<CourseProgram> ) request.getAttribute ( "listCourses" );
%>

<jsp:include page="_top-page.jsp" />

<div class="container-fluid">
	<div class="row">
        <div class="col-sm-offset-2 col-sm-8">
		
		<h3>Courses Management::List</h3>
		<div class="col-sm-offset-4 col-sm-8">
			<a href="<%= response.encodeURL( "/auth/course?action=new" ) %>" >
				<button type="button" class="btn">Add New Course</button>
			</a>
			&nbsp;
			<a href="<%= response.encodeURL( "/auth/course?action=list" ) %>" >
				<button type="button" class="btn">List All Courses</button>
			</a>
		</div>

		<table class="table table-striped">
			<caption><h2>List of Courses</h2></caption>
			<tr>
				<th>Course Name</th>
				<th>Duration</th>
				<th>Description</th>
				<th>Actions</th>
			</tr>
			<% for ( CourseProgram course : listCourses ) { %>
				<tr>
					<td><%= course.getCourseName() %></td>
					<td><%= course.getDuration() %></td>
					<td><%= course.getDescription() %></td>
					<td>
						<a href = "<%= response.encodeURL( "/auth/course?action=edit&courseCode=" + course.getCourseCode() ) %>" >Edit</a>
						&nbsp;
						<a href = "<%= response.encodeURL( "/auth/course?action=delete&courseCode=" + course.getCourseCode() ) %>" 
							onclick="return confirm('Are you sure you want to delete?')">
							<button type="button" class="btn">Delete</button>
						</a>                     
					</td>
				</tr>
			<% } %>
		</table>
		</div> <!-- col -->
    </div> <!-- row -->
</div>  <!-- container -->

<jsp:include page="_botton-page.jsp" />
