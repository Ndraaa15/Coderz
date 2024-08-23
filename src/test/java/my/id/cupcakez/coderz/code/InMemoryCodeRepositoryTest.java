package my.id.cupcakez.coderz.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCodeRepositoryTest {
    InMemoryCodeRepository inMemoryCodeRepository;

    // BeforeEach annotation is used to signal that the annotated method should be executed before each @Test method in the current test class
    @BeforeEach
    void setUp() {
        inMemoryCodeRepository = new InMemoryCodeRepository();
        inMemoryCodeRepository.createCode(new Code(10, "Java", "Java Spring Boot", 3, "Campus", LocalDateTime.now(), null));
        inMemoryCodeRepository.createCode(new Code(11, "Python", "Python Django", 3, "Campus", LocalDateTime.now(), null));
    }

    @Test
    void shouldReturnAllCodes() {
        assertEquals(2, inMemoryCodeRepository.getCodes().size(), "Should return all codes");
    }

    @Test
    void shouldReturnCodeById() {
        Code code = inMemoryCodeRepository.getCodeByID(10).get();
        assertEquals("Java", code.techStack(), "Should return code with id 10");
    }

    @Test
    void shouldCreateCode() {
        inMemoryCodeRepository.createCode(new Code(12, "Java", "Java Spring Boot", 3, "Campus", LocalDateTime.now(), null));
        assertEquals(3, inMemoryCodeRepository.getCodes().size(), "Should create new code");
    }

    @Test
    void shouldUpdateCode() {
        inMemoryCodeRepository.updateCode(10, new Code(10, "Java", "Java Spring Boot", 3, "Campus", LocalDateTime.now(), null));
        Code code = inMemoryCodeRepository.getCodeByID(10).get();
        assertEquals("Java", code.techStack(), "Should update code with id 10");
    }

    @Test
    void shouldDeleteCode() {
        inMemoryCodeRepository.deleteCode(10);
        assertEquals(1, inMemoryCodeRepository.getCodes().size(), "Should delete code with id 10");
    }

    @Test
    void shouldThrowCodeNotFoundException() {
        assertThrows(CodeNotFoundException.class, () -> inMemoryCodeRepository.getCodeByID(100).get(), "Should throw CodeNotFoundException");
    }
}