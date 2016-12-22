package com.blibli.future.model;

/**
 * Created by ARDI on 10/6/2016.
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String photo;

    //relationship begins
    @OneToMany(mappedBy = "catering", fetch = FetchType.EAGER)
    private List<Product> products;
    @OneToMany(mappedBy = "catering", fetch = FetchType.EAGER)
    private List<Order> orders;
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

    public int getDp() {
        return Integer.parseInt(dp);
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Set<String> getAvailableCategory(){
        Set<String> categories = null;
        for(Product product : products){

        }
        return categories;
    }

    // HACKKK MOVE THIS TO USER MODEL

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
