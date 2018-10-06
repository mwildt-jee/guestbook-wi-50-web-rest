package de.hsw.jee.guestbook.web.presentation;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hsw.jee.guestbook.web.model.User;
import de.hsw.jee.guestbook.web.services.Services;
import de.hsw.jee.guestbook.web.services.UserService;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private final UserService userService = Services.getUserService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String form = "<html><head></head><body>"
				+ "<h1>Login</h1>"
					+ "<form action=\"" + request.getContextPath() + "/auth\" method=\"POST\">"
					+ "<label>Benutzernname</label><input type=\"text\" name=\"username\" />"
					+ "<label>Password</label><input type=\"passord\" name=\"password\" />"
					+ "<input type=\"submit\" />"
				+ "</form>"
				+ "</body></html>";
		response.getWriter().println(form);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String username = request.getParameter("username");
		final Optional<User> user = this.userService.findByUsername(username);
		
		if(!user.isPresent()) {
			this.doGet(request, response);
		} else {
			final String password = request.getParameter("password");
			if (this.userService.checkPassword(user.get(), password)) {
				request.getSession().setAttribute("USER", user.get());
				response.sendRedirect(request.getContextPath() + "/");
			} else {
				this.doGet(request, response);
			}	
		}
		
	}

}
