package bg.softuni.regalcinema.repo;

import bg.softuni.regalcinema.model.Marathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarathonRepository extends JpaRepository<Long, Marathon> {
}
