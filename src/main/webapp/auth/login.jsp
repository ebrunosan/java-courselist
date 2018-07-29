<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% 
	String message = (String) request.getAttribute( "message" ); 
%>

<jsp:include page="_top-page.jsp" />

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
			<h3>Login</h3>
            <form class="form-horizontal" data-toggle="validator" 
			role="form" action="/auth/login" method="post">
			
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
						<% if ( message != null && !message.isEmpty() ) { %>
							<p><font color=red><%= message %></font></p>
						<% } %>
                    </div>
                </div>

                <div class="form-group">
                    <label for="user-email" class="col-sm-4 control-label">User email</label>
                    <div class="col-sm-8">
					
                        <input type="email" name="user-email" id="user-email" class="form-control" 
							placeholder="User email" maxlength="40" required
							data-error="Oops, that email address is invalid" 
						/>
						
						<div class="help-block with-errors"></div>
                    </div>
                </div>
				
                <div class="form-group">
                    <label for="user-pwd" class="col-sm-4 control-label">Password</label>
                    <div class="col-sm-8">
					
						<input type="password" name="user-pwd" id="user-pwd" class="form-control" 
							placeholder="User password" data-minlength="4"  maxlength="8" required
						/>
						
						<div class="help-block with-errors">Minimum of 4 characters</div>
                    </div>
                </div>
				
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button type="submit" class="btn btn-sm btn-primary">Login</button>
						&nbsp
						<a href="/newuser.jsp">Create account</a> or <a href="/forgotpswd.jsp">Forgot password</a>
					</div>
                </div>
				
            </form>
        </div>
    </div>
</div>

<jsp:include page="_botton-page.jsp" />
