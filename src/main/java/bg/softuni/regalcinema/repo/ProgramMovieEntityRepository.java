package bg.softuni.regalcinema.repo;

import bg.softuni.regalcinema.model.ProgramMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramMovieEntityRepository extends JpaRepository<ProgramMovieEntity, Long> {
}
