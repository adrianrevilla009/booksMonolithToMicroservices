package es.codeurjc.books.utils;

import es.codeurjc.books.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArchitectureModel {
    ResponseEntity<?> createUser(User user);
    User replaceUser(User newUser, long id);
    List<User> getUsers();
    User getUser(long id);
    ResponseEntity<User> deleteUser(long id);
}
