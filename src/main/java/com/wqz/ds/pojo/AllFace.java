package com.wqz.ds.pojo;

import java.util.Date;

public class AllFace {
    private Integer id;

    private Integer cameraid;

    private String picurl;

    private Date datetime;

    public Byte isMale;
    public Integer age;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCameraid() {
        return cameraid;
    }

    public void setCameraid(Integer cameraid) {
        this.cameraid = cameraid;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
