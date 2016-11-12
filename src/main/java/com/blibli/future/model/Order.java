package com.blibli.future.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by ARDI on 11/2/2016.
 */

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idOrder;
    private Date orderDate;
    //deliv date
    private Date theDate;
    private long quantities;
    private long prices;

    //relationship begins
    @OneToOne(mappedBy = "order")
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Catering> caterings;
    @OneToMany(mappedBy = "catering", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;
    //relationship ends


    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getTheDate() {
        return theDate;
    }

    public void setTheDate(Date theDate) {
        this.theDate = theDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Catering> getCaterings() {
        return caterings;
    }

    public void setCaterings(List<Catering> caterings) {
        this.caterings = caterings;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public long getQuantities() {
        return quantities;
    }

    public void setQuantities(long quantities) {
        this.quantities = quantities;
    }

    public long getPrices() {
        return prices;
    }

    public void setPrices(long prices) {
        this.prices = prices;
    }


}
