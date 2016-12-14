package com.example.yilunwu.abcworry.view.candle;

import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by yilunwu on 16/12/12.
 */
public abstract class ICandle {
    //蜡烛底部左下坐标
    protected int mCurX;
    protected int mCurY;
    //蜡烛宽高
    protected int mCandleWidth;
    protected int mCandleHeight;
    //蜡烛左眼坐标
    protected Point mEyeLPoint;
    //蜡烛右眼坐标
    protected Point mEyeRPoint;
    //蜡烛眼睛半径
    protected int mEyeRadius;
    //眼睛间隔距离
    protected int mEyeDevide;
    //身体颜色
    protected int mCandleColor;
    //是否停止动画中
    protected boolean mIsAnimStoping = false;
    //蜡烛芯坐标
    protected Point mCandlewickPoint;
    public void initAnim(){
    }
    public void stopAnim(){
    }
    public void drawSelf(Canvas canvas){
    }
}
