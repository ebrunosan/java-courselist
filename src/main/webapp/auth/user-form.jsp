<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.User,java.util.*" %>

<%
	String message 	= ( String ) request.getAttribute ( "message" );
	User user 		= ( User ) request.getAttribute ( "user" );
	
	boolean editMode;
	if ( user != null && user.getUserId() > 0 )
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
			
			<h3>Users Management::
				<% if ( editMode ) { %>Edit<% } else { %>Add New<% } %>
			</h3>

			<form class="form-horizontal" data-toggle="validator" role="form" 
				<% if ( editMode ) { %>
					action="<%= response.encodeURL( "/auth/user?action=update" ) %>" method="post">
				<% } else { %>
					action="<%= response.encodeURL( "/auth/user?action=insert" ) %>" method="post">
				<% } %>
			
				<% if ( editMode ) { %>
					<input type="hidden" name="userId" value="<%= user.getUserId() %>" />
				<% } %>

                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
						<a href="<%= response.encodeURL( "/auth/user?action=new" ) %>" >
							<button type="button" class="btn">Add New User</button>
						</a>
						&nbsp;
						<a href="<%= response.encodeURL( "/auth/user?action=list" ) %>" >
							<button type="button" class="btn">List All Users</button>
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
                    <label for="userEmail" class="col-sm-4 control-label">Email</label>
                    <div class="col-sm-8">
					
                        <input type="email" name="userEmail" id="userEmail" class="form-control" 
							<% if ( user != null ) { out.print( "value='" + user.getEmail() + "'" ); }%>
							placeholder="User email" maxlength="40" required
							data-error="Oops, that email address is invalid" 
						/>
						
						<div class="help-block with-errors"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="firstName" class="col-sm-4 control-label">First name</label>
                    <div class="col-sm-8">
					
						<input type="text" name="firstName" id="firstName" class="form-control" 
							<% if ( user != null ) { out.print( "value='" + user.getFirstName() + "'" ); }%>
							placeholder="First name" data-minlength="4"  maxlength="20" required
						/>
						
						<div class="help-block with-errors"></div>
                    </div>
                </div>
				
                <div class="form-group">
                    <label for="lastName" class="col-sm-4 control-label">Last name</label>
                    <div class="col-sm-8">
					
						<input type="text" name="lastName" id="lastName" class="form-control" 
							<% if ( user != null ) { out.print( "value='" + user.getLastName() + "'" ); }%>
							placeholder="First name" data-minlength="4"  maxlength="30" required
						/>
						
						<div class="help-block with-errors"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="pass" class="col-sm-4 control-label">Password</label>
                    <div class="col-sm-8">
					
						<input type="password" name="pass" id="pass" class="form-control" 
							<% if ( user != null ) { out.print( "value='" + user.getPass() + "'" ); }%>
							placeholder="User password" data-minlength="4"  maxlength="8" required
						/>
						
						<div class="help-block with-errors">Minimum of 4 characters</div>
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
</div> <!-- container -->

<jsp:include page="_botton-page.jsp" />