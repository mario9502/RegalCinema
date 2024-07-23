package bg.softuni.regalcinema.repo;

import bg.softuni.regalcinema.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Long, Program> {
}
