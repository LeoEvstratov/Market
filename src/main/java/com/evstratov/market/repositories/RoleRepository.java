package com.evstratov.market.repositories;

import com.evstratov.market.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll();

    Optional<Role> findByName(String name);
//    @Query(value = "SELECT roles.role_name FROM roles WHERE roles.id= ?1",nativeQuery = true)
//    String findRoleNameById(Long id);
}
