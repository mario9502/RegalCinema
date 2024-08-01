package bg.softuni.regalcinema.repo;

import bg.softuni.regalcinema.model.Cinema;
import bg.softuni.regalcinema.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    Optional<Program> findByDate(LocalDate date);
    Optional<Program> findByCinemasContaining(List<Cinema> cinemas);
}
