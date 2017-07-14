package com.lost.administrator.md.entity;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class Menu {
    private int img;
    private String name;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public Menu() {
    }
}
