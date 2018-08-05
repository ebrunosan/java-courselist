<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.*,java.util.*" %>

<%
	String message 	= ( String ) request.getAttribute ( "message" );
	Student student = (Student) request.getAttribute ("student");
	List<CourseProgram> listCourses = ( ArrayList<CourseProgram> ) request.getAttribute ( "listCourses" );
	
	boolean editMode;
	if ( student != null && student.getStudentId() > 0 )
	{
		editMode = true;
	}
	else
	{
		editMode = false;
	}
%>

<jsp:include page="_top-page.jsp" />

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
			
			<h3>Student profile ::
				<% if ( editMode ) { %>Edit<% } else { %>Add New<% } %>
			</h3>

			<form class="form-horizontal" data-toggle="validator" role="form" 
				<% if ( editMode ) { %>
					action="<%= response.encodeURL( "/auth/student?action=update" ) %>" method="post">
				<% } else { %>
					action="<%= response.encodeURL( "/auth/student?action=insert" ) %>" method="post">
				<% } %>
			
				<% if ( editMode ) { %>
					<input type="hidden" name="studentId" value="<%= student.getStudentId() %>" />
				<% } %>

                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
						<a href="<%= response.encodeURL( "/auth/student?action=new" ) %>" >
							<button type="button" class="btn">Add New Student</button>
						</a>
						&nbsp;
						<a href="<%= response.encodeURL( "/auth/student?action=list" ) %>" >
							<button type="button" class="btn">List All Students</button>
						</a>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
						<% if ( message != null && !message.isEmpty() ) { %>
							<p><font color=red><%= message %></font></p>
						<% } %>
                    </div>
                </div>

                <div class="form-group">
                    <label for="studentName" class="col-sm-4 control-label">Student Name:</label>
                    <div class="col-sm-8">
					
                        <input type="text" name="name" size="50" class="form-control"
							<% if (student != null) { out.print( "value='" + student.getName() +"'" ); } %>
							placeholder="Name" data-minlength="1"  maxlength="50" required
						/>
						
						<div class="help-block with-errors"></div>
					</div>
					
                </div>

                <div class="form-group">
                    <label for="age" class="col-sm-4 control-label">Age</label>
                    <div class="col-sm-8">
					
						<input type="number" name="age" size="50" class="form-control"
							<% if (student != null) { out.print( "value='" + student.getAge() +"'" ); } %>
							placeholder="Age" data-minlength="1" maxlength="3"  required
						/>
						
						<div class="help-block with-errors"></div>
					</div>
                </div>
				
                <div class="form-group">
                    <label for="gender" class="col-sm-4 control-label">Gender:</label>
                    <div class="col-sm-8">
					
						<input type="text" name="gender" size="50" class="form-control"
							<% if (student != null) { out.print( "value='" + student.getGender() +"'" ); } %>
							placeholder="Gender" data-minlength="1"  maxlength="10" required
						/>
						
						<div class="help-block with-errors"></div>
					</div>
                </div>

                <div class="form-group">
                    <label for="country" class="col-sm-4 control-label">Country:</label>
                    <div class="col-sm-8">
					
						<input type="text" name="country" size="50" class="form-control"
							<% if (student != null) { out.print( "value='" + student.getCountry() +"'" ); } %>
							placeholder="Country" data-minlength="1" maxlength="150"  required
						/>
						<div class="help-block with-errors"></div>
					</div>
				</div>

				<div class="form-group">
                    <label for="course" class="col-sm-4 control-label">Course:</label>
                    <div class="col-sm-8">
						<select name="course_id" class="form-control">
							<% for(CourseProgram course : listCourses) { %>
							<option <% out.print( "value='" + course.getCourseCode() +"'" ); %> > 
								<% out.print( course.getCourseName() ); %>
							</option>
							<% } %>
						</select>
					</div>
					<div class="help-block with-errors"></div>
				</div>
	
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button type="submit" class="btn btn-sm btn-primary">Save</button>
						&nbsp
                        <button type="reset" class="btn btn-sm btn-primary">Reset</button>
					</div>
                </div>
				
            </form>
			
        </div> <!-- col -->
    </div> <!-- row -->
</div> <!-- container -->

	
<jsp:include page="_botton-page.jsp" />
