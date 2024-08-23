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
public class JdbcCodeRepository {
  private static final Logger log = LoggerFactory.getLogger(JdbcCodeRepository.class);
  // JdbcClient is a class that is used to interact with the database,
  // it is used to execute SQL queries and retrieve results
  private final JdbcClient jdbcClient;

  // Autoconfigure the dependency injection
  // By adding the class in contructor, Spring will automatically inject the
  // dependency
  public JdbcCodeRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public List<Code> getCodes() {
    return jdbcClient
        .sql("select id, tech_stack, description, duration, location, created_at, updated_at from code")
        .query(Code.class).list();
  }

  public Optional<Code> getCodeByID(Integer id) {
    return jdbcClient.sql(
        "select id, tech_stack, description, duration, location, created_at, updated_at from code WHERE id = :id")
        .param("id", id).query(Code.class).optional();
  }

  public void createCode(Code code) {
    var created = jdbcClient.sql(
        "INSERT INTO code (tech_stack, description, duration, location, created_at) VALUES (?, ?, ?, ?, ?)")
        .params(List.of(code.techStack(), code.description(), code.duration(), code.location(), LocalDateTime.now()))
        .update();
    Assert.state(created == 1, "Failed to insert code");
  }

  public void updateCode(Code code, Integer id) {
    var updated = jdbcClient
        .sql(
            "UPDATE code SET tech_stack = ?, description = ?, duration = ?, location = ?, updated_at = ? WHERE id = ?")
        .params(
            List.of(code.techStack(), code.description(), code.duration(), code.location(), LocalDateTime.now(), id))
        .update();

    Assert.state(updated == 1, "Failed to update code");
  }

  public void deleteCode(Integer id) {
    var deleted = jdbcClient.sql("DELETE FROM code WHERE id = ?").param(id).update();

    Assert.state(deleted == 1, "Failed to delete code");
  }

  public void saveAll(List<Code> codes) {
    codes.stream().forEach(this::createCode);
  }

  public int count() {
    return jdbcClient.sql("SELECT COUNT(*) FROM code").query(Integer.class).single();
  }


}
