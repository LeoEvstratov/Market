package com.evstratov.market.controllers;


import com.evstratov.market.entities.Role;
import com.evstratov.market.entities.User;
import com.evstratov.market.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> allRoles = userService.getAllRoles();
        //todo cut out role selection, role has to be selected by admin
        model.addAttribute("allRoles", allRoles);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "index";
    }

    @GetMapping("/profile")
    public String showProfilePage() {
        return "profile";
    }


    @GetMapping("/accessDenied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
