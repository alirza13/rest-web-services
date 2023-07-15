package com.alirza.rest.webservices.restwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alirza.rest.webservices.restwebservices.exception.PostNotFoundException;
import com.alirza.rest.webservices.restwebservices.exception.UserNotFoundException;
import com.alirza.rest.webservices.restwebservices.jpa.PostRepository;
import com.alirza.rest.webservices.restwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	private UserRepository userRepository;
	private PostRepository postRepository;

	public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
		super();
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@GetMapping(path = "/jpa/user")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/jpa/user/{id}")
	public EntityModel<User> getUser(@PathVariable long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(webMvcLinkBuilder.withRel("all-users"));
		return entityModel;
	}

	@PostMapping(path = "/jpa/user")
	public void createUser(@Valid @RequestBody User user) {
		userRepository.save(user);
	}

	@DeleteMapping(path = "/jpa/user/{id}")
	public void deleteUser(@PathVariable long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		} else {
			userRepository.deleteById(id);
		}
	}

	@GetMapping(path = "/jpa/user/{id}/post")
	public List<Post> getPostsForUser(@PathVariable long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		} else {
			return user.get().getPostList();
		}
	}

	@GetMapping(path = "/jpa/user/{id}/post/{postId}")
	public Post getPostOfUser(@PathVariable long id, @PathVariable long postId) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		} else {
			Optional<Post> post = postRepository.findById(postId);
			if (post.isEmpty()) {
				throw new PostNotFoundException(null);
			} else {
				return post.get();
			}
		}
	}

	@PostMapping(path = "/jpa/user/{id}/post")
	public ResponseEntity<Post> createPostForUser(@Valid @RequestBody Post post, @PathVariable long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		} else {
			post.setUser(user.get());
			Post savedPost = postRepository.save(post);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedPost.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

}
