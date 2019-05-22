package com.json.itemdecoration.slidedelete;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.json.itemdecoration.R;
import com.json.itemdecoration.untils.StrUntils;
import com.json.itemdecoration.untils.adapter.BaseRecyclerViewAdapter;
import com.json.itemdecoration.untils.adapter.RecyclerViewHolder;

import java.util.List;

/**
 * 清单列表adapter
 * <p>
 * Created by DavidChen on 2018/5/30.
 */

public class InventoryAdapter extends BaseRecyclerViewAdapter<SlideModel.DataBean.BillRecordListBean> {

    private OneClickLister mClickListener;

     public InventoryAdapter(Context context, List<SlideModel.DataBean.BillRecordListBean > data) {
        super(context, data, R.layout.slide_item_recycler);
    }



    @Override
    protected void onBindData(RecyclerViewHolder holder, SlideModel.DataBean.BillRecordListBean bean, int position) {
        TextView deleteView = (TextView) holder.getView(R.id.tv_delete);
        deleteView.setTag(position);
        if (!deleteView.hasOnClickListeners()) {
            deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        mClickListener.onDeleteClick(v, (Integer) v.getTag());
                    }
                }
            });
        }


        TextView updateView = (TextView) holder.getView(R.id.tv_update);
        updateView.setTag(position);
        if (!updateView.hasOnClickListeners()) {
            updateView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        mClickListener.onUpdateClick(v, (Integer) v.getTag());
                    }
                }
            });
        }

        final RelativeLayout leftRl = ((RelativeLayout) holder.getView(R.id.layout_left));
        final TextView content = (TextView) holder.getView(R.id.content);
        final String datas = bean.getRemark();
        leftRl.setTag(position);
        if (!leftRl.hasOnClickListeners()) {
            leftRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null == datas || "".equals(datas.trim())) {
                        content.setVisibility(View.GONE);
                    } else {
                        content.setText("备注: " + datas);
                        if (content.getVisibility() == View.VISIBLE) {
                            content.setVisibility(View.GONE);
                        } else {
                            content.setVisibility(View.VISIBLE);
                        }
                    }
                    if (mClickListener != null) {
                        mClickListener.onItemClick(v, (Integer) v.getTag());
                    }
                }
            });
        }



        ((TextView) holder.getView(R.id.date)).setText(StrUntils.getDate2String(bean.getCreateTime()));
        ((TextView) holder.getView(R.id.inpute)).setText(bean.getCategoryName());
        ((TextView) holder.getView(R.id.money)).setText(bean.getAmount());



    }





    public void setOnItemClickListener(OneClickLister listener) {
        if (listener != null) {
            this.mClickListener = listener;
        }
    }


    public interface OneClickLister {
        void onItemClick(View view, int position);
        void onDeleteClick(View view, int position);
        void onUpdateClick(View view, int position);
    }

}
