package com.json.itemdecoration.untils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

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
    private float mDownX;


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
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int tempScrollX = getScrollX();
        super.onLayout(changed, l, t, r, b);
        final int scrollX = getScrollX();


        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof LinearLayout){
                Log.d("--> view - LinearLayout " + i + " : ", view.getMeasuredWidth() + "");
                ViewGroup viewGroup = (ViewGroup) view;
                for (int j = 0; j < viewGroup.getChildCount(); j ++){
                    View childView = viewGroup.getChildAt(j);
                    if (childView instanceof LinearLayout){
                        Log.d("--> childView - LinearLayout " + j + " : ", view.getMeasuredWidth() + "");

                    } else if  (childView instanceof RecyclerView){
                        Log.d("--> childView - RecyclerView " + j + " : ", view.getMeasuredWidth() + "");
                    }

                }
            }

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
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            mDownX = ev.getX();
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE){
            float moveX = ev.getX();
            Log.d("moveX -->", String.valueOf(moveX));

        } if (ev.getAction() == MotionEvent.ACTION_MOVE){

        }
        return super.onTouchEvent(ev);

    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        int changeX = x - oldx;
        int width = 0;

        if (x != 0 && oldx == 0) {
            width = Math.abs(x - oldx);
            Log.d("--> width :", width + "");
        } else if (x == 0 && oldx != 0) {
            width = Math.abs(x - oldx);
            Log.d("--> width :", width + "");
        }


        if (mView != null) {
            mView.getChildAt(0).scrollBy(-changeX, y);
//            mView.scrollTo(x, y);

        }
    }


    public void setScrollView(HorizontalScrollView view) {
        mView = view;
    }


}





















