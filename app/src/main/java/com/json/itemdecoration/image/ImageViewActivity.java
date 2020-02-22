package com.json.itemdecoration.image;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.json.itemdecoration.R;

/**
 *
 * @description 图片放大
 * @author puyantao
 * @date 2019/12/12 10:22
 */
public class ImageViewActivity extends AppCompatActivity {
    private DragImageView mDragImageView;

    public static void startImageViewActivity(Activity activity){
        Intent intent = new Intent(activity, ImageViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        mDragImageView = findViewById(R.id.drag_iv);
        mDragImageView.setDrawable(getResources().getDrawable(R.mipmap.pag1));
    }
}













