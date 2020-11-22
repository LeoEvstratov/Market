package com.evstratov.market.repositories;

import com.evstratov.market.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<OrderStatus, Integer> {
    Optional<OrderStatus> findByName(String name);
}
