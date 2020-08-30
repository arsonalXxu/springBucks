package org.arsonal.springBucks;

import lombok.extern.slf4j.Slf4j;
import org.arsonal.springBucks.model.Coffee;
import org.arsonal.springBucks.model.CoffeeOrder;
import org.arsonal.springBucks.model.OrderState;
import org.arsonal.springBucks.repository.CoffeeOrderRepository;
import org.arsonal.springBucks.repository.CoffeeRepository;
import org.arsonal.springBucks.service.CoffeeOrderService;
import org.arsonal.springBucks.service.CoffeeService;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication
public class SpringBucksApplication implements CommandLineRunner {

    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService orderService;
    @Autowired
    private CoffeeRepository coffeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBucksApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("All Coffee: {}", coffeeRepository.findAll());

        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            CoffeeOrder order = orderService.createOrder("Li Lei", latte.get());
            log.info("Update INIT to PAID: {}", orderService.updateState(order, OrderState.PAID));
            log.info("Update PAID to INIT: {}", orderService.updateState(order, OrderState.INIT));
        }
    }
}
