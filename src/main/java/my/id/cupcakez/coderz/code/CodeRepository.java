package my.id.cupcakez.coderz.code;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CodeRepository extends ListCrudRepository<Code, Integer> {
    // This query annotation is used to define the query that will be executed
    // The query will be executed when the method is called
    @Query("SELECT * FROM codes WHERE tech_stack = :techStack")
    List<Code> findByTechStack(String techStack);
}
