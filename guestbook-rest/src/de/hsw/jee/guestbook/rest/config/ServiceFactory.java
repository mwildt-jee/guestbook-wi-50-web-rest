package de.hsw.jee.guestbook.rest.config;

import de.hsw.jee.guestbook.rest.config.BeanHolder;
import de.hsw.jee.guestbook.rest.service.GuestbookService;

public class ServiceFactory {
	
	public static final BeanHolder<GuestbookService> guestbookService = BeanHolder.of(GuestbookService::new);

	public static GuestbookService getGuestbookService() {
		return guestbookService.get();
	}
	
}
