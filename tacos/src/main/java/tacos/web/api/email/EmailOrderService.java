package tacos.web.api.email;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import tacos.data.IngredientRepository;
import tacos.data.PaymentMethodRepository;
import tacos.data.UserRepository;
import tacos.data.entity.*;

@Service
public class EmailOrderService {

    private UserRepository userRepo;
    private IngredientRepository ingredientRepo;
    private PaymentMethodRepository paymentMethodRepo;

    public EmailOrderService(UserRepository userRepo, IngredientRepository ingredientRepo,
                             PaymentMethodRepository paymentMethodRepo) {
        this.userRepo = userRepo;
        this.ingredientRepo = ingredientRepo;
        this.paymentMethodRepo = paymentMethodRepo;
    }

    public Mono<Order> convertEmailOrderToDomainOrder(Mono<EmailOrder> emailOrder) {
        // TODO: Probably should handle unhappy case where email address doesn't match a given user or
        //       where the user doesn't have at least one payment method.

        return emailOrder.flatMap(eOrder -> {
            Mono<User> userMono = Mono.just(userRepo.findByEmail(eOrder.getEmail()));

            Mono<PaymentMethod> paymentMono = userMono.flatMap(user -> {
                return Mono.just(paymentMethodRepo.findByUserUsername(user.getUsername()));
            });
            return Mono.zip(userMono, paymentMono)
                    .flatMap(tuple -> {
                        User user = tuple.getT1();
                        PaymentMethod paymentMethod = tuple.getT2();
                        Order order = new Order();
                        order.setUser(new User());
                        order.setCcNumber(paymentMethod.getCcNumber());
                        order.setCcCVV(paymentMethod.getCcCVV());
                        order.setCcExpiration(paymentMethod.getCcExpiration());
                        order.setDeliveryName(user.getFullname());
                        order.setDeliveryStreet(user.getStreet());
                        order.setDeliveryCity(user.getCity());
                        order.setDeliveryState(user.getState());
                        order.setDeliveryZip(user.getZip());
                        order.setPlacedAt(new Date());

                        return emailOrder.map(eOrd -> {
                            List<EmailOrder.EmailTaco> emailTacos = eOrd.getTacos();
                            for (EmailOrder.EmailTaco emailTaco : emailTacos) {
                                List<String> ingredientIds = emailTaco.getIngredients();
                                List<Ingredient> ingredients = new ArrayList<>();
                                for (String ingredientId : ingredientIds) {
                                    Mono<Ingredient> ingredientMono = Mono.just(ingredientRepo.findById(ingredientId).orElseThrow());
                                    ingredientMono.subscribe(i ->
                                            ingredients.add(new Ingredient(i.getName(), "email",i.getType())));
                                }
                                Taco taco = new Taco();
                                order.addDesign(taco);
                            }
                            return order;
                        });
                    });
        });
    }
}