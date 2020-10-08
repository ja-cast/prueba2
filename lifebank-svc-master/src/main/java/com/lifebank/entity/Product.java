package com.lifebank.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="lb_prc_product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="prc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name="prc_name")
    String name;
    @Column(name="prc_product")
    Integer productType;
    @Column(name="prc_user")
    String user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prc_product", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private ProductType productTypeEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ProductType getProductTypeEntity() {
        return productTypeEntity;
    }

    public void setProductTypeEntity(ProductType productTypeEntity) {
        this.productTypeEntity = productTypeEntity;
    }
}
