package com.blibli.future.model;

import javax.persistence.*;

/**
 * Created by dhika on 12/11/2016.
 */
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Order order;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(Order o, Product p) {
        this.product = p;
        setOrder(o);
    }

    public long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        order.addOrderDetail(this);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPrice() {
        return product.getPrice() * order.getQuantities();
    }

    public int getDiscountedPrice() {
        return this.product.getPrice(order.getQuantities());
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order=" + order.getId() +
                ", product=" + product +
                '}';
    }
}
