package tacos.messaging;

import tacos.data.entity.Order;

public interface OrderMessagingService {

    void sendOrder(Order order);

}