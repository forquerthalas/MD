package com.lost.administrator.md.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import com.lost.administrator.md.R;
import com.lost.administrator.md.activity.LocationActivity;
import com.lost.administrator.md.activity.SearchActivity;
import com.lost.administrator.md.activity.StoreInfoActivity;
import com.lost.administrator.md.entity.Menu;
import com.lost.administrator.md.entity.Store;
import com.lost.administrator.md.utils.CommonAdapter;
import com.lost.administrator.md.utils.DensityUtils;
import com.lost.administrator.md.utils.GlideImageLoader;
import com.lost.administrator.md.utils.ViewHolder;
import com.lost.administrator.md.widget.MyLoadListView;
import com.lost.administrator.md.widget.ObservableScrollView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/7 0007.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, ObservableScrollView.OnScrollChangeListener,
                         SwipeRefreshLayout.OnRefreshListener, MyLoadListView.OnLoadListener {


    private ObservableScrollView scrollView;
    private LinearLayout search_goods,search_position,ll_search;

    private List<String> list_img=new ArrayList<String>();
    private Banner banner;
    //定位控件所在布局
    private LinearLayout ll_dingWei;
    //经纬度
    private double latitude;
    private double longitude;
    //显示定位地点的textView
    private TextView tv_address;


    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    private String aoiName="";
    private PoiSearch poiSearch;
    private PoiSearch.Query query;


    //定位请求码
    private final  int REQUEST_LOCATION=0x11;

    //加载时的动画
    private AnimationDrawable anim;
    private ImageView iv_anim;
    private LinearLayout ll_animation;
    //分类菜单
    private GridView gridView;
    private List<Menu> list_menu=new ArrayList<Menu>();
    private CommonAdapter<Menu> adapter_menu;
    //下拉刷新
    private SwipeRefreshLayout refresh;
    //测试商家
    private String url="http://p3.so.qhmsg.com/bdr/_240_/t01949e1bf746570228.jpg";
    private List<Store> list_store=new ArrayList<Store>();
    private CommonAdapter<Store> adapter_store;
    private MyLoadListView listView_store;

    //是否加载完毕全部商家
    private boolean isOver=false;
    private int loadTime=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.homefragment_layout, null);

        initView(view);
        initData();
        LocationAsync async=new LocationAsync();
        async.execute(mLocationClient);
        anim.start();
        initEvent();
