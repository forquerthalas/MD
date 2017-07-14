package com.lost.administrator.md.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.lost.administrator.md.R;
import com.lost.administrator.md.entity.MyPingJia;
import com.lost.administrator.md.utils.CommonAdapter;
import com.lost.administrator.md.utils.DensityUtils;
import com.lost.administrator.md.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MyPingJiaActivity extends AppCompatActivity implements View.OnScrollChangeListener, View.OnClickListener {
    private ListView listView;
    private LinearLayout ll_title;
    private ScrollView scroll;
    private List<MyPingJia> lists;
    private CommonAdapter<MyPingJia> adapter;
    private TextView tv_title;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ping_jia);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        listView= (ListView) findViewById(R.id.lv_myPingJia);
        ll_title= (LinearLayout) findViewById(R.id.ll_title_myPingJia);
        scroll= (ScrollView) findViewById(R.id.scroll_myPingJia);
        tv_title= (TextView) findViewById(R.id.tv_title_myPingJia);
        iv_back= (ImageView) findViewById(R.id.iv_back_myPingJia);
    }


    private void initData() {
        lists=new ArrayList<MyPingJia>();
        for (int i=1;i<10;i++){
            lists.add(new MyPingJia("","并没有名字"+i,"2012-02-31",getString(R.string.mpjia),"鸡翅膀","跑得快",5f,5f,5f));
        }
        adapter=new CommonAdapter<MyPingJia>(this,lists,R.layout.item_mypingjia_layout) {
            @Override
            public void convert(ViewHolder holder, MyPingJia m) {
                holder.setText(R.id.tv_name_myPingJia,m.getName())
                        .setText(R.id.tv_time_myPingJia,m.getTime())
                        .setRatingBar(R.id.ratingBar_myPingJia,m.getScore_all())
                        .setText(R.id.tv_store_myPingJia,"商家:"+m.getScore_store()+"分")
                        .setText(R.id.tv_send_myPingJia,"配送:"+m.getScore_send()+"分");
                if (m.getUrl().equals("")){
                    holder.setImageResource(R.id.iv_icon_myPingJia,R.drawable.default_mine);
                }else{
                    holder.setImgUrl(R.id.iv_icon_myPingJia,m.getUrl());
                }
                if (m.getZan().equals("")){
                    holder.setGone(R.id.ll_zan_myPingJia);
                }else {
                    holder.setVisible(R.id.ll_zan_myPingJia)
                            .setText(R.id.tv_zan_myPingjia,m.getZan());
                }
                if (m.getSendInfo().equals("")){
                    holder.setGone(R.id.ll_send_myPingJia);
                }else {
                    holder.setVisible(R.id.ll_send_myPingJia)
                            .setText(R.id.tv_sendInfo_myPingJia,m.getSendInfo());
                }
            }
        };
        listView.setAdapter(adapter);
        DensityUtils.setListViewHeightBasedOnChildren(listView);

    }

    private void initEvent() {
        scroll.setOnScrollChangeListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onScrollChange(View view, int x, int y, int oldx, int oldy) {
        float height=430f;
        float alpha=0;
//        Toast.makeText(MyPingJiaActivity.this,"滑动"+y+"alpha"+y/height*255, Toast.LENGTH_SHORT).show();

        if (y==0){

            ll_title.setBackgroundColor(Color.argb(0,255,255,255));
            tv_title.setTextColor(Color.argb(0,0,0,0));
        }
        ll_title.setBackgroundColor(Color.argb(y/150*255,255,255,255));
        tv_title.setTextColor(Color.argb(y/150*255,0,0,0));
        ll_title.setAlpha(y/height);
        tv_title.setAlpha(y/height);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back_myPingJia:
                finish();
                break;
        }
    }
}
