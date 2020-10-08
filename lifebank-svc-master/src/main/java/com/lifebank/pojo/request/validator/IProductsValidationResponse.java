package com.lifebank.pojo.request.validator;
import io.jsonwebtoken.Claims;

public interface IProductsValidationResponse {
    public int getResponseCode();
    public Claims getJwtClaims();
    public String getUser();
    public String getJwt();
}
