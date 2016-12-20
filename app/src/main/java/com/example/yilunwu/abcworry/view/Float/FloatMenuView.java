package com.example.yilunwu.abcworry.view.Float;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.example.yilunwu.abcworry.R;
import com.example.yilunwu.abcworry.utils.FloatViewManager;


/**
 * =======================================================
 * 版权：Copyright LiYing 2015-2016. All rights reserved.
 * 作者：liying - liruoer2008@yeah.net
 * 日期：2016/12/10 20:00
 * 版本：1.0
 * 描述：
 * 备注：
 * =======================================================
 */
public class FloatMenuView extends LinearLayout {
    private LinearLayout ll;
    private TranslateAnimation animation;
    public FloatMenuView(final Context context) {
        super(context);
        View root = View.inflate(context, R.layout.float_menu_view, null);
        ll = (LinearLayout) root.findViewById(R.id.ll);
        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0);
        animation.setDuration(500);
        animation.setFillAfter(true);
        ll.setAnimation(animation);
        root.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FloatViewManager manager = FloatViewManager.getInstance(context);
                manager.hideFloatMenuView();
                manager.showFloatCircleView();
                return false;
            }
        });
        addView(root);
    }

    public void startAnimation() {
        animation.start();
    }
}
