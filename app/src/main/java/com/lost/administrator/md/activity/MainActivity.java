package com.lost.administrator.md.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.lost.administrator.md.R;
import com.lost.administrator.md.adapter.FragmentPagerAdapter;
import com.lost.administrator.md.fragment.HomeFragment;
import com.lost.administrator.md.fragment.MineFragment;
import com.lost.administrator.md.fragment.OrderFragment;
import com.lost.administrator.md.fragment.TextFragment;
import com.lost.administrator.md.widget.MyRadioButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "MainActivity";
    private RadioGroup radioGroup;
    private HomeFragment tab1=new HomeFragment();
    private OrderFragment tab2=new OrderFragment();
    private MineFragment tab3=new MineFragment();

    private List<Fragment> fragments=new ArrayList<Fragment>();
    private MyRadioButton rb_home;
    private FragmentPagerAdapter adapter;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb_home = (MyRadioButton) findViewById(R.id.rb_home);
        viewPager= (ViewPager) findViewById(R.id.viewpager);

        radioGroup.check(R.id.rb_home);
        fragments.add(tab1);
        fragments.add(tab2);
        fragments.add(tab3);



        adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.setOffscreenPageLimit(2);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "onPageSelected: "+position);
        switch (position){
            case 0:
                radioGroup.check(R.id.rb_home);
                break;
            case 1:
                radioGroup.check(R.id.rb_order);
                break;
            case 2:
                radioGroup.check(R.id.rb_mine);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        Log.d(TAG, "onCheckedChanged: "+i);
        switch (i){
            case R.id.rb_home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rb_order:
                viewPager.setCurrentItem(1);
                break;
            case R.id.rb_mine:
                viewPager.setCurrentItem(2);
                break;
        }


    }

}
