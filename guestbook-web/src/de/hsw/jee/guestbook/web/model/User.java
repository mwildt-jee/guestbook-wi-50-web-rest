package de.hsw.jee.guestbook.web.model;

public class User {
	
	public static User UNKONWN = new User("Unbekannter Benutzer");
	
	private String username;
	private String password;
	
	public User(){};
	
	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
		
}
