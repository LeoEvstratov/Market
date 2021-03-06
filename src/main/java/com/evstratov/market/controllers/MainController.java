package com.evstratov.market.controllers;

import com.evstratov.market.entities.Product;
import com.evstratov.market.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {
    private ProductService productService;

//    @GetMapping("/")
//    public String showHomePage(@RequestParam(name = "searchQuery", required = false) String searchQuery, Model model) {
//        List<Product> productsList;
//        if (searchQuery == null || searchQuery.isEmpty()) {
//            productsList = productService.getAllProductsList();
//        } else {
//            productsList = productService.searchByName(searchQuery);
//        }
//        model.addAttribute("productsList", productsList);
//        model.addAttribute("searchQuery", searchQuery);
//        return "index";
//    }

    @GetMapping("/")
    public String showMainPage(Model model) {
        return "mainPage";
    }


//    @GetMapping("/ex")
//    public String throwEx(Model model) throws Exception {
//        throw new Exception("EXCEPTION TO LOG IN DB");// to test exception logging
//    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
