package com.json.itemdecoration.looper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.json.itemdecoration.R;
import com.json.itemdecoration.looper.banner.CommunityGalleryAdapter;
import com.json.itemdecoration.looper.banner.GalleryView;
import com.json.itemdecoration.looper.banner.GalleryViewPager;
import com.json.itemdecoration.untils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puyantao
 * @description 无限轮廓图
 * @date 2020/9/16 20:02
 */
public class LooperActivity extends AppCompatActivity {
    private BaseBannerLoopView mBaseBannerView;
    private GalleryView mGalleryView;

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

        mGalleryView = findViewById(R.id.gallery_view);
        mGalleryView.initData(mBannerArr);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGalleryView.onDestroy();
    }
}














