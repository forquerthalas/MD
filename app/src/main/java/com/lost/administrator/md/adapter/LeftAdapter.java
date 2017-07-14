package com.lost.administrator.md.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lost.administrator.md.R;
import com.lost.administrator.md.entity.GoodsModel;
import com.lost.administrator.md.entity.GoodsType;


/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class LeftAdapter extends BaseAdapter {
    private Context context;
    private GoodsModel dishModel;

    public LeftAdapter(Context context, GoodsModel dishModel){
        this.context = context;
        this.dishModel = dishModel;
    }

    @Override
    public int getCount() {
        return dishModel.getLeftList().size();
    }

    @Override
    public Object getItem(int position) {
        return dishModel.getLeftList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.dish_left_list_item, null);
            holder = new ViewHolder();
            holder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        GoodsType dishType = dishModel.getLeftList().get(position);
        holder.tvLeft.setText(dishType.getType());

        if(dishModel.getLeftPositionSelected() == position){
            holder.tvLeft.setBackgroundColor(Color.WHITE);
        }else{
            holder.tvLeft.setBackgroundColor(0xffeeeeee);
        }

        return convertView;
    }

    static class ViewHolder{
        TextView tvLeft;
    }
}
