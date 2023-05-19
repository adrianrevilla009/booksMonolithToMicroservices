package es.codeurjc.books.utils;

import es.codeurjc.books.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArchitectureModel {
    ResponseEntity<User> deleteUser(long id);
}
