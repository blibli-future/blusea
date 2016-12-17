package com.blibli.future.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ARDI on 11/2/2016.
 */
@Entity
@Table(name="blusea_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date createDate;
    private Date deliveryDate;
    private long quantities;
    private long totalPrices;
    private String note;

    /**
     * Order Status Explanation
     * 0 = customer still shopping
     * 1 = customer checkout and pay dp, waiting for confirmation from catering
     * 2 = catering say OK and starting to prepare order
     * 3 = order shipped and completed
     */
    private int status;
    public static int ORDER_STATUS_CART = 0;
    public static int ORDER_STATUS_CONFIRMATION = 1;
    public static int ORDER_STATUS_WAITING = 2;
    public static int ORDER_STATUS_COMPLETE = 3;

    //relationship begins
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Catering catering;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();
    //relationship ends


    public Order() {
        this.status = ORDER_STATUS_CART;
    }

    public long getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public long getQuantities() {
        return quantities;
    }

    public void setQuantities(long quantities) {
        this.quantities = quantities;
    }

    public long getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(long totalPrices) {
        this.totalPrices = totalPrices;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Catering getCatering() {
        return catering;
    }

    public void setCatering(Catering catering) {
        this.catering = catering;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        this.orderDetails.add(orderDetail);
    }

    public void addOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetails.addAll(orderDetail);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
