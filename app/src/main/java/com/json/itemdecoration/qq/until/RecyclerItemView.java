package com.json.itemdecoration.qq.until;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.json.itemdecoration.R;

/**
 *
 * Description 具有滑动效果的 Item    需要继承水平滚动视图
 * Author puyantao
 * Email 1067899750@qq.com
 * Date 2019/5/19 14:07
 */

public class RecyclerItemView extends HorizontalScrollView {

    private LinearLayout slide;//滑动弹出的按钮容器
    private LinearLayout leftLayout;
    private TextView delete, update;

    private int slideWidth; // 滑动弹出这个控件的宽度
    private int leftViewHeight;

    private onSlidingButtonListener onSbl;//滑动按钮侦听器

    private Boolean isOpen = false;//判断是否有删除按钮被打开

    public RecyclerItemView(Context context) {
        this(context, null);
    }

    public RecyclerItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOverScrollMode(OVER_SCROLL_NEVER);
    }

    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        slide = (LinearLayout) findViewById(R.id.slide);
        leftLayout = findViewById(R.id.layout_left);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
    }

    //通过布局获取按钮宽度
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, 0);
            //获取水平滚动条可以滑动的范围，即右侧按钮的宽度

            leftViewHeight = leftLayout.getHeight();
            slideWidth = slide.getWidth();

            delete.setHeight(leftViewHeight);
            update.setHeight(leftViewHeight);

        }
    }

    //触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                onSbl.onDownOrMove(this);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                changeScrollx();
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    //滚动改变（拖动多少显示多少，根据手势变化）
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        slide.setTranslationX(l - slideWidth);
    }

    // 按滚动条被拖动距离判断关闭或打开菜单 (被拖动的距离有没有隐藏或显示控件的一半以上？)
    public void changeScrollx() {
        //判断拖动的距离有没有超过删除按钮的一半
        if (getScrollX() >= (slideWidth / 2)) {
            //推动了一半以上就打开
            this.smoothScrollTo(slideWidth, 0);
            isOpen = true;
            onSbl.onMenuIsOpen(this);
        } else {
            //没有一半以上就关上
            this.smoothScrollTo(0, 0);
            isOpen = false;
        }
    }

    // 关闭菜单
    public void closeMenu() {
        if (!isOpen) {
            return;
        }
        this.smoothScrollTo(0, 0);
        isOpen = false;
    }

    //设置滑动按钮监听器
    public void setSlidingButtonListener(onSlidingButtonListener listener) {
        onSbl = listener;
    }

    //滑动按钮侦听器
    public interface onSlidingButtonListener {
        void onMenuIsOpen(View view);

        void onDownOrMove(RecyclerItemView recycler);
    }
}
