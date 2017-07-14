package com.lost.administrator.md.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.lost.administrator.md.R;


/**
 * Created by Kevin on 2016/9/14.
 */
public class MyRadioButton extends RadioButton {

    // 申明上下左右对应的图片
    private Drawable drawableTop, drawableBottom, drawableLeft, drawableRight;
    // 上下左右图片对应长和宽
    private int mTopWidth, mTopHeight, mBottomWidth, mBottomHeight, mRightWidth, mRightHeight, mLeftWidth, mLeftHeight;

    public MyRadioButton(Context context) {
        super(context);
        initView(context, null);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        if (attrs != null) {
            float scale = context.getResources().getDisplayMetrics().density;
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyRadioButton);
            int n = a.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = a.getIndex(i);
                switch (attr) {
                    case R.styleable.MyRadioButton_drawableBottom:
                        drawableBottom = a.getDrawable(attr);
                        break;
                    case R.styleable.MyRadioButton_drawableTop:
                        drawableTop = a.getDrawable(attr);
                        break;
                    case R.styleable.MyRadioButton_drawableLeft:
                        drawableLeft = a.getDrawable(attr);
                        break;
                    case R.styleable.MyRadioButton_drawableRight:
                        drawableRight = a.getDrawable(attr);
                        break;
                    case R.styleable.MyRadioButton_drawableTopWidth:
                        mTopWidth = (int) (a.getDimension(attr, 20) * scale + 0.5f);
                        break;
                    case R.styleable.MyRadioButton_drawableTopHeight:
                        mTopHeight = (int) (a.getDimension(attr, 20) * scale + 0.5f);
                        break;
                    case R.styleable.MyRadioButton_drawableBottomWidth:
                        mBottomWidth = (int) (a.getDimension(attr, 20) * scale + 0.5f);
                        break;
                    case R.styleable.MyRadioButton_drawableBottomHeight:
                        mBottomHeight = (int) (a.getDimension(attr, 20) * scale + 0.5f);
                        break;
                    case R.styleable.MyRadioButton_drawableRightWidth:
                        mRightWidth = (int) (a.getDimension(attr, 20) * scale + 0.5f);
                        break;
                    case R.styleable.MyRadioButton_drawableRightHeight:
                        mRightHeight = (int) (a.getDimension(attr, 20) * scale + 0.5f);
                        break;
                    case R.styleable.MyRadioButton_drawableLeftWidth:
                        mLeftWidth = (int) (a.getDimension(attr, 20) * scale + 0.5f);
                        break;
                    case R.styleable.MyRadioButton_drawableLeftHeight:
                        mLeftHeight = (int) (a.getDimension(attr, 20) * scale + 0.5f);
                        break;

                    default:
                        break;
                }
            }
            a.recycle();
            setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
        }
    }
    // 设置Drawable定义的大小
    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {

        if (left != null) {
            left.setBounds(0, 0, mLeftWidth <= 0 ? left.getIntrinsicWidth() : mLeftWidth, mLeftHeight <= 0 ? left.getMinimumHeight() : mLeftHeight);
        }
        if (right != null) {
            right.setBounds(0, 0, mRightWidth <= 0 ? right.getIntrinsicWidth() : mRightWidth, mRightHeight <= 0 ? right.getMinimumHeight() : mRightHeight);
        }
        if (top != null) {
            top.setBounds(0, 0, mTopWidth <= 0 ? top.getIntrinsicWidth() : mTopWidth, mTopHeight <= 0 ? top.getMinimumHeight() : mTopHeight);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mBottomWidth <= 0 ? bottom.getIntrinsicWidth() : mBottomWidth, mBottomHeight <= 0 ? bottom.getMinimumHeight()
                    : mBottomHeight);
        }
        setCompoundDrawables(left, top, right, bottom);
    }
}