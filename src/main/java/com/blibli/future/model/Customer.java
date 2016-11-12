package com.blibli.future.model;

import javax.persistence.*;

@Entity
public class Customer extends User {

    private String fullName;
    private String nickName;

    //relationship begins
    @OneToOne
    @JoinColumn(name="id")
    private Order order;
    //relationship ends

    public Customer() {
        super();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
