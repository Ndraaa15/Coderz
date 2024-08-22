package my.id.cupcakez.coderz.code;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

// Repository is a class that is used to store and retrieve data from the database
//
@Repository
public class JdbcClientCodeRepository {
  private static final Logger log = LoggerFactory.getLogger(JdbcClientCodeRepository.class);
  // JdbcClient is a class that is used to interact with the database,
  // it is used to execute SQL queries and retrieve results
  private final JdbcClient jdbcClient;

  // Autoconfigure the dependency injection
  // By adding the class in contructor, Spring will automatically inject the
  // dependency
  public JdbcClientCodeRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public List<Code> getCodes() {
    return jdbcClient
        .sql("select id, tech_stack, description, duration, location, created_at, updated_at from codes")
        .query(Code.class).list();
  }

  public Optional<Code> getCodeByID(Integer id) {
    return jdbcClient.sql(
        "select id, tech_stack, description, duration, location, created_at, updated_at from codes WHERE id = :id")
        .param("id", id).query(Code.class).optional();
  }

  public void createCode(Code code) {
    var created = jdbcClient.sql(
        "INSERT INTO codes (tech_stack, description, duration, location, created_at) VALUES (?, ?, ?, ?, ?)")
        .params(List.of(code.techStack(), code.description(), code.duration(), code.location(), LocalDateTime.now()))
        .update();
    Assert.state(created == 1, "Failed to insert code");
  }

  public void updateCode(Code code, Integer id) {
    var updated = jdbcClient
        .sql(
            "UPDATE codes SET tech_stack = ?, description = ?, duration = ?, location = ?, updated_at = ? WHERE id = ?")
        .params(
            List.of(code.techStack(), code.description(), code.duration(), code.location(), LocalDateTime.now(), id))
        .update();

    Assert.state(updated == 1, "Failed to update code");
  }

  public void deleteCode(Integer id) {
    var deleted = jdbcClient.sql("DELETE FROM codes WHERE id = ?").param(id).update();

    Assert.state(deleted == 1, "Failed to delete code");
  }

  public void saveAll(List<Code> codes) {
    codes.stream().forEach(this::createCode);
  }

  public int count() {
    return jdbcClient.sql("SELECT COUNT(*) FROM codes").query(Integer.class).single();
  }

  // private List<Code> codes = new ArrayList<>();

  // List<Code> getCodes() {
  // return codes;
  // }

  // // Optional is a class that is used to represent a value which may be absent
  // or ff
  // // present
  // Optional<Code> getCodeByID(Integer id) {
  // return codes.stream().filter(code -> code.id().equals(id)).findFirst();
  // }

  // void createCode(Code code) {
  // codes.add(code);
  // }

  // void updateCode(Integer id, Code code) {
  // Optional<Code> data = getCodeByID(id);
  // if (data.isPresent()) {
  // codes.set(codes.indexOf(data.get()), code);
  // }
  // }

  // void deleteCode(Integer id) {
  // codes.removeIf(code -> code.id().equals(id));
  // }

  // // PostConstruct annotation is used on a method that needs to be executed
  // after
  // // dependency injection is done to perform any initialization
  // @PostConstruct
  // private void init() {
  // codes.add(new Code(1, "Java", "Java Spring Boot", 3, "Campus",
  // LocalDateTime.now(), null));
  // codes.add(new Code(2, "Python", "Python Django", 3, "Campus",
  // LocalDateTime.now(), null));
  // codes.add(new Code(3, "Java", "Java Spring Boot", 3, "Campus",
  // LocalDateTime.now(), null));
  // codes.add(new Code(4, "Python", "Python Django", 3, "Campus",
  // LocalDateTime.now(), null));
  // }

}
