package com.blibli.future.model;

/**
 * Created by ARDI on 10/6/2016.
 */
import java.util.List;

import javax.persistence.*;


@Entity
public class Catering {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String username;
    private String cateringName;
    private String email;
    private String password;
    private String address;
    private String description;
    private String phoneNumber;

    @Override
    public String toString() {
        return "Catering{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", cateringName='" + cateringName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getCateringName() {
        return cateringName;
    }

    public void setCateringName(String cateringName) {
        this.cateringName = cateringName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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