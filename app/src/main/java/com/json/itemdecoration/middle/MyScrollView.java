package com.json.itemdecoration.middle;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * @author puyantao
 * @describe
 * @create 2019/12/13 13:49
 */
public class MyScrollView extends LinearLayout {
    private Scroller mScroller;
    /**
     * 整个的ScrollView的高度
     */
    private int scrollHeight = 0;
    private float lastX = 0;
    private float lastY = 0;
    /**
     * 在自定义view中经常使用的是重新绘制
     * 在viewGroup中经常使用scrollBy等进行移动
     */

    private int windowHeight;
    /**
     * 这是进行记录横向或者竖向是否进行滑动了
     */
    private float moveX = 0;
    private float moveY = 0;

    private VelocityTracker mVelocityTracker;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        init();
    }

    public void init() {
        this.setOrientation(LinearLayout.VERTICAL);
    }

    /**
     *  如果是继承了LinearLayout 那么就没必要进行测量高度和摆放布局，仅仅进行计算滑动的距离就可以了。
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        scrollHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            //添加上所有view的margin，获取整个子view的高度
            scrollHeight += child.getMeasuredHeight() + lp.bottomMargin + lp.topMargin;
        }
        //获取ScrollView的高度
        windowHeight = getMeasuredHeight();
        Log.e("TAG", "MyScrollView---scrollHeight---" + scrollHeight + "   windowHeight----" + windowHeight);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("TAG", "MyScrollView---dispatchTouchEvent---" + ev.getAction());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = moveX = ev.getX();
                lastY = moveY = ev.getY();
                mScroller.forceFinished(true);
                if(mVelocityTracker==null){
                    mVelocityTracker=VelocityTracker.obtain();
                }else{
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TAG", "MyScrollView---getScrollY====" + getScrollY());
                mVelocityTracker.addMovement(ev);
                float curX = ev.getX();
                float curY = ev.getY();
                //垂直滑动的时候进行移动
                if (Math.abs(curY - lastY) > Math.abs(curX - lastX)) {
                    scrollBy(0, -(int) (curY - lastY));
//                  或者使用下面的方法，因为设置了平滑过渡时间为0
//                   mScroller.startScroll(0,getScrollY(),0,-(int)(curY-lastY),0);

                    invalidate();
                }
                lastX = curX;
                lastY = curY;
                break;
            case MotionEvent.ACTION_UP:

                //这里做了一个回弹的效果，在第二个参数中设置了滚动了多少距离，然后dy为它的负值，立马就回弹回去
                //这里肯定不能传递ev.getX()，因为getX()是获取的手指点击的位置,因此一定要使用getScrollY()，这是获取的滚动后的距离。
                //这里getScrollY()是在scrollTo()或scrollBy()中进行赋值。因此要调用这个方法，一定要先调用这两个方法。
                //startScroll()方法不适合在action_move中调用，因为这个方法默认的时间就是250毫秒，频繁的使用postInvalidate()进行刷新，就会导致移动动作的
                //覆盖，反而出现很难移动的效果。因为action_move的回调很快，每个十几像素就回调了。如果将startScroll()的第五个参数设置为0，也就是间隔时间设置为0
                //mScroller.startScroll(0,(int)getScrollY(),0,-(int)(curY-lastY),0);  那么就出现了和scrollBy()相似的效果
                mScroller.abortAnimation();
                if (getScrollY() < 0) {
                    //证明达到上边界了，这时候要进行回弹处理
                    mScroller.startScroll(0, getScrollY(), 0, -getScrollY());
                    postInvalidate();// 重绘执行computeScroll()
                } else if (windowHeight + getScrollY() > scrollHeight) {
                    //达到最底部
                    mScroller.startScroll(0, getScrollY(), 0, -(getScrollY() - (scrollHeight - windowHeight)));
                    postInvalidate();// 重绘执行computeScroll()
                } else {
                    mVelocityTracker.computeCurrentVelocity(1000);
                    float yVelocity = mVelocityTracker.getYVelocity();
                    Log.e("TAG", "MyScrollView---yVelocity====" + yVelocity);
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
                    if (Math.abs(yVelocity) > 50) {
//                        mScroller.extendDuration(2000);
                        mScroller.fling(0, getScrollY(), 0, -(int) yVelocity,
                                0, 0,
                                0, (scrollHeight - windowHeight));
                        postInvalidate();
                    }
                }
                //进行计算移动的距离
                moveX = Math.abs(ev.getX() - moveX);
                moveY = Math.abs(ev.getY() - moveY);
                //如果横向或者竖向已经移动了一段距离，那么就不能响应子控件的点击事件
                if (moveY > 10) {
                    return true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("TAG", "MyScrollView---onInterceptTouchEvent---" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("TAG", "MyScrollView---onTouchEvent---" + ev.getAction());
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //如果scroller还没停止，那么就还进行不停的绘制
        if (mScroller.computeScrollOffset()) {
            Log.e("TAG", "MyScrollView---computeScroll---");
            //注意这里的getCurrY()的源码获取的是进行微移后的当前的坐标，不是相对距离
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
