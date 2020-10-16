package com.evstratov.market.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private double subtotalPrice;


    public OrderItem(Product product) {
        this.product = product;
        quantity = 1;
        subtotalPrice = product.getPrice();
    }

    public OrderItem() {
    }

    public void increaseQuantity() {
        quantity++;
        subtotalPrice = product.getPrice() * quantity;
    }

    public void decreaseQuantity() {
        quantity--;
        subtotalPrice = product.getPrice() * quantity;
    }

    public double getSubtotalPrice() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (quantity != orderItem.quantity) return false;
        if (Double.compare(orderItem.subtotalPrice, subtotalPrice) != 0) return false;
        if (id != null ? !id.equals(orderItem.id) : orderItem.id != null) return false;
        return product != null ? product.equals(orderItem.product) : orderItem.product == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(subtotalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
