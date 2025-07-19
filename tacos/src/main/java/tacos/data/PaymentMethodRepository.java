package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.data.entity.PaymentMethod;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Long> {

    PaymentMethod findByUserId(Long userId);

    PaymentMethod findByUserUsername(String username);
}