package com.json.itemdecoration.looper.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.json.itemdecoration.R;
import com.json.itemdecoration.looper.ScaleBannerTransformer;
import com.json.itemdecoration.untils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puyantao
 * @describe
 * @create 2020/9/17 12:39
 */
public class GalleryView extends RelativeLayout {
    private Context mContext;
    private GalleryViewPager mGalleryViewPager;
    private LinearLayout mGalleryDotsLl;
    private List<TextView> txtPoints;

    public GalleryView(Context context) {
        this(context, null);
    }

    public GalleryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        intiView();
    }

    private void intiView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.gallery__view_layout, null);
        addView(view);

        mGalleryViewPager = findViewById(R.id.gallery_view_pager);
        mGalleryDotsLl = findViewById(R.id.gallery_dots_ll);
    }


    /**
     * 初始化数据
     */
    public void initData(final int[] data) {
        CommunityGalleryAdapter communityGalleryAdapter = new CommunityGalleryAdapter(mContext, data);
        mGalleryViewPager.setAdapter(communityGalleryAdapter);
        mGalleryViewPager.setPageTransformer(true, new ScaleBannerTransformer());
        mGalleryViewPager.setDuration(3000);
        mGalleryViewPager.startAutoCycle();
        mGalleryViewPager.setSliderTransformDuration(1500, null);
        mGalleryViewPager.setOnClickChangerListener(new GalleryViewPager.OnClickChangerListener() {
            @Override
            public void onSelectionPosition(int position) {
                changePoints((position - 1) % data.length);
            }
        });


        //初始化圆点
        txtPoints = new ArrayList<>();
        int d = DensityUtil.dp2px(6);
        int m = 10;
        for (int i = 0; i < data.length; i++) {
            TextView txt = new TextView(mContext);
            LinearLayout.LayoutParams params;
            if (i == 0) {
                params = new LinearLayout.LayoutParams(d * 2, d);
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
                    params = new LinearLayout.LayoutParams(d * 2, d);
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


    public void onDestroy() {
        mGalleryViewPager.stopAutoCycle();
    }
}










