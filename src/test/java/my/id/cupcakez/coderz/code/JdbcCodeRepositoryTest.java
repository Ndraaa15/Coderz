package my.id.cupcakez.coderz.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

// JdbcTest annotation is used to test the JPA layer
// So it ignore all the other layers, and just for testing the JPA layer
// Only focus on jdbc component
@JdbcTest // just load certain things
@Import(JdbcCodeRepository.class) // Import the class that we want to test
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JdbcCodeRepositoryTest {
    @Autowired
    JdbcCodeRepository jdbcCodeRepository;

    @BeforeEach
    void setUp() {
        jdbcCodeRepository.createCode(new Code(20, "Java", "Java Spring Boot", 3, "Campus", LocalDateTime.now(), null));
        jdbcCodeRepository.createCode(new Code(21, "Python", "Python Django", 3, "Campus", LocalDateTime.now(), null));
    }


    @Test
    void shouldGetCodes() {
        assertEquals(7, jdbcCodeRepository.getCodes().size(), "Should return all codes");
    }

    @Test
    void shoudlGetCodeByID() {
        Code code = jdbcCodeRepository.getCodeByID(1).get();
        assertEquals("C#", code.techStack(), "Should return code with id 20");
    }

    @Test
    void shoudlCreateCode() {
        jdbcCodeRepository.createCode(new Code(22, "Java", "Java Spring Boot", 3, "Campus", LocalDateTime.now(), null));
        assertEquals(8, jdbcCodeRepository.getCodes().size(), "Should create new code");
    }

    @Test
    void shouldUpdateCode() {
        jdbcCodeRepository.updateCode(new Code(1, "Java", "Java Spring Boot", 3, "Campus", LocalDateTime.now(), LocalDateTime.now()), 1);
        Code code = jdbcCodeRepository.getCodeByID(1).get();
        assertEquals("Java", code.techStack(), "Should update code with id 20");
    }

    @Test
    void shouldDeleteCode() {
        jdbcCodeRepository.deleteCode(4);
        assertEquals(6, jdbcCodeRepository.getCodes().size(), "Should delete code with id 20");
    }

}