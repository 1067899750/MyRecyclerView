package com.json.itemdecoration.water;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.json.itemdecoration.R;

import static android.view.View.MeasureSpec.AT_MOST;

public class MyDrawView extends View {
    private final float DEF_WIDTH = 650;
    private final float DEF_HIGHT = 400;

    private int mBaseWidth;
    private int mBaseHeight;
    private Context mContext;
    private int mDegress = -30;//旋转角度角度

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mBackgroundColor;

    Bitmap mBitmapLogo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_alphametal_watermark);

    public MyDrawView(Context context) {
        super(context);
        initView(context);
    }

    public MyDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context){
        this.mContext = context;

        mBackgroundColor = Color.parseColor("#2A2D4F");
        mBackgroundPaint.setColor(mBackgroundColor);

        mPaint.setDither(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == AT_MOST && heightSpecMode == AT_MOST) {
            setMeasuredDimension((int) DEF_WIDTH, (int) DEF_HIGHT);

        } else if (widthSpecMode == AT_MOST) {
            setMeasuredDimension((int) DEF_WIDTH, heightSpecSize);

        } else if (heightSpecMode == AT_MOST) {
            setMeasuredDimension(widthSpecSize, (int) DEF_HIGHT);

        } else {
            setMeasuredDimension(widthSpecSize, heightSpecSize);
        }
        mBaseWidth  = getMeasuredWidth();
        mBaseHeight = getMeasuredHeight();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBaseWidth = w;
        mBaseHeight = h;

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //绘制背景颜色
        canvas.drawColor(mBackgroundColor);

        //logo宽高
        int width = mBitmapLogo.getWidth();
        int height = mBitmapLogo.getHeight();


        mBaseWidth += Math.abs(mBaseHeight * Math.tan(mDegress));
        mBaseHeight += Math.abs(mBaseWidth * Math.tan(mDegress));


        canvas.save();
        canvas.rotate(mDegress);
        int index = 0;
        for (int positionY = height / 10; positionY <= mBaseHeight; positionY += height * 2) {
            float fromX = -mBaseWidth + (index++ % 2) * width;
            for (float positionX = fromX; positionX <= mBaseWidth; positionX += width*1.5) {
                if (mBitmapLogo != null) {
                    canvas.drawBitmap(mBitmapLogo, positionX, positionY, null);
                }

            }
        }
        canvas.restore();


    }


    public void drawMainViewLogo(Canvas canvas) {
        if (mBitmapLogo != null) {
            int mLeft = getWidth() / 2 - mBitmapLogo.getWidth() / 2;
            int mTop = mBaseHeight / 2 - mBitmapLogo.getHeight() / 2;
            canvas.drawBitmap(mBitmapLogo, mLeft, mTop, null);
        }
    }




}































