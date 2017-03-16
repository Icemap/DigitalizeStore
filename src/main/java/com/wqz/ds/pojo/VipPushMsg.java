package com.wqz.ds.pojo;

import java.util.Date;

public class VipPushMsg {
    private Integer id;

    private Integer vipId;

    private Date datetime;

    private Byte isBought;

    private String boughtMoney;

    private String boughtList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Byte getIsBought() {
        return isBought;
    }

    public void setIsBought(Byte isBought) {
        this.isBought = isBought;
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
}