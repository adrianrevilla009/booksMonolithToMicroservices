package es.codeurjc.books.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.List;

import es.codeurjc.books.utils.ArchitectureModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.codeurjc.books.model.Comment;
import es.codeurjc.books.model.User;
import es.codeurjc.books.service.CommentService;
import es.codeurjc.books.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService users;

	@Autowired
	private CommentService comments;
	@Autowired
	private ArchitectureModel architectureModel;
	@PostMapping("/users/")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {

			users.save(user);

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest()
					.body("User nick should be unique");
		}

		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(location).body(user);
	}

	@PutMapping("/users/{id}")
	public User replaceUser(@RequestBody User newUser, @PathVariable long id) {
		newUser.setId(id);
		users.replace(newUser);
		return newUser;
	}

	@GetMapping("/users/")
	public List<User> getUsers() {
		return users.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable long id) {
		return users.findById(id).orElseThrow();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id) {
		return this.architectureModel.deleteUser(id);
	}
}