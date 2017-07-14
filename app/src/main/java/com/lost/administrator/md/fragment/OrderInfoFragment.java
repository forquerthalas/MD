package com.lost.administrator.md.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



import com.lost.administrator.md.entity.Order;
import com.lost.administrator.md.utils.CommonAdapter;
import com.lost.administrator.md.utils.DensityUtils;
import com.lost.administrator.md.utils.ViewHolder;
import com.lost.administrator.md.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/11/26 0026.
 */

public class OrderInfoFragment extends Fragment {

    private ListView listView;
    private CommonAdapter<Order> adapter;

    private List<Order> list_order=new ArrayList<Order>();
    @Override @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.oinfo_fragment_layout, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        listView= (ListView) view.findViewById(R.id.lvOrder);

    }

    private void initData() {
        for (int i=1;i<4;i++){
            list_order.add(new Order("哦呵呵",2,99f));
        }
        adapter=new CommonAdapter<Order>(getContext(),list_order,R.layout.item_orderinfo_layout) {
            @Override
            public void convert(ViewHolder holder, Order order) {
                holder.setText(R.id.tv_name_order,order.getName())
                        .setText(R.id.tv_num_order,"X"+order.getCount())
                        .setText(R.id.tv_price_order,"￥"+order.getCount()*order.getPrice());
            }
        };
        listView.setAdapter(adapter);
        DensityUtils.setListViewHeightBasedOnChildren(listView);
        adapter.notifyDataSetChanged();
    }
}
