package com.lost.administrator.md.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;


import com.lost.administrator.md.R;
import com.lost.administrator.md.entity.Order;
import com.lost.administrator.md.utils.CommonAdapter;
import com.lost.administrator.md.utils.DensityUtils;
import com.lost.administrator.md.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class OrderInfoActivity extends AppCompatActivity {

    private ListView listView;
    private List<Order> list_order=new ArrayList<Order>();
    private float totalPrice=0;
    private CommonAdapter<Order> adapter;
    //总价 优惠 实付
    private TextView tv_totalPrice,tv_total,tv_youHui,tv_pay;
    private float youHui=10000f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderinfo);
        initView();
        initData();
        bindData();
    }

    private void initView() {
        listView= (ListView) findViewById(R.id.lv_orderInfo);
        tv_totalPrice= (TextView) findViewById(R.id.tv_totalPrice_order);
        tv_total= (TextView) findViewById(R.id.tv_total_order);
        tv_youHui= (TextView) findViewById(R.id.tv_youHui_order);
        tv_pay= (TextView) findViewById(R.id.tv_pay_order);

    }

    private void initData() {
        for (int i=1;i<4;i++){
            list_order.add(new Order("哦呵呵",2,99f));
        }
        for (Order o:list_order){
           totalPrice+=o.getPrice()*o.getCount();
        }
        adapter=new CommonAdapter<Order>(this,list_order,R.layout.item_orderinfo_layout) {
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

    private void bindData() {
        tv_totalPrice.setText("￥"+totalPrice);
        tv_total.setText("￥"+totalPrice);
        tv_youHui.setText("￥"+youHui);
        tv_pay.setText("￥"+(totalPrice-youHui));
    }

}
