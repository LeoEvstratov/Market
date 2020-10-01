package com.evstratov.market.services;

import com.evstratov.market.entities.Product;
import com.evstratov.market.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getAllProductsList() {
        return productRepository.findAll();
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
