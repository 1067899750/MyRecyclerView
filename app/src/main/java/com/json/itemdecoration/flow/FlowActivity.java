package com.json.itemdecoration.flow;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.json.itemdecoration.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @description 流式布局
 * @author puyantao
 * @date 2020/11/17 10:12
 */
public class FlowActivity extends AppCompatActivity {
    private static final String TAG = "FlowActivity";
    private FlowLayout mFlowLayout;
    private List<String> mContentList = new ArrayList<>();

    public static void startActivity(Activity activity){
        Intent intent = new Intent(activity, FlowActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        initData();
        initView();
    }


    private void initData() {
        for (int i = 0; i <10; i++) {
            mContentList.add("Android");
            mContentList.add("Java");
            mContentList.add("IOS");
            mContentList.add("python");
        }
    }

    private void initView() {
        mFlowLayout =  findViewById(R.id.flow);
        //往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        if (mFlowLayout != null) {
            mFlowLayout.removeAllViews();
        }
        for (int i = 0; i < mContentList.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(28, 10, 28, 10);
            tv.setText(mContentList.get(i));
            tv.setMaxEms(10);
            tv.setSingleLine();
            tv.setBackgroundResource(R.drawable.shape_travel_loc_bg);
            tv.setLayoutParams(layoutParams);
            mFlowLayout.addView(tv, layoutParams);
        }

    }


}