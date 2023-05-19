package es.codeurjc.books.utils;

import es.codeurjc.books.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MicroserviceModel implements ArchitectureModel {

    @Value("${server.microserviceHost}")
    private String microserviceHost;


    private User getUser(long id) {
        final String uri = this.microserviceHost + "/users/" + id;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, User.class);
    }

    @Override
    public ResponseEntity<User> deleteUser(long id) {
        final String uri = this.microserviceHost + "/users/" + id;

        RestTemplate restTemplate = new RestTemplate();

        User user = this.getUser(id);
        // this is a void, so I have to retrieve user before
        restTemplate.delete(uri, ResponseEntity.class);
        return ResponseEntity.ok(user);
    }
}
