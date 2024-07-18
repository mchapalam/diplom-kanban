package app.chapala.diplom.repository;


import app.chapala.diplom.model.Job;
import app.chapala.diplom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "SELECT * FROM diplom_schemas.app_user ORDER BY RANDOM() LIMIT :countUsers", nativeQuery = true)
    List<User> getRandomCountUsers(Long countUsers);

    @Query(value = "SELECT * FROM diplom_schemas.app_user u ORDER BY wages ", nativeQuery = true)
    List<User> filterByWages();

    @Query(value = "SELECT * FROM diplom_schemas.app_user u ORDER BY wages Desc", nativeQuery = true)
    List<User> filterByWagesDesc();

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);
}
