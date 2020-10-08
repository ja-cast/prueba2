package com.lifebank.pojo.request.transactions;

import java.util.Date;

public class TransactionsRequest {
    private String jwt;
    private int account;
    private Date startDate;
    private Date endDate;

    public TransactionsRequest(String jwt, int account, Date startDate, Date endDate) {
        this.jwt = jwt;
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
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
