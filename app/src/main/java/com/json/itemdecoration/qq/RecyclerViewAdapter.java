package com.json.itemdecoration.qq;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.json.itemdecoration.R;
import com.json.itemdecoration.qq.until.Item;
import com.json.itemdecoration.qq.until.RecyclerItemView;
import com.json.itemdecoration.qq.until.RecyclerUtils;

import java.util.ArrayList;

/**
 * item_recycler.xml 的适配器
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SimpleHolder>
        implements RecyclerItemView.onSlidingButtonListener{
    private Context context;

    private ArrayList<Item> dataImage;    //头像（谁的头像）

    private onSlidingViewClickListener onSvcl;
    private RecyclerItemView recyclers;
    private ArrayList<SimpleHolder> mSimpleHolders = new ArrayList<>();

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public RecyclerViewAdapter(Context context, ArrayList<Item> dataImage) {
        this.context = context;
        this.dataImage = dataImage;;
    }

    @Override
    public SimpleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        return new SimpleHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleHolder holder, final int position) {
        mSimpleHolders.add(holder);
        holder.title.setText(dataImage.get(position).getMoney());
        holder.time.setText(dataImage.get(position).getDate());
        holder.content.setText(dataImage.get(position).getDetail());


        holder.layout_left.getLayoutParams().width = RecyclerUtils.getScreenWidth(context);

        holder.layout_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"做出操作，进入新的界面或弹框", Toast.LENGTH_SHORT).show();
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    //获得布局下标（点的哪一个）
                    int subscript = holder.getLayoutPosition();
                    onSvcl.onItemClick(view, subscript);
                }
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"其他："+position, Toast.LENGTH_SHORT).show();
                int subscript = holder.getLayoutPosition();
                onSvcl.onUpdateBtnCilck(view,subscript);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"删除了："+position, Toast.LENGTH_SHORT).show();
                int subscript = holder.getLayoutPosition();
                onSvcl.onDeleteBtnCilck(view,subscript);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataImage.size();
    }

    @Override
    public void onMenuIsOpen(View view) {
        recyclers = (RecyclerItemView) view;
    }

    @Override
    public void onDownOrMove(RecyclerItemView recycler) {
        if(menuIsOpen()){
            if(recyclers != recycler){
                closeMenu();
            }
        }
    }

    class SimpleHolder extends  RecyclerView.ViewHolder {

        public TextView title;
        public TextView content;
        public TextView time;
        public TextView update;
        public TextView delete;
        public LinearLayout layout_left;
        public SimpleHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            content = (TextView) view.findViewById(R.id.content);
            time = (TextView) view.findViewById(R.id.time);
            update = (TextView) view.findViewById(R.id.update);
            delete = (TextView) view.findViewById(R.id.delete);
            layout_left = (LinearLayout) view.findViewById(R.id.layout_left);

            ((RecyclerItemView)view).setSlidingButtonListener(RecyclerViewAdapter.this);
        }
    }

    //删除数据
    public void removeData(int position){
        dataImage.remove(position);
//        notifyDataSetChanged();
        notifyItemRemoved(position);

    }

    //关闭菜单
    public void closeMenu() {
        recyclers.closeMenu();
        recyclers = null;

    }

    // 判断是否有菜单打开
    public Boolean menuIsOpen() {
        if(recyclers != null){
            return true;
        }
        return false;
    }

    //设置在滑动侦听器上
    public void setOnSlidListener(onSlidingViewClickListener listener) {
        if (listener != null) {
            onSvcl = listener;
        }
    }

    // 在滑动视图上单击侦听器
    public interface onSlidingViewClickListener {
        void onItemClick(View view, int position);
        void onDeleteBtnCilck(View view, int position);
        void onUpdateBtnCilck(View view, int position);
    }

}
