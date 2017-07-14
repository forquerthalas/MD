package com.lost.administrator.md.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.lost.administrator.md.R;
import com.lost.administrator.md.adapter.FragmentPagerAdapter;
import com.lost.administrator.md.fragment.OrderInfoFragment;
import com.lost.administrator.md.fragment.OrderStateFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderItemActivity extends FragmentActivity implements View.OnClickListener {
    private RadioGroup rg_order;
    private ViewPager viewPager;
    private OrderStateFragment tab1=new OrderStateFragment();
    private OrderInfoFragment tab2=new OrderInfoFragment();
    private FragmentPagerAdapter pagerAdapter;
    private List<Fragment> fragments=new ArrayList<Fragment>();
    private TextView tv1,tv2,tv_state,tv_info;
    private ImageView iv_call,iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);
        initView();
        initData();
        rg_order.check(R.id.rb_state_order);

    }

    private void initView() {
        rg_order= (RadioGroup) findViewById(R.id.radioGroup_order);
        viewPager= (ViewPager) findViewById(R.id.viewpager_order);
        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);
        tv_state= (TextView) findViewById(R.id.tv_state);
        tv_info= (TextView) findViewById(R.id.tv_info);
        iv_call= (ImageView) findViewById(R.id.iv_call);
        iv_back= (ImageView) findViewById(R.id.iv_orderBack);
        iv_call.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        tv_state.setOnClickListener(this);
        tv_info.setOnClickListener(this);
    }

    private void initData() {
        fragments.add(tab1);
        fragments.add(tab2);
        pagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rg_order.check(R.id.rb_state_order);
                        break;
                    case 1:
                        rg_order.check(R.id.rb_info_order);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(2);

        rg_order.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_state_order:
                        viewPager.setCurrentItem(0);
                        tv1.setVisibility(View.VISIBLE);
                        tv2.setVisibility(View.GONE);
                        tv_state.setTextColor(Color.BLACK);
                        tv_info.setTextColor(Color.GRAY);
                        break;
                    case R.id.rb_info_order:
                        viewPager.setCurrentItem(1);
                        tv1.setVisibility(View.GONE);
                        tv2.setVisibility(View.VISIBLE);
                        tv_state.setTextColor(Color.GRAY);
                        tv_info.setTextColor(Color.BLACK);
                        break;
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_call:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:10086"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.iv_orderBack:
                finish();
                break;
            case R.id.tv_state:
                rg_order.check(R.id.rb_state_order);
                break;
            case R.id.tv_info:
                rg_order.check(R.id.rb_info_order);
                break;
        }
    }
}
