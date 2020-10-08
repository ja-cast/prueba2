package com.lifebank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="lb_tpr_tipo_producto")
public class ProductType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="tpr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="tpr_description")
    private String description;
    @OneToMany(targetEntity = Product.class, mappedBy = "id", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Product> product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
