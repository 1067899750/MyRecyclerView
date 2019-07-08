package com.json.itemdecoration.middle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.json.itemdecoration.R;
import com.json.itemdecoration.tworecycler.SwapScrollView;
import com.json.itemdecoration.untils.MyNestedScrollview;
import com.json.itemdecoration.untils.StrUntils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description 向中间双向滑动 RecyckerView
 * Author puyantao
 * Email 1067899750@qq.com
 * Date 2019/7/3 10:08
 */
public class RecyclerMiddleActivity extends AppCompatActivity {
    @BindView(R.id.centre_recycler)
    RecyclerView centreRecycler;
    @BindView(R.id.left_recycler)
    RecyclerView leftRecycler;
    @BindView(R.id.right_recycler)
    RecyclerView rightRecycler;
    @BindView(R.id.left_sv)
    MyNestedScrollview leftSv;
    @BindView(R.id.right_sv)
    MyNestedScrollview rightSv;

    private Context mContext;

    private LeftMiddleAdapter mLeftMiddleAdapter;
    private RightMiddleAdapter mRightMiddleAdapter;
    private CenterMiddleAdapter mCenterMiddleAdapter;


    private ArrayList<String> mCenterListItems;
    private ArrayList<MiddelsItem> mLeftMiddelsItems;
    private ArrayList<MiddelsItem> mRightMiddelsItems;

    public static void startRecyclerMiddleActivity(Activity activity) {
        Intent intent = new Intent(activity, RecyclerMiddleActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_middle);
        ButterKnife.bind(this);

        mContext = RecyclerMiddleActivity.this;

        mCenterListItems = new ArrayList<>();
        mLeftMiddelsItems = new ArrayList<>();
        mRightMiddelsItems = new ArrayList<>();
        initDatas();
        initView();
    }

    private void initDatas() {
        for (int i = 0; i < 25; i++) {
            mCenterListItems.add(StrUntils.floatToString(100 + i, 2));
        }

        for (int i = 0; i < 25; i++) {
            MiddelsItem middelsItem = new MiddelsItem();
            middelsItem.setSell(StrUntils.floatToString(20 + i, 2));
            middelsItem.setBuy(StrUntils.floatToString(10 + i, 2));
            middelsItem.setPresentBuy("55.21%");
            middelsItem.setPresentSell("22.01%");
            mLeftMiddelsItems.add(middelsItem);
            mRightMiddelsItems.add(middelsItem);
        }
    }

    private void initView() {
        mCenterMiddleAdapter = new CenterMiddleAdapter(mContext, mCenterListItems, R.layout.center_middle_layout);
        final LinearLayoutManager centerllm = new LinearLayoutManager(mContext);
        centerllm.setOrientation(LinearLayoutManager.VERTICAL);
        centreRecycler.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        centreRecycler.setLayoutManager(centerllm);
        centreRecycler.setAdapter(mCenterMiddleAdapter);
        setRecyclerViewSlide(centreRecycler, leftRecycler, rightRecycler);


        mLeftMiddleAdapter = new LeftMiddleAdapter(mContext, mLeftMiddelsItems, R.layout.left_middel_layout);
        final LinearLayoutManager leftllm = new LinearLayoutManager(mContext);
        leftllm.setOrientation(LinearLayoutManager.VERTICAL);
        leftRecycler.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        leftRecycler.setLayoutManager(leftllm);
        leftRecycler.setAdapter(mLeftMiddleAdapter);
        setRecyclerViewSlide(leftRecycler, rightRecycler, centreRecycler);

        mRightMiddleAdapter = new RightMiddleAdapter(mContext, mRightMiddelsItems, R.layout.reght_middle_layout);
        LinearLayoutManager rightllm = new LinearLayoutManager(mContext);
        rightllm.setOrientation(LinearLayoutManager.VERTICAL);
        rightRecycler.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        rightRecycler.setLayoutManager(rightllm);
        rightRecycler.setAdapter(mRightMiddleAdapter);
        setRecyclerViewSlide(rightRecycler, leftRecycler, centreRecycler);



        leftSv.setScrollView(rightSv);
        rightSv.setScrollView(leftSv);

    }


    /**
     * 设置 RecyclerView 的联动
     *
     * @param recyclerView  滑动的 RecyclerView
     * @param recyclerViews 联动的 RecyclerView 组
     */
    private void setRecyclerViewSlide(RecyclerView recyclerView, final RecyclerView... recyclerViews) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    for (int i = 0; i < recyclerViews.length; i++) {
                        recyclerViews[i].scrollBy(dx, dy);
                    }

                }
            }
        });
    }

}



















