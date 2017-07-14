package com.lost.administrator.md.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.lost.administrator.md.R;
import com.lost.administrator.md.entity.GoodsInfo;
import com.lost.administrator.md.port.ShopToDetailListener;

import java.util.List;

public class CartAdapter extends BaseAdapter {

    private ShopToDetailListener shopToDetailListener;

    public void setShopToDetailListener(ShopToDetailListener callBackListener) {
        this.shopToDetailListener = callBackListener;
    }
    private List<GoodsInfo> goodsInfos;
    private LayoutInflater mInflater;
    public CartAdapter(Context context, List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return goodsInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.trade_widget, null);
            viewHolder = new ViewHolder();
            viewHolder.commodityName = (TextView) convertView.findViewById(R.id.commodityName);
            viewHolder.commodityPrise = (TextView) convertView.findViewById(R.id.commodityPrise);
            viewHolder.commodityNum = (TextView) convertView.findViewById(R.id.commodityNum);
            viewHolder.increase = (ImageView)  convertView.findViewById(R.id.increase);
            viewHolder.reduce = (ImageView)  convertView.findViewById(R.id.reduce);
            viewHolder.shoppingNum = (TextView)  convertView.findViewById(R.id.shoppingNum);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.commodityName.setText(goodsInfos.get(position).getGoods());
        viewHolder.commodityPrise.setText(goodsInfos.get(position).getPrice());
        viewHolder.commodityNum.setText(1+"");
        viewHolder.shoppingNum.setText(goodsInfos.get(position).getNumber()+"");

        viewHolder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = goodsInfos.get(position).getNumber();
                num++;
                goodsInfos.get(position).setNumber(num);
                viewHolder.shoppingNum.setText(goodsInfos.get(position).getNumber()+"");
                if (shopToDetailListener != null) {
                    shopToDetailListener.onUpdateDetailList(goodsInfos.get(position), "1");
                } else {
                }
            }
        });

        viewHolder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = goodsInfos.get(position).getNumber();
                if (num > 0) {
                    num--;
                    if(num==0){
                        goodsInfos.get(position).setNumber(num);
                        shopToDetailListener.onRemovePriduct(goodsInfos.get(position));
                    }else {
                        goodsInfos.get(position).setNumber(num);
                        viewHolder.shoppingNum.setText(goodsInfos.get(position).getNumber()+"");
                        if (shopToDetailListener != null) {
                            shopToDetailListener.onUpdateDetailList(goodsInfos.get(position), "2");
                        } else {
                        }
                    }

                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        /**
         * 购物车商品名称
         */
        public TextView commodityName;
        /**
         * 购物车商品价格
         */
        public TextView commodityPrise;
        /**
         * 购物车商品数量
         */
        public TextView commodityNum;
        /**
         * 增加
         */
        public ImageView increase;
        /**
         * 减少
         */
        public ImageView reduce;
        /**
         * 商品数目
         */
        public TextView shoppingNum;
    }


}