//        anim.stop();
//        //加载完成后停止动画
//        ll_animation.setVisibility(View.GONE);
        return view;

    }
    private void initEvent() {
        ll_dingWei.setOnClickListener(this);
        ll_search.setOnClickListener(this);
        scrollView.setScrollViewListener(this);
        //下拉监听
        refresh.setOnRefreshListener(this);
        listView_store.setOnLoadListener(this);


        //判断是否到底部
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        int scrollY = v.getScrollY();
                        int height = v.getHeight();
                        int scrollViewMeasuredHeight = scrollView.getChildAt(0).getMeasuredHeight();

                        if ((scrollY + height) == scrollViewMeasuredHeight) {
                           // 滑动到底部
                            if (!isOver){
                                scrollView.setEnabled(false);
                                listView_store.startLoad();
                                scrollView.setEnabled(true);
                            }else{
                                Toast.makeText(getActivity(), "更多商家正在筹备中", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                }
                return false;
            }
        });
      listView_store.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Intent intent = new Intent(getContext(), StoreInfoActivity.class);
              startActivity(intent);
          }
      });
    }

    private void initData() {
        loadTime=0;
        list_img.add("http://p2.so.qhmsg.com/bdr/_240_/t01763685e6fa84f6c7.jpg");
        list_img.add("http://p4.so.qhmsg.com/bdr/_240_/t01bc1f29c745f6b5fe.jpg");
        list_img.add("https://p.ssl.qhimg.com/dmsmfl/120_115_/t01e9e9008332aaee5d.png");
        banner.setImages(list_img);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
        //banner的点击事件
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });

        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity().getApplicationContext());


        anim= (AnimationDrawable) iv_anim.getDrawable();

        //分类菜单
        list_menu.add(new Menu(R.drawable.m1,"美食"));
        list_menu.add(new Menu(R.drawable.m2,"超市"));
        list_menu.add(new Menu(R.drawable.m3,"鲜果购"));
        list_menu.add(new Menu(R.drawable.m4,"汉堡披萨"));
        list_menu.add(new Menu(R.drawable.m5,"正餐优选"));
        list_menu.add(new Menu(R.drawable.m6,"美团专送"));
        list_menu.add(new Menu(R.drawable.m7,"家常菜"));
        list_menu.add(new Menu(R.drawable.m8,"精选小吃"));
        adapter_menu=new CommonAdapter<Menu>(getActivity(),list_menu,R.layout.item_menu_layout) {
            @Override
            public void convert(ViewHolder holder, Menu menu) {
             holder.setImageResource(R.id.iv_menu,menu.getImg())
                     .setText(R.id.tv_menu,menu.getName());
            }
        };
        gridView.setAdapter(adapter_menu);
        //商家测试数据
        for (int i=1;i<6;i++){
            list_store.add(new Store("商家"+i,999,"接受预订","10:00",30,20,5,url));
        }
        adapter_store=new CommonAdapter<Store>(getActivity(),list_store,R.layout.item_store_layout) {
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
        listView_store.setAdapter(adapter_store);
        DensityUtils.setListViewHeightBasedOnChildren(listView_store);
    }

    private void initView(View view) {
        banner= (Banner) view.findViewById(R.id.banner);
        ll_dingWei= (LinearLayout) view.findViewById(R.id.ll_location);
        ll_search= (LinearLayout) view.findViewById(R.id.ll_search_home);
        tv_address= (TextView) view.findViewById(R.id.tv_address);
        scrollView= (ObservableScrollView) view.findViewById(R.id.scrollView);
        search_goods= (LinearLayout) view.findViewById(R.id.search_goods);
        search_position= (LinearLayout) view.findViewById(R.id.ll_location);
        iv_anim= (ImageView) view.findViewById(R.id.iv_animation);
        ll_animation= (LinearLayout) view.findViewById(R.id.ll_animation);
        gridView= (GridView) view.findViewById(R.id.gv_menu);
        refresh= (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        listView_store= (MyLoadListView) view.findViewById(R.id.lv_store);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_location:
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);

                startActivityForResult(intent,REQUEST_LOCATION);
                break;
            case R.id.ll_search_home:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_LOCATION){
            if (resultCode==getActivity().RESULT_OK){
                aoiName = data.getStringExtra("address");
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("Login",
                        getActivity().MODE_PRIVATE).edit();

                editor.putString("address",aoiName);
                editor.commit();
                tv_address.setText(aoiName);
            }
        }
    }
    //重写滑动方法
    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {


        float height=120f;
        //根据纵向位移计算透明度
        float alpha=y/height;
        //255是最大值 根据比例获取实际值
        alpha=alpha*255;
        //argb是透明度
        if (y==0){
            refresh.setEnabled(true);
        }else {
            refresh.setEnabled(false);
        }
        if(y<=height &&  y>=2){
            search_goods.setBackgroundColor(Color.argb(((int)alpha),255,230,64));

        }

        if (y>=2){


            search_goods.setVisibility(View.VISIBLE);

            search_position.setVisibility(View.GONE);

        }else{

            search_goods.setVisibility(View.GONE);

            search_position.setVisibility(View.VISIBLE);

        }

    }
    //重写下拉刷新方法
    @Override
    public void onRefresh() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              refresh.setRefreshing(false);
            }
        },2000);
    }
    //重写上拉加载的方法
    @Override
    public void onLoad() {


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                for (int i=1;i<6;i++){

                    list_store.add(new Store("加载的商家"+i,999,"接受预订","10:00",30,20,5,url));
                }
                adapter_store.notifyDataSetChanged();
                //自动增加listView的高度
                listView_store.loadComplete();
                DensityUtils.setListViewHeightBasedOnChildren(listView_store);

            }
        },3000);
        loadTime++;
        if (loadTime==3){
            isOver=true;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem) {

    }

    //定位AsyncTask
    class LocationAsync extends  AsyncTask<AMapLocationClient,Integer,String>{

        private AMapLocationListener listener;
        String city;
        @Override
        protected String doInBackground(AMapLocationClient... aMapLocationClients) {
            AMapLocationClient aMapLocationClient = aMapLocationClients[0];
            //初始化AMapLocationClientOption对象
            mLocationOption = new AMapLocationClientOption();

            //设置定位模式 高精度模式
            mLocationOption.setLocationMode
                    (AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置单次定位
            mLocationOption.setOnceLocation(true);
            //设置需要返回地址信息
            mLocationOption.setNeedAddress(true);


            ////给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            query=new PoiSearch.Query("","","");
            poiSearch=new PoiSearch(getActivity(),query);

            listener=new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation aMapLocation) {
                    aoiName= city = aMapLocation.getCity();
                    if (!aoiName.equals("")){
                        tv_address.setText(aoiName);
                    }
                    latitude=aMapLocation.getLatitude();
                    longitude=aMapLocation.getLongitude();
                    poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude,longitude),1000));
                    poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                        @Override
                        public void onPoiSearched(PoiResult poiResult, int i) {
                            if (i==1000&&poiResult!=null){
                                ArrayList<PoiItem> pois = poiResult.getPois();
                                aoiName=pois.get(0).getTitle();
                                tv_address.setText(aoiName);
                              anim.stop();
                              //加载完成后停止动画
                              ll_animation.setVisibility(View.GONE);
                            }

                        }

                        @Override
                        public void onPoiItemSearched(PoiItem poiItem, int i) {

                        }
                    });
                    poiSearch.searchPOIAsyn();
                }
            };
            aMapLocationClient.setLocationListener(listener);
            aMapLocationClient.startLocation();

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
        }
    }
}


