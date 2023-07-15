package com.alirza.rest.webservices.restwebservices.user;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alirza.rest.webservices.restwebservices.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	private UserDaoService userDaoService;

	public UserResource(UserDaoService userDaoService) {
		super();
		this.userDaoService = userDaoService;
	}

	@GetMapping(path = "/user")
	public List<User> getAllUsers() {
		return userDaoService.findAll();
	}

	@GetMapping(path = "/user/{id}")
	public EntityModel<User> getUser(@PathVariable long id) {
		User user = userDaoService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("User Not Found");
		}
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(webMvcLinkBuilder.withRel("all-users"));
		return entityModel;
	}

	@PostMapping(path = "/user")
	public void createUser(@Valid @RequestBody User user) {
		userDaoService.addUser(user);
	}

	@DeleteMapping(path = "/user/{id}")
	public void deleteUser(@PathVariable long id) {
		User user = userDaoService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("User Not Found");
		} else {
			userDaoService.deleteOne(id);
		}
	}
}
