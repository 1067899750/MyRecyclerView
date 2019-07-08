package com.json.itemdecoration.middle;

import android.content.Context;
import android.widget.TextView;

import com.json.itemdecoration.R;
import com.json.itemdecoration.untils.adapter.BaseRecyclerViewAdapter;
import com.json.itemdecoration.untils.adapter.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe
 * @Author puyantao
 * @Email 1067899750@qq.com
 * @create 2019/7/3 10:30
 */
public class CenterMiddleAdapter  extends BaseRecyclerViewAdapter<String> {


    public CenterMiddleAdapter(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    protected void onBindData(RecyclerViewHolder holder, String bean, int position) {
        ((TextView)holder.getView(R.id.center_tv)).setText(bean);
    }
}









