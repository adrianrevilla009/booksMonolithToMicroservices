package adrian.books.controller;

import adrian.books.model.User;
import adrian.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
public class UserController {

	@Autowired
	private UserService users;

//	@Autowired
//	private CommentService comments;

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

		User user = users.findById(id).orElseThrow();

//		List<Comment> comment = comments.findAllCommentsByUserId(id);
//		if (comment.size() == 0) {
//			users.deleteById(id);
//			return ResponseEntity.ok(user);
//		} else {
//			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
//		}
		return null;
	}
}