<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.Course,java.util.*" %>

<%
	String message 	= ( String ) request.getAttribute ( "message" );
	Course course = (Course) request.getAttribute ("course");
	
	boolean editMode;
	if ( course != null && course.getCourseCode() > 0 )
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
		
			<h3>Courses Management ::
				<% if ( editMode ) { %>Edit<% } else { %>Add New<% } %>
			</h3>
			
			<form class="form-horizontal" data-toggle="validator" role="form" 
				<% if ( editMode ) { %>
					action="<%= response.encodeURL( "/auth/course?action=update" ) %>" method="post">
				<% } else { %>
					action="<%= response.encodeURL( "/auth/course?action=insert" ) %>" method="post">
				<% } %>
			
				<% if ( editMode ) { %>
					<input type="hidden" name="courseCode" value="<%= course.getCourseCode() %>" />
				<% } %>
				
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-8">
						<a href="<%= response.encodeURL( "/auth/course?action=new" ) %>" >
						<button type="button" class="btn">Add New Course/Program</button>
						</a>
						&nbsp;
						<a href="<%= response.encodeURL( "/auth/course?action=list" ) %>" >
						<button type="button" class="btn">List All Courses/Programs</button>
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
					<label for="courseName" class="col-sm-4 control-label">Course Name:</label>
					<div class="col-sm-8">
						<input type="text" name="courseName" size="50" class="form-control"
							<% if (course != null) { out.print( "value='" + course.getCourseName() +"'" ); } %> 
							placeholder="Course Name" data-minlength="1"  maxlength="50" required
						/>
						<div class="help-block with-errors"></div>
					</div>
				</div>
				
				<div class="form-group">
					<label for="duration" class="col-sm-4 control-label">Duration:</label>
					<div class="col-sm-8">
							<input type="text" name="duration" size="20" class="form-control"
								<% if (course != null) { out.print( "value='" + course.getDuration() + "'" ); } %>
								placeholder="Duration" data-minlength="1"  maxlength="20" required
							/>
						<div class="help-block with-errors"></div>
					</div>
				</div>
					
				<div class="form-group">
					<label for="description" class="col-sm-4 control-label">Description:</label>
					<div class="col-sm-8">
							<input type="text" name="description" size="50" class="form-control"
								<% if (course != null) { out.print( "value='" + course.getDescription() + "'" ); } %>
								placeholder="Description" data-minlength="1"  maxlength="50" required
							/>
						<div class="help-block with-errors"></div>
					</div>
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
</div> <!--container -->
	
<jsp:include page="_botton-page.jsp" />
