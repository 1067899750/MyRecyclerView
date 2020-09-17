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
    private GalleryViewPager mGalleryViewPager;
    private int[] mBannerArr = {
            R.mipmap.pag1,
            R.mipmap.pag2,
    };
    private LinearLayout mGalleryDotsLl;
    private List<TextView> txtPoints;


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

        mGalleryViewPager = findViewById(R.id.gallery_view_pager);
        mGalleryDotsLl = findViewById(R.id.gallery_dots_ll);
        initCircle();
        CommunityGalleryAdapter communityGalleryAdapter = new CommunityGalleryAdapter(this, mBannerArr);
        mGalleryViewPager.setAdapter(communityGalleryAdapter);
        mGalleryViewPager.setPageTransformer(true, new ScaleBannerTransformer());
        mGalleryViewPager.setDuration(3000);
        mGalleryViewPager.startAutoCycle();
        mGalleryViewPager.setSliderTransformDuration(1500, null);
        mGalleryViewPager.setOnClickChangerListener(new GalleryViewPager.OnClickChangerListener() {
            @Override
            public void onSelectionPosition(int position) {
                changePoints((position - 1) % mBannerArr.length);
            }
        });

    }


    /**
     * 初始化小圆点
     */
    public void initCircle() {
        txtPoints = new ArrayList<>();
        int d = DensityUtil.dp2px(6);
        int m = 10;
        for (int i = 0; i < mBannerArr.length; i++) {
            TextView txt = new TextView(this);
            LinearLayout.LayoutParams params;
            if (i == 0) {
                params = new LinearLayout.LayoutParams(d * 2, d );
                txt.setBackgroundResource(R.drawable.home_yuan_sel);
            } else {
                params = new LinearLayout.LayoutParams(d, d);
                txt.setBackgroundResource(R.drawable.home_yuan);
            }
            params.setMargins(m, m, m, m);
            txt.setLayoutParams(params);
            txtPoints.add(txt);
            mGalleryDotsLl.addView(txt);
        }
    }


    private void changePoints(int pos) {
        int d = DensityUtil.dp2px(6);
        int m = 10;
        if (txtPoints != null) {
            for (int i = 0; i < txtPoints.size(); i++) {
                LinearLayout.LayoutParams params;
                if (pos == i) {
                    params = new LinearLayout.LayoutParams(d * 2, d );
                    txtPoints.get(i).setBackgroundResource(R.drawable.home_yuan_sel);
                } else {
                    params = new LinearLayout.LayoutParams(d, d);
                    txtPoints.get(i).setBackgroundResource(R.drawable.home_yuan);
                }
                params.setMargins(m, m, m, m);
                txtPoints.get(i).setLayoutParams(params);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGalleryViewPager.stopAutoCycle();
    }
}














