package my.id.cupcakez.coderz.code;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

// Mutable object (Record)
// Including getter and setter,  constructor,  toString,  equals,  hashCode

// We can use to some annotation to validate the data from jakarta.validation.constraints and dont forget to add annotation valid in the controller
public record Code(@Id Integer id,
    @NotEmpty String techStack,
    @NotEmpty String description,
    @Positive Integer duration,
    @NotEmpty String location,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    @Version
    Integer version
) {

  // This code below will run when we create new instance of the class
  // public Code {
  // if (duration < 0) {
  // throw new IllegalArgumentException("Duration must be greater than 0");
  // }
  // }
}
