package es.codeurjc.books.utils;

import es.codeurjc.books.model.Comment;
import es.codeurjc.books.model.User;
import es.codeurjc.books.service.CommentService;
import es.codeurjc.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
public class MonolithModel implements ArchitectureModel {

    @Autowired
    private UserService users;

    @Autowired
    private CommentService comments;

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
