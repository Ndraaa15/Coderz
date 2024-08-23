package my.id.cupcakez.coderz.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest(CodeController.class)
class CodeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    // MockBean annotation is used to add mock objects to the Spring application context
    // It is used to mock the repository layer
    @MockBean
    CodeRepository jdbcCodeRepository;

    private final List<Code> codes = new ArrayList<>();

    @BeforeEach
    void setUp() {
        codes.add(new Code(1, "Java", "Java Spring Boot", 3, "Campus", null, null));
        codes.add(new Code(2, "Python", "Python Django", 3, "Campus", null, null));
    }

    @Test
    void shouldGetCodes() throws Exception {
        when(jdbcCodeRepository.findAll()).thenReturn(codes);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/codes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(codes.size())));

    }

    @Test
    void shouldGetCodeById() throws Exception {
        Code code = codes.getFirst();
        when(jdbcCodeRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(code));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/codes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.techStack", is(code.techStack())))
                .andExpect(jsonPath("$.duration", is(code.duration())));

    }

    @Test
    void createCode()  throws Exception {
        var run = new Code(3, "Java", "Java Spring Boot", 3, "Campus", null, null);
        when(jdbcCodeRepository.save(ArgumentMatchers.any(Code.class))).thenReturn(run);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/codes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(run)))
                .andExpect(status().isCreated());

    }

    @Test
    void updateCode() throws Exception {
        var run = new Code(3, "Java", "Java Spring Boot", 3, "Campus", null, null);
        when(jdbcCodeRepository.save(ArgumentMatchers.any(Code.class))).thenReturn(run);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/codes/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(run)))
                .andExpect(status().isNoContent());

    }

    @Test
    void deleteCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/codes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(codes.getFirst()))).andExpect(status().isNoContent());
    }

}