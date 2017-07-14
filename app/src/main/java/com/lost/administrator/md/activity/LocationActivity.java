package com.lost.administrator.md.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.lost.administrator.md.R;
import com.lost.administrator.md.utils.CommonAdapter;
import com.lost.administrator.md.utils.ViewHolder;
import java.util.List;

public class LocationActivity extends AppCompatActivity implements PoiSearch.OnPoiSearchListener, View.OnClickListener {


    private PoiSearch poiSearch;
    private PoiSearch.Query query;
    private List<PoiItem> poiItems;// poi数据
    private double latitude;
    private double longitude;
    private ListView listView;
    private CommonAdapter adapter;
    //添加新地址
    private TextView tv_add;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        tv_add= (TextView) findViewById(R.id.tv_addAddress);
        tv_add.setOnClickListener(this);
        iv_back= (ImageView) findViewById(R.id.iv_back_loc);
        iv_back.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent!=null){
             longitude = intent.getDoubleExtra("longitude", 0);
             latitude = intent.getDoubleExtra("latitude", 0);
        }
        listView= (ListView) findViewById(R.id.list_near);
        query=new PoiSearch.Query("","","");
        query.setPageSize(20);
        poiSearch=new PoiSearch(this,query);
        poiSearch.setOnPoiSearchListener(this);
        if (longitude!=0&&latitude!=0){
            poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(this.latitude,
                    longitude), 1000));//设置周边搜索的中心点以及半径

            poiSearch.searchPOIAsyn();
        }else{
            Toast.makeText(LocationActivity.this, "Error!请重试", Toast.LENGTH_SHORT).show();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("address",poiItems.get(i).getTitle());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    @Override
    public void onPoiSearched(PoiResult result, int i) {

        if (i==1000&&result!=null){


            poiItems = result.getPois();// 取得第一页的poiitem数据，页数从数字0开始

            adapter=new CommonAdapter<PoiItem>(this,poiItems,R.layout.item_near_layout) {
                @Override
                public void convert(ViewHolder holder, PoiItem o) {
                    holder.setText(R.id.tv_near,o.getTitle()+"     "+o.getDistance()+"米");

                }
            };
            listView.setAdapter(adapter);

        }else{
            Toast.makeText(this, "Error,PleaseRestartYourApp", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_addAddress:
                Intent intent = new Intent(this, AddAddressActivity.class);
                intent.putExtra("comm",true);
                startActivity(intent);
                break;
            case R.id.iv_back_loc:
                finish();
                break;
        }
    }
}
