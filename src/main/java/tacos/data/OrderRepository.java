package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.data.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
