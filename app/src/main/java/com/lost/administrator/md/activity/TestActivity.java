package com.lost.administrator.md.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.lost.administrator.md.R;

public class TestActivity extends AppCompatActivity {


    private MapView mapView;
    private AMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mapView= (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
         map = mapView.getMap();

    }
}
