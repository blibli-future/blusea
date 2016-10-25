package com.blibli.future.model;

import javax.persistence.Entity;

/**
 * Created by dhika on 25/10/2016.
 */
@Entity
public class Consumer extends User {
    private String fullName;
    private String nickName;

    public Consumer() {
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
}
