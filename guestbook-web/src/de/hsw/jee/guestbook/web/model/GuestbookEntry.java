package de.hsw.jee.guestbook.web.model;

import java.util.Date;

public class GuestbookEntry{
	
	private User user;
	private String title;
	private String message;
	private Date dateCreated;
	
	public GuestbookEntry() {};
	
	public GuestbookEntry(User user, String title, String message) {
		this.user = user;
		this.title = title;
		this.message = message;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}