package com.lost.administrator.md.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.lost.administrator.md.R;
import com.lost.administrator.md.entity.GoodsInfo;
import com.lost.administrator.md.entity.GoodsType;
import com.lost.administrator.md.port.onCallBackListener;
import com.lost.administrator.md.widget.SectionedBaseAdapter;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

public class RightAdapter extends SectionedBaseAdapter {

	List<GoodsType> pruductCagests;
    private HolderClickListener mHolderClickListener;
    private Context context;
    private LayoutInflater mInflater;


    private onCallBackListener callBackListener;

    public void setCallBackListener(onCallBackListener callBackListener) {
        this.callBackListener = callBackListener;
    }


    public RightAdapter(Context context, List<GoodsType> pruductCagests){
		this.context = context;
		this.pruductCagests = pruductCagests;
		mInflater = LayoutInflater.from(context);
	}

    @Override
    public Object getItem(int section, int position) {
        return pruductCagests.get(section).getProduct().get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return pruductCagests.size();
    }

    @Override
    public int getCountForSection(int section) {
        return pruductCagests.get(section).getProduct().size();
    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.product_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_goods = (ImageView) convertView.findViewById(R.id.iv_icon_goods);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.prise = (TextView) convertView.findViewById(R.id.prise);
            viewHolder.increase = (ImageView) convertView.findViewById(R.id.increase);
            viewHolder.reduce = (ImageView) convertView.findViewById(R.id.reduce);
            viewHolder.shoppingNum = (TextView) convertView.findViewById(R.id.shoppingNum);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final GoodsInfo g = pruductCagests.get(section).getProduct().get(position);
        viewHolder.name.setText(g.getGoods());
        viewHolder.prise.setText(String.valueOf(g.getPrice()));
        viewHolder.shoppingNum.setText(String.valueOf(g.getNumber()));
        setUrlImage(viewHolder.iv_goods,g.getPicture());
        Log.d("TAG", "getItemView: "+g.getPicture());
        if (g.getNumber()==0){
            viewHolder.reduce.setVisibility(View.GONE);
        }else{
            viewHolder.reduce.setVisibility(View.VISIBLE);
        }
        viewHolder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = g.getNumber();
                viewHolder.reduce.setVisibility(View.VISIBLE);
                num++;
                g.setNumber(num);
                viewHolder.shoppingNum.setText(g.getNumber()+"");
                if (callBackListener != null) {
                    callBackListener.updateProduct(g, "1");
                } else {
                }
                if(mHolderClickListener!=null){
                    int[] start_location = new int[2];
                    viewHolder.shoppingNum.getLocationInWindow(start_location);//获取点击商品图片的位置
                    Drawable drawable = context.getResources().getDrawable(R.drawable.s9);//复制一个新的商品图标
                    //TODO:解决方案，先监听到左边ListView的Item中，然后在开始动画添加
                    mHolderClickListener.onHolderClick(drawable, start_location);
                }
            }
        });
        viewHolder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = g.getNumber();
                if (num > 0) {
                    num--;
                    if (num==0){
                        viewHolder.reduce.setVisibility(View.GONE);
                    }
                    g.setNumber(num);
                    viewHolder.shoppingNum.setText(g.getNumber()+"");
                    if (callBackListener != null) {
                        callBackListener.updateProduct(g, "2");
                    } else {
                    }
                }
            }
        });

        viewHolder.shoppingNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    int shoppingNum = Integer.parseInt(viewHolder.shoppingNum.getText().toString());
                }
            }
        });

        return convertView;
    }

    //加载网络图片
    private void setUrlImage(ImageView iv, String url){
        ImageOptions options=new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(40),DensityUtil.dip2px(40))
                .setRadius(10)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setUseMemCache(true)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .build();

        x.image().bind(iv,url,options);

    }

    class ViewHolder {
        /**
         * 商品图片
         */
        public ImageView iv_goods;
        /**
         * 商品名称
         */
        public TextView name;
        /**
         * 商品价格
         */
        public TextView prise;
        /**
         * 增加
         */
        public ImageView increase;
        /**
         * 商品数目
         */
        public TextView shoppingNum;
        /**
         * 减少
         */
        public ImageView reduce;
    }

    public void SetOnSetHolderClickListener(HolderClickListener holderClickListener){
        this.mHolderClickListener = holderClickListener;
    }
    public interface HolderClickListener{
        public void onHolderClick(Drawable drawable, int[] start_location);
    }
    

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.header_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        layout.setClickable(false);
        ((TextView) layout.findViewById(R.id.textItem)).setText(pruductCagests.get(section).getType());
        return layout;
    }

}
