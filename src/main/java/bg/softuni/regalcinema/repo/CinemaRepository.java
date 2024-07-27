package bg.softuni.regalcinema.repo;

import bg.softuni.regalcinema.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    boolean existsByName(String name);

    Optional<Cinema> findByName(String cinema);
}
