<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.*,java.util.*" %>

<% 
	List<Student> listStudents = (ArrayList<Student>) request.getAttribute ("listStudents");
%>
<jsp:include page="_top-page.jsp" />
 
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
			
			<h3>Students List</h3>
			<div class="col-sm-offset-4 col-sm-8">
				<a href="<%= response.encodeURL( "/auth/student?action=new" ) %>" >Add New Student</a>
                &nbsp;
                <a href="<%= response.encodeURL( "/auth/student?action=list" ) %>" >List All Students</a>
			</div>


			<table class="table table-striped">
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
							<a href = "<%= response.encodeURL( "/auth/student?action=delete&studentId=" + student.getStudentId() ) %>" 
								onclick="return confirm('Are you sure you want to delete?')">
								<button type="button" class="btn">Delete</button>
							</a>                     
						</td>
					</tr>
				<% } %>
			</table>
        </div> <!-- col -->
    </div> <!-- row -->
</div> <!-- container -->


<jsp:include page="_botton-page.jsp" />
