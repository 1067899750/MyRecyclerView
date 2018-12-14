package com.json.itemdecoration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.json.itemdecoration.expandableview.ExpandableListViewActivity;
import com.json.itemdecoration.relateview.TwoRecyclerActivity;
import com.json.itemdecoration.tworecycler.TwoRecyActivity;
import com.json.itemdecoration.water.WaterMarkActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        startActivity(new Intent(MainActivity.this, WaterMarkActivity.class));

    }


    @OnClick({R.id.btn_explandle_list_view,
            R.id.btn_two_recycler,
            R.id.btn_two_recycler2,
            R.id.water_mark_btn})
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
        }
    }
}










