package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.data.entity.Order;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Optional<Order> findById(Long Id);
}
