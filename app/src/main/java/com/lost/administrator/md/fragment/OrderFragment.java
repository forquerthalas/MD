package com.lost.administrator.md.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.lost.administrator.md.R;
import com.lost.administrator.md.activity.LoginActivity;
import com.lost.administrator.md.adapter.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/7 0007.
 */
public class OrderFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private Button btn_login;

    //radio group radioButton
    private RadioGroup rg_order;
    private RadioButton rb_order,rb_pingjia;
    private ViewPager viewPager;
    private List<Fragment> list_fragment=new ArrayList<Fragment>();
    private AllOrderFragment tab1=new AllOrderFragment();
    private PingJiaFragment tab2=new  PingJiaFragment();
    private FragmentPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orderfragment_layout, null);
        initView(view);
        initData();
        initEvent();

        return view;

    }

    private void initData() {
        list_fragment.add(tab1);
        list_fragment.add(tab2);
        adapter=new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list_fragment.get(position);
            }
            @Override
            public int getCount() {
                return list_fragment.size();
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(2);
        rg_order.setOnCheckedChangeListener(this);
    }

    private void initEvent() {
        btn_login.setOnClickListener(this);
        rb_order.setOnClickListener(this);
        rb_pingjia.setOnClickListener(this);

    }

    private void initView(View view) {
        btn_login= (Button) view.findViewById(R.id.btn_login_order);
        viewPager= (ViewPager) view.findViewById(R.id.viewpager_order);
        rg_order= (RadioGroup) view.findViewById(R.id.radioGroup_order);
        rb_order= (RadioButton) view.findViewById(R.id.rb_allOrder_order);
        rb_pingjia= (RadioButton) view.findViewById(R.id.rb_pingJia_order);
        rg_order.check(R.id.rb_allOrder_order);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login_order:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rg_order.check(R.id.rb_allOrder_order);
                break;
            case 1:
                rg_order.check(R.id.rb_pingJia_order);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
       switch (i){
           case R.id.rb_allOrder_order:
               viewPager.setCurrentItem(0);
               break;
           case R.id.rb_pingJia_order:
               viewPager.setCurrentItem(1);
               break;
       }    }
}
