package com.json.itemdecoration.middle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.json.itemdecoration.R;
import com.json.itemdecoration.untils.StrUntils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextActivity extends AppCompatActivity {

    @BindView(R.id.lixt_looper_banner)
    ListLoopBanner lixtLooperBanner;

    private ArrayList<MiddelsItem> mLeftMiddelsItems;



    public static void startTextActivity(Activity activity) {
        Intent intent = new Intent(activity, TextActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);

        mLeftMiddelsItems = new ArrayList<>();
        initDatas();

        lixtLooperBanner.initDatas(mLeftMiddelsItems);

    }


    private void initDatas() {
        for (int i = 0; i < 25; i++) {
            MiddelsItem middelsItem = new MiddelsItem();
            middelsItem.setSell(StrUntils.floatToString(20 + i, 2));
            middelsItem.setBuy(StrUntils.floatToString(10 + i, 2));
            middelsItem.setPresentBuy("55.21%");
            middelsItem.setPresentSell("22.01%");
            mLeftMiddelsItems.add(middelsItem);
        }
    }





}







