package com.json.itemdecoration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.json.itemdecoration.expandableview.ExpandableListViewActivity;
import com.json.itemdecoration.image.ImageViewActivity;
import com.json.itemdecoration.looper.LooperActivity;
import com.json.itemdecoration.middle.RecyclerMiddleActivity;
import com.json.itemdecoration.middle.TextActivity;
import com.json.itemdecoration.piceure.ChoosePhotoActivity;
import com.json.itemdecoration.qq.QQDeleteActivity;
import com.json.itemdecoration.relateview.TwoRecyclerActivity;
import com.json.itemdecoration.slidedelete.SlideRecyclerViewActivity;
import com.json.itemdecoration.tworecycler.TwoRecyActivity;
import com.json.itemdecoration.water.WaterMarkActivity;
import com.json.itemdecoration.wave.WaveProgressActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_explandle_list_view)
    Button btnExplandleListView;
    @BindView(R.id.btn_two_recycler)
    Button btnTwoRecycler;
    @BindView(R.id.btn_two_recycler2)
    Button btnTwoRecycler2;
    @BindView(R.id.water_mark_btn)
    Button waterMarkBtn;
    @BindView(R.id.recycler_view_deletel)
    Button recyclerViewDeletel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_explandle_list_view,
            R.id.btn_two_recycler,
            R.id.btn_two_recycler2,
            R.id.water_mark_btn,
            R.id.recycler_view_deletel,
            R.id.wave_pregress_btn,
            R.id.picture_btn,
            R.id.qq_btn,
            R.id.looper_btn,
            R.id.middle_rv_btn,
            R.id.iamge_view_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_explandle_list_view:
                startActivity(new Intent(MainActivity.this, ExpandableListViewActivity.class));
                break;

            case R.id.btn_two_recycler:
                startActivity(new Intent(MainActivity.this, TwoRecyclerActivity.class));
                break;

            case R.id.btn_two_recycler2:
                startActivity(new Intent(MainActivity.this, TwoRecyActivity.class));
                break;

            case R.id.water_mark_btn:
                startActivity(new Intent(MainActivity.this, WaterMarkActivity.class));
                break;


            case R.id.recycler_view_deletel:
                startActivity(new Intent(MainActivity.this, SlideRecyclerViewActivity.class));
                break;

            case R.id.wave_pregress_btn:
                startActivity(new Intent(MainActivity.this, WaveProgressActivity.class));
                break;

            case R.id.picture_btn:
                startActivity(new Intent(MainActivity.this, ChoosePhotoActivity.class));
                break;

            case R.id.qq_btn:
                startActivity(new Intent(MainActivity.this, QQDeleteActivity.class));
                break;

            case R.id.looper_btn:
                LooperActivity.startLooperActivity(MainActivity.this);
                break;

            case R.id.middle_rv_btn:
                RecyclerMiddleActivity.startRecyclerMiddleActivity(MainActivity.this);
                break;

            case  R.id.iamge_view_btn:
                ImageViewActivity.startImageViewActivity(MainActivity.this);
                break;
        }
    }


}










