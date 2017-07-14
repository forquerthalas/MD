package com.lost.administrator.md.utils;

import android.widget.ImageView;

import com.lost.administrator.md.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public  class ImagUtil {
    public static void setUrlImage(ImageView iv, String url){
        ImageOptions options=new ImageOptions.Builder()
                .setRadius(10)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setUseMemCache(true)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .build();
        x.image().bind(iv,url,options);

    }
}
