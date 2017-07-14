package com.lost.administrator.md.port;


import com.lost.administrator.md.entity.GoodsInfo;


public interface onCallBackListener {
    /**
     * Type表示添加或减少
     * @param product
     * @param type
     */
    void updateProduct(GoodsInfo product, String type);
}
