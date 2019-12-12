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
public class ListLoopBanner extends RelativeLayout implements GestureDetector.OnGestureListener {

    private ArrayList<MiddelsItem> mMiddelsItems;


    private int mTouchSlop; // 判定为拖动的最小移动像素数
    private OverScroller mScroller;

    protected int mScrollX = 0;
    protected boolean touch = false;
    private boolean mMultipleTouch = false;
    private boolean mScrollEnable = true;

    //每个item长度
    private int viewWidth;
    //当前偏移量
    private int translationX = 0;
    //按下时的坐标
    private float mPointDownX;
    private float mPointDownY;
    //移动时的坐标
    private float mPointLastMoveX;
    private float mPointLastMoveY;
    //移动时的坐标
    private float mPointMoveX;
    private float mPointMoveY;
    //抬起时的坐标
    private float mPointUpX;
    private float mPointUpY;

    public ListLoopBanner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mMiddelsItems = new ArrayList<>();
        initView(context);
    }

    private void initView(Context context) {


        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
//        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mScroller = new OverScroller(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);





    }

    //直接跳转到第一个页面
    private void reSetBanner() {
//        Animator animator = ObjectAnimator.ofFloat(mRootLL, "translationX", translationX, -viewWidth);
//        animator.setDuration(1);
//        animator.setStartDelay(100);
//        animator.start();
//        translationX = -viewWidth;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPointDownX = ev.getX();
                mPointDownY = ev.getY();
                mPointLastMoveX = ev.getX();
                mPointLastMoveY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mPointMoveX = ev.getX();
                mPointMoveY = ev.getY();
                float moveX = Math.abs(mPointMoveX - mPointLastMoveX);
                float moveY = Math.abs(mPointMoveY - mPointLastMoveY);
                mPointLastMoveX = mPointMoveX;
                mPointLastMoveY = mPointMoveY;
                if (moveX > moveY) {
                    return true;
                }
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPointDownX = ev.getX();
                mPointDownY = ev.getY();
                mPointLastMoveX = ev.getX();
                mPointLastMoveY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mPointMoveX = ev.getX();
                mPointMoveY = ev.getY();
                float moveX = Math.abs(mPointMoveX - mPointLastMoveX);
                float moveY = Math.abs(mPointMoveY - mPointLastMoveY);
                mPointLastMoveX = mPointMoveX;
                mPointLastMoveY = mPointMoveY;
                if (moveX > moveY) {
                    return false;
                }
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch = true;
                mPointDownX = event.getX();
                mPointDownY = event.getY();
                mPointLastMoveX = event.getX();
                mPointLastMoveY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mPointMoveX = event.getX();
                mPointMoveY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                touch = false;
                mPointUpX = event.getX();
                mPointUpY = event.getY();
                break;

        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (!isTouch()) {
                //  scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
                scrollTo(mScroller.getCurrX(), 0);
            } else {
                mScroller.forceFinished(true);
            }
        }
    }


    @Override
    public void scrollBy(int x, int y) {
        scrollTo(mScrollX - Math.round(x), 0);
    }


    @Override
    public void scrollTo(int x, int y) {
        if (!isScrollEnable()) {
            mScroller.forceFinished(true);
            return;
        }
        int oldX = mScrollX;
        mScrollX = x;
        if (mScrollX < getMinScrollX()) { //滑动最右边
            mScrollX = getMinScrollX();
            onRightSide();
            mScroller.forceFinished(true);

        } else if (mScrollX > getMaxScrollX()) {//滑动最左边
            mScrollX = getMaxScrollX();
            onLeftSide();
            mScroller.forceFinished(true);
        }
        onScrollChanged(mScrollX, 0, oldX, 0);
    }


    // 获取位移的最小值
    public int getMinScrollX() {
        return 0;
    }

    //获取位移的最大值
    public int getMaxScrollX() {
        return 0;
    }

    //滑到了最左边
    public void onLeftSide() {

    }

    // 滑到了最右边
    public void onRightSide() {

    }


    /**
     * 移动试图
     *
     * @param x
     */
    private void moveWithPoint(int x) {

    }

    /**
     * 手指离开界面
     */
    private void onPointLeave() {


    }

    //是否在触摸中
    public boolean isTouch() {
        return touch;
    }


    //是否是多指触控
    public boolean isMultipleTouch() {
        return mMultipleTouch;
    }


    /******************************长按，点击手势*****************************************/

    // 单击, 触摸屏按下时立刻触发
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    //短按, 触摸屏按下后片刻后抬起，会触发这个手势，如果迅速抬起则不会
    @Override
    public void onShowPress(MotionEvent e) {

    }

    //抬起, 手指离开触摸屏时触发(长按、滚动、滑动时，不会触发这个手势)
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    //滚动, 触摸屏按下后移动
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (!isMultipleTouch()) {
            scrollBy(Math.round(distanceX), 0);
            return true;
        }
        return false;
    }

    //长按, 触摸屏按下后既不抬起也不移动，过一段时间后触发
    @Override
    public void onLongPress(MotionEvent e) {

    }

    //滑动, 触摸屏按下后快速移动并抬起，会先触发滚动手势，跟着触发一个滑动手势
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (!isTouch() && isScrollEnable()) {
            mScroller.fling(mScrollX, 0
                    , Math.round(velocityX), 0,
                    Integer.MIN_VALUE, Integer.MAX_VALUE,
                    0, 0);
        }
        return true;
    }


    public boolean isScrollEnable() {
        return mScrollEnable;
    }


}






































