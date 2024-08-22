package my.id.cupcakez.coderz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	// Since CommandLineRunner is a functional interface, we can use lambda
	// expression
	// CommandLineRunner something that run after the application context is loaded
	// (or started)
	// Bean is a class with metadata that is used by Spring to create an object
	// Bean put the object in application context
	// @Bean
	// CommandLineRunner runner(CodeRepository codeRepository) {
	// return args -> {
	// Code code = new Code(1, "Java", "Java Spring Boot", 3, "Campus",
	// LocalDateTime.now(), null);
	// codeRepository.createCode(code);
	// };
	// }
}
