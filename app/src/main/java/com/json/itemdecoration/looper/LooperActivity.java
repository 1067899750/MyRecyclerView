package com.json.itemdecoration.looper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.json.itemdecoration.R;
import com.json.itemdecoration.looper.banner.GalleryAdapter;
import com.json.itemdecoration.looper.banner.GalleryViewPager;
import com.json.itemdecoration.looper.banner.a.RoundedImageView;

/**
 * @author puyantao
 * @description 无限轮廓图
 * @date 2020/9/16 20:02
 */
public class LooperActivity extends AppCompatActivity {
    private BaseBannerLoopView mBaseBannerView;
    private GalleryViewPager mGallerViewPager;
    private int[] mBannerArr = {
            R.mipmap.pag1,
            R.mipmap.pag2,
            R.mipmap.pag3,
            R.mipmap.pag4,
    };

    public static void startLooperActivity(Activity activity) {
        Intent intent = new Intent(activity, LooperActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);
        mBaseBannerView = findViewById(R.id.banner_loop_view);
        mBaseBannerView.initViewPager(mBannerArr);

        mGallerViewPager = findViewById(R.id.view_pager);
        mGallerViewPager.setAdapter(new Adapter());
        mGallerViewPager.setPageTransformer(true, new ScaleBannerTransformer());
        mGallerViewPager.setDuration(3000);
        mGallerViewPager.startAutoCycle();
        mGallerViewPager.setSliderTransformDuration(1500, null);

    }


    class Adapter extends GalleryAdapter {

        @Override
        public int getGallerySize() {
            return mBannerArr.length;
        }

        @Override
        public View getItemView(int position) {
            View view = LayoutInflater.from(LooperActivity.this).inflate(R.layout.item_image, null);
            RoundedImageView imageView = view.findViewById(R.id.image_view);
            imageView.setBackgroundResource(mBannerArr[position -1]);
            return view;
        }
    }

}














