package com.lost.administrator.md.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.lzy.widget.HeaderViewPager;

import com.lzy.widget.tab.PagerSlidingTabStrip;
import com.lost.administrator.md.R;
import com.lost.administrator.md.fragment.HeaderViewPagerFragment;

import com.lost.administrator.md.fragment.ProductsFragment;
import com.lost.administrator.md.fragment.StoreFragment;
import com.lost.administrator.md.fragment.StorePingJiaFragment;
import com.lost.administrator.md.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class StoreInfoActivity extends BaseActivity {

    public List<HeaderViewPagerFragment> fragments;
    private HeaderViewPager scrollableLayout;
    private View titleBar_Bg;
    private TextView titleBar_title;
    private View status_bar_fix;
    private View titleBar;
    private ImageView iv_back;
    //收藏checkbox
    private CheckBox cb_coll;
    //收藏详情的TextView 店家名称
    private TextView tv_coll,tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);

        //内容的fragment
        fragments = new ArrayList<>();
        fragments.add(new ProductsFragment());
        fragments.add(new StoreFragment());
        fragments.add(new StorePingJiaFragment());

        cb_coll= (CheckBox) findViewById(R.id.cb_coll_store);
        tv_coll= (TextView) findViewById(R.id.tv_coll);
        tv_title= (TextView) findViewById(R.id.tv_name_store);
        cb_coll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    Toast.makeText(StoreInfoActivity.this, "收藏成功!可在我的收藏查看", Toast.LENGTH_SHORT).show();
                    tv_coll.setText("已收藏");
                }else{
                    Toast.makeText(StoreInfoActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                    tv_coll.setText("收藏");
                }
            }
        });

        scrollableLayout = (HeaderViewPager) findViewById(R.id.scrollableLayout);
        titleBar = findViewById(R.id.titleBar);
        titleBar_Bg = titleBar.findViewById(R.id.bg);
        //当状态栏透明后，内容布局会上移，这里使用一个和状态栏高度相同的view来修正内容区域
        status_bar_fix = titleBar.findViewById(R.id.status_bar_fix);
        iv_back= (ImageView) titleBar.findViewById(R.id.back_title);
        status_bar_fix.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.getStatusHeight(this)));
        titleBar_title = (TextView) titleBar.findViewById(R.id.title);
        titleBar_Bg.setAlpha(0);
        status_bar_fix.setAlpha(0);
        titleBar_title.setText("标题栏透明度(0%)");
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //tab标签和内容viewpager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new ContentAdapter(getSupportFragmentManager()));
        tabs.setViewPager(viewPager);
        scrollableLayout.setCurrentScrollableContainer(fragments.get(0));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                scrollableLayout.setCurrentScrollableContainer(fragments.get(position));
            }
        });
        scrollableLayout.setOnScrollListener(new HeaderViewPager.OnScrollListener() {
            @Override
            public void onScroll(int currentY, int maxY) {
                //让头部具有差速动画,如果不需要,可以不用设置
//                pagerHeader.setTranslationY(currentY / 2);
                //动态改变标题栏的透明度,注意转化为浮点型
                float alpha = 1.0f * currentY / maxY;
                titleBar_Bg.setAlpha(alpha);
                //注意头部局的颜色也需要改变
                status_bar_fix.setAlpha(alpha);
                titleBar_title.setText("标题栏透明度(" + (int) (alpha * 100) + "%)");
            }
        });
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //当前窗口获取焦点时，才能正真拿到titlebar的高度，此时将需要固定的偏移量设置给scrollableLayout即可
        scrollableLayout.setTopOffset(titleBar.getHeight());
    }

    /**
     * 内容页的适配器
     */
    private class ContentAdapter extends FragmentPagerAdapter {

        public ContentAdapter(FragmentManager fm) {
            super(fm);
        }

        public String[] titles = new String[]{"      点菜    ", "     商家    ", "     评价      "};

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
