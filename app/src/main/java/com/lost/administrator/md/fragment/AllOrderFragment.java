package com.lost.administrator.md.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lost.administrator.md.R;
import com.lost.administrator.md.activity.OrderItemActivity;
import com.lost.administrator.md.entity.AllOrder;
import com.lost.administrator.md.entity.Order;
import com.lost.administrator.md.utils.CommonAdapter;
import com.lost.administrator.md.utils.DensityUtils;
import com.lost.administrator.md.utils.ImagUtil;
import com.lost.administrator.md.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class AllOrderFragment extends Fragment {
    private RecyclerView rv_order;
    private List<Order> list_order=new ArrayList<Order>();
    private List<AllOrder> list_orders=new ArrayList<AllOrder>();
    private MyAdapter adapter;

    //    private String url="https://ps.ssl.qhimg.com/sdmt/137_135_100/t011fd64776e672353d.jpg";
    private String url="http://p0.so.qhmsg.com/t01497e20a8fac30725.jpg";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.allorder_fragment, null);
        initView(view);
        initData(view);
        rv_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrderItemActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void initView(View view) {
        rv_order= (RecyclerView) view.findViewById(R.id.recycler_order);
    }


    private void initData(View view) {
        for (int i=1;i<3;i++){
            list_order.add(new Order("秀色可餐"+i,2));
        }
        for (int i=1;i<10;i++){
            list_orders.add(new AllOrder(url,"哦"+i,1,14.5f,list_order));
        }
        adapter=new MyAdapter(getActivity(),list_orders);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        rv_order.setLayoutManager(manager);
        rv_order.setAdapter(adapter);
        adapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), OrderItemActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }
    //适配器
    class MyAdapter extends RecyclerView.Adapter<MyHolder>{
        private OnItemClickLitener mOnItemClickLitener;
        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
        {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }
        private LayoutInflater inflater;
        private List<AllOrder> list;
        public MyAdapter(Context context, List<AllOrder> o){
            this.inflater=LayoutInflater.from(context);
            this.list=o;
        }
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(inflater.inflate(R.layout.item_order_layout,null));
        }

        @Override
        public void onBindViewHolder(final MyHolder holder, int position) {

            holder.bindHolder(list_orders.get(position));
            if (mOnItemClickLitener != null)
            {
                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return list_orders.size();
        }

    }
    class MyHolder extends RecyclerView.ViewHolder {

        private ImageView iv_icon;
        private TextView tv_name,tv_state,tv_count,tv_price;
        private ListView listView;
        //绑定控件
        public MyHolder(View itemView) {

            super(itemView);
            iv_icon= (ImageView) itemView.findViewById(R.id.iv_icon_order);
            tv_name= (TextView) itemView.findViewById(R.id.tv_storeName_order);
            tv_state= (TextView) itemView.findViewById(R.id.tv_state_order);
            tv_count= (TextView) itemView.findViewById(R.id.tv_state_order);
            tv_price= (TextView) itemView.findViewById(R.id.tv_price_order);
            listView= (ListView) itemView.findViewById(R.id.lv_list_order);

        }
        //绑定数据
        public void bindHolder(final AllOrder o){
            ImagUtil.setUrlImage(iv_icon,o.getUrl());
            tv_name.setText(o.getName());
            tv_price.setText("￥"+o.getPay_true());
            tv_count.setText("共"+o.getList().size()+"件商品，实付");
            tv_state.setText(o.getState()==1?"订单完成":"未付款" );
            CommonAdapter<Order> adapter1 = new CommonAdapter<Order>(getActivity(), o.getList(),R.layout.item_info_layout)
            {
                @Override
                public void convert(ViewHolder holder, Order order) {
                    holder.setText(R.id.tv_name_food, order.getName()).setText(R.id.tv_num_food, "X" + order.getCount());
                }};
            listView.setAdapter(adapter1);
            DensityUtils.setListViewHeightBasedOnChildren(listView);
            adapter1.notifyDataSetChanged();

        }
    }
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
}
