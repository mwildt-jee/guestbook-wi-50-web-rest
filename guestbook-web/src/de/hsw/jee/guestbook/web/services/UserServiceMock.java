package de.hsw.jee.guestbook.web.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import de.hsw.jee.guestbook.web.model.User;

public class UserServiceMock implements UserService {

	private final List<User> users = new LinkedList<>();
	
	@Override
	public User register(String username, String password) throws UserException {
		final User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		if (this.findByUsername(username).isPresent()) {
			throw UserException.userAlreadyExists(username);
		}
		users.add(user);
		return user;
	}
	
	@Override
	public Optional<User> findByUsername(String username) {
		return users.stream()
				.filter(u -> u.getUsername().equals(username))
				.findFirst();
	}

	@Override
	public boolean checkPassword(User user, String password) {
		return Objects.equals(user.getPassword(), password);
	}
	
}
