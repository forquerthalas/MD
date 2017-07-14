package com.lost.administrator.md.entity;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class Store {
    private String name,canOrder,url,when;
    private int distance,time,qiSong,peiSong;
    public Store() {
    }

    public Store(String name, int distance, String canOrder, String when,
                 int time, int qiSong, int peiSong, String url) {
        this.name = name;
        this.distance = distance;
        this.canOrder = canOrder;
        this.when = when;
        this.time = time;
        this.qiSong = qiSong;
        this.peiSong = peiSong;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getCanOrder() {
        return canOrder;
    }

    public void setCanOrder(String canOrder) {
        this.canOrder = canOrder;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getQiSong() {
        return qiSong;
    }

    public void setQiSong(int qiSong) {
        this.qiSong = qiSong;
    }

    public int getPeiSong() {
        return peiSong;
    }

    public void setPeiSong(int peiSong) {
        this.peiSong = peiSong;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
