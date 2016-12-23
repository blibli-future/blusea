package com.blibli.future.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private String note;
    private int quantities;
    private int totalPrices;
    @Transient
    private int noDiscountPrice;
    @Transient
    private int discountAmount;

    /**
     * Order Status Explanation
     * 0 = customer still shopping
     * 1 = customer checkout and pay dp, waiting for confirmation from catering
     * 2 = catering say OK and starting to prepare order
     * 3 = order shipped and completed
     */
    private int status;
    public static int ORDER_STATUS_CART = 0;
    public static int ORDER_STATUS_PENDING = 1;
    public static int ORDER_STATUS_WAITING = 2;
    public static int ORDER_STATUS_COMPLETE = 3;

    //relationship begins
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Catering catering;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
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

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public int getTotalPrices() {
        return totalPrices;
    }

    public void updateTotalPrices() {
        int tmp = 0;
        for (OrderDetail od: this.orderDetails) {
            tmp += od.getDiscountedPrice() * this.quantities;
        }
        totalPrices = tmp;
    }

    public int getNoDiscountPrice() {
        int noDiscount = 0;
        for (OrderDetail od: this.orderDetails) {
            noDiscount += od.getProduct().getPrice() * this.quantities;
        }
        return noDiscount;
    }

    public int getDiscountAmount() {
        return getTotalPrices() - getNoDiscountPrice();
    }

    public void setTotalPrices(int totalPrices) {
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

    public int getDp() {
        return this.catering.getDp() * this.getTotalPrices() / 100;
    }

    public int getRemainingPayment() {
        return getTotalPrices() - getDp();
    }

    public boolean isCart() {
        return this.status == ORDER_STATUS_CART;
    }

    public boolean isPending() {
        return this.status == ORDER_STATUS_PENDING;
    }

    public boolean isWaiting() {
        return this.status == ORDER_STATUS_WAITING;
    }

    public boolean isComplete() {
        return this.status == ORDER_STATUS_COMPLETE;
    }

    public boolean isNotComplete() {
        return this.status != ORDER_STATUS_COMPLETE;
    }

    public String getPlainTextStatus() {
        String plainText = "";
        if(isCart()) {
            plainText = "Menunggu pembayaran DP dari pembeli.";
        } else if (isPending()) {
            plainText = "Menunggu konfirmasi penerimaan pesanan dari pemilik catering.";
        } else if (isWaiting()) {
            plainText = "Menunggu pesanan disiapkan oleh catering.";
        } else if (isComplete()) {
            plainText = "Pesanan selesai";
        }
        return plainText;
    }

    public String getPlainTextDeliveryDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(deliveryDate);
    }

    public String getPlainTextCreateDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(createDate);
    }
}
