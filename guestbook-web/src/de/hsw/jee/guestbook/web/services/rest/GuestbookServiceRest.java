package de.hsw.jee.guestbook.web.services.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.media.jfxmedia.Media;

import de.hsw.jee.guestbook.web.model.GuestbookEntry;
import de.hsw.jee.guestbook.web.model.User;
import de.hsw.jee.guestbook.web.services.GuestbookService;
import de.hsw.jee.guestbook.web.services.Services;
import de.hsw.jee.guestbook.web.services.UserService;

public class GuestbookServiceRest implements GuestbookService {

	public static final String BASE_URL = "http://localhost:8080/guestbook-rest/api";
	
	private final UserService userService = Services.getUserService();
	
	@Override
	public List<GuestbookEntry> getAll() {
		/*
		 * Lesen der Einträge erfolgt nicht über den Service, sondern per
		 * Rest-Service gegen das restbok-Projekt
		 */
		final Response response = ClientBuilder.newBuilder()
			.build()
			.target(BASE_URL + "/gb")
			.request()
			.accept(MediaType.APPLICATION_JSON)
			.get();
		
		final GenericType<List<GuestbookRestEntry>> type = new GenericType<List<GuestbookRestEntry>>() {};
		
		return response.readEntity(type).stream()
				.map(this::mapFromRestEntry)
				.collect(Collectors.toList());
	}

	private GuestbookEntry mapFromRestEntry(GuestbookRestEntry restEntry) {
		final User user = userService.findByUsername(restEntry.getUsername())
				.orElse(User.UNKONWN);
		
		final GuestbookEntry res = new GuestbookEntry();
		res.setUser(user);
		res.setTitle(restEntry.getTitle());
		res.setMessage(restEntry.getMessage());
		res.setDateCreated(restEntry.getCreated());
		return res;
	}
	
	@Override
	public GuestbookEntry insert(final User user, final String title, final String message) {
		final GuestbookRestEntry restEntry = new GuestbookRestEntry();
		restEntry.setUsername(user.getUsername());
		restEntry.setMessage(message);
		restEntry.setTitle(title);
		
		final Response response = ClientBuilder.newBuilder()
				.build()
				.target(BASE_URL + "/gb")
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(restEntry, MediaType.APPLICATION_JSON));
		
		return Optional.ofNullable(response.readEntity(GuestbookRestEntry.class))
				.map(this::mapFromRestEntry)
				.orElse(null);
	}

}
