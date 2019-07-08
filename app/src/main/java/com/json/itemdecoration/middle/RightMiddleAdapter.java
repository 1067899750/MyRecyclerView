package com.json.itemdecoration.middle;

import android.content.Context;
import android.widget.TextView;

import com.json.itemdecoration.R;
import com.json.itemdecoration.untils.adapter.BaseRecyclerViewAdapter;
import com.json.itemdecoration.untils.adapter.RecyclerViewHolder;

import java.util.List;

/**
 * @Describe
 * @Author puyantao
 * @Email 1067899750@qq.com
 * @create 2019/7/3 10:30
 */
public class RightMiddleAdapter extends BaseRecyclerViewAdapter<MiddelsItem> {

    public RightMiddleAdapter(Context context, List<MiddelsItem> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    protected void onBindData(RecyclerViewHolder holder, MiddelsItem bean, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.drawable.selector_item_btn3);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.selector_item_btn4);
        }
        ((TextView)holder.getView(R.id.tv_right_sell_percent)).setText(bean.getPresentSell());
        ((TextView)holder.getView(R.id.tv_right_buy_percent)).setText(bean.getPresentBuy());
        ((TextView)holder.getView(R.id.tv_right_sell)).setText(bean.getSell());
        ((TextView)holder.getView(R.id.tv_right_buy)).setText(bean.getBuy());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
