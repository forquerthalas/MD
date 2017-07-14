package com.lost.administrator.md.entity;

import java.util.List;

public class Goods {

    private String type;
    private List<GoodsInfo> dishes;

    public Goods(String type, List<GoodsInfo> dishes) {
        this.type = type;
        this.dishes = dishes;
    }

    public String getType() {
        return type;
    }

    public List<GoodsInfo> getDishes() {
        return dishes;
    }

}
