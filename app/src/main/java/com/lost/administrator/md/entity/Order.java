package com.lost.administrator.md.entity;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class Order {
    private String name;
    private int count;
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Order(String name, int count) {
        this.name = name;
        this.count = count;
    }  public Order(String name, int count,float price) {
        this.name = name;
        this.count = count;
        this.price=price;
    }
    public Order() {

    }
}
