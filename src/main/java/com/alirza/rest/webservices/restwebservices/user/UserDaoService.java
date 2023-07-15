package com.alirza.rest.webservices.restwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> userList = new ArrayList<>();
	private static long userId;

	static {
		userList.add(new User(++userId, "Alirza", LocalDate.now().minusYears(35)));
		userList.add(new User(++userId, "Ahmet", LocalDate.now().minusYears(65)));
		userList.add(new User(++userId, "Veli", LocalDate.now().minusYears(15)));
	}

	public List<User> findAll() {
		return userList;
	}

	public User findOne(long id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return userList.stream().filter(predicate).findFirst().orElse(null);
	}

	public void addUser(User user) {
		user.setId(++userId);
		userList.add(user);
	}

	public void deleteOne(long id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		userList.removeIf(predicate);
	}
}
