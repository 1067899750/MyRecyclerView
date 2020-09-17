package com.json.itemdecoration.looper;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * @author puyantao
 * @description :
 * @date 2020/9/16
 */
public class MyAdapter extends PagerAdapter {
    private List<ImageView> mImageList;

    public MyAdapter(List<ImageView> data) {
        this.mImageList = data;
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView view = mImageList.get(position);
        ViewParent viewParent = view.getParent();
        if (viewParent == null) {
            container.addView(view);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("--->", "点击了第" + position + "张图");
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}