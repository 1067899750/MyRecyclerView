package com.json.itemdecoration.qq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.json.itemdecoration.MainActivity;
import com.json.itemdecoration.R;
import com.json.itemdecoration.qq.until.Item;

import java.util.ArrayList;
/**
 * 
 * Description
 * Author puyantao
 * Email 1067899750@qq.com
 * Date 2019/5/19 14:15
 */
public class QQDeleteActivity extends AppCompatActivity implements RecyclerViewAdapter.onSlidingViewClickListener  {

    private RecyclerView recycler;              //在xml 中 RecyclerView 布局
    private RecyclerViewAdapter rvAdapter;      //item_recycler 布局的 适配器

    //设置数据
    private ArrayList<Item> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqdelete);
        //初始化RecyclerView
        init();
        //将 RecyclerView 布局设置为线性布局
        recycler.setLayoutManager(new LinearLayoutManager(this));
        intDatas();//插入数据
        //更新界面
        updateInterface();
    }

    public void init() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
    }




    public void updateInterface() {
        if (rvAdapter == null) {
            //实例化 RecyclerViewAdapter 并设置数据
            rvAdapter = new RecyclerViewAdapter(this, mItems);
            //将适配的内容放入 mRecyclerView
            recycler.setAdapter(rvAdapter);
            //控制Item增删的动画，需要通过ItemAnimator  DefaultItemAnimator -- 实现自定义动画
            recycler.setItemAnimator(new DefaultItemAnimator());
        } else {
            //强调通过 getView() 刷新每个Item的内容
            rvAdapter.notifyDataSetChanged();
        }
        //设置滑动监听器 （侧滑）
        rvAdapter.setOnSlidListener(this);
    }





    //通过 position 区分点击了哪个 Item
    @Override
    public void onItemClick(View view, int position) {
        //在这里可以做出一些反应（跳转界面、弹出弹框之类）
        Toast.makeText(QQDeleteActivity.this, "点击了：" + position, Toast.LENGTH_SHORT).show();
    }

    //点击删除按钮时，根据传入的 position 调用 RecyclerAdapter 中的 removeData() 方法
    @Override
    public void onDeleteBtnCilck(View view, int position) {
        rvAdapter.removeData(position);
    }

    @Override
    public void onUpdateBtnCilck(View view, int position) {

    }


    private void intDatas() {
        mItems = new ArrayList<>();
        for (int i =0; i < 10; i++){
            Item item = new Item();
            item.setDate("10-1-" +1);
            item.setInout("收入" + i);
            item.setMoney("100" + i);
            item.setDetail("按时");
            mItems.add(item);
        }
    }
}
