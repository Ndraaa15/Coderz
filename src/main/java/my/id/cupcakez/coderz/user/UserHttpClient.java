package my.id.cupcakez.coderz.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface UserHttpClient {
    // Get Exchange annotation is used to define the HTTP method and the URL
    @GetExchange("/users")
    List<User> findAll();

    @GetExchange("/users/{id}")
    User findById(@PathVariable Integer id);
}
