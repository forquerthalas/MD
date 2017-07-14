package com.lost.administrator.md.port;


import com.lost.administrator.md.entity.GoodsInfo;


public interface ShopToDetailListener {
    /**
     * Type表示添加或减少
     * @param product
     * @param type
     */
    void onUpdateDetailList(GoodsInfo product, String type);

    void onRemovePriduct(GoodsInfo product);
}
