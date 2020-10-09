package com.json.itemdecoration.wx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.json.itemdecoration.R;
import com.json.itemdecoration.looper.LooperActivity;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author puyantao
 * @description 模仿微信索引
 * @date 2020/10/9 11:37
 */
public class WXActivity extends AppCompatActivity {
    private SideBar mSideBar;
    private ListView mListView;
    private ArrayList<User> list;
    private  SortAdapter mSortAdapter;

    public static void startWxActivity(Activity activity) {
        Intent intent = new Intent(activity, WXActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_x);
        mSideBar = findViewById(R.id.side_bar);
        mListView = findViewById(R.id.list_View);
        list = new ArrayList<>();
        mSortAdapter = new SortAdapter(this, list);
        mListView.setAdapter(mSortAdapter);

        initData();
        initEvent();

    }


    private void initData() {
        list.add(new User("亳州")); // 亳[bó]属于不常见的二级汉字
        list.add(new User("大娃"));
        list.add(new User("二娃"));
        list.add(new User("三娃"));
        list.add(new User("四娃"));
        list.add(new User("五娃"));
        list.add(new User("六娃"));
        list.add(new User("七娃"));
        list.add(new User("喜羊羊"));
        list.add(new User("美羊羊"));
        list.add(new User("懒羊羊"));
        list.add(new User("沸羊羊"));
        list.add(new User("暖羊羊"));
        list.add(new User("慢羊羊"));
        list.add(new User("灰太狼"));
        list.add(new User("红太狼"));
        list.add(new User("孙悟空"));
        list.add(new User("黑猫警长"));
        list.add(new User("舒克"));
        list.add(new User("贝塔"));
        list.add(new User("海尔"));
        list.add(new User("阿凡提"));
        list.add(new User("邋遢大王"));
        list.add(new User("哪吒"));
        list.add(new User("没头脑"));
        list.add(new User("不高兴"));
        list.add(new User("蓝皮鼠"));
        list.add(new User("大脸猫"));
        list.add(new User("大头儿子"));
        list.add(new User("小头爸爸"));
        list.add(new User("蓝猫"));
        list.add(new User("淘气"));
        list.add(new User("叶峰"));
        list.add(new User("楚天歌"));
        list.add(new User("江流儿"));
        list.add(new User("Tom"));
        list.add(new User("Jerry"));
        list.add(new User("12345"));
        list.add(new User("54321"));
        list.add(new User("_(:з」∠)_"));
        list.add(new User("……%￥#￥%#"));
        // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
        Collections.sort(list);
        mSortAdapter.notifyDataSetChanged();
    }

    private void initEvent() {
        mSideBar.setOnStrSelectCallBack(new ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < list.size(); i++) {
                    if (selectStr.equalsIgnoreCase(list.get(i).getFirstLetter())) {
                        // 选择到首字母出现的位置
                        mListView.setSelection(i);
                        return;
                    }
                }
            }
        });


    }


}

















