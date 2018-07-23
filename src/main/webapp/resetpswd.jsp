<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% 
	String message = (String) request.getAttribute( "message" ); 
%>

<jsp:include page="/auth/_top-page.jsp" />

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
			<h3>Reset Password</h3>
            <form class="form-horizontal" data-toggle="validator" 
			role="form" action="/reset" method="post">
			
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
						<% if ( message != null && !message.isEmpty() ) { %>
							<p><font color=red><%= message %></font></p>
						<% } %>
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
                    <label for="conf-pwd" class="col-sm-4 control-label">Confirm</label>
                    <div class="col-sm-8">
					
						<input type="password" name="conf-pwd" id="conf-pwd" class="form-control" 
						placeholder="Confirm password" data-minlength="4"  maxlength="8" required
						/>
						
						<div class="help-block with-errors">Minimum of 4 characters</div>
                    </div>
                </div>
				
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button type="submit" class="btn btn-sm btn-primary">Reset</button>
					</div>
                </div>
				
            </form>
        </div>
    </div>
</div>

<jsp:include page="/auth/_botton-page.jsp" />
