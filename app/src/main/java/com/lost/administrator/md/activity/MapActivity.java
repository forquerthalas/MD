package com.lost.administrator.md.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.amap.api.maps.model.animation.TranslateAnimation;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.lost.administrator.md.R;
import com.lost.administrator.md.utils.CommonAdapter;
import com.lost.administrator.md.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class
MapActivity extends AppCompatActivity implements LocationSource, AMapLocationListener,
        AMap.OnCameraChangeListener, PoiSearch.OnPoiSearchListener, AdapterView.OnItemClickListener, AMap.OnMapLoadedListener {


    private MapView mapView;
    private AMap aMap;
    private OnLocationChangedListener mlistener;
    private AMapLocationClient aMapLocationClient;
    private AMapLocationClientOption option;
    private List<PoiItem> poiItems;
    private PoiSearch poiSearch;
    private PoiSearch.Query query;
    private double latitude;
    private double longitude;
    private CommonAdapter<PoiItem> adapter;
    private ListView lv_poi;
    private ListView listView;
    private LatLng latlng;
    Marker screenMarker = null;
    Marker growMarker = null;
    private static int REQUEST_CODE = 0x11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        if (Build.VERSION.SDK_INT>=23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE},
                        REQUEST_CODE);
            }

        }
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        lv_poi = (ListView) findViewById(R.id.list_poi);
        init();
        listView.setOnItemClickListener(this);
        aMap.setOnMapLoadedListener(this);
    }

    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        aMap.setLocationSource(this);
        //设置默认刷新按钮显示
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        //设置为true表示显示定位层并触发定位，false隐藏定位层不触发定位 默认false
        aMap.setMyLocationEnabled(true);
        //设置定位模式为定位模式,可以有定位 跟随 地图根据面方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);

        //aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW); 跟随
        //aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);旋转
        aMap.setOnCameraChangeListener(this);
        poiItems = new ArrayList<PoiItem>();
        query = new PoiSearch.Query("", "", "");
        poiSearch = new PoiSearch(this, query);
        listView = (ListView) findViewById(R.id.list_poi);

    }

    /**
     * 在地图上添加marker
     */

    private void addMarkersToMap() {

//        addMarkerInScreenCenter();

        addGrowMarker();
    }

    /**
     * 添加一个从地上生长的Marker
     */
    public void addGrowMarker() {
        if (growMarker == null) {
            MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    .position(latlng);
            growMarker = aMap.addMarker(markerOptions);
        }
        LatLng latLng = aMap.getCameraPosition().target;
        Point screenPosition = aMap.getProjection().toScreenLocation(latLng);
        growMarker.setPositionByPixels(screenPosition.x, screenPosition.y);
        startGrowAnimation();
    }

    /**
     * 地上生长的Marker
     */
    private void startGrowAnimation() {
        if (growMarker != null) {
            Animation animation = new ScaleAnimation(0, 1, 0, 1);
            animation.setInterpolator(new LinearInterpolator());
            //整个移动所需要的时间
            animation.setDuration(1000);
            //设置动画
            growMarker.setAnimation(animation);
            //开始动画
            growMarker.startAnimation();
        }
    }

    /**
     * 在屏幕中心添加一个Marker
     */
    private void addMarkerInScreenCenter() {
        LatLng latLng = aMap.getCameraPosition().target;
        Point screenPosition = aMap.getProjection().toScreenLocation(latLng);
        screenMarker = aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.purple_pin)));
        //设置Marker在屏幕上,不跟随地图移动
        screenMarker.setPositionByPixels(screenPosition.x, screenPosition.y);

    }

    //开启定位
    @Override
    public void activate(OnLocationChangedListener listener) {
        mlistener = listener;
        if (aMapLocationClient == null) {
            aMapLocationClient = new AMapLocationClient(this);
            option = new AMapLocationClientOption();
            //设置定位监听
            aMapLocationClient.setLocationListener(this);
            //设置为高精度定位模式
            option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            aMapLocationClient.setLocationOption(option);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            aMapLocationClient.startLocation();

        }
    }

    //marker跳动动画
    public void startJumpAnimation() {

        if (screenMarker != null) {
            //根据屏幕距离计算需要移动的目标点
            final LatLng latLng = screenMarker.getPosition();
            Point point = aMap.getProjection().toScreenLocation(latLng);
            point.y -= dip2px(this, 125);
            LatLng target = aMap.getProjection()
                    .fromScreenLocation(point);
            //使用TranslateAnimation,填写一个需要移动的目标点
            Animation animation = new TranslateAnimation(target);
            animation.setInterpolator(new Interpolator() {
                @Override
                public float getInterpolation(float input) {
                    // 模拟重加速度的interpolator
                    if (input <= 0.5) {
                        return (float) (0.5f - 2 * (0.5 - input) * (0.5 - input));
                    } else {
                        return (float) (0.5f - Math.sqrt((input - 0.5f) * (1.5f - input)));
                    }
                }
            });
            //整个移动所需要的时间
            animation.setDuration(600);
            //设置动画
            screenMarker.setAnimation(animation);
            //开始动画
            screenMarker.startAnimation();

        } else {
            Log.e("ama", "screenMarker is null");
        }
    }

    //dip和px转换
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //停止定位
    @Override
    public void deactivate() {
        mlistener = null;
        if (aMapLocationClient != null) {
            aMapLocationClient.stopLocation();
            aMapLocationClient.onDestroy();
        }
        aMapLocationClient = null;
    }

    //定位回调
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mlistener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mlistener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
                LatLng location = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                latlng = location;
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
            }
        }
    }

    //cameraChangeListener
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        addMarkersToMap();
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        //获取滑动地图后的经纬度

        LatLng latLng = cameraPosition.target;
        latitude = latLng.latitude;
        longitude = latLng.longitude;
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude, longitude), 1000));
        poiSearch.searchPOIAsyn();
        poiSearch.setOnPoiSearchListener(this);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
//        startJumpAnimation();
        startGrowAnimation();

    }

    //
    @Override
    public void onMapLoaded() {

    }

    //搜索周边poi回调
    @Override
    public void onPoiSearched(PoiResult result, int i) {

        if (i == 1000 && result != null) {
            poiItems = result.getPois();
        }
        if (adapter == null) {

            adapter = new CommonAdapter<PoiItem>(this, poiItems, R.layout.item_poi_layout) {
                @Override
                public void convert(ViewHolder holder, PoiItem poiItem) {
                    holder.setText(R.id.tv_name_poi, poiItem.getTitle())
                            .setText(R.id.tv_address_poi, poiItem.getSnippet());

                }
            };
            lv_poi.setAdapter(adapter);
        } else {
            adapter.notifyData(poiItems);
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView!=null){

            mapView.onResume();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (mapView!=null){

            mapView.onPause();
            deactivate();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mapView!=null){

            mapView.onDestroy();
        }
        if (null != aMapLocationClient) {
            aMapLocationClient.onDestroy();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        intent.putExtra("address", poiItems.get(i).getTitle());
        setResult(RESULT_OK, intent);
        finish();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_CODE){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                //请求权限成功

            }else{
                //请求权限失败
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

}


