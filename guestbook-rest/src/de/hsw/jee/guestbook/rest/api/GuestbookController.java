package de.hsw.jee.guestbook.rest.api;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hsw.jee.guestbook.rest.config.ServiceFactory;
import de.hsw.jee.guestbook.rest.model.GuestbookEntry;
import de.hsw.jee.guestbook.rest.service.GuestbookService;

@Path("/gb")
public class GuestbookController {

	private final GuestbookService guestbookService = ServiceFactory.getGuestbookService();
	
	/**
	 * Lesen aller eintraege
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<GuestbookEntry> index(){
		System.out.println("GuestbookControlle::index");
		return guestbookService.findAll();
	}
	
	/**
	 * Controller Methode für das Anlegen eines neuen Eintrags
	 * @param entry
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public GuestbookEntry insert(final GuestbookEntry entry) {
		System.out.println("GuestbookControlle::insert");
		return guestbookService.save(entry);
	}
	
	/**
	 * Controller Methode für das Löschen eines Eintrags
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		System.out.println("GuestbookControlle::delete " + id);
		final Optional<GuestbookEntry> entry = guestbookService.findById(id);
		if (entry.isPresent()) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}
	
}
