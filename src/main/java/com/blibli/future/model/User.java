package com.blibli.future.model;

import javax.persistence.*;

@Entity
@Table(name="blusea_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fullName;
    private String nickName;
    private String email;
    private String password;

    public User() {
    }

    public User(String fullName, String nickName, String email) {
        this.fullName = fullName;
        this.nickName = nickName;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, fullName='%s', nickName='%s']",
                id, fullName, nickName);
    }
}
