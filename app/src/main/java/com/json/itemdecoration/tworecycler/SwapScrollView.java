package com.json.itemdecoration.tworecycler;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 *
 * Description
 * Author puyantao
 * Email 1067899750@qq.com
 * Date 2018-12-18 17:19
 */

public class SwapScrollView extends HorizontalScrollView {

    private ScrollViewListener scrollViewListener = null;

    public SwapScrollView(Context context) {
        super(context);
    }

    public SwapScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SwapScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }


    public interface ScrollViewListener {
        void onScrollChanged(SwapScrollView scrollView, int x, int y, int oldx, int oldy);
    }
}



