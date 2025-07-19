package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.data.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);
}