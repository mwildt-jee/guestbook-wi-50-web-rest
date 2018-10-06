package de.hsw.jee.guestbook.rest.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import de.hsw.jee.guestbook.rest.model.GuestbookEntry;

public class GuestbookService {

	private final Map<Long, GuestbookEntry> data = new HashMap<>();
	private final AtomicLong sequence = new AtomicLong();
	
	/**
	 * List alle Produkte
	 * @return
	 */
	public List<GuestbookEntry> findAll() {
		return Collections.unmodifiableList(new ArrayList<>(data.values()));
	}
	
	/**
	 * erzeugt die Id für das nächste Produkt.
	 * @return
	 */
	private Long nextId() {
		return sequence.incrementAndGet();
	}
	
	/**
	 * Speichern oder Update eines GuestbookEntry. Beim Speichern wird eine Id vergebem (siehe nextid)
	 * @param GuestbookEntry entry
	 * @return
	 */
	public GuestbookEntry save(final GuestbookEntry entry) {
		if (entry.getId() == null) {
			entry.setId(this.nextId());
			entry.prePersist();
		} else {
			entry.preUpdate();
		}
		this.data.put(entry.getId(), entry);
		return entry;
	}
	
	
	/**
	 * Liest ein GuestbookEntry anhand der Id.
	 * @param id
	 * @return
	 */
	public Optional<GuestbookEntry> findById(final Long id) {
		if(data.containsKey(id)) {
			return Optional.of(data.get(id));
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Löscht ein Produkt.
	 * @param product
	 */
	public void delete(GuestbookEntry entry) {
		this.data.remove(entry.getId());
	}
	
}
