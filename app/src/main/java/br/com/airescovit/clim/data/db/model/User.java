package br.com.airescovit.clim.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Logics on 12/01/2018.
 */

@Entity
public class User {
    private String name;
    private String email;
    @Generated(hash = 1297780640)
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
