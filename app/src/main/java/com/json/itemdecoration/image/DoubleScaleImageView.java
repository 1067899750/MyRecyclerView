package com.json.itemdecoration.image;

/**
 * @author puyantao
 * @describe 对图片进行放大缩小
 * @create 2019/12/12 10:17
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;


public class DoubleScaleImageView extends AppCompatImageView implements View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    private boolean isFirst = false;
    /**
     * 双击放大的值
     */
    private float doubleScale;
    private Matrix mScaleMatrix;
    /**
     * 默认的缩放值
     */
    private float defaultScale;
    /**
     * 记录上一次多点触控的数量
     */
    private int mLastPinterCount;
    private float mLastX;
    private float mLastY;
    private int mTouchSlop;
    private boolean isCanDrag;
    private boolean isCheckLeft;
    private boolean isCheckTop;
    private GestureDetector mGestureDetector;

    public DoubleScaleImageView(Context context) {
        this(context, null);
    }

    public DoubleScaleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("ClickableViewAccessibility")
    public DoubleScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScaleMatrix = new Matrix();
        setScaleType(ScaleType.MATRIX);
        setOnTouchListener(this);
        // getScaledTouchSlop是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件。如果小于这个距离就不触发移动控件
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                float x = e.getX();
                float y = e.getY();
                if (getScale() < doubleScale) {
                    // 放大
                    mScaleMatrix.postScale(doubleScale / getScale(), doubleScale / getScale(), x, y);
                } else {
                    // 缩小
                    mScaleMatrix.postScale(defaultScale / getScale(), defaultScale / getScale(), x, y);
                }
                setImageMatrix(mScaleMatrix);
                return super.onDoubleTap(e);
            }
        });
    }

    /**
     * view附加到窗体上时调用该方法
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    /**
     * 将视图从窗体上分离的时候调用该方法
     */
    @SuppressWarnings("deprecation")
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    /**
     * 在这个方法中获取ImageView加载完成后的图片
     */
    @Override
    public void onGlobalLayout() {
        if (!isFirst) {
            // 获取控件的宽度和高度
            int width = getWidth();
            int height = getHeight();
            // 得到我们的图片以及图片的宽度及高度
            Drawable drawable = getDrawable();
            if (drawable == null) {
                return;
            }
            // 图片的宽度
            int imageWidth = drawable.getIntrinsicWidth();
            // 图片的高度
            int imageHeight = drawable.getIntrinsicHeight();
            float scale = 1.0f;
            // 如果图片宽度大于控件宽度，但是图片高度小于控件 高度，我们要缩小图片
            if (imageWidth > width && imageHeight < height) {
                scale = width * 1.0f / imageWidth;
            }
            // 如果图片宽度小于控件宽度，但是图片高度大于控件 高度，我们要缩小图片
            if (imageWidth < width && imageHeight > height) {
                scale = height * 1.0f / imageHeight;
            }
            // 如果图片的宽度都 大于或小于控件宽度，我们则要对图片进行对应缩放，保证图片占满控件
            if ((imageWidth > width && imageHeight > height) || (imageWidth < width && imageHeight < height)) {
                scale = Math.min(width * 1.0f / imageWidth, height * 1.0f / imageHeight);
            }
            // 初始化对应的缩放值
            defaultScale = scale;
            doubleScale = defaultScale * 2;
            // 图片缩放后，将图片要移动到控件中心
            int dx = width / 2 - imageWidth / 2;
            int dy = height / 2 - imageHeight / 2;
            mScaleMatrix.postTranslate(dx, dy);
            mScaleMatrix.postScale(defaultScale, defaultScale, width / 2, height / 2);
            setImageMatrix(mScaleMatrix);
            isFirst = true;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        }
        float x = 0;
        float y = 0;
        // 获取放在屏幕上的手指数量
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            x += event.getX(i);
            y += event.getY(i);
        }
        x /= pointerCount;
        y /= pointerCount;
        if (mLastPinterCount != pointerCount) {
            isCanDrag = false;
            mLastX = x;
            mLastY = y;
        }
        mLastPinterCount = pointerCount;
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mLastX;
                float dy = y - mLastY;
                isCanDrag = isMove(dx, dy);
                if (isCanDrag) {
                    RectF rectf = getMatrixRectF();
                    if (null != getDrawable()) {
                        isCheckLeft = isCheckTop = true;
                        // 如果图片宽度小于控件宽度（屏幕宽度）不允许横向移动
                        if (rectf.width() < getWidth()) {
                            dx = 0;
                            isCheckLeft = false;
                        }
                        // 如果图片高度小于控件高度（屏幕高度）不允许纵向移动
                        if (rectf.height() < getHeight()) {
                            dy = 0;
                            isCheckTop = false;
                        }
                        mScaleMatrix.postTranslate(dx, dy);
                        checkTranslateWithBorder();
                        setImageMatrix(mScaleMatrix);
                    }
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mLastPinterCount = 0;
                break;
        }
        return true;
    }

    /**
     * 移动图片时进行边界检查
     */
    private void checkTranslateWithBorder() {
        RectF rectf = getMatrixRectF();
        float delX = 0;
        float delY = 0;
        int width = getWidth();
        int height = getHeight();
        if (rectf.top > 0 && isCheckTop) {
            delY = -rectf.top;
        }
        if (rectf.bottom < height && isCheckTop) {
            delY = height - rectf.bottom;
        }
        if (rectf.left > 0 && isCheckLeft) {
            delX = -rectf.left;
        }
        if (rectf.right < width && isCheckLeft) {
            delX = width - rectf.right;
        }
        mScaleMatrix.postTranslate(delX, delY);
    }

    /**
     * 判断是否有移动
     *
     * @param x
     * @param y
     * @return
     */
    private boolean isMove(float x, float y) {
        return Math.sqrt(x * x + y * y) > mTouchSlop;
    }

    /**
     * 获取图片的位置
     */
    private RectF getMatrixRectF() {
        Matrix matrix = mScaleMatrix;
        RectF rectF = new RectF();
        if (getDrawable() != null) {
            rectF.set(0, 0, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        return rectF;
    }

    /**
     * 获取当前图片的缩放值
     *
     * @return
     */
    private float getScale() {
        float values[] = new float[9];
        mScaleMatrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }
}
