package com.lifebank.component.parser;

import com.lifebank.entity.PrdXPty;
import com.lifebank.pojo.response.products.IProductsResponse;

public interface IProductsParser {
    public IProductsResponse parse(Iterable<PrdXPty> records);
}
