package de.hsw.jee.guestbook.web.services;

import java.util.List;

import de.hsw.jee.guestbook.web.model.GuestbookEntry;
import de.hsw.jee.guestbook.web.model.User;

public interface GuestbookService {

	List<GuestbookEntry> getAll();
	
	GuestbookEntry insert(
			User user,
			String title,
			String message);
	
	
}
