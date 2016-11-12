package com.blibli.future.model;

/**
 * Created by ARDI on 10/6/2016.
 */
import java.util.List;

import javax.persistence.*;


@Entity
public class Catering extends User {
//    private String username;
    private String cateringName;
    private String address;

    @Column(columnDefinition="text")
    private String description;
    private String phoneNumber;
    private String dp;

    //relationship begins
    @OneToMany(mappedBy = "catering", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;
    @ManyToOne
    private Order order;
    //relationship ends

    public Catering(){
        super();
    }
    
    public String getCateringName() {
        return cateringName;
    }

    public void setCateringName(String cateringName) {
        this.cateringName = cateringName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean hasProduct(Product products){
        for(Product containedProduct: getProducts()){
            if(containedProduct.getId() == products.getId()){
                return true;
            }
        }
        return false;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
