package com.evstratov.market.controllers;

import com.evstratov.market.entities.Product;
import com.evstratov.market.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
    // https://getbootstrap.com/docs/4.1/getting-started/introduction/
    private ProductService productService;

    @RequestMapping("/")
    public String showHomePage(Model model) {
        List<Product> allProductsList = productService.getAllProductsList();
        model.addAttribute("productsList", allProductsList);
        return "index";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public String showAdminPanel() {
        return "admin-panel";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
