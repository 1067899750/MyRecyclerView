package com.json.itemdecoration.looper;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import android.widget.TextView;

import com.json.itemdecoration.R;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author puyantao
 * @description :
 * @date 2020/9/16
 */
public class LoopViewPager extends ViewPager {

    private OnPageChangeListener mOuterPageChangeListener;
    private LoopPagerAdapterWrapper mAdapter;

    private MyHandler mHandler;
    private final static int HANDLE_LOOP_MSG = 101;
    private AtomicBoolean isAutoLoop = new AtomicBoolean();

    public void setTxtPoints(List<TextView> txtPoints) {
        this.txtPoints = txtPoints;
    }

    private List<TextView> txtPoints;

    public void changePoints(int pos) {
        if (txtPoints != null) {
            for (int i = 0; i < txtPoints.size(); i++) {
                if (pos == i) {
                    txtPoints.get(i).setBackgroundResource(R.drawable.home_yuan_sel);
                } else {
                    txtPoints.get(i).setBackgroundResource(R.drawable.home_yuan);
                }
            }
        }
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        mAdapter = new LoopPagerAdapterWrapper(adapter);
        super.setAdapter(mAdapter);
        isAutoLoop.set(false);
        setCurrentItem(0, false);
    }

    @Override
    public PagerAdapter getAdapter() {
        return mAdapter != null ? mAdapter.getRealAdapter() : null;
    }

    /**
     * 获取当前真实的item
     *
     * @return
     */
    public int getRealItem() {
        return mAdapter != null ? mAdapter.toRealPosition(super.getCurrentItem()) : 0;
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        int realItem = mAdapter.toInnerPosition(item);
        super.setCurrentItem(realItem, smoothScroll);
    }


    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOuterPageChangeListener = listener;
    }

    public LoopViewPager(Context context) {
        super(context);
        init();
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        super.setOnPageChangeListener(onPageChangeListener);
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            Scroller scroller = new Scroller(getContext(), (Interpolator) interpolator.get(null)) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                    //控制滑动速度
                    super.startScroll(startX, startY, dx, dy,
                            (int) (1300 * (double) Math.abs(dx) / getWidth(getContext())));
                }
            };
            scrollerField.set(this, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void autoLoop(boolean isAuto) {
        if (mHandler == null) {
            mHandler = new MyHandler(getContext());
        }
        if (isAuto) {
            mHandler.sendEmptyMessageDelayed(HANDLE_LOOP_MSG, 3000);
        } else {
            mHandler.removeCallbacksAndMessages(null);
        }
        isAutoLoop.set(isAuto);
    }

    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        private float mPreviousOffset = -1;
        private float mPreviousPosition = -1;

        @Override
        public void onPageSelected(int position) {

            int realPosition = mAdapter.toRealPosition(position);
            changePoints(realPosition);
            if (mPreviousPosition != realPosition) {
                mPreviousPosition = realPosition;
                if (mOuterPageChangeListener != null) {
                    mOuterPageChangeListener.onPageSelected(realPosition);
                }
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            int realPosition = position;
            if (mAdapter != null) {
                realPosition = mAdapter.toRealPosition(position);

                if (positionOffset == 0
                        && mPreviousOffset == 0
                        && (position == 0 || position == mAdapter.getCount() - 1)) {
                    setCurrentItem(realPosition, false);
                }
            }

            mPreviousOffset = positionOffset;
            if (mOuterPageChangeListener != null) {
                if (realPosition != mAdapter.getRealCount() - 1) {
                    mOuterPageChangeListener.onPageScrolled(realPosition,
                            positionOffset, positionOffsetPixels);
                } else {
                    if (positionOffset > .5) {
                        mOuterPageChangeListener.onPageScrolled(0, 0, 0);
                    } else {
                        mOuterPageChangeListener.onPageScrolled(realPosition,
                                0, 0);
                    }
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case SCROLL_STATE_DRAGGING:
                    if (isAutoLoop.get()) {
                        mHandler.removeCallbacksAndMessages(null);
                    }
                    break;
                case SCROLL_STATE_IDLE:
                    if (isAutoLoop.get()) {
                        mHandler.sendEmptyMessageDelayed(HANDLE_LOOP_MSG, 3000);
                    }
                    break;
                case SCROLL_STATE_SETTLING:
                    break;
            }
            if (mOuterPageChangeListener != null) {
                mOuterPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private class MyHandler extends Handler {

        WeakReference<Context> mWeakReference;

        public MyHandler(Context context) {
            mWeakReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            final Context context = mWeakReference.get();
            if (context == null) {
                return;
            }
            switch (msg.what) {
                case HANDLE_LOOP_MSG:
                    int curItem = getCurrentItem();
                    setCurrentItem(++curItem);
                    changePoints(curItem % txtPoints.size());
                    break;
            }
        }
    }


    public int getWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}















