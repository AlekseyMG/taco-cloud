package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.data.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}