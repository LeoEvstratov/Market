package com.evstratov.market.repositories;

import com.evstratov.market.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //    @Query(
//            value = "SELECT * FROM products p WHERE p.title LIKE %?1%",
//            nativeQuery = true)
//    List<Product> searchByName(String title);
    Product getProductById(Long id);
}
