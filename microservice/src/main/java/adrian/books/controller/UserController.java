package adrian.books.controller;

import adrian.books.dto.CommentDto;
import adrian.books.model.User;
import adrian.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
public class UserController {
	@Value("${server.monolithHost}")
	private String monolithHost;

	@Autowired
	private UserService users;

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

		final String uri = this.monolithHost + "/users/" + id + "/comments";
		RestTemplate restTemplate = new RestTemplate();
		List<CommentDto> comment = restTemplate.getForObject(uri, List.class);

		if (comment.size() == 0) {
			users.deleteById(id);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
	}
}