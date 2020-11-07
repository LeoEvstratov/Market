package com.evstratov.market.services;

import com.evstratov.market.entities.Product;
import com.evstratov.market.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getAllProductsList() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        if (product.getCreateAt() == null) {
            product.setCreateAt(new Date());
        }
        product.setImagePath("null");
        productRepository.save(product);
    }

    public List<Product> searchByName(String searchQuery) {
//        return productRepository.searchByName(searchQuery);// todo fix search
        return Collections.emptyList();
    }


    public Optional<Product> getProductById(Long productId) {
        return Optional.of(productRepository.getProductById(productId));
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
