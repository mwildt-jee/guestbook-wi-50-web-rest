package de.hsw.jee.guestbook.rest.model;

import java.util.Date;

public class GuestbookEntry {

	private Long id;
	private String username;
	private String title;
	private String message;
	private Date created;
	private Date modified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public void prePersist() {
		this.created = new Date();
		this.preUpdate();
	}

	public void preUpdate() {
		this.modified = new Date();
	}

}
