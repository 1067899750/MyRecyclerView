package com.json.itemdecoration.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author puyantao
 * @describe
 * @create 2019/12/13 9:56
 */
public class DragImageView extends View {

    private Paint mPaint;
    private Drawable mDrawable;
    private Rect mDrawableRect = new Rect();
    // private Rect mDrawableOffsetRect = new Rect();
    private Context mContext;
    private float mRation_WH = 0;
    private float mOldX = 0;
    private float mOldY = 0;
    private float mOldX0, mOldY0, mOldX1, mOldY1, mOldK, mOldB, mOldHandsX,
            mOldHandsY;
    private double mD1;
    private boolean isFirst = true;
    /**
     * 单点按下
     */
    private int SINGALDOWN = 1;
    /**
     * 双点按下
     */
    private int MUTILDOWM = 2;
    /**
     * 双点拖拽
     */
    private int MUTILMOVE = 3;
    private int mStatus = 0;

    enum STATUS {
        SINGAL, MUTILDOWN, MUTILMOVE;
    }

    public DragImageView(Context context) {
        super(context);
        initView(context);
    }


    public DragImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DragImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(35.0f);
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if (mDrawable == null || mDrawable.getIntrinsicHeight() == 0
                || mDrawable.getIntrinsicWidth() == 0) {
            return;
        }
        setBounds();
        mDrawable.draw(canvas);
        // Log.i("draw", "draw+++++++++++++++++++++++++++++++++++++++");

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getPointerCount()) {
            case 1:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mStatus = SINGALDOWN;
                        mOldX = event.getX();
                        mOldY = event.getY();
                        // Log.i("x_y_down", event.getX() + "__" + event.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        checkBounds();
                        // Log.i("x_y_up", event.getX() + "__" + event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Log.i("x_y_move", event.getX() + "__" + event.getY());
                        if (mStatus == SINGALDOWN) {
                            int offsetWidth = (int) (event.getX() - mOldX);
                            int offsetHeight = (int) (event.getY() - mOldY);
                            // Log.i("x_y_offset", offsetWidth + "__" + offsetHeight);
                            mOldX = event.getX();
                            mOldY = event.getY();
                            mDrawableRect.offset(offsetWidth, offsetHeight);
                            invalidate();
                        }

                        break;
                    default:
                        break;
                }
                break;
            default:
                /*
                 * mStatus = MUTILDOWM; if (mStatus == MUTILDOWM) { mOldX0 =
                 * event.getX(0); mOldY0 = event.getY(0); mOldX1 = event.getX(1);
                 * mOldY1 = event.getY(1); mOldK = (mOldY1 - mOldY0) / (mOldX1 -
                 * mOldX0); mOldB = (mOldY0 * mOldX1 - mOldY1 * mOldX0) / (mOldX1 -
                 * mOldX0); mOldHandsX = (mOldX0 + mOldX1) / 2; mOldHandsY =
                 * mOldHandsX * mOldK + mOldB; mD1 = Math.sqrt(Math.pow(mOldX0 -
                 * mOldX1, 2) + Math.pow(mOldY0 - mOldY1, 2)); Log.i("mD1", mD1 +
                 * "________________"); }
                 */
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                        Log.i("DOUBLETOWDOWN", "true");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Log.i("x_y_move", event.getX(0) + "__" + event.getY(0) +
                        // "___"
                        // + event.getX(1) + "__" + event.getY(1));
                        mStatus = MUTILMOVE;
                        float X0 = event.getX(0);
                        float Y0 = event.getY(0);
                        float X1 = event.getX(1);
                        float Y1 = event.getY(1);
                        float k = (Y1 - Y0) / (X1 - X0);
                        float b = (Y0 * X1 - Y1 * X0) / (X1 - X0);
                        int RectCenterX = mDrawableRect.centerX();
                        int RectCenterY = mDrawableRect.centerY();
                        float mHandsX = (X0 + X1) / 2;
                        float mHandsY = mHandsX * k + b;
                        double mD2 = Math.sqrt(Math.pow(X0 - X1, 2)
                                + Math.pow(Y0 - Y1, 2));

                        Log.i("GCM", mD2 + "________________X:" + mHandsX + "___Y:"
                                + mHandsY);
                        if (mD1 < mD2) {

                            // double mMultiple = mD2 / mD1;
                            // int newWidth = (int) (mDrawableRect.width() * mMultiple);
                            // int newHeight = (int) (newWidth / mRation_WH);
                            //
                            // int newleft = mDrawableRect.left / 2;
                            // int newtop = mDrawableRect.top / 2;
                            // int newright = mDrawableRect.right * (3 / 2);
                            // int newbotto = mDrawableRect.bottom * (3 / 2);
                            // // mDrawableRect.set(newleft, newtop, newright,
                            // newbotto);
                            //
                            // mDrawableRect.set(RectCenterX - newWidth / 2, RectCenterY
                            // - newHeight / 2, RectCenterX + newWidth / 2,
                            // RectCenterY + newHeight / 2);
                            // invalidate();
                            if (mDrawableRect.width() < mContext.getResources()
                                    .getDisplayMetrics().widthPixels * 2) {
                                int offsetwidth = 10;
                                int offsettop = (int) (offsetwidth / mRation_WH);
                                mDrawableRect.set(mDrawableRect.left - offsetwidth,
                                        mDrawableRect.top - offsettop,
                                        mDrawableRect.right + offsetwidth,
                                        mDrawableRect.bottom + offsettop);
                                Log.i("GCM", "aaaaaaaaaaaaaaa");

                                invalidate();
                            }
                            // mDrawableRect.offset((int) mHandsX, (int) mHandsY);

                        } else {
                            if (mDrawableRect.width() > mContext.getResources()
                                    .getDisplayMetrics().widthPixels / 3) {
                                int offsetwidth = 10;
                                int offsettop = (int) (offsetwidth / mRation_WH);
                                mDrawableRect.set(mDrawableRect.left + offsetwidth,
                                        mDrawableRect.top + offsettop,
                                        mDrawableRect.right - offsetwidth,
                                        mDrawableRect.bottom - offsettop);
                                invalidate();
                                Log.i("GCM", "bbbbbbbbbbbbbbb");
                            }
                        }
                        mD1 = mD2;
                        if (mHandsX < RectCenterX) {
                            if (mHandsY < RectCenterY) {
                                Log.i("PPPPPPP", "1");

                            } else {
                                Log.i("PPPPPPP", "3");
                            }
                        } else {
                            if (mHandsY < RectCenterY) {
                                Log.i("PPPPPPP", "2");
                            } else {
                                Log.i("PPPPPPP", "4");
                            }
                        }

                        //

                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("mStatus", "mutildouble_up");
                        mStatus = 0;
                        break;
                    default:
                        break;
                }
                break;
        }

        return true;
    }

    public void setBounds() {
        if (isFirst) {
            mRation_WH = (float) mDrawable.getIntrinsicWidth()
                    / (float) mDrawable.getIntrinsicHeight();
            int px_w = Math.min(getWidth(), dip2px(mContext, mDrawable.getIntrinsicWidth()));
            int px_h = (int) (px_w / mRation_WH);
            int left = (getWidth() - px_w) / 2;
            int top = (getHeight() - px_h) / 2;
            int right = px_w + left;
            int bottom = px_h + top;
            mDrawableRect.set(left, top, right, bottom);
            // mDrawableOffsetRect.set(mDrawableRect);
            isFirst = false;
            Log.i("rect1______", mDrawableRect.left + "," + mDrawableRect.top
                    + "," + mDrawableRect.right + "," + mDrawableRect.bottom);
        }
        mDrawable.setBounds(mDrawableRect);
        Log.i("rect2______", mDrawableRect.left + "," + mDrawableRect.top + ","
                + mDrawableRect.right + "," + mDrawableRect.bottom);
        Log.i("center_______",
                mDrawableRect.centerX() + "," + mDrawableRect.centerY());

    }

    public void checkBounds() {
        int newLeft = mDrawableRect.left;
        int newTop = mDrawableRect.top;
        boolean isChange = false;
        if (newLeft < -mDrawableRect.width()) {
            newLeft = -mDrawableRect.width();
            isChange = true;
        }
        if (newTop < -mDrawableRect.height()) {
            newTop = -mDrawableRect.height();
            isChange = true;
        }
        if (newLeft > getWidth()) {
            newLeft = getWidth();
            isChange = true;
        }
        if (newTop > getHeight()) {
            newTop = getHeight();
            isChange = true;
        }
        if (isChange) {
            mDrawableRect.offsetTo(newLeft, newTop);
            invalidate();
        }
    }

    public Drawable getmDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable mDrawable) {
        this.mDrawable = mDrawable;
    }

    public int dip2px(Context context, int value) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

}
