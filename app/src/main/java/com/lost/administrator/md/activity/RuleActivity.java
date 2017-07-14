package com.lost.administrator.md.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lost.administrator.md.R;

public class RuleActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll_rule_1,ll_rule_2,ll_rule_3,ll_rule_4,ll_rule_5,
            ll_rule_6,ll_rule_7,ll_rule_8,ll_rule_9,ll_rule_0;
    private ImageView iv_rule1,iv_rule2,iv_rule3,iv_rule4,iv_rule5,iv_rule6,
            iv_rule7,iv_rule8,iv_rule9,iv_rule0;
    private Animation anim_show,anim_miss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        anim_show= AnimationUtils.loadAnimation(this,R.anim.anim_show);
        anim_miss= AnimationUtils.loadAnimation(this,R.anim.anim_miss);
    }

    private void initView() {
        ll_rule_1= (LinearLayout) findViewById(R.id.ll_rule_1);
        ll_rule_2= (LinearLayout) findViewById(R.id.ll_rule_2);
        ll_rule_3= (LinearLayout) findViewById(R.id.ll_rule_3);
        ll_rule_4= (LinearLayout) findViewById(R.id.ll_rule_4);
        ll_rule_5= (LinearLayout) findViewById(R.id.ll_rule_5);
        ll_rule_6= (LinearLayout) findViewById(R.id.ll_rule_6);
        ll_rule_7= (LinearLayout) findViewById(R.id.ll_rule_7);
        ll_rule_8= (LinearLayout) findViewById(R.id.ll_rule_8);
        ll_rule_9= (LinearLayout) findViewById(R.id.ll_rule_9);
        ll_rule_0= (LinearLayout) findViewById(R.id.ll_rule_10);

        iv_rule1= (ImageView) findViewById(R.id.iv_rule1);
        iv_rule2= (ImageView) findViewById(R.id.iv_rule2);
        iv_rule3= (ImageView) findViewById(R.id.iv_rule3);
        iv_rule4= (ImageView) findViewById(R.id.iv_rule4);
        iv_rule5= (ImageView) findViewById(R.id.iv_rule5);
        iv_rule6= (ImageView) findViewById(R.id.iv_rule6);
        iv_rule7= (ImageView) findViewById(R.id.iv_rule7);
        iv_rule8= (ImageView) findViewById(R.id.iv_rule8);
        iv_rule9= (ImageView) findViewById(R.id.iv_rule9);
        iv_rule0= (ImageView) findViewById(R.id.iv_rule10);

    }

    private void initEvent() {
        iv_rule1.setOnClickListener(this);
        iv_rule2.setOnClickListener(this);
        iv_rule3.setOnClickListener(this);
        iv_rule4.setOnClickListener(this);
        iv_rule5.setOnClickListener(this);
        iv_rule6.setOnClickListener(this);
        iv_rule7.setOnClickListener(this);
        iv_rule8.setOnClickListener(this);
        iv_rule9.setOnClickListener(this);
        iv_rule0.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_rule1:
                show(ll_rule_1,iv_rule1);
                break;
            case R.id.iv_rule2:
                show(ll_rule_2,iv_rule2);
                break;
            case R.id.iv_rule3:
                show(ll_rule_3,iv_rule3);
                break;
            case R.id.iv_rule4:
                show(ll_rule_4,iv_rule4);
                break;
            case R.id.iv_rule5:
                show(ll_rule_5,iv_rule5);
                break;
            case R.id.iv_rule6:
                show(ll_rule_6,iv_rule6);
                break;
            case R.id.iv_rule7:
                show(ll_rule_7,iv_rule7);
                break;
            case R.id.iv_rule8:
                show(ll_rule_8,iv_rule8);
                break;
            case R.id.iv_rule9:
                show(ll_rule_9,iv_rule9);
                break;
            case R.id.iv_rule10:
                show(ll_rule_0,iv_rule0);
                break;
        }

    }

    private void show(LinearLayout ll,ImageView iv){
        if (ll.getVisibility()==View.VISIBLE){
            iv.startAnimation(anim_miss);
            ll.setVisibility(View.GONE);
        }else{
            iv.startAnimation(anim_show);
            ll.setVisibility(View.VISIBLE);
        }
    }
}
