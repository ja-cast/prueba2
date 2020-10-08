package com.lifebank.pojo.response.products;

import java.util.ArrayList;
import java.util.List;

public class ProductResponseNode {
    private String productName;
    private List<ProductDetail> products;

    public ProductResponseNode() {
        this.products = new ArrayList<ProductDetail>();
    }

    public String getProductName() {
        return productName;
    }

    public List<ProductDetail> getProducts() {
        return products;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProducts(List<ProductDetail> products) {
        this.products = products;
    }
}
