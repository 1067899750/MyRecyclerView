package com.json.itemdecoration.looper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.json.itemdecoration.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description 无限轮廓图
 * Author puyantao
 * Email 1067899750@qq.com
 * Date 2019/7/3 9:13
 */
public class LooperActivity extends AppCompatActivity {


    @BindView(R.id.lb_view)
    LoopBannerView lbView;

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
        ButterKnife.bind(this);


        lbView.startLoop();

    }


}














