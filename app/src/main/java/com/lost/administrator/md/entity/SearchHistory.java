package com.lost.administrator.md.entity;

/**
 * Created by Administrator on 2016/12/10 0010.
 */

public class SearchHistory {
    private String title;
    private int type;

    public SearchHistory() {
    }

    public SearchHistory(int url, String title) {
        this.type = url;
        this.title = title;
    }

    public int getUrl() {
        return type;
    }

    public void setUrl(int url) {
        this.type = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
