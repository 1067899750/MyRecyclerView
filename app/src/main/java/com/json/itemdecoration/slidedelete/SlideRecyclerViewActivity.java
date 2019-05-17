package com.json.itemdecoration.slidedelete;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.json.itemdecoration.R;
import com.json.itemdecoration.untils.StrUntils;
import com.json.itemdecoration.untils.SlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description
 * Author puyantao
 * Email 1067899750@qq.com
 * Date 2019/5/17 10:00
 */

public class SlideRecyclerViewActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view_list)
    SlideRecyclerView recyclerViewList;

    private SlideModel mSlideModel;
    private ArrayList<SlideModel> mInventories;
    List<SlideModel.DataBean.BillRecordListBean> mBillRecordListBeans;
    private InventoryAdapter mInventoryAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);

        initData();



        mInventoryAdapter = new InventoryAdapter(this, mBillRecordListBeans);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_inset));
        recyclerViewList.addItemDecoration(itemDecoration);
        recyclerViewList.setAdapter(mInventoryAdapter);
        mInventoryAdapter.setOnItemClickListener(new InventoryAdapter.OneClickLister() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onDeleteClick(View view, int position) {

            }

            @Override
            public void onUpdateClick(View view, int position) {

            }
        });

    }

    private void initData() {
        //获取asset目录下的资源文件
        String assetsData = StrUntils.getAssetsData(this,"delete.json");
        Gson gson = new Gson();
        mSlideModel = gson.fromJson(assetsData, SlideModel.class);
        mBillRecordListBeans = mSlideModel.getData().getBillRecordList();
    }



}



















