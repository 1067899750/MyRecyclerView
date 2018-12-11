package com.json.itemdecoration.tworecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.json.itemdecoration.R;

import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyViewHolder> {
    private List<Items> mDatas;

    public RightAdapter(List<Items> data) {
        this.mDatas = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.drawable.selector_item_btn3);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.selector_item_btn4);
        }

        holder.tvNewPriceValue.setText(mDatas.get(position).getNewPrices());
        holder.tvUpDownValue.setText(mDatas.get(position).getUpDowms());
        holder.tvBuyValue.setText(mDatas.get(position).getBuy());
        holder.tvBuyNumsValue.setText(mDatas.get(position).getBuyNums());
        holder.tvBuyDateValue.setText(mDatas.get(position).getBuyDates());
        holder.tvSellValue.setText(mDatas.get(position).getSell());
        holder.tvSellNumsValue.setText(mDatas.get(position).getSellNums());
        holder.tvSellDateValue.setText(mDatas.get(position).getSellDates());
        holder.tvYTDPutValue.setText(mDatas.get(position).getYTDPut());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNewPriceValue;
        private TextView tvUpDownValue;
        private TextView tvBuyValue;
        private TextView tvBuyNumsValue;
        private TextView tvBuyDateValue;
        private TextView tvSellValue;
        private TextView tvSellNumsValue;
        private TextView tvSellDateValue;
        private TextView tvYTDPutValue;

        public MyViewHolder(View v) {
            super(v);
            tvNewPriceValue = v.findViewById(R.id.tvNewPriceValue);
            tvUpDownValue = v.findViewById(R.id.tvUpDownValue);
            tvBuyValue = v.findViewById(R.id.tvBuyValue);
            tvBuyNumsValue = v.findViewById(R.id.tvBuyNumsValue);
            tvBuyDateValue = v.findViewById(R.id.tvBuyDateValue);
            tvSellValue = v.findViewById(R.id.tvSellValue);
            tvSellNumsValue = v.findViewById(R.id.tvSellNumsValue);
            tvSellDateValue = v.findViewById(R.id.tvSellDateValue);
            tvYTDPutValue = v.findViewById(R.id.tvYTDPutValue);


        }
    }
}
