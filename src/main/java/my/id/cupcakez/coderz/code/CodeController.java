package my.id.cupcakez.coderz.code;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Annotation is a tag that is used to provide metadata about the class (adding new functionality)

// RestController is a class that is used to create RESTful web services using Spring MVC (Model-View-Controller)
// Expected to be used in combination with @RequestMapping annotation
// This class can return data in JSON or XML format
@RestController
@RequestMapping("/api/v1/codes")
public class CodeController {
  // @Autowired
  private final CodeRepository codeRepository;

  // To use dependency injection, need to use annotation @Repository in repository
  // class it will be automatically injected

  // We can also use @Autowired annotation to inject the repository class (field
  // injection)

  // But it not recomended because it can make the code harder to test because it
  // use reflection
  public CodeController(CodeRepository codeRepository) {
    this.codeRepository = codeRepository;
  }

  // GetMapping is used to map the HTTP GET requests onto specific handler methods
  @GetMapping("")
  List<Code> getCodes() {
    return codeRepository.findAll();
  }

  // PathVariable is used to extract the value of a URI template variable
  @GetMapping("/{id}")
  Code getCodeById(@PathVariable Integer id) {
    Optional<Code> code = codeRepository.findById(id);
    if (code.isEmpty()) {
      throw new CodeNotFoundException();
    }
    return code.get();
  }

  // PostMapping is used to map the HTTP POST requests onto specific handler
  // methods

  // RequestBody is used to bind the HTTP request/response body with a domain (as
  // a json object)

  // Valid is used to validate the request body based on the constraints defined
  // in the domain class
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  void createCode(@Valid @RequestBody Code code) {
    codeRepository.save(code);
  }

  // PutMapping is used to map the HTTP PUT requests onto specific handler methods
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{id}")
  void updateCode(@Valid @RequestBody Code code, @PathVariable Integer id) {
    codeRepository.save(code);
  }

  // DeleteMapping is used to map the HTTP DELETE requests onto specific handler
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  void deleteCode(@PathVariable Integer id) {
    codeRepository.delete(codeRepository.findById(id).get());
  }

  @GetMapping("/tech-stack/{techStack}")
    List<Code> getCodesByTechStack(@PathVariable String techStack) {
        return codeRepository.findByTechStack(techStack);
    }
}
