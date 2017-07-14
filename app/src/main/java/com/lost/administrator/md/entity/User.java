package com.lost.administrator.md.entity;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class User {
    private String name,psw,id;
    private float banlance;//余额
    public User() {
    }

    public User(String name, String psw, String id) {
        this.name = name;
        this.psw = psw;
        this.id = id;
    }

    public float getBanlance() {
        return banlance;
    }

    public void setBanlance(float banlance) {
        this.banlance = banlance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
