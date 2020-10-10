package com.json.itemdecoration.wx.right.untils;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;

/**
 * @author puyantao
 * @describe
 * @create 2020/10/10 12:36
 */
public class TopLinearSmoothScroller extends LinearSmoothScroller {
    public TopLinearSmoothScroller(Context context) {
        super(context);
    }

    @Override
    public int getVerticalSnapPreference() {
        return SNAP_TO_START;
    }
}
