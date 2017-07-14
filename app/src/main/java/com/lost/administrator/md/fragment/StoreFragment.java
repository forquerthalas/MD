package com.lost.administrator.md.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lost.administrator.md.R;


public class StoreFragment extends HeaderViewPagerFragment {

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.storeinfo_fragment_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView= (ListView) view.findViewById(R.id.lv_store);

    }


    @Override
    public View getScrollableView() {

        return listView;
    }
}

