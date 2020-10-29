package com.evstratov.market.controllers;


import com.evstratov.market.entities.Role;
import com.evstratov.market.entities.User;
import com.evstratov.market.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute("user") @Valid User user,
                                  BindingResult bindingResult) {
        if (!user.getPassword().equals(user.getPasswordConfirmation())){
            bindingResult.addError(new ObjectError("passwordConfirmation","passwords are not equal"));
        }// todo password confirmation message doesnt appear
        if (bindingResult.hasErrors()){
            return "registration";
        }
        userService.saveUser(user); // todo user is not saving in db
        return "redirect:/";
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
