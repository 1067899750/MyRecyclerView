package com.json.itemdecoration.looper;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.json.itemdecoration.R;
import com.json.itemdecoration.untils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puyantao
 * @description :
 * @date 2020/9/16
 */
public class BaseBannerLoopView extends RelativeLayout {
    private Context mContext;
    private LoopViewPager mLoopViewPager;
    private LinearLayout mLayoutDots;
    private List<ImageView> mImageList = new ArrayList();

    public BaseBannerLoopView(Context context) {
        this(context, null);
    }

    public BaseBannerLoopView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseBannerLoopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        intiView();
    }

    private void intiView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_item_view, null);
        addView(view);
        mLoopViewPager = findViewById(R.id.loop_view_pager);
        mLayoutDots = findViewById(R.id.layout_dots);

    }


    public void initViewPager(int[] mBannerArr) {
        //图片圆角处理
        for (int i = 0; i < mBannerArr.length; i++) {
            ImageView view1 = new ImageView(mContext);
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory
                    .create(getResources(), BitmapFactory.decodeResource(getResources(), mBannerArr[i]));
            roundedBitmapDrawable.setCornerRadius(30);
            view1.setScaleType(ImageView.ScaleType.FIT_XY);
            view1.setImageDrawable(roundedBitmapDrawable);
            mImageList.add(view1);
        }

        mLoopViewPager.setClipChildren(false);
        //设置viewpage之间的间距
        mLoopViewPager.setPageMargin(DensityUtil.dp2px(15));
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mLoopViewPager.getLayoutParams();
        //高度根据图片进行适配（这里图片为750 *300）
        params.height = getResources().getDisplayMetrics().heightPixels;
        params.width = getResources().getDisplayMetrics().widthPixels;
        mLoopViewPager.setLayoutParams(params);
        mLoopViewPager.setAdapter(new MyAdapter(mImageList));
        mLoopViewPager.setPageTransformer(true, new ScaleBannerTransformer());
        mLoopViewPager.autoLoop(true);
        initCircle();
    }


    /**
     * 初始化小圆点
     */
    private void initCircle() {
        List mTxtPoints = new ArrayList<>();
        int d = DensityUtil.dp2px(6);
        int m = 10;
        for (int i = 0; i < mImageList.size(); i++) {
            TextView txt = new TextView(mContext);
            if (i == 0) {
                txt.setBackgroundResource(R.drawable.home_yuan_sel);
            } else {
                txt.setBackgroundResource(R.drawable.home_yuan);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(d, d);
            params.setMargins(m, m, m, m);
            txt.setLayoutParams(params);
            mTxtPoints.add(txt);
            mLayoutDots.addView(txt);
        }
        mLoopViewPager.setTxtPoints(mTxtPoints);
    }

}














