package my.id.cupcakez.coderz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// This test depedency is using a lot of library
// Test scope depedency :
// - JUnit
// - Hamcrest
// - Mockito -> Mocking framework (for mocking one layer, for example just controller)
// - AssertJ -> Fluent assertions
// - Spring Test
// - JSONAssert

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
