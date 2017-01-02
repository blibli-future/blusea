package com.blibli.future.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="persistent_logins")
public class PersistentLogin {
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Id
    private String session;
    private String username;
    private String token;
    private Date lastUsed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}
