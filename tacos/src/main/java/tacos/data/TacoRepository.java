package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.data.entity.Taco;

import java.util.Optional;

public interface TacoRepository extends CrudRepository<Taco, Long> {

    Optional<Taco> findById(Long id);
}
