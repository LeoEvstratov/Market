package com.evstratov.market.services;

import com.evstratov.market.entities.*;
import com.evstratov.market.repositories.OrderItemRepository;
import com.evstratov.market.repositories.OrderRepository;
import com.evstratov.market.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartService {
    private StatusRepository statusRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    public ShoppingCart getCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @Transactional
    public void makeOrder(ShoppingCart shoppingCart, User user) {
        Order order = new Order();
        Optional<OrderStatus> status = statusRepository.findById(1);
        if (status.isPresent()) order.setStatus(status.get());
        order.setCustomer(user);
        order.setOrderTime(new Date());
        orderRepository.save(order);

        order.setOrderItems(shoppingCart.getOrderItems());
        for (OrderItem o : order.getOrderItems()) {
            o.setOrder(order);
        }
        orderRepository.save(order);
       shoppingCart.clear();
    }

    @Autowired
    public void setStatusRepository(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


}
