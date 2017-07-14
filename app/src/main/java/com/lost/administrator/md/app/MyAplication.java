package com.lost.administrator.md.app;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class MyAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
