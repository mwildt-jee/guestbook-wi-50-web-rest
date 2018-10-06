package de.hsw.jee.guestbook.web.services;

public class UserException extends Exception {

	private UserException(String message) {
		super(message);
	}

	public static UserException userAlreadyExists(String username) {
		return new UserException("Der Benutzer mit den namen " + username + " existiert bereits");
	}

}
