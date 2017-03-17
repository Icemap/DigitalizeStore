package com.wqz.ds.pojo;

public class CameraPushMsg {
    private Integer id;

    private Integer cameraId;

    private Byte isAdd;

    private Byte isMale;

    private Byte isEnterStore;

    private Integer storeId;

    private String hold;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }

    public Byte getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(Byte isAdd) {
        this.isAdd = isAdd;
    }

    public Byte getIsMale() {
        return isMale;
    }

    public void setIsMale(Byte isMale) {
        this.isMale = isMale;
    }

    public Byte getIsEnterStore() {
        return isEnterStore;
    }

    public void setIsEnterStore(Byte isEnterStore) {
        this.isEnterStore = isEnterStore;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getHold() {
        return hold;
    }

    public void setHold(String hold) {
        this.hold = hold == null ? null : hold.trim();
    }
}