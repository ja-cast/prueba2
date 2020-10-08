package com.lifebank.pojo.response.products;

import java.util.ArrayList;
import java.util.List;

public class ProductsResponse implements IProductsResponse{
    private List<ProductResponseNode> products;

    public ProductsResponse() {
        this.products = new ArrayList<ProductResponseNode>();
    }

    public List<ProductResponseNode> getProductNodes() {
        return products;
    }

    public void setProductNodes(List<ProductResponseNode> products) {
        this.products = products;
    }
}
