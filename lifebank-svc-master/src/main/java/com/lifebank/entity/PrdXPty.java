package com.lifebank.entity;

public class PrdXPty {
    private String productTypeName;
    private Integer productId;
    private String productName;

    public PrdXPty(String productTypeName, Integer productId, String productName) {
        this.productTypeName = productTypeName;
        this.productId = productId;
        this.productName = productName;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
