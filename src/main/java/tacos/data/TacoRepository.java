package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.data.entity.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
