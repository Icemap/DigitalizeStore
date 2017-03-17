package com.wqz.ds.pojo;

import java.util.Date;

public class StoreBillsPushMsg {
    private Integer id;

    private Integer storeId;

    private Date datetime;

    private String boughtMoney;

    private String boughtList;

    private String hold;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getBoughtMoney() {
        return boughtMoney;
    }

    public void setBoughtMoney(String boughtMoney) {
        this.boughtMoney = boughtMoney == null ? null : boughtMoney.trim();
    }

    public String getBoughtList() {
        return boughtList;
    }

    public void setBoughtList(String boughtList) {
        this.boughtList = boughtList == null ? null : boughtList.trim();
    }

    public String getHold() {
        return hold;
    }

    public void setHold(String hold) {
        this.hold = hold == null ? null : hold.trim();
    }
}