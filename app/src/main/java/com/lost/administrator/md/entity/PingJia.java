package com.lost.administrator.md.entity;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class PingJia {
    private String type,count;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public PingJia(String type, String count) {
        this.type = type;
        this.count = count;
    }
    public PingJia() {

    }
}
