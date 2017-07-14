package com.lost.administrator.md.entity;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class FoodsInfo {
    private String url,name;
    private int monthCount,num,haoPing,chaPing;
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public FoodsInfo() {

    }

    public FoodsInfo(String url, String name, int monthCount, int num, int haoPing, int chaPing, float price) {
        this.url = url;
        this.name = name;
        this.monthCount = monthCount;
        this.num = num;
        this.haoPing = haoPing;
        this.chaPing = chaPing;
        this.price=price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getHaoPing() {
        return haoPing;
    }

    public void setHaoPing(int haoPing) {
        this.haoPing = haoPing;
    }

    public int getChaPing() {
        return chaPing;
    }

    public void setChaPing(int chaPing) {
        this.chaPing = chaPing;
    }
}
