package com.json.itemdecoration.untils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.json.itemdecoration.R;

/**
 * @Describe
 * @Author puyantao
 * @Email 1067899750@qq.com
 * @create 2019/7/3 17:25
 */
public class ScrollerLayout extends ViewGroup {
    /**
     * 用于完成滚动操作的实例
     */
    private Scroller mScroller;

    /**
     * 判定为拖动的最小移动像素数
     */
    private int mTouchSlop;

    /**
     * 手机按下时的屏幕坐标
     */
    private float mXDown;
    private float mYDown;

    /**
     * 手机当时所处的屏幕坐标
     */
    private float mXMove;
    private float mYMove;

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;
    private float mYlastMove;

    // 初始化左右边界值
    private int leftBorder;
    private int rightBorder;
    private int topBorder;
    private int bottomBortor;

    private int origintion = 0;


    public ScrollerLayout(Context context) {
        this(context, null);
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ScrollerLayout, defStyleAttr, 0);
        origintion = array.getInt(R.styleable.ScrollerLayout_orientation, 0);
        array.recycle();
        initView(context);
    }

    private void initView(Context context){
        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);

        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        //mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i ++ ){
            View childView = getChildAt(i);
            // 为ScrollerLayout中的每一个子控件测量大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i ++){
            View childView = getChildAt(i);
            if (origintion == 1) {
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(),childView.getMeasuredHeight());

            } else if (origintion == 0){
                childView.layout(0, i * childView.getMeasuredHeight(), childView.getMeasuredWidth(),
                        (i + 1) * childView.getMeasuredHeight());
            }
        }
        // 初始化左右上下边界值
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(childCount - 1).getRight();
        topBorder = getChildAt(0).getTop();
        bottomBortor = getChildAt(childCount - 1).getBottom();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mYDown = ev.getRawY();
                mXLastMove  = mXMove;
                mYlastMove = mYMove;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                mYMove = ev.getRawY();
                if (origintion == 1) {
                    float diff = Math.abs(mXMove - mXDown);
                    mXLastMove = mXMove;
                    // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                    if (diff > mTouchSlop) {
                        return true;
                    }

                } else if (origintion == 0){
                    float diff = Math.abs(mYMove - mYDown);
                    mYlastMove = mYMove;
                    if (diff > mTouchSlop){
                        return true;
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                mYMove = event.getRawY();

                if (origintion == 1) {
                    int scrolledX = (int) (mXLastMove - mXMove);
                    if (getScrollX() + scrolledX < leftBorder) {
                        scrollTo(leftBorder, 0);
                        return true;
                    } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                        scrollTo(rightBorder - getWidth(), 0);
                        return true;
                    }
                    scrollBy(scrolledX, 0);
                    mXLastMove = mXMove;

                } else if (origintion == 0){
                    int scrolledY = (int) (mYlastMove - mYMove);
                    if (getScrollY() + scrolledY < topBorder){
                        scrollTo(0, topBorder);
                        return true;
                    } else if (getScrollY() + getHeight() + scrolledY > bottomBortor){
                        scrollTo(0, bottomBortor - getHeight());
                        return true;
                    }
                    scrollBy(0, scrolledY);
                    mYlastMove = mYMove;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (origintion == 1) {
                    // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                    int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                    int dx = targetIndex * getWidth() - getScrollX();
                    // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                    mScroller.startScroll(getScrollX(), 0, dx, 0);

                } else if (origintion == 0){
                    int targetIndey = (getScrollY() + getHeight() / 2) / getHeight();
                    int dy = targetIndey * getHeight() - getScrollY();
                    mScroller.startScroll(0, getScrollY(), 0, dy);
                }
                invalidate();
                break;
        }

        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
