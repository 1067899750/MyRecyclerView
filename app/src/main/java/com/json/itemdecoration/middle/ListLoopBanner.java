package com.json.itemdecoration.middle;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.RelativeLayout;

import com.json.itemdecoration.R;

import java.util.ArrayList;

/**
 * @Describe
 * @Author puyantao
 * @Email 1067899750@qq.com
 * @create 2019/7/4 9:29
 */
public class ListLoopBanner extends RelativeLayout {
    /**
     * 判定为拖动的最小移动像素数
     */
    private int mTouchSlop;
    private OverScroller mScroller;

    private VelocityTracker mVelocityTracker;
    /**
     * 每个item长度
     */
    private int viewWidth;
    private int scrollWidth;
    /**
     * 移动时的坐标
     */
    private float mPointLastMoveX;
    private float mPointLastMoveY;
    /**
     * 移动时的坐标
     */
    private float mPointMoveX;
    private float mPointMoveY;
    /**
     * 抬起时的坐标
     */
    private float mPointUpX;
    private float mPointUpY;

    public ListLoopBanner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mScroller = new OverScroller(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            scrollWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            Log.d("-->", scrollWidth + " ");
        }

    }

    /**
     * 直接跳转到第一个页面
     */
    private void reSetBanner() {
//        Animator animator = ObjectAnimator.ofFloat(mRootLL, "translationX", translationX, -viewWidth);
//        animator.setDuration(1);
//        animator.setStartDelay(100);
//        animator.start();
//        translationX = -viewWidth;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPointLastMoveX = ev.getX();
                mPointLastMoveY = ev.getY();
                mScroller.forceFinished(true);
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(ev);
                mPointMoveX = ev.getX();
                mPointMoveY = ev.getY();
                float moveX = Math.abs(mPointMoveX - mPointLastMoveX);
                float moveY = Math.abs(mPointMoveY - mPointLastMoveY);
                //垂直滑动的时候进行移动
                if (moveY < moveX) {
//                  或者使用下面的方法，因为设置了平滑过渡时间为0
                    mScroller.startScroll(getScrollX(), 0, -(int) (mPointMoveX - mPointLastMoveX), 0, 0);

                    invalidate();
                } else {
                    return false;
                }
                mPointLastMoveX = mPointMoveX;
                mPointLastMoveY = mPointMoveY;
                break;
            case MotionEvent.ACTION_UP:
                mScroller.abortAnimation();
                if (getScaleX() < 0) {
                    //证明达到上边界了，这时候要进行回弹处理
                    mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0);
                    postInvalidate();// 重绘执行computeScroll()
                } else if (getScrollY() > scrollWidth) {
                    //达到最右端
                    mScroller.startScroll(getScrollX(), 0, -(getScrollX() - scrollWidth), 0);
                    postInvalidate();// 重绘执行computeScroll()
                } else {
                    mVelocityTracker.computeCurrentVelocity(1000);
                    float xVelocity = mVelocityTracker.getXVelocity();
                    Log.e("TAG", "MyScrollView---yVelocity====" + xVelocity);
                    /**
                     * fling 方法参数注解
                     * startX 滚动起始点X坐标
                     * startY 滚动起始点Y坐标
                     * velocityX   当滑动屏幕时X方向初速度，以每秒像素数计算
                     * velocityY   当滑动屏幕时Y方向初速度，以每秒像素数计算
                     * minX    X方向的最小值，scroller不会滚过此点。
                     *　maxX    X方向的最大值，scroller不会滚过此点。
                     *　minY    Y方向的最小值，scroller不会滚过此点。
                     *　maxY    Y方向的最大值，scroller不会滚过此点。
                     */
                    if (Math.abs(xVelocity) > 50) {
                        mScroller.fling(getScrollX(), 0, -(int) xVelocity, 0,
                                0, 0,
                                scrollWidth, 0);
                        postInvalidate();
                    }
                }
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i ++){
                View view = getChildAt(i);
                view.scrollBy(mScroller.getCurrX(), 0);
            }
            postInvalidate();
        }
    }



}






































