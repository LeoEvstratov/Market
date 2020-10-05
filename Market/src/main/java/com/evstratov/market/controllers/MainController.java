package com.evstratov.market.controllers;

import com.evstratov.market.entities.Product;
import com.evstratov.market.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    // https://getbootstrap.com/docs/4.1/getting-started/introduction/
    private ProductService productService;

    @RequestMapping("/")
    public String showHomePage(@RequestParam(name = "searchQuery", required = false) String searchQuery, Model model) {
        List<Product> productsList;
        if (searchQuery == null || searchQuery.isEmpty()) {
            productsList = productService.getAllProductsList();
        } else {
            productsList = productService.searchByName(searchQuery);
        }
        model.addAttribute("productsList", productsList);
        model.addAttribute("searchQuery", searchQuery);
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
