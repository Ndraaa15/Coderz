package my.id.cupcakez.coderz.code;

import java.io.InputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

// Component is to mark the class as a Spring component so the spring framework can autodetect and instantiate it
@Component
public class CodeJsonDataLoader implements CommandLineRunner {
  private static final Logger log = LoggerFactory.getLogger(CodeJsonDataLoader.class);
  private final CodeRepository codeRepository;
  private final ObjectMapper objectMapper;

  public CodeJsonDataLoader(CodeRepository codeRepository, ObjectMapper objectMapper) {
    this.codeRepository = codeRepository;
    this.objectMapper = objectMapper;
  }

  @Override
  public void run(String... args) throws Exception {
    if (codeRepository.count() == 0) {
      try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/codes.json")) {
        Codes codes = objectMapper.readValue(inputStream, Codes.class);
        log.info("Data loaded successfully");

        codeRepository.saveAll(codes.codes());
      } catch (IOException e) {
        log.error("Failed to load data", e);
      }
    } else {
      log.info("Data already loaded");
    }
  }

}
