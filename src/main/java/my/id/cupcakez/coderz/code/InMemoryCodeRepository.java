package my.id.cupcakez.coderz.code;

import jakarta.annotation.PostConstruct;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InMemoryCodeRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryCodeRepository.class);
     private List<Code> codes = new ArrayList<>();

     List<Code> getCodes() {
         return codes;
     }

     // Optional is a class that is used to represent a value which may be absent
     // or present
     Optional<Code> getCodeByID(Integer id) {
         return Optional.ofNullable(codes.stream().filter(code -> code.id().equals(id)).findFirst().orElseThrow(CodeNotFoundException::new));
     }

     void createCode(Code code) {
        codes.add(code);
     }

     void updateCode(Integer id, Code code) {
     Optional<Code> data = getCodeByID(id);
     if (data.isPresent()) {
         codes.set(codes.indexOf(data.get()), code);
     }
     }

     void deleteCode(Integer id) {
         codes.removeIf(code -> code.id().equals(id));
     }

     // PostConstruct annotation is used on a method that needs to be executed
     // after dependency injection is done to perform any initialization
     @PostConstruct
     private void init() {
     codes.add(new Code(1, "Java", "Java Spring Boot", 3, "Campus",
     LocalDateTime.now(), null));
     codes.add(new Code(2, "Python", "Python Django", 3, "Campus",
     LocalDateTime.now(), null));
     codes.add(new Code(3, "Java", "Java Spring Boot", 3, "Campus",
     LocalDateTime.now(), null));
     codes.add(new Code(4, "Python", "Python Django", 3, "Campus",
     LocalDateTime.now(), null));
     }

}
