package com.lost.administrator.md.utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

import org.xutils.x;

/**
 * Created by Administrator on 2016/10/12.
 */
public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        x.image().bind(imageView,path.toString());
}
}
