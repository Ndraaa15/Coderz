package my.id.cupcakez.coderz;

import org.springframework.stereotype.Component;

// Component annotation is used to indicate that the class is a Spring
@Component
public class WelcomeMessage {
  public String getWelcomeMessage() {
    return "Welcome to Spring Boot!!!";
  }
}
