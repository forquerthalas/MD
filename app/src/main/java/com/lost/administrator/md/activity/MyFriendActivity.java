package com.lost.administrator.md.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lost.administrator.md.R;
import com.lost.administrator.md.entity.Friends;
import com.lost.administrator.md.utils.CommonAdapter;
import com.lost.administrator.md.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyFriendActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private ListView listView;
    private CommonAdapter<Friends> adapter;
    private List<Friends> list=new ArrayList<Friends>();
    private String url="http://p0.so.qhmsg.com/dm/664_417_/t016051cbaa49b1246d.jpg";
    private TextView tv_invite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend);
        iv_back= (ImageView) findViewById(R.id.iv_back_friends);
        listView= (ListView) findViewById(R.id.lv_friend);
        tv_invite= (TextView) findViewById(R.id.tv_invite_friend);
        iv_back.setOnClickListener(this);
        tv_invite.setOnClickListener(this);


        for (int i=1;i<10;i++){
            list.add(new Friends(url,"小鹿"+i));
        }
        adapter=new CommonAdapter<Friends>(this,list,R.layout.item_friends_layout) {
            @Override
            public void convert(ViewHolder holder, Friends friends) {
                holder.setText(R.id.tv_name_friends,friends.getName())
                        .setRoundImgUrl(R.id.iv_item_friends,friends.getUrl());
            }
        };
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back_friends:
                finish();
                break;
            case R.id.tv_invite_friend:
                Toast.makeText(this, "就不邀请", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
