package org.arsonal.springBucks.repository;

import org.arsonal.springBucks.model.BaseEntity;
import org.arsonal.springBucks.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);

    List<CoffeeOrder> findByItems_Name(String name);

}
