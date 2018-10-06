package de.hsw.jee.guestbook.web.presentation;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthFilter
 * 
 * Dieser Filter übernimmt die Rolle der Zugriffskontrolle für alle Servlets in Abhängigkeit eine erfolgten Logins.
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		final HttpServletRequest httpRequest = HttpServletRequest.class.cast(request);
		// Wenn ein user eingeloggt ist, sind alle Pfad OK
		if (null != httpRequest.getSession().getAttribute("USER")) {
			if(null != httpRequest.getParameter("logout")) {
				// Wenn der Paremeter Lgout existiert, wird ein logout durchgeführt
				httpRequest.getSession().removeAttribute("USER");
				httpRequest.getSession().invalidate();
				HttpServletResponse.class.cast(response).sendRedirect(httpRequest.getContextPath() + "/auth");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			/*
			 * Falls kein Login stattgefunden hat, ist nur der auth-Pfad erlaubt, andernfalls erfolgt ein redirect.
			 */
			if(httpRequest.getServletPath().equals("/auth")) {
				chain.doFilter(request, response);
			} else {
				HttpServletResponse.class.cast(response).sendRedirect(httpRequest.getContextPath() + "/auth");
			}
		}
			
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
