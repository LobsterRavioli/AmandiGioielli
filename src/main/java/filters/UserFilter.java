package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserFilter implements Filter {

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
	
		String loginURI = hrequest.getContextPath() + "/user_pages";
		boolean loginRequest = hrequest.getRequestURI().startsWith(loginURI);
		if (loginRequest) {
		    System.out.println("Check role in the session");
		    // check the token from session
		    HttpSession session = hrequest.getSession(false);
		    boolean loggedIn = session != null && session.getAttribute("userRoles") != null;
	
		    if (!loggedIn) {
			System.out.println("Redirect to login form");
			hresponse.sendRedirect(hrequest.getContextPath() + "/common_pages/login_user.jsp");
		    } else {
			// admin resource
			chain.doFilter(request, response);
		    }
		} else {
		    // accessible resource
		    chain.doFilter(request, response);
		}
    }

    public void init(FilterConfig fConfig) throws ServletException {
    	System.out.println("Init the filter");
    }

}