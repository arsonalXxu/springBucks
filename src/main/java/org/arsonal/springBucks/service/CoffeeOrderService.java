package org.arsonal.springBucks.service;

import lombok.extern.slf4j.Slf4j;
import org.arsonal.springBucks.model.Coffee;
import org.arsonal.springBucks.model.CoffeeOrder;
import org.arsonal.springBucks.model.OrderState;
import org.arsonal.springBucks.repository.CoffeeOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Slf4j
@Service
@Transactional
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrder createOrder(String customer, Coffee... coffees) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffees))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder saved = coffeeOrderRepository.save(order);
        log.info("New Order: {}", saved);
        return saved;
    }

    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State Order: {}, {}", state, order.getState());
            return false;
        }

        order.setState(state);
        coffeeOrderRepository.save(order);
        log.info("Update Order: {}", order);
        return true;
    }
}
