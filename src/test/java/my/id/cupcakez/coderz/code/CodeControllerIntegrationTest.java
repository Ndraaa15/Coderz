package my.id.cupcakez.coderz.code;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CodeControllerIntegrationTest {

    @LocalServerPort
    int randomServerPort;

    @BeforeEach
    void setUp() {
    }
}