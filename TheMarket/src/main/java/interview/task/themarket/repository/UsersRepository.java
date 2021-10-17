package interview.task.themarket.repository;

import interview.task.themarket.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.id = :id")
    Optional<User> findUserById(@Param("id") Long id);

    @Query(value = "select * from users u where u.username = :username", nativeQuery = true)
    Optional<User> findUserByNameNativeQ(@Param("username") String username);

}
