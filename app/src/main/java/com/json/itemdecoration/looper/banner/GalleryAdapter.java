package com.json.itemdecoration.looper.banner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author puyantao
 * @description
 * @date 2020/9/16 21:29
 */
public abstract class GalleryAdapter extends PagerAdapter {

    public abstract int getGallerySize();

    public abstract View getItemView(int position);

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= getGallerySize();
        if (position <= 0) {
            position = getGallerySize() + position;
        }
        View view = getItemView(position);
        container.addView(view);
        return view;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
