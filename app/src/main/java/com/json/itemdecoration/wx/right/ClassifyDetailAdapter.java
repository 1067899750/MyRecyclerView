package com.json.itemdecoration.wx.right;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.json.itemdecoration.R;
import com.json.itemdecoration.relateview.adapter.RvAdapter;
import com.json.itemdecoration.relateview.adapter.RvHolder;
import com.json.itemdecoration.relateview.listener.RvListener;
import com.json.itemdecoration.wx.right.bean.RightBean;

import java.util.List;


public class ClassifyDetailAdapter extends RvAdapter<RightBean> {

    public ClassifyDetailAdapter(Context context, List<RightBean> list, RvListener listener) {
        super(context, list, listener);
    }


    @Override
    protected int getLayoutId(int viewType) {
        return viewType == 0 ? R.layout.item_title : R.layout.item_classify_detail;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).isTitle() ? 0 : 1;
    }

    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new ClassifyHolder(view, viewType, listener);
    }

    public class ClassifyHolder extends RvHolder<RightBean> {
        TextView tvCity;
        ImageView avatar;
        TextView tvTitle;

        public ClassifyHolder(View itemView, int type, RvListener listener) {
            super(itemView, type, listener);
            switch (type) {
                case 0:
                    tvTitle = itemView.findViewById(R.id.tv_title);
                    break;
                case 1:
                    tvCity = itemView.findViewById(R.id.tvCity);
                    avatar = itemView.findViewById(R.id.ivAvatar);
                    break;
            }

        }

        @Override
        public void bindHolder(RightBean sortBean, int position) {
            int itemViewType = ClassifyDetailAdapter.this.getItemViewType(position);
            switch (itemViewType) {
                case 0:
                    tvTitle.setText(sortBean.getName());
                    break;
                case 1:
                    tvCity.setText(sortBean.getName());
                    break;
            }

        }
    }
}
