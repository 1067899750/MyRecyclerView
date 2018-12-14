package com.json.itemdecoration.tworecycler;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.json.itemdecoration.R;

import java.util.ArrayList;



public class TwoRecyActivity extends AppCompatActivity {
    private Context mContext;
    private RecyclerView mLeftRecycler;
    private RecyclerView mRightRecycler;
    private LinearLayout llMain;
    private ArrayList<String> mContractArrayList;
    private ArrayList<Items> mItemsArrayList;

    private LeftAdapter mLeftAdapter;
    private RightAdapter mRightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_recy);

        mContext = TwoRecyActivity.this;

        mContractArrayList = new ArrayList<>();
        mItemsArrayList = new ArrayList<>();

        initDatas();
        initView();
    }


    private void initView() {
        mLeftRecycler = findViewById(R.id.left_recycler);
        mRightRecycler = findViewById(R.id.right_recycler);


        mLeftAdapter = new LeftAdapter(mContractArrayList);
        LinearLayoutManager leftllm = new LinearLayoutManager(mContext);
        leftllm.setOrientation(LinearLayoutManager.VERTICAL);
        mLeftRecycler.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mLeftRecycler.setLayoutManager(leftllm);
        mLeftRecycler.setAdapter(mLeftAdapter);
        mLeftRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    mRightRecycler.scrollBy(dx, dy);
                }
            }
        });


        mRightAdapter = new RightAdapter(mItemsArrayList);
        LinearLayoutManager rightllm = new LinearLayoutManager(mContext);
        rightllm.setOrientation(LinearLayoutManager.VERTICAL);
        mRightRecycler.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRightRecycler.setLayoutManager(rightllm);
        mRightRecycler.setAdapter(mRightAdapter);
        mRightRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    mLeftRecycler.scrollBy(dx, dy);
                }
            }
        });

    }

    private void initDatas() {
        for (int i = 0; i < 25; i++) {
            mContractArrayList.add("CASH COP" + i);
        }

        for (int i = 0; i < 25; i++) {
            Items items = new Items();
            items.setNewPrices("111--" + i);
            items.setUpDowms("+11--" + i);
            items.setBuy("10--" + i);
            items.setBuyNums("12-" + i);
            items.setBuyDates("14--" + i);
            items.setSell("21--" + i);
            items.setSellNums("22--" + i);
            items.setSellDates("23--" + i);
            items.setYTDPut("23222--" + i);
            mItemsArrayList.add(items);
        }


    }


}












