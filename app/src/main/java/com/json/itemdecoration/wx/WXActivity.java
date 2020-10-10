package com.json.itemdecoration.wx;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.json.itemdecoration.R;
import com.json.itemdecoration.relateview.listener.CheckListener;
import com.json.itemdecoration.wx.bean.User;
import com.json.itemdecoration.wx.right.SortDetailFragment;
import com.json.itemdecoration.wx.right.bean.CategoryOneArrayBean;
import com.json.itemdecoration.wx.right.untils.ItemHeaderDecoration;
import com.json.itemdecoration.wx.utils.ISideBarSelectCallBack;
import com.json.itemdecoration.wx.utils.SideBar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author puyantao
 * @description 模仿微信索引
 * @date 2020/10/9 11:37
 */
public class WXActivity extends AppCompatActivity implements CheckListener {
    private SideBar mSideBar;
    private ArrayList<User> list;
    private SortDetailFragment mSortDetailFragment;
    private ArrayList<CategoryOneArrayBean> mCategoryOneArrayBeans = new ArrayList<>();
    private ArrayList<CategoryOneArrayBean.CategoryTwoArrayBean> twoArrayBeans;

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

        initData();
        initEvent();

    }

    private void initData() {
        list = new ArrayList<>();
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

        String sign = "0";
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                sign = list.get(i).getFirstLetter();
                twoArrayBeans = new ArrayList<>();
            }
            CategoryOneArrayBean oneArrayBean = new CategoryOneArrayBean();

            if (sign.equals(list.get(i).getFirstLetter())) {
                CategoryOneArrayBean.CategoryTwoArrayBean twoArrayBean = new CategoryOneArrayBean.CategoryTwoArrayBean();
                twoArrayBean.setName(list.get(i).getName());
                twoArrayBeans.add(twoArrayBean);

            } else {
                sign = list.get(i).getFirstLetter();
                oneArrayBean.setName(list.get(i - 1).getFirstLetter());
                oneArrayBean.setCategoryTwoArray(twoArrayBeans);
                mCategoryOneArrayBeans.add(oneArrayBean);
                twoArrayBeans = new ArrayList<>();
                i--;
            }
            if (i == list.size() - 1) {
                oneArrayBean.setName(list.get(i).getFirstLetter());
                oneArrayBean.setCategoryTwoArray(twoArrayBeans);
                mCategoryOneArrayBeans.add(oneArrayBean);
            }
        }

        createFragment();
    }

    public void createFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mSortDetailFragment = new SortDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("right", mCategoryOneArrayBeans);
        mSortDetailFragment.setArguments(bundle);
        mSortDetailFragment.setListener(this);
        fragmentTransaction.add(R.id.lin_fragment, mSortDetailFragment);
        fragmentTransaction.commit();
    }


    private void initEvent() {
        mSideBar.setOnStrSelectCallBack(new ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int position = 0; position < mCategoryOneArrayBeans.size(); position++) {
                    if (selectStr.equalsIgnoreCase(mCategoryOneArrayBeans.get(position).getName())) {
                        // 选择到首字母出现的位置
                        int count = 0;
                        for (int i = 0; i < position; i++) {
                            count += mCategoryOneArrayBeans.get(i).getCategoryTwoArray().size();
                        }
                        count += position;
                        mSortDetailFragment.setData(count);
                        ItemHeaderDecoration.setCurrentTag(String.valueOf(position));
                        return;
                    }
                }
            }
        });


    }


    @Override
    public void check(int position, boolean isScroll) {

    }
}

















