package com.json.itemdecoration.water;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.json.itemdecoration.R;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 *
 * Description 水印应用
 * Author puyantao
 * Email 1067899750@qq.com
 * Date 2018-12-14 15:21
 */

public class WaterMarkActivity extends AppCompatActivity {

    @BindView(R.id.rlLogo)
    View rlLogo;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_mark);
        ButterKnife.bind(this);

        mContext = WaterMarkActivity.this;

        rlLogo.setBackgroundDrawable(new MyDrawLogo(mContext, -30));


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_alphametal_watermark);


    }




}












