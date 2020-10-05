package com.evstratov.market.controllers;

import com.evstratov.market.entities.Category;
import com.evstratov.market.entities.Product;
import com.evstratov.market.services.CategoryService;
import com.evstratov.market.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    ProductService productService;
    CategoryService categoryService;

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        Product product = new Product();
        List<Category> allCategories = categoryService.getAllCategories();
        model.addAttribute("categories", allCategories);
        model.addAttribute("product", product);
        return "add-product";
    }

    @GetMapping("/modify/{productId}")
    public String modifyProduct(@PathVariable(name = "productId") Long productId, Model model) {
        List<Category> allCategories = categoryService.getAllCategories();
        model.addAttribute("categories", allCategories);
        Optional<Product> product = productService.getProductById(productId);
        if (product.isPresent()){
            model.addAttribute("product", product.get());
        }
        return "add-product";
    }

    @PostMapping("addProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
