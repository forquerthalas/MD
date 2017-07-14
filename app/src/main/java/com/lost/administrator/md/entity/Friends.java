package com.lost.administrator.md.entity;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class Friends {
    private String url,name;

    public Friends() {
    }

    public Friends(String url, String name) {
        this.url = url;
        this.name = name;
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
}
