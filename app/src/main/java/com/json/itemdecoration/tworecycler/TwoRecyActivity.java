package com.json.itemdecoration.tworecycler;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.json.itemdecoration.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TwoRecyActivity extends AppCompatActivity {
    @BindView(R.id.left_recycler)
    RecyclerView leftRecycler;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.tvNewPrice)
    TextView tvNewPrice;
    @BindView(R.id.tvUpDown)
    TextView tvUpDown;
    @BindView(R.id.tvBuy)
    TextView tvBuy;
    @BindView(R.id.tvBuyNums)
    TextView tvBuyNums;
    @BindView(R.id.tvBuyDate)
    TextView tvBuyDate;
    @BindView(R.id.tvSell)
    TextView tvSell;
    @BindView(R.id.tvSellNums)
    TextView tvSellNums;
    @BindView(R.id.tvSellDate)
    TextView tvSellDate;
    @BindView(R.id.tvYTDPut)
    TextView tvYTDPut;
    @BindView(R.id.right_recycler)
    RecyclerView rightRecycler;
    @BindView(R.id.rightScrollView)
    SwapScrollView rightScrollView;
    @BindView(R.id.llMain)
    LinearLayout llMain;

    private Context mContext;
    private RecyclerView mLeftRecycler;
    private RecyclerView mRightRecycler;
    private ArrayList<String> mContractArrayList;
    private ArrayList<Items> mItemsArrayList;

    private LeftAdapter mLeftAdapter;
    private RightAdapter mRightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_recy);
        ButterKnife.bind(this);

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

        rightScrollView.setScrollViewListener(new SwapScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(SwapScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (x != 0) {
                    cardView.setContentPadding(0, 0, 5, 0);
                    cardView.setCardElevation(8);
                } else {
                    cardView.setContentPadding(0, 0, 0, 0);
                    cardView.setCardElevation(0);
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
            items.setNewPrices(111 + i + "");
            items.setUpDowms("+" + 11 + i);
            items.setBuy(10 + i + "");
            items.setBuyNums(12 + i + "");
            items.setBuyDates("14:" + i);
            items.setSell(21 + i + "");
            items.setSellNums(22 + i + "");
            items.setSellDates("23:" + i);
            items.setYTDPut(23222 - i + "");
            mItemsArrayList.add(items);
        }


    }


}












