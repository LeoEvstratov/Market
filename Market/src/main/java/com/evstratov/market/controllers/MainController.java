package com.evstratov.market.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    // https://getbootstrap.com/docs/4.1/getting-started/introduction/

    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public String showAdminPanel() {
        return "admin-panel";
    }
}
