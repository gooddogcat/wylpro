package com.example.yilunwu.abcworry.utils;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.example.yilunwu.abcworry.view.Float.FloatCircleView;
import com.example.yilunwu.abcworry.view.Float.FloatMenuView;

import java.lang.reflect.Field;

/**
 * Created by yilunwu on 16/12/20.
 */
public class FloatViewManager {
    private Context context;
    // 用来管理悬浮球的显示与隐藏，位置改变
    private WindowManager wm;
    private FloatCircleView circleView;
    private FloatMenuView menuView;
    private static FloatViewManager instance;
    private WindowManager.LayoutParams params;

    private View.OnTouchListener circleViewTouchListener = new View.OnTouchListener() {
        private float startX;
        private float startY;
        private float originX, originY;
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = event.getRawX();
                    startY = event.getRawY();
                    originX = startX;
                    originY = startY;
                    break;
                case MotionEvent.ACTION_MOVE:
                    float x = event.getRawX();
                    float y = event.getRawY();
                    float dx = x - startX;
                    float dy = y - startY;
                    params.x += dx;
                    params.y += dy;
                    circleView.setDragState(true);
                    wm.updateViewLayout(circleView, params);
                    startX = x;
                    startY = y;
                    break;
                case MotionEvent.ACTION_UP:
                    float endX = event.getRawX();
                    float endY = event.getRawY();
                    if (endX > getScreenWidth() / 2) {
                        params.x = getScreenWidth() - circleView.width;
                    } else {
                        params.x = 0;
                    }
                    circleView.setDragState(false);
                    wm.updateViewLayout(circleView, params);
                    if (Math.abs(endX - originX) > 6 || Math.abs(endY - originY) > 6) {
                        // 认为是拖动事件，消费掉
                        return true;
                    }
                    break;
            }
            // 认为是点击事件，交给onClick去处理
            return false;
        }
    };
    private FloatViewManager(Context context) {
        this.context = context;
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        circleView = new FloatCircleView(context);
        circleView.setOnTouchListener(circleViewTouchListener);
        circleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 隐藏circleView，显示菜单栏，开启动画
                wm.removeView(circleView);
                showFloatMenuView();
                menuView.startAnimation();
            }
        });
        menuView = new FloatMenuView(context);
    }

    private void showFloatMenuView() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.width = getScreenWidth();
        params.height = getScreenHeight() - getStatusHeight();
        params.gravity = Gravity.BOTTOM|Gravity.LEFT;
        params.x = 0;
        params.y = 0;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
//        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.format = PixelFormat.RGBA_8888;
        wm.addView(menuView, params);
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public int getScreenWidth() {
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public int getScreenHeight() {
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 获取状态栏的高度
     * @return
     */
    public int getStatusHeight() {
        int height = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object o = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (Integer) field.get(o);
            height = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {

        }
        return height;
    }
    public static FloatViewManager getInstance(Context context) {
        if (instance == null) {
            synchronized (FloatViewManager.class) {
                if (instance == null) {
                    instance = new FloatViewManager(context);
                }
            }
        }
        return instance;
    }

    /**
     * 展示浮窗小球到窗体上
     */
    public void showFloatCircleView() {
        if (params == null) {
            params = new WindowManager.LayoutParams();
            params.width = circleView.width;
            params.height = circleView.height;
            params.gravity = Gravity.TOP|Gravity.LEFT;
            params.x = 0;
            params.y = 0;
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
//        params.type = WindowManager.LayoutParams.TYPE_TOAST;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            params.format = PixelFormat.RGBA_8888;
        }
        wm.addView(circleView, params);
    }

    public void hideFloatMenuView() {
        wm.removeView(menuView);
    }
}
