package com.lost.administrator.md.entity;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class MyPingJia {
    private String url,name,time,content,zan,sendInfo;
    private float score_all,score_store,score_send;

    public MyPingJia() {
    }

    public MyPingJia(String url, String name, String time, String content, String zan, String sendInfo, float score_all, float score_store, float score_send) {
        this.url = url;
        this.name = name;
        this.time = time;
        this.content = content;
        this.zan = zan;
        this.sendInfo = sendInfo;
        this.score_all = score_all;
        this.score_store = score_store;
        this.score_send = score_send;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getZan() {
        return zan;
    }

    public void setZan(String zan) {
        this.zan = zan;
    }

    public String getSendInfo() {
        return sendInfo;
    }

    public void setSendInfo(String sendInfo) {
        this.sendInfo = sendInfo;
    }

    public float getScore_all() {
        return score_all;
    }

    public void setScore_all(float score_all) {
        this.score_all = score_all;
    }

    public float getScore_store() {
        return score_store;
    }

    public void setScore_store(float score_store) {
        this.score_store = score_store;
    }

    public float getScore_send() {
        return score_send;
    }

    public void setScore_send(float score_send) {
        this.score_send = score_send;
    }
}
