package com.lost.administrator.md.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class AllOrder {
    private String url,name;
    private float pay_true;
    private int state;//1已付款 0 未付款
    private List<Order> list;

    public AllOrder() {

    }

    public AllOrder(String url, String name, int state, float pay_true, List<Order> list) {
        this.url = url;
        this.name = name;
        this.state = state;
        this.pay_true = pay_true;
        this.list = list;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getPay_true() {
        return pay_true;
    }

    public void setPay_true(float pay_true) {
        this.pay_true = pay_true;
    }

    public List<Order> getList() {
        return list;
    }

    public void setList(List<Order> list) {
        this.list = list;
    }
}
