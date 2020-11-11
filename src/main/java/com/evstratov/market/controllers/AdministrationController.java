package com.evstratov.market.controllers;

import com.evstratov.market.entities.Order;
import com.evstratov.market.entities.Product;
import com.evstratov.market.entities.User;
import com.evstratov.market.services.OrderService;
import com.evstratov.market.services.ProductService;
import com.evstratov.market.services.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("administration")
public class AdministrationController {
    private ProductService productService;
    private UserService userService;
    private OrderService orderService;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/")
    public String showAdminPanel() {
        return "administration/admin-panel";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/products")
    public String showProductsControl(@RequestParam(name = "searchQuery", required = false) String searchQuery, Model model) {
        List<Product> productsList;
        if (searchQuery == null || searchQuery.isEmpty()) {
            productsList = productService.getAllProductsList();
        } else {
            productsList = productService.searchByName(searchQuery);
        }
        model.addAttribute("productsList", productsList);
        model.addAttribute("searchQuery", searchQuery);
        return "administration/products";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/users")
    public String showUsersControl(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "administration/users";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/orders")
    public String showOrdersControl(Model model) {
        List<Order> orderList = orderService.getAllOrders();
        model.addAttribute("orderList", orderList);
        return "administration/orders";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}