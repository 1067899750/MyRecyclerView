package com.json.itemdecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListViewActivity extends AppCompatActivity {

    private TitleItemDecoration titleItemDecoration;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private List<CategoryBean> lists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置测试数据
        setDatas();

        adapter = new CategoryAdapter(this, lists);
        recyclerView.setAdapter(adapter);
        titleItemDecoration = new TitleItemDecoration(this, lists);
        recyclerView.addItemDecoration(titleItemDecoration);

    }

    private void setDatas() {
        lists = new ArrayList<>();
        lists.add(new CategoryBean("A", "哎"));
        lists.add(new CategoryBean("A", "爱"));
        lists.add(new CategoryBean("A", "昂"));
        lists.add(new CategoryBean("B", "beautiful"));
        lists.add(new CategoryBean("B", "beak"));
        lists.add(new CategoryBean("B", "but"));
        lists.add(new CategoryBean("B", "bring"));
        lists.add(new CategoryBean("C", "category"));
        lists.add(new CategoryBean("C", "can"));
        lists.add(new CategoryBean("C", "captive"));
        lists.add(new CategoryBean("C", "computer"));
        lists.add(new CategoryBean("D", "default"));


    }

}
