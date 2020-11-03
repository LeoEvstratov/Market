package com.evstratov.market.controllers;

import com.evstratov.market.entities.Product;
import com.evstratov.market.entities.ShoppingCart;
import com.evstratov.market.services.ProductService;
import com.evstratov.market.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api")
@org.springframework.web.bind.annotation.RestController
public class RestController {
    private ProductService productService;
    private ShoppingCartService shoppingCartService;

    @GetMapping("/products")
    public List<Product> getAllProducts(Model model) {
        return productService.getAllProductsList();
    }

    //    @PostMapping(path = "/products")
//    public List<Product> postAllProducts(@RequestBody String postPayload) {
//        System.out.println(postPayload);
//        return productService.getAllProductsList();
//    }

    @GetMapping("/addToCart/{id}")
    public int addProductToCart(HttpSession session, @PathVariable(name = "id") Long id) {
        Optional<Product> product = productService.getProductById(id);

        if (product.isPresent()) {
            ShoppingCart cart = shoppingCartService.getCart(session);
            cart.addProduct(product.get());
            System.out.println(product.get().toString() + "added to cart");
        }
        return HttpStatus.OK.value();
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
