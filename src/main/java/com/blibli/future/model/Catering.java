package com.blibli.future.model;

/**
 * Created by ARDI on 10/6/2016.
 */
import java.util.List;

import javax.persistence.*;


@Entity
public class Catering extends User {
    private String username;
    private String cateringName;
//    private String email;
//    private String password;
    private String address;
    private String description;
    private String phoneNumber;

    @Override
    public String toString() {
        return "Catering{" +
//                "id=" + id +
                ", username='" + username + '\'' +
                ", cateringName='" + cateringName + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dp='" + dp + '\'' +
                ", products=" + products +
                '}';
    }

    private String dp;

    @OneToMany(mappedBy = "catering", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;

    public Catering(){
        super();
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
