package com.json.itemdecoration.looper;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author puyantao
 * @description :
 * @date 2020/9/16
 */
public class ScaleBannerTransformer implements ViewPager.PageTransformer {
    private float scale = 0.85f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position >= 0 && position <= 1) {
            page.setScaleY(scale + (1 - scale) * (1 - position));
        } else if (position > -1 && position < 0) {
            page.setScaleY(1 + (1 - scale) * position);
        } else {
            page.setScaleY(scale);
        }
    }
}
