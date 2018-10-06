package de.hsw.jee.guestbook.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import de.hsw.jee.guestbook.web.model.User;
import de.hsw.jee.guestbook.web.services.GuestbookService;
import de.hsw.jee.guestbook.web.services.Services;
import de.hsw.jee.guestbook.web.services.UserException;
import de.hsw.jee.guestbook.web.services.UserService;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
	
	private final UserService userService = Services.getUserService();
	private final GuestbookService guestbookService = Services.getGuestbookService();
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			User admin = userService.register("admin", "admin");
			User user = userService.register("user", "user");
//			guestbookService.insert(admin, "Der erste Eintrag", "Hallo Leute! Ich bins, der Admin");
//			guestbookService.insert(user, "Gruß", "Auch der user wünsch alle Gute.");
		} catch (UserException e) {
			throw new RuntimeException(e);
		}
	}

}
