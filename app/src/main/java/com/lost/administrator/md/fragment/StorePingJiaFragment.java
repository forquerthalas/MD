package com.lost.administrator.md.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.lost.administrator.md.R;
import com.lost.administrator.md.entity.PingJia;

import java.util.ArrayList;
import java.util.List;


public class StorePingJiaFragment extends HeaderViewPagerFragment {



    private RatingBar ratingBar_send,ratingBar_store;
    private GridView gridView;
    private List<PingJia> list_pingJia;
    private GridAdapter adapter;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pingjia_fragment_store, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        list_pingJia=new ArrayList<PingJia>();
        list_pingJia.add(new PingJia("全部","1222"));
        list_pingJia.add(new PingJia("有图","1222"));
        list_pingJia.add(new PingJia("好评","1222"));
        list_pingJia.add(new PingJia("中评","1222"));
        list_pingJia.add(new PingJia("差评","1222"));
        list_pingJia.add(new PingJia("追评","1222"));
        list_pingJia.add(new PingJia("味道赞","1222"));
        adapter=new GridAdapter();
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        int colum=1;
        if (list_pingJia.size()/3==0){
            colum=list_pingJia.size()/3;
        }else{
            colum=list_pingJia.size()/3+1;
        }
        params.height=160*colum;
        gridView.setLayoutParams(params);
        gridView.setAdapter(adapter);
    }

    private void initView(View view) {
        ratingBar_send= (RatingBar) view.findViewById(R.id.ratingBar_send);
        ratingBar_store= (RatingBar) view.findViewById(R.id.ratingBar_store);
        ratingBar_send.setStar(5);
        ratingBar_store.setStar(5);
        gridView= (GridView) view.findViewById(R.id.gridView_pingJia);
        listView= (ListView) view.findViewById(R.id.lv_pingJia);


    }

    @Override
    public View getScrollableView() {

        return listView;
    }

    class GridAdapter extends BaseAdapter {
        Hodler holder=new Hodler();
        @Override
        public int getCount() {
            return list_pingJia.size();
        }

        @Override
        public Object getItem(int position) {
            return list_pingJia.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_gv_layout, null);
                holder.textView= (TextView) convertView.findViewById(R.id.tv_gv_item);
                convertView.setTag(holder);
            }else{
                holder=(Hodler) convertView.getTag();
            }
            PingJia p = list_pingJia.get(position);
            holder.textView.setText(p.getType()+"("+p.getCount()+")");
            return convertView;
        }
    }
     class Hodler{
         TextView textView;
    }

}
