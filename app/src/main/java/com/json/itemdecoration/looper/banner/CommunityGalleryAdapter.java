package com.json.itemdecoration.looper.banner;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.json.itemdecoration.R;

/**
 * @author puyantao
 * @describe
 * @create 2020/9/17 11:09
 */
public class CommunityGalleryAdapter extends GalleryAdapter {
    private Context mContext;
    private int[] mBannerArr;

    public CommunityGalleryAdapter(Context context, int[] bannerArr) {
        mContext = context;
        mBannerArr = bannerArr;
    }

    @Override
    public int getGallerySize() {
        return mBannerArr.length;
    }

    @Override
    public View getItemView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image, null);
        ImageView imageView = view.findViewById(R.id.image_view);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory
                .create(mContext.getResources(), BitmapFactory.decodeResource(mContext.getResources(), mBannerArr[position - 1]));
        roundedBitmapDrawable.setCornerRadius(8);
        imageView.setBackgroundResource(mBannerArr[position - 1]);
        return view;
    }
}












