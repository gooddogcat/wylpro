package com.example.yilunwu.abcworry.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by yilunwu on 16/11/30.
 */

/**
 * 仿QQ主界面滑动删除置顶
 */
public class SlideMenuView extends RelativeLayout {
    private int mScreenWidth;
    private int mScreenHeight;
    private Context mContext;
    private int mDeleteWidth;
    private int mDeleteHeight;
    private int state = 0;
    private static final int START = 0;
    private static final int PULL = 1;
    private static final int RELEASE = 2;
    private int mStartX;
    private int mStartY;
    private int mTotalMoveX;
    private Scroller mScroller;
    private boolean isShowing = false;
    private OnClickListener mOnDeleteClickListener;
    private OnClickListener mOnTopClickListener;
    private String name;
    private TextView mTvName;
    private Button mBtnDelete;
    private Button mBtnTop;
    private int start_x;
    private int start_y;

    public SlideMenuView(Context context) {
        super(context);
        init(context);
    }

    public SlideMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlideMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context mContext) {

        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;
        mScroller = new Scroller(mContext);
        this.mContext = mContext;
    }

    public void setOnDeleteClickListener(OnClickListener listener) {
        this.mOnDeleteClickListener = listener;
    }

    public void setOnTopClickListener(OnClickListener listener) {
        this.mOnTopClickListener = listener;
    }

    public void setTextName(String name) {
        this.name = name;
    }

    //由于是自定义LinearLayout，应该覆盖这个方法来实现自己对控件宽高的安排
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //if语句用来判断子控件的数量，也可以通过getChildAt来获取子控件
        if (getChildCount() > 0) {
            for (int n = 0; n < getChildCount(); n++) {
                View view = getChildAt(n);
                measureChild(view, widthMeasureSpec, heightMeasureSpec);
            }
        }
    }
    //由于布局文件中包含有三个子控件，在自定义View中不要使用findViewById来寻找子控件。
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (getChildCount() > 0) {
            for (int n = 0; n < getChildCount(); n++) {
                View view = getChildAt(n);
                if (n == 0) {
                    view.layout(l, t, view.getMeasuredWidth(), b);
                } else if (n == 1) {
                    mDeleteHeight = view.getMeasuredHeight();
                    mDeleteWidth = view.getMeasuredWidth();
                    view.layout(mScreenWidth, t, mScreenWidth + view.getMeasuredWidth(), b);
                } else if (n == 2) {
                    view.layout(mScreenWidth + view.getMeasuredWidth(), t, mScreenWidth + view.getMeasuredWidth() * 2, b);
                }
            }
        }
        //跟布局代码相关
        mTvName = (TextView) getChildAt(0);
        mBtnDelete = (Button) getChildAt(1);
        mBtnTop = (Button) getChildAt(2);
        if (mOnDeleteClickListener != null) {
            mBtnDelete.setOnClickListener(mOnDeleteClickListener);
        }
        if (mOnTopClickListener != null) {
            mBtnTop.setOnClickListener(mOnTopClickListener);
        }
        if (name != null) {
            mTvName.setText(name);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getChildCount() > 0) {
            for (int n = 0; n < getChildCount(); n++) {
                View view = getChildAt(n);
                drawChild(canvas, view, 50);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN://记录坐标点
                mStartX = (int) ev.getX();
                mStartY = (int) ev.getY();
                start_x = (int) ev.getX();
                start_y = (int) ev.getY();
                state = PULL;
                break;
            case MotionEvent.ACTION_MOVE:
                //不让listView拦截事件
                if (Math.abs(ev.getX() - start_x) > Math.abs(ev.getY() - start_y)) {
                    requestDisallowInterceptTouchEvent(true);//不让父控件去处理这个方法
                } else {
                    requestDisallowInterceptTouchEvent(false);
                }
                if (state == PULL) {
                    mTotalMoveX += ((ev.getX() - mStartX));
                    if (!isShowing && mTotalMoveX < 0) {
                        scrollBy((int) (mStartX - ev.getX()), 0);
                    }
                    mStartX = (int) ev.getX();
                }
                break;
            case MotionEvent.ACTION_UP://在抬手的时候再实现滑动操作
                if (mTotalMoveX < 0) {
                    if (!isShowing) {
                        if (Math.abs(mTotalMoveX) > mDeleteWidth * 2 / 3) {
                            startScroll(-mTotalMoveX, 0, mDeleteWidth * 2 + mTotalMoveX, 0, 500);
                            isShowing = true;
                        } else {
                            startScroll(-mTotalMoveX, 0, mTotalMoveX, 0, 500);
                            isShowing = false;
                        }
                    }
                } else {
                    if (isShowing) {
                        startScroll(mDeleteWidth * 2, 0, -mDeleteWidth * 2, 0, 500);
                        isShowing = false;
                    }
                }
                mTotalMoveX = 0;
                state = START;
                break;
        }
        return true;
    }

    public void startScroll(int startX, int startY, int dx, int dy, int time) {

        mScroller.startScroll(startX, startY, dx, dy, time);
        postInvalidate();//调用computeScroll()函数
    }

    /**
     * 完成实际的滚动
     */
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {//如果滑动操作还没有完成，则会返回true
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }
    }
}
