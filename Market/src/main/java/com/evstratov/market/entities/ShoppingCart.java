package com.evstratov.market.entities;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ShoppingCart {
    private Set<OrderItem> orderItems = new HashSet<>();
    private double totalCost;

    public void addProduct(Product product) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProduct().equals(product)) {
                orderItem.increaseQuantity();
                return;
            }
        }
        orderItems.add(new OrderItem(product));
    }

    public void removeProduct(Product product) {//todo not removing orderitem when quantity is zero
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProduct().equals(product)) {
                orderItem.decreaseQuantity();
                break;
            }
        }
        orderItems.removeIf(e -> e.getQuantity() <= 0);
    }

    public double getTotalCost() {
        for (OrderItem orderItem : orderItems) {
            totalCost += orderItem.getSubtotalPrice();
        }
        return totalCost;
    }
}
