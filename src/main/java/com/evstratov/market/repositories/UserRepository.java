package com.evstratov.market.repositories;


import com.evstratov.market.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    boolean existsUserByUsername(String username);
}
