package com.evstratov.market.services;

import com.evstratov.market.entities.Order;
import com.evstratov.market.entities.OrderStatus;
import com.evstratov.market.repositories.OrderRepository;
import com.evstratov.market.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private StatusRepository statusRepository;


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long orderId) {
        return orderRepository.getOne(orderId);
    }

    public void updateOrderStatus(Long id, OrderStatus status) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(status);
            orderRepository.save(order);
        }
    }

    public Set<OrderStatus> getAllOrderStatuses() {
        return statusRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setStatusRepository(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

}
