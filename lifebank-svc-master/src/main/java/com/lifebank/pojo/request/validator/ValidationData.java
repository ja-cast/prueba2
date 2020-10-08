package com.lifebank.pojo.request.validator;
import io.jsonwebtoken.Claims;

import java.util.Date;

public class ValidationData implements IProductsValidationResponse {
    private int responseCode;
    private String user;
    private String password;
    private String jwt;
    private Claims jwtClaims;
    private int account;
    private Date startDate;
    private Date endDate;

    public ValidationData(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public Claims getJwtClaims() {
        return jwtClaims;
    }

    public void setJwtClaims(Claims jwtClaims) {
        this.jwtClaims = jwtClaims;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
