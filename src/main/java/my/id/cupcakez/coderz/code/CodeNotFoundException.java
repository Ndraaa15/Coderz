package my.id.cupcakez.coderz.code;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

// ResponseStatus is used to annotate a method that handles exceptions
// HttpStatus is an enumeration that defines the standard HTTP status codes
// This class is used to handle the exception when the code is not found
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CodeNotFoundException extends RuntimeException {
  public CodeNotFoundException() {
    super("Code Not Found!!");
  }

}
