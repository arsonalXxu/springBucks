package org.arsonal.springBucks.repository;

import org.arsonal.springBucks.model.Coffee;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoffeeRepository extends BaseRepository<Coffee, Long> {
    Optional<Coffee> findOne(Example<Coffee> coffeeExample);
}
