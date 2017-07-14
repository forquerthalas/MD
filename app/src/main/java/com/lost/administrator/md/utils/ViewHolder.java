package com.lost.administrator.md.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.hedgehog.ratingbar.RatingBar;
import com.wx.ovalimageview.RoundImageView;
import com.lost.administrator.md.R;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;




public class ViewHolder {
    private static final String TAG = "ViewHolder";
    //用来存放View的容器
    private SparseArray<View> mViews;
    //Item的View
    private View convertView;
    private int position;
    static CommonAdapter adapter_c;
    private ViewHolder(Context context, int layoutId, int position){
        mViews = new SparseArray<View>();
        this.position = position;
        convertView = LayoutInflater.from(context).inflate(layoutId,null);
        convertView.setTag(this);
    }

    public static ViewHolder getInstance(Context context, View convertView, int layoutId, int position,CommonAdapter adapter){
        adapter_c=adapter;
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder(context,layoutId,position);
            convertView = holder.convertView;
        }else{
            holder = (ViewHolder) convertView.getTag();
            holder.position = position;
        }
        return holder;
    }

    public <T extends View> T getView(int viewId){
        View v = mViews.get(viewId);
        if(v == null){
            v = convertView.findViewById(viewId);
            mViews.put(viewId,v);
        }
        return (T) v;
    }

    public View getConvertView() {
        return convertView;
    }

    public ViewHolder setText(int viewId,String text){
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId,int resId){
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public ViewHolder setImgUrl(int viewId,String url){
        ImageView img=getView(viewId);
        ImageOptions options=new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(80))
                .setRadius(10)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setUseMemCache(true)
                .build();
        x.image().bind(img,url,options);
        return this;
    }
    //设置字体中间划线
    public ViewHolder setNoText(int viewId,String text){
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        view.setText(text);
        return this;
    }
    //设置RateingBar的星星数量
    public ViewHolder setRatingBar(int viewId,float rating){
        RatingBar bar=getView(viewId);
        bar.setStar(rating);

        return this;
    }
    //设置Visibility为Gone
    public ViewHolder setGone(int viewId){
        View view = getView(viewId);
        view.setVisibility(View.GONE);
        return this;
    }
    //设置Visibility为visible
    public ViewHolder setVisible(int viewId){
        View view = getView(viewId);
        view.setVisibility(View.VISIBLE);
        return this;
    }
    //设置roundImageView的网络图片
    public ViewHolder setRoundImgUrl(int viewId, String url){
        final RoundImageView img=getView(viewId);
        ImageOptions options=new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(60), DensityUtil.dip2px(60))
                .setRadius(10)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setUseMemCache(true)
                .setFailureDrawableId(R.drawable.dongman)
                .setLoadingDrawableId(R.drawable.jiazaizhong)
                .build();
        x.image().loadDrawable(url, options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                img.setImageDrawable(result);
                adapter_c.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        return this;
    }


}
