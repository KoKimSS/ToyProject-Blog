package ToyProject.blogWorld.repository.user;

import ToyProject.blogWorld.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByLoginIdAndPassword(String loginId, String password);
    Boolean existsByloginId(String loginId);

}
