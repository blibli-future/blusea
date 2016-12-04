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
    @ManyToOne
    private Product product;

    private int quantity;
    private int price;
    private int note;

    public OrderDetail() {
    }

    public OrderDetail(Order o, Product p, int price, int quantity) {
        this.product = p;
        setOrder(o);
        this.price = price;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order=" + order.getId() +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", note=" + note +
                '}';
    }
}
