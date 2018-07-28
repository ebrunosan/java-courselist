<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% 
	String message 		= (String) request.getAttribute( "message" ); 
	String userEmail 	= (String) request.getAttribute( "userEmail" ); 
	String firstName 	= (String) request.getAttribute( "firstName" ); 
	String lastName 	= (String) request.getAttribute( "lastName" ); 
%>

<jsp:include page="/auth/_top-page.jsp" />

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
			<h3>Register new user</h3>
            <form class="form-horizontal" data-toggle="validator" 
			role="form" action="/newuser" method="post">

                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
						<% if ( message != null && !message.isEmpty() ) { %>
							<p><font color=red><%= message %></font></p>
						<% } %>
                    </div>
                </div>

                <div class="form-group">
                    <label for="userEmail" class="col-sm-4 control-label">Your email</label>
                    <div class="col-sm-8">
					
                        <input type="email" name="userEmail" id="userEmail" class="form-control" 
							<% if ( userEmail != null) { out.print( "value='" + userEmail + "'" ); }%>
							placeholder="User email" maxlength="40" required
							data-error="Oops, that email address is invalid" 
						/>
						
						<div class="help-block with-errors"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="firstName" class="col-sm-4 control-label">Your first name</label>
                    <div class="col-sm-8">
					
						<input type="text" name="firstName" id="firstName" class="form-control" 
							<% if ( firstName != null) { out.print( "value='" + firstName + "'" ); }%>
							placeholder="First name" data-minlength="4"  maxlength="20" required
						/>
						
						<div class="help-block with-errors"></div>
                    </div>
                </div>
				
                <div class="form-group">
                    <label for="lastName" class="col-sm-4 control-label">Your last name</label>
                    <div class="col-sm-8">
					
						<input type="text" name="lastName" id="lastName" class="form-control" 
							<% if ( lastName != null) { out.print( "value='" + lastName + "'" ); }%>
							placeholder="First name" data-minlength="4"  maxlength="30" required
						/>
						
						<div class="help-block with-errors"></div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button type="submit" class="btn btn-sm btn-primary">Submit</button>
					</div>
                </div>
				
            </form>
        </div>
    </div>
</div>

<jsp:include page="/auth/_botton-page.jsp" />
