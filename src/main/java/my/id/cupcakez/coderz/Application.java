package my.id.cupcakez.coderz;

import my.id.cupcakez.coderz.user.User;
import my.id.cupcakez.coderz.user.UserHttpClient;
import my.id.cupcakez.coderz.user.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class Application {
	// Logger is a class that is used to log the information
	// This code below to create instance logger in springboot
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		// This code below to print log information
		log.info("Application started successfully!!!");

		// Bean is a class with metadata that is used by Spring to create an object
		// This code mean all the code must in same dir with main class

		// WelcomeMessage welcomeMessage = (WelcomeMessage)
		// context.getBean("welcomeMessage");
		// System.out.println(welcomeMessage.getWelcomeMessage());

		// Using this maven is automatically reload (build the project) when we change
		// and save

	}

	// Annotation will load all the beans in the application context
	@Bean
	UserHttpClient userHttpClient(){
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return httpServiceProxyFactory.createClient(UserHttpClient.class);
	}

	// Since CommandLineRunner is a functional interface, we can use lambda
	// expression
	// CommandLineRunner something that run after the application context is loaded
	// (or started)
	// Bean is a class with metadata that is used by Spring to create an object
	// Bean put the object in application context
	 @Bean
	 CommandLineRunner runner(UserHttpClient client) {
		 return args -> {
			 client.findAll().forEach(user -> log.info(user.toString()));
		 };
	 }
}
