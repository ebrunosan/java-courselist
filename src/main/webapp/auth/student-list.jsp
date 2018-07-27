<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.*,java.util.*" %>

<% 
	List<Student> listStudents = (ArrayList<Student>) request.getAttribute ("listStudents");
%>
<jsp:include page="_top-page.jsp" />
<div class="container">
	<h1>Students Management</h1>
	<h2>
		<a href="<%= response.encodeURL( "/auth/student?action=new" ) %>" >Add New Student</a>
		&nbsp;
		<a href="<%= response.encodeURL( "/auth/student?action=list" ) %>" >List All Students</a>
	</h2>

	<table border="1" cellpadding="5">
        <caption><h2>List of Students</h2></caption>
        <tr>
            <th>Name</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        <% for(Student student : listStudents) { %>
            <tr>
                
                <td><%out.print(student.getName()); %></td>
                <td><%out.print(student.getAge()); %></td>
                <td><%out.print(student.getGender()); %></td>
                <td><%out.print(student.getCountry()); %></td>
                <td>
                    <a href = "<%= response.encodeURL( "/auth/student?action=edit&studentId=" + student.getStudentId() ) %>" >Edit</a>
					&nbsp;
					<a href = "<%= response.encodeURL( "/auth/student?action=delete&studentId=" + student.getStudentId() ) %>" >Delete</a>                     
                </td>
            </tr>
        <% } %>
    </table>
</div>   

<jsp:include page="_botton-page.jsp" />
