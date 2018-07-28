<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% 
	String message = (String) request.getAttribute( "message" ); 
	User user 		= (User) request.getAttribute ( "user" );
%>

<jsp:include page="/auth/_top-page.jsp" />

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
			<h3>Forgot Password</h3>
            <form class="form-horizontal" data-toggle="validator" 
			role="form" action="/forgot" method="post">

                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
						<% if ( message != null && !message.isEmpty() ) { %>
							<p><font color=red><%= message %></font></p>
						<% } %>
                    </div>
                </div>

                <div class="form-group">
                    <label for="user-email" class="col-sm-4 control-label">Inform your email</label>
                    <div class="col-sm-8">
					
                        <input type="email" name="user-email" id="user-email" class="form-control" 
							<% if ( user != null ) { out.print( "value='" + user.getEmail() + "'" ); }%>
							placeholder="User email" maxlength="40" required
							data-error="Oops, that email address is invalid" 
						/>
						
						<div class="help-block with-errors"></div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button type="submit" class="btn btn-sm btn-primary">Send email</button>
					</div>
                </div>
				
            </form>
        </div>
    </div>
</div>

<jsp:include page="/auth/_botton-page.jsp" />
