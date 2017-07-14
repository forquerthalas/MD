package com.lost.administrator.md.entity;

import java.util.List;

public class GoodsType {

    private int id;
    private String type;
    private String createtime;
    private List<GoodsInfo> product;
    public GoodsType(String type){
        this.type=type;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public List<GoodsInfo> getProduct() {
        return product;
    }

    public void setProduct(List<GoodsInfo> product) {
        this.product = product;
    }
}
