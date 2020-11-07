package com.evstratov.market.controllers;

import com.evstratov.market.entities.Order;
import com.evstratov.market.entities.Product;
import com.evstratov.market.entities.ShoppingCart;
import com.evstratov.market.entities.User;
import com.evstratov.market.services.ProductService;
import com.evstratov.market.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ShoppingCartController {
    private ProductService productService;
    private ShoppingCartService shoppingCartService;

    @GetMapping("/cart")
    public String showCart(Model model, HttpSession session) {
        ShoppingCart cart = shoppingCartService.getCart(session);
        model.addAttribute("cartItems", cart.getOrderItems());
        model.addAttribute("totalCost", cart.getTotalCost());
        return "cartPage";
    }

    @GetMapping("addToCart/{id}")
    public String addToCart(@PathVariable(value = "id") Long id, HttpSession session) {
        Optional<Product> product = productService.getProductById(id);

        if (product.isPresent()) {
            ShoppingCart cart = shoppingCartService.getCart(session);
            cart.addProduct(product.get());
        }
        return "redirect:/cart";
    }

    @GetMapping("removeFromCart/{id}")
    public String removeFromCart(@PathVariable("id") Long id, HttpSession session) {
        Optional<Product> product = productService.getProductById(id);
        ShoppingCart cart = shoppingCartService.getCart(session);
        cart.removeProduct(product.get());
        return "redirect:/cart";
    }

    @GetMapping("/orderConfirmation")
    public String orderConfirmation(@AuthenticationPrincipal User user, Model model, HttpSession session) {
        if (user == null) return "login";
        ShoppingCart cart = shoppingCartService.getCart(session);
        model.addAttribute("cart", cart);
        return "orderConfirmPage";
    }

    @PostMapping("makeOrder")
    public String makeOrder(@AuthenticationPrincipal User user,
                            @RequestParam("address") String address,
                            HttpSession session
    ) {
        shoppingCartService.makeOrder(shoppingCartService.getCart(session), user, address);
        return "successOrder";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
}
