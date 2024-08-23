package my.id.cupcakez.coderz.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.lang.reflect.ParameterizedType;
import java.util.List;

// Component is for to put this class in application context
// If this annotation not used, the class will not be created as a bean
// required a bean of type 'my.id.cupcakez.coderz.user.UserRestClient' that could not be found.
@Component
public class UserRestClient {
    // RestClient is for to make a request to the server
    private final RestClient restClient;

    public UserRestClient(RestClient.Builder builder) {
        JdkClientHttpRequestFactory jdkClientHttpRequestFactory = new JdkClientHttpRequestFactory();
        jdkClientHttpRequestFactory.setReadTimeout(5000);
        // Interceptor is a class that is used to intercept the request and response
        // Interceptor usually use for logging, authentication, etc
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .requestFactory(jdkClientHttpRequestFactory)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("User-Agent", "Java 11 HttpClient")
                .build();
    }

    public List<User> findAll(){
        return restClient
                .get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public User findById(int id){
        return restClient
                .get()
                .uri("/users/{id}", id)
                .retrieve()
                .body(User.class);
    }


}
