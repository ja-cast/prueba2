package com.lifebank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="lb_usr_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="usr_code")
    private String userId;
    @Column(name="usr_password")
    private String password;
    @Column(name="usr_status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
