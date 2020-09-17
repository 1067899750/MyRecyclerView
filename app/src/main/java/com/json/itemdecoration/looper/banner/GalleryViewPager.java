package com.json.itemdecoration.looper.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.json.itemdecoration.R;
import com.json.itemdecoration.untils.DensityUtil;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author puyantao
 * @description
 * @date 2020/9/16 21:27
 */
public class GalleryViewPager extends LinearLayout {
    private Context mContext;
    private ViewPager mViewPager;
    private LinearLayout mGalleryDots;

    public GalleryViewPager(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public GalleryViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.gallery, this);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setPageMargin(DensityUtil.dp2px(15));
        mViewPager.setOffscreenPageLimit(3);

        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_UP: {
                        recoverCycle();
                        break;
                    }
                }
                return false;
            }
        });

        mGalleryDots = findViewById(R.id.gallery_dots);

    }


    public void setPageMargin(int px) {
        mViewPager.setPageMargin(px);
    }

    public void setOffscreenPageLimit(int count) {
        mViewPager.setOffscreenPageLimit(count);
    }

    public void setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer) {
        mViewPager.setPageTransformer(reverseDrawingOrder, transformer);
    }

    public void setViewPagerMargin(int px) {
        MarginLayoutParams layoutParams = (MarginLayoutParams) mViewPager.getLayoutParams();
        layoutParams.setMargins(px, 0, px, 0);
        mViewPager.setLayoutParams(layoutParams);
    }

    public void setCurrentItem(int position) {
        mViewPager.setCurrentItem(position);
    }

    public void setAdapter(PagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);
    }


    private Timer mCycleTimer;
    private TimerTask mCycleTask;

    /**
     *
     */
    private Timer mResumingTimer;
    private TimerTask mResumingTask;

    /**
     *
     */
    private boolean mCycling;

    private long mSliderDuration = 3000;

    private boolean mAutoCycle;
    private boolean mAutoRecover = true;

    private Handler mh = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            moveNextPosition(true);
        }
    };

    public void startAutoCycle() {
        startAutoCycle(mSliderDuration, mSliderDuration, mAutoRecover);
    }

    /**
     * start auto cycle.
     *
     * @param delay       delay time
     * @param duration    animation duration time.
     * @param autoRecover if recover after user touches the slider.
     */
    public void startAutoCycle(long delay, long duration, boolean autoRecover) {
        if (mCycleTimer != null) mCycleTimer.cancel();
        if (mCycleTask != null) mCycleTask.cancel();
        if (mResumingTask != null) mResumingTask.cancel();
        if (mResumingTimer != null) mResumingTimer.cancel();
        mSliderDuration = duration;
        mCycleTimer = new Timer();
        mAutoRecover = autoRecover;
        mCycleTask = new TimerTask() {
            @Override
            public void run() {
                mh.sendEmptyMessage(0);
            }
        };
        mCycleTimer.schedule(mCycleTask, delay, mSliderDuration);
        mCycling = true;
        mAutoCycle = true;
    }

    /**
     * pause auto cycle.
     */
    public void pauseAutoCycle() {
        if (mCycling) {
            mCycleTimer.cancel();
            mCycleTask.cancel();
            mCycling = false;
        } else {
            if (mResumingTimer != null && mResumingTask != null) {
                recoverCycle();
            }
        }
    }

    /**
     * set the duration between two slider changes. the duration value must >= 500
     *
     * @param duration
     */
    public void setDuration(long duration) {
        if (duration >= 500) {
            mSliderDuration = duration;
            if (mAutoCycle && mCycling) {
                startAutoCycle();
            }
        }
    }

    /**
     * stop the auto circle
     */
    public void stopAutoCycle() {
        if (mCycleTask != null) {
            mCycleTask.cancel();
        }
        if (mCycleTimer != null) {
            mCycleTimer.cancel();
        }
        if (mResumingTimer != null) {
            mResumingTimer.cancel();
        }
        if (mResumingTask != null) {
            mResumingTask.cancel();
        }
        mAutoCycle = false;
        mCycling = false;
    }

    /**
     * when paused cycle, this method can weak it up.
     */
    public void recoverCycle() {
        if (!mAutoRecover || !mAutoCycle) {
            return;
        }

        if (!mCycling) {
            if (mResumingTask != null && mResumingTimer != null) {
                mResumingTimer.cancel();
                mResumingTask.cancel();
            }
            mResumingTimer = new Timer();
            mResumingTask = new TimerTask() {
                @Override
                public void run() {
                    startAutoCycle();
                }
            };
            mResumingTimer.schedule(mResumingTask, 6000);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                pauseAutoCycle();
                break;
        }
        return false;
    }

    /**
     * set the duration between two slider changes.
     *
     * @param period
     * @param interpolator
     */
    public void setSliderTransformDuration(int period, Interpolator interpolator) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mViewPager.getContext(), interpolator, period);
            mScroller.set(mViewPager, scroller);
        } catch (Exception e) {

        }
    }

    /**
     * set current slider
     *
     * @param position
     */
    public void setCurrentPosition(int position, boolean smooth) {
        if (getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }
        if (position >= getRealAdapter().getCount()) {
            throw new IllegalStateException("Item position is not exist");
        }
        int p = mViewPager.getCurrentItem() % getRealAdapter().getCount();
        int n = (position - p) + mViewPager.getCurrentItem();
        mViewPager.setCurrentItem(n, smooth);
    }

    public void setCurrentPosition(int position) {
        setCurrentPosition(position, true);
    }

    /**
     * move to prev slide.
     */
    public void movePrevPosition(boolean smooth) {

        if (getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }

        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, smooth);
    }

    public void movePrevPosition() {
        movePrevPosition(true);
    }

    /**
     * move to next slide.
     */
    public void moveNextPosition(boolean smooth) {
        if (getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, smooth);
    }
    public void moveNextPosition() {
        moveNextPosition(true);
    }


    private PagerAdapter getRealAdapter() {
        PagerAdapter adapter = mViewPager.getAdapter();
        return adapter;
    }
}
