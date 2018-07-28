package main.java.session;

import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter( "/auth/*" )

@SuppressWarnings("serial")
public class AuthFilter implements Filter {
    
	private ServletContext context;
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException 
	{
		this.context = filterConfig.getServletContext();
		this.context.log( "----- [AuthFilter] init -----" );
	}

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) 
			throws IOException, ServletException 
	{
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;

			// ----------------------------- logging requested URI
			String uri = req.getRequestURI();
			this.context.log( ">>> [AuthFilter | BEGIN] Requested Resource: "+uri );

			// ----------------------------- checking HTML page reqeust OR session stablished OR login call
			HttpSession session = req.getSession(false);
			String loginURI = req.getContextPath() + "/auth/login";
			
			boolean loggedIn = session != null && session.getAttribute( "userName" ) != null;
			boolean loginRequest = req.getRequestURI().equals( loginURI );

			if ( loggedIn || loginRequest )
			{	// ---- IF default request -> redirect to welcome page
				chain.doFilter(req, res);			
				this.context.log( "<<< [AuthFilter | END] chaining the request" );
			}
			else 
			{	// ---- OTHERWISE -> Redirect to login servlet
				res.sendRedirect( loginURI );
				this.context.log( "<<< [AuthFilter | END] redirect to login" );
			}
		} 
		catch ( Throwable e ) 
		{
            logException( request, response, e );
        }
    }

    private void logException( ServletRequest request, ServletResponse response, Throwable e ) 
			throws IOException, ServletException 
	{
        HttpServletRequest req = ( HttpServletRequest ) request;
		
        System.err.println( "Request.RequestURL=" + req.getRequestURL() );
        System.err.println( "Request.UserPrincipal=" + req.getUserPrincipal() );
		
		e.printStackTrace();
        
		throw new ServletException( e.getMessage(), e );
	}
	
    @Override
    public void destroy() 
	{
		this.context.log( "----- [AuthFilter] destroy -----" );
	}
}