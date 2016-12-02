package com.example.yilunwu.abcworry.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.yilunwu.abcworry.R;

/**
 * Created by yilunwu on 16/11/15.
 */

/**
 * 侧滑返回
 */
public class SwipeBackLayout extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    // 主界面
    private View mainView;
    // 主界面的宽度
    private int mainViewWidth;
    // 模式，默认是左滑
    private int mode = MODE_LEFT;
    // 监听器
    private SwipeBackListener listener;
    // 是否支持边缘滑动返回, 默认是支持
    private boolean isEdge = true;

    private int mEdge;
    // 阴影Drawable
    private Drawable shadowDrawable;
    // 阴影Drawable固有宽度
    private int shadowDrawbleWidth;
    // 已经滑动的百分比
    private float movePercent;
    // 滑动的总长度
    private int totalWidth;
    // 默认的遮罩透明度
    private static final int DEFAULT_SCRIM_COLOR = 0x99000000;
    // 遮罩颜色
    private int scrimColor = DEFAULT_SCRIM_COLOR;
    // 透明度
    private static final int ALPHA = 255;

    private Paint mPaint;
    /**
     * 滑动的模式，左滑
     */
    public static final int MODE_LEFT = 0;
    /**
     * 滑动的模式，右滑
     */
    public static final int MODE_RIGHT = 1;
    // 最小滑动速度
    private static final int MINIMUM_FLING_VELOCITY = 400;

    private static final String TAG = "SwipeBackLayout";

    /**
     * 设置滑动的模式。有左滑、右滑和双向
     *
     * @param mode
     */
    public void setMode(int mode) {
        this.mode = mode;
        if (mode == MODE_LEFT) {
            mEdge = ViewDragHelper.EDGE_LEFT;
        } else {
            mEdge = ViewDragHelper.EDGE_RIGHT;
        }
        mViewDragHelper.setEdgeTrackingEnabled(mEdge);
    }

    public SwipeBackLayout(Context context) {
        this(context, null);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwipeBackLayout);
        // 得到滑动模式，默认左滑
        mode = a.getInt(R.styleable.SwipeBackLayout_swipe_mode, MODE_LEFT);
        a.recycle();
        initView();
    }

    private void initView() {
        float density = getResources().getDisplayMetrics().density;
        float minVel = density * MINIMUM_FLING_VELOCITY;
        initShadowView();
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return mainView == child; // 只有是主界面时才可以被滑动
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                // 根据模式区分
                switch (mode) {
                    case MODE_LEFT:  // 左边
                        if (left < 0) {
                            return 0;
                        } else if (Math.abs(left) > totalWidth) {
                            return totalWidth;
                        } else {
                            return left;
                        }
                    case MODE_RIGHT:  // 右边
                        if (left > 0) {
                            return 0;
                        } else if (Math.abs(left) > totalWidth) {
                            return -totalWidth;
                        } else {
                            return left;
                        }
                    default:
                        return left;
                }
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                switch (mode) {
                    case MODE_LEFT:
                        movePercent = left * 1f / totalWidth;
                        Log.i(TAG, "movePercent = " + movePercent);
                        break;
                    case MODE_RIGHT:
                        movePercent = Math.abs(left) * 1f / totalWidth;
                        Log.i(TAG, "movePercent = " + movePercent);
                        break;
                }
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                if (mode == MODE_LEFT) {
                    return Math.abs(totalWidth);
                } else {
                    return -totalWidth;
                }
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                switch (mode) {
                    case MODE_LEFT:
                        if (xvel > -mViewDragHelper.getMinVelocity() && Math.abs(releasedChild.getLeft()) > mainViewWidth / 2.0f) {
                            swipeBackToFinish(totalWidth, 0);
                        } else if (xvel > mViewDragHelper.getMinVelocity()) {
                            swipeBackToFinish(totalWidth, 0);
                        } else {
                            swipeBackToRestore();
                        }
                        break;
                    case MODE_RIGHT:
                        if (xvel < mViewDragHelper.getMinVelocity() && Math.abs(releasedChild.getLeft()) > mainViewWidth / 2.0f) {
                            swipeBackToFinish(-totalWidth, 0);
                        } else if (xvel < -mViewDragHelper.getMinVelocity()) {
                            swipeBackToFinish(-totalWidth, 0);
                        } else {
                            swipeBackToRestore();
                        }
                        break;
                }
            }
        });
        // 设置最小滑动速度
        mViewDragHelper.setMinVelocity(minVel);
    }

    // 初始化阴影Drawable
    private void initShadowView() {
        if (Build.VERSION.SDK_INT >= 21) {
            shadowDrawable = getResources().getDrawable(mode == MODE_LEFT ? R.drawable.shadow_left : R.drawable.shadow_right, getContext().getTheme());
        } else {
            shadowDrawable = getResources().getDrawable(mode == MODE_LEFT ? R.drawable.shadow_left : R.drawable.shadow_right);
        }
        if (shadowDrawable != null) {
            shadowDrawbleWidth = shadowDrawable.getIntrinsicWidth();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount();
        if (count == 1) {
            // 获取子view
            mainView = getChildAt(0);
        } else {
            throw new IllegalArgumentException("the child of swipebacklayout can not be empty and must be the one");
        }
    }


    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        Log.i(TAG, "" + (mViewDragHelper.getViewDragState() == ViewDragHelper.STATE_IDLE));
        if (child == mainView && mViewDragHelper.getViewDragState() != ViewDragHelper.STATE_IDLE) {
            // 绘制阴影
            drawShadowDrawable(canvas, child);
            // 绘制遮罩层
            drawScrimColor(canvas, child);
        }
        return super.drawChild(canvas, child, drawingTime);
    }

    // 绘制遮罩层
    private void drawScrimColor(Canvas canvas, View child) {
        // 设置透明度
        int baseAlpha = (scrimColor & 0xFF000000) >>> 24;
        // 根据滑动进度动态设置透明度
        int alpha = (int) (baseAlpha * (1 - movePercent));
        int color = alpha << 24 | (scrimColor & 0xffffff);
        // 设置绘制矩形区域
        Rect rect;
        if (mode == MODE_LEFT) { // 左滑
            rect = new Rect(0, 0, child.getLeft(), getHeight());
        } else { // 右滑
            rect = new Rect(child.getRight(), 0, getWidth(), getHeight());
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        canvas.drawRect(rect, mPaint);
    }

    // 绘制阴影
    private void drawShadowDrawable(Canvas canvas, View child) {
        Rect drawableRect = new Rect();
        // 得到mainView的矩形
        child.getHitRect(drawableRect);
        // 设置shadowDrawable绘制的矩形
        if (mode == MODE_LEFT) { // 左滑
            shadowDrawable.setBounds(drawableRect.left - shadowDrawbleWidth, drawableRect.top, drawableRect.left, drawableRect.bottom);
        } else { // 右滑
            shadowDrawable.setBounds(drawableRect.right, drawableRect.top, drawableRect.right + shadowDrawbleWidth, drawableRect.bottom);
        }
        // 设置shadowDrawable的透明度,最低为0.3
        shadowDrawable.setAlpha((int) ((1 - movePercent > 0.3 ? 1 - movePercent : 0.3) * ALPHA));
        // 将shadowDrawable绘制在canvas上
        shadowDrawable.draw(canvas);
    }

    /**
     * 滑动返回，结束该View
     */
    public void swipeBackToFinish(int finalLeft, int finalTop) {
        if (mViewDragHelper.smoothSlideViewTo(mainView, finalLeft, finalTop)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        if (listener != null) {
            listener.onSwipeBackFinish();
        }
    }

    /**
     * 滑动到原位
     */
    public void swipeBackToRestore() {
        if (mViewDragHelper.smoothSlideViewTo(mainView, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        // 重绘，保证在滑动的时候可以绘制阴影
        invalidate();
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_CANCEL:
                return false;
        }
        return mViewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 得到主界面的宽度
        mainViewWidth = w;
        //总长度
        totalWidth = mainViewWidth + shadowDrawbleWidth;
    }

    public interface SwipeBackListener {
        /**
         * 该方法会在滑动返回完成的时候回调
         */
        void onSwipeBackFinish();
    }

    /**
     * 设置滑动返回监听器
     *
     * @param listener
     */
    public void setSwipeBackListener(SwipeBackListener listener) {
        this.listener = listener;
    }
}
