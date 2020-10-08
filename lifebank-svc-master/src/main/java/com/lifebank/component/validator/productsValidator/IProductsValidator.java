package com.lifebank.component.validator.productsValidator;

import com.lifebank.pojo.request.validator.IProductsValidationResponse;

public interface IProductsValidator {
    <T> IProductsValidationResponse validate(T input);
}
