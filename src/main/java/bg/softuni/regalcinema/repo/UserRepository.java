package bg.softuni.regalcinema.repo;

import bg.softuni.regalcinema.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsernameOrEmail(String username, String email);

    Optional<UserEntity> findByUsername(String username);
}
