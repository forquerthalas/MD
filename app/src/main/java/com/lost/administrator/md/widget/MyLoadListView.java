package com.lost.administrator.md.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.lost.administrator.md.R;


/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class MyLoadListView extends ListView implements  AbsListView.OnScrollListener{
    private View footer;
    //是否到底部 如果第一个可见+可见的总数==所有item数量则到底部

    //第一个可见的item ,可见的item的数量,所有item的数量
    private int firstVisibleItem,  visibleItemCount, totalItemCount;

    private OnLoadListener onLoadListener;
    private boolean isLoading =false;

    public MyLoadListView(Context context) {
        super(context);
        initview(context);
    }

    public void setOnLoadListener(OnLoadListener listener){
        this.onLoadListener=listener;
    }

    public MyLoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }

    public MyLoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context);
    }
    private void initview(Context context) {

        footer= LayoutInflater.from(context).inflate(R.layout.footer_layout,null);
        footer.setVisibility(View.GONE);
        footer.setPadding(0,-footer.getHeight(),0,0);
        this.setOnScrollListener(this);
        this.addFooterView(footer);

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if(firstVisibleItem+visibleItemCount==totalItemCount && scrollState==SCROLL_STATE_IDLE){
            footer.setVisibility(View.VISIBLE);
            footer.setPadding(0,0,0,0);
            if (onLoadListener!=null) {
                if (!isLoading) {
                    isLoading = true;
                    onLoadListener.onLoad();
                }
            }
        }
    }
    public void startLoad(){
        footer.setPadding(0, 0, 0, 0);
        footer.setVisibility(View.VISIBLE);
        if (onLoadListener!=null) {
            if (!isLoading) {
                isLoading = true;
                onLoadListener.onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        this.firstVisibleItem=firstVisibleItem;
        this.visibleItemCount=visibleItemCount;
        this.totalItemCount=totalItemCount;
        if(onLoadListener!=null){
            onLoadListener.onScroll(view,firstVisibleItem);
        }

    }
    //加载完成时隐藏底部的View footer
    public void loadComplete(){
        footer.setPadding(0, -1 * footer.getHeight(), 0, 0);
        footer.setVisibility(View.GONE);
        isLoading=false;
    }


    public interface OnLoadListener{
        void onLoad();
        //用于判断是不是完全在顶部
        void onScroll(AbsListView view, int firstVisibleItem);
    }


}
