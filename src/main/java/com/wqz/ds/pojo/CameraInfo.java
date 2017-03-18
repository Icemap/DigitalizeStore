package com.wqz.ds.pojo;

public class CameraInfo {
    private Integer id;

    private String url;

    private Byte isEnter;

    private String area;

    private String hotPointPath;

    private String pathPath;

    private Integer storeId;

    private String hold;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getIsEnter() {
        return isEnter;
    }

    public void setIsEnter(Byte isEnter) {
        this.isEnter = isEnter;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getHotPointPath() {
        return hotPointPath;
    }

    public void setHotPointPath(String hotPointPath) {
        this.hotPointPath = hotPointPath == null ? null : hotPointPath.trim();
    }

    public String getPathPath() {
        return pathPath;
    }

    public void setPathPath(String pathPath) {
        this.pathPath = pathPath == null ? null : pathPath.trim();
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