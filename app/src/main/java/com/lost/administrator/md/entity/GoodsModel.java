package com.lost.administrator.md.entity;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GoodsModel {

    private List<GoodsType> leftList = new ArrayList<>();
    /**
     * 当前选中项
     */
    private int leftPositionSelected = 0;

    private List<Goods> rightList = new ArrayList<>();

    public void addLeft(String typeName){
        leftList.add(new GoodsType(typeName));
    }

    public List<GoodsType> getLeftList() {
        return Collections.unmodifiableList(leftList);
    }

    public void setLeftList(List<GoodsType> leftList) {
        this.leftList = leftList;
    }

    public int getLeftPositionSelected() {
        return leftPositionSelected;
    }

    public void setLeftPositionSelected(int leftPositionSelected) {
        this.leftPositionSelected = leftPositionSelected;
    }

    public void addRight(String dishType, List<GoodsInfo> dishList){
        rightList.add(new Goods(dishType, dishList));
    }

    public List<Goods> getRightList() {
        return rightList;
    }

    public void setRightList(List<Goods> rightList) {
        this.rightList = rightList;
    }
}
