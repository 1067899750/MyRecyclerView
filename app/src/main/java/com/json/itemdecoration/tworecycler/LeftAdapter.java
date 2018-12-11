package com.json.itemdecoration.tworecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.json.itemdecoration.R;

import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {
    private List<String> mDatas;
    public LeftAdapter(List<String> data) {
        this.mDatas = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.drawable.selector_item_btn3);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.selector_item_btn4);
        }

        holder.tvContractName.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public final TextView tvContractName;
        public MyViewHolder(View v) {
            super(v);
            tvContractName = v.findViewById(R.id.tvContractName);
        }
    }


}
