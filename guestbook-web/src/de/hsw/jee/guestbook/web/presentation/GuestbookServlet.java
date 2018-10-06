package de.hsw.jee.guestbook.web.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hsw.jee.guestbook.web.model.GuestbookEntry;
import de.hsw.jee.guestbook.web.model.User;
import de.hsw.jee.guestbook.web.services.GuestbookService;
import de.hsw.jee.guestbook.web.services.Services;

/**
 * Servlet implementation class GuestbookServlet
 */
@WebServlet("/")
public class GuestbookServlet extends HttpServlet {
       
	private GuestbookService guestbookService = Services.getGuestbookService();	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		guestbookService.insert(
			User.class.cast(req.getSession().getAttribute("USER")),
			req.getParameter("title"),
			req.getParameter("message")
		);
		resp.sendRedirect(req.getContextPath() + "/guestbook");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final StringBuffer out = new StringBuffer()
			.append("<html><body><h1>Guestbook</h1>")
			.append(header(request))
			.append("<ul>");
		
		for(GuestbookEntry entry : guestbookService.getAll()) {
			out.append(this.entry(entry));
		}
		out.append("</ul>");
	
		final String action = request.getContextPath() + "/";
		
		out.append("<h2>HInterlasse eine Nachricht</h2>")
		 	.append("<form method=\"POST\" action=\"").append(action).append("\" >")
				
				.append("<label>Titel</label>")
				.append("<input type=\"text\" name=\"title\" /></br>")

				.append("<label>Nachricht</label>")
				.append("<textarea name=\"message\" ></textarea></br>")
				
				.append("<input type=\"submit\" name=\"Senden\" >")
			.append("</form>");

		out.append("</body></html>");
	
		response.getWriter().print(out.toString());
	}

	public String entry(GuestbookEntry entry) {
		return new StringBuffer()
				.append("<div>")
				.append("<h3>").append(entry.getTitle()).append("</h3>")
				.append("<span>Von: ").append(entry.getUser().getUsername()).append("</span>")
				.append("<p>").append(entry.getMessage()).append("</p>")
				.append("</div>")
				.toString();
	}
	
	private String header(HttpServletRequest request) {
		return "<div>Hallo, " + User.class.cast(request.getSession().getAttribute("USER")).getUsername() + "! "
				+ "<a href=\"?logout\">Logout</a></div>";
	}

}
