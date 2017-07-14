package com.lost.administrator.md.entity;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class Address {
    private String Street,door,name,sex,tel;
    private boolean delMode=false;

    public Address() {
    }

    public Address(String street, String door, String name, String sex, String tel) {
        Street = street;
        this.door = door;
        this.name = name;
        this.sex = sex;
        this.tel = tel;
        delMode=false;
    }

    public boolean isDelMode() {
        return delMode;
    }

    public void setDelMode(boolean delMode) {
        this.delMode = delMode;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
