package com.lost.administrator.md.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.lost.administrator.md.R;
import com.lost.administrator.md.entity.Store;
import com.lost.administrator.md.utils.CommonAdapter;
import com.lost.administrator.md.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyCollectActivity extends AppCompatActivity {

    private ListView listView;
    private CommonAdapter<Store> adapter;
    private List<Store> lists;
    private String url="http://img1.imgtn.bdimg.com/it/u=3309882942,388742491&fm=21&gp=0.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        initView();
        initData();
    }

    private void initView() {
        listView= (ListView) findViewById(R.id.lv_collect);
    }

    private void initData() {
        lists=new ArrayList<Store>();

        for (int i=1;i<6;i++){
            lists.add(new Store("收藏"+i,999,"接受预订","10:00",30,20,5,url));
        }
        adapter=new CommonAdapter<Store>(this,lists,R.layout.item_store_layout) {
            @Override
            public void convert(ViewHolder holder, Store store) {
                holder.setText(R.id.tv_name_store,store.getName())
                        .setImgUrl(R.id.iv_icon_store,store.getUrl())
                        .setText(R.id.tv_distance_store,store.getDistance()+"m")
                        .setText(R.id.tv_canOrder_store,store.getCanOrder())
                        .setText(R.id.tv_begin_store,store.getWhen()+"后开始配送")
                        .setText(R.id.tv_time_store,store.getTime()+"分钟")
                        .setText(R.id.tv_sendInfo_store,
                                "起送价 ￥"+store.getQiSong()+" | 配送费 ￥"+store.getPeiSong());
            }
        };
        listView.setAdapter(adapter);
    }
}
