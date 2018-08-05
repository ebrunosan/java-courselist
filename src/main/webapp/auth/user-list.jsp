<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.model.User,java.util.*" %>

<%
	List<User> listUsers = ( ArrayList<User> ) request.getAttribute ( "listUsers" );
%>

<jsp:include page="_top-page.jsp" />

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
			
			<h3>Users Management::List</h3>
			<div class="col-sm-offset-4 col-sm-8">
				<a href="<%= response.encodeURL( "/auth/user?action=new" ) %>" >
					<button type="button" class="btn">Add New User</button>
				</a>
				&nbsp;
				<a href="<%= response.encodeURL( "/auth/user?action=list" ) %>" >
					<button type="button" class="btn">List All Users</button>
				</a>
			</div>


			<table class="table table-striped">
				<caption><h2>List of Users</h2></caption>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>User email</th>
					<th>Actions</th>
				</tr>
				<% for ( User user : listUsers ) { %>
					<tr>
						<td><%= user.getFirstName() %></td>
						<td><%= user.getLastName() %></td>
						<td><%= user.getEmail() %></td>
						<td>
							<a href = "<%= response.encodeURL( "/auth/user?action=edit&userId=" + user.getUserId() ) %>" >
								<button type="button" class="btn">Edit</button>
							</a>
							&nbsp;
							<a href = "<%= response.encodeURL( "/auth/user?action=delete&userId=" + user.getUserId() ) %>" 
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
