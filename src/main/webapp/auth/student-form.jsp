<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.*" %>

<%
	Student student = (Student) request.getAttribute ("student");
%>

<jsp:include page="_top-page.jsp" />


<div class="container">
	<h1>Student Form</h1>
	<h2>
		<a href="<%= response.encodeURL( "/auth/student?action=new" ) %>" >Add New Student</a>
		&nbsp;
		<a href="<%= response.encodeURL( "/auth/student?action=list" ) %>" >List All Students</a>
	</h2>

	<% if (student != null) { %>
		<form action="<%= response.encodeURL( "/auth/student?action=update" ) %>" method="post">
	<% } else { %>
		<form action="<%= response.encodeURL( "/auth/student?action=insert" ) %>" method="post">
	<% } %>
	
	<table border="1" cellpadding="5">
		<caption>
			<h2>
				<% if (student != null) { %>Edit Student<% } else { %>Add New Student<% } %>
			</h2>
		</caption>
		<% if (student != null) { %>
			<input type="hidden" name="studentId" value="<%= student.getStudentId() %>" />
		<% } %>
		<tr>
			<th>Student name: </th>
			<td>
				<input type="text" name="name" size="50"
					<% if (student != null) { out.print( "value='" + student.getName() +"'" ); } %>
				/>
			</td>
        </tr>
        
        <tr>
			<th>Age: </th>
			<td>
				<input type="text" name="age" size="50"
					<% if (student != null) { out.print( "value='" + student.getAge() +"'" ); } %>
				/>
			</td>
        </tr>

        <tr>
			<th>Gender: </th>
			<td>
				<input type="text" name="gender" size="50"
					<% if (student != null) { out.print( "value='" + student.getGender() +"'" ); } %>
				/>
			</td>
        </tr>

        <tr>
			<th>Country: </th>
			<td>
				<input type="text" name="country" size="50"
					<% if (student != null) { out.print( "value='" + student.getCountry() +"'" ); } %>
				/>
			</td>
		</tr>
		
		<tr>
			<th>Course:</th>
			<td>
				<select name="course_id">
					<option value="1">CSAT</option>
				</select>
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
