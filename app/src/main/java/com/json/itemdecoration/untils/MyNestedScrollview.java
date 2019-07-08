package com.json.itemdecoration.untils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.json.itemdecoration.R;


/**
 * @Describe
 * @Author puyantao
 * @Email 1067899750@qq.com
 * @create 2019/7/3 14:01
 */
public class MyNestedScrollview extends HorizontalScrollView {
    private boolean mIsScrollToRight;
    private int mWidth;
    private HorizontalScrollView mView;


    public MyNestedScrollview(@NonNull Context context) {
        this(context, null);
    }

    public MyNestedScrollview(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyNestedScrollview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyScrollViewStyle);
        mIsScrollToRight = ta.getBoolean(R.styleable.MyScrollViewStyle_appScrollToRight, false);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int tempScrollX = getScrollX();
        super.onLayout(changed, l, t, r, b);
        final int scrollX = getScrollX();


        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            Log.d("--> view" + i + " : ", view.getMeasuredWidth() + "");
        }

        mWidth = getChildAt(0).getMeasuredWidth();
        if (mIsScrollToRight) {
            this.scrollBy(mWidth, 0);
            Log.d("--> SVWidth :", getMeasuredWidth() + "");
            Log.d("--> mWidth :", mWidth + "");

        } else {
            this.scrollBy(0, 0);
        }

    }


    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        int changeX = x - oldx;
        int width = 0;

        if ((x == 0 && oldx != 0) || (x != 0 && oldx == 0)) {
            width = Math.abs(x - oldx);
            Log.d("--> width :", width + "");
        }


        if (mView != null) {
//            mView.getChildAt(0).scrollBy(-changeX, y);
//            mView.scrollTo(x, y);

        }
    }


    public void setScrollView(HorizontalScrollView view) {
        mView = view;
    }


}





















