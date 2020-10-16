package com.evstratov.market.controllers;

import com.evstratov.market.entities.Product;
import com.evstratov.market.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts(Model model) {
        return productService.getAllProductsList();
    }
    @PostMapping(path="/products")
    public List<Product> postAllProducts(@RequestBody String postPayload){
        System.out.println(postPayload);
        return productService.getAllProductsList();
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
