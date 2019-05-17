package com.json.itemdecoration.wave;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;


import com.json.itemdecoration.R;
import com.json.itemdecoration.untils.WaveProgressView;
import android.support.v7.app.AppCompatActivity;

/**
 *
 * Description 测试波纹进度条
 * Author puyantao
 * Email 1067899750@qq.com
 * Date 2019/5/17 13:58
 */

public class WaveProgressActivity extends AppCompatActivity {

    private WaveProgressView wpv_git;

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            float progress = wpv_git.getProgress();
            if (progress >= 100) {
                progress = 0;
            }
            wpv_git.setProgress(++progress);
            wpv_git.setText(progress + "%");
            handler.sendEmptyMessageDelayed(0, 100);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        wpv_git = (WaveProgressView) findViewById(R.id.wpv_git);
        handler.sendEmptyMessage(0);
    }
}
