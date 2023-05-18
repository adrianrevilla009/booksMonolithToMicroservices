package es.codeurjc.books.utils;

import es.codeurjc.books.model.Comment;
import es.codeurjc.books.model.User;
import es.codeurjc.books.service.CommentService;
import es.codeurjc.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

public class MonolithModel implements ArchitectureModel {

    @Autowired
    private UserService users;

    @Autowired
    private CommentService comments;

    @Override
    public ResponseEntity<?> createUser(User user) {
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

    @Override
    public User replaceUser(User newUser, long id) {
        newUser.setId(id);
        users.replace(newUser);
        return newUser;
    }

    @Override
    public List<User> getUsers() {
        return users.findAll();
    }

    @Override
    public User getUser(long id) {
        return users.findById(id).orElseThrow();
    }

    @Override
    public ResponseEntity<User> deleteUser(long id) {
        User user = users.findById(id).orElseThrow();

        List<Comment> comment = comments.findAllCommentsByUserId(id);
        if (comment.size() == 0) {
            users.deleteById(id);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
    }
}
