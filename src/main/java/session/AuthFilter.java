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
                if ( session != null )
                {
                    session.setAttribute( "uri", req.getRequestURI() );     // for setting the active menu
                }
                
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
			e.printStackTrace();
			throw new ServletException( e );
        }
    }

    @Override
    public void destroy() 
	{
		this.context.log( "----- [AuthFilter] destroy -----" );
	}
}