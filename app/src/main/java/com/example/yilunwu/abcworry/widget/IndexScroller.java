package com.example.yilunwu.abcworry.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SectionIndexer;


/**
 * Created by yilunwu on 16/12/7.
 */
public class IndexScroller {
    private float mIndexbarWidth;//索引条的宽度
    private float mIndexbarMargin;//索引条距离右侧边缘的距离
    private float mPreviewPadding;//在中心显示的预览文本到四周的距离
    private float mDensity;//当前屏幕密度除以160
    private float mScaledDensity;//当前屏幕密度除以160（设置字体的尺寸）
    private float mAlphaRate;//透明度（用于显示和隐藏索引条）,0..1,闭区间
    private int mState=STATE_HIDDEN;//索引条的状态
    private int mListViewWidth;
    private int mListViewHeight;
    private int mCurrentSection=-1;
    private boolean mIsIndexing=false;
    private ListView mListView =null;
    private SectionIndexer mIndexer=null;
    private String[] mSections=null;
    private RectF mIndexbarRect;//索引区域

    private static final int STATE_HIDDEN=0;
    private static final int STATE_SHOWING=1;
    private static final int STATE_SHOWN=2;
    private static final int STATE_HIDING=3;

    //索引条初始化与尺寸本地化
    public IndexScroller(Context context,ListView lv){
        //获取屏幕密度的比值
        mDensity=context.getResources().getDisplayMetrics().density;
        mScaledDensity=context.getResources().getDisplayMetrics().scaledDensity;
        mListView=lv;
        setAdapter(mListView.getAdapter());
        //根据屏幕密度计算索引条的宽度，(单位:像素)
        mIndexbarWidth=20*mDensity;
        mIndexbarMargin=10*mDensity;
        mPreviewPadding=5*mDensity;



    }

    public void setAdapter(Adapter adapter){
        if (adapter instanceof SectionIndexer){
            mIndexer= (SectionIndexer) adapter;
            mSections=(String[])mIndexer.getSections();

        }
    }

    //绘制索引条和预览文本
    public void draw(Canvas canvas){
        //1.绘制索引条，包括索引条的背景和文本
        //2.绘制预览文本和背景
        //如果索引条隐藏，不进行绘制
        if (mState==STATE_HIDDEN){
            return;
        }
        //设置索引条背景的绘制属性
        Paint indexbarPaint=new Paint();
        indexbarPaint.setColor(Color.BLACK);
        indexbarPaint.setAlpha((int) (64 *mAlphaRate));

        //绘制索引条(4个角都是圆角的矩形区域)
        canvas.drawRoundRect(mIndexbarRect,5 *mDensity,5*mDensity,indexbarPaint);
        //绘制Sections
        if (mSections!=null&&mSections.length>0){
            //预览文本和背景
            if (mCurrentSection>=0){
                Paint previewPaint=new Paint();
                previewPaint.setColor(Color.BLACK);
                previewPaint.setAlpha(96);

                Paint previewTextPaint=new Paint();
                previewTextPaint.setColor(Color.WHITE);
                previewTextPaint.setTextSize(50*mScaledDensity);
                float previewTextWidth=previewTextPaint.measureText(mSections[mCurrentSection]);
                float previewSize=2*mPreviewPadding+previewTextPaint.descent()-previewTextPaint.ascent();
                //预览文本背景的区域
                RectF previewRect=new RectF(
                        (mListViewWidth-previewSize)/2,
                        (mListViewHeight-previewSize)/2,
                        (mListViewWidth-previewSize)/2+previewSize,
                        (mListViewHeight-previewSize)/2+previewSize);
                //绘制背景
                 canvas.drawRoundRect(previewRect,5*mDensity,5*mDensity,previewPaint);
                //绘制预览文本
                canvas.drawText(
                        mSections[mCurrentSection],
                        previewRect.left+(previewSize-previewTextWidth)/2-1,
                        previewRect.top+mPreviewPadding-previewTextPaint.ascent()+1,
                        previewTextPaint
                );

            }
        }

        //设置索引的绘制属性
        Paint indexPaint=new Paint();
        indexPaint.setColor(Color.WHITE);
        indexPaint.setAlpha((int) (255*mAlphaRate));
        indexPaint.setTextSize(12*mScaledDensity);
        float sectionHeight=(mIndexbarRect.height()-2*mIndexbarMargin)/mSections.length;
        float paddingTop=(sectionHeight-(indexPaint.descent()-indexPaint.ascent()))/2;
        for (int i=0;i<mSections.length;i++){
            float paddingLeft=(mIndexbarWidth-indexPaint.measureText(mSections[i]))/2;
            //绘制索引条上的文字
            canvas.drawText(mSections[i],mIndexbarRect.left+paddingLeft,
                    mIndexbarRect.top+mIndexbarMargin+sectionHeight*i
            +paddingTop-indexPaint.ascent(),indexPaint);

        }


    }

    public void onSizeChanged(int w,int h,int oldw,int oldh){
        mListViewWidth=w;
        mListViewHeight=h;
        mIndexbarRect=new RectF(w-mIndexbarMargin-mIndexbarWidth,
                mIndexbarMargin,
                w-mIndexbarMargin,
                h-mIndexbarMargin);
    }

    public void show(){
        if (mState==STATE_HIDDEN)
            setState(STATE_SHOWING);
        else if (mState==STATE_HIDING)
            setState(STATE_HIDING);
    }

    public void hide(){
        if (mState==STATE_SHOWN)
            setState(STATE_HIDING);
    }


    private void fade(long delay){
        mhandler.removeMessages(0);
        mhandler.sendEmptyMessageAtTime(0, SystemClock.uptimeMillis()+delay);
    }

    private Handler mhandler=new Handler() {
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            switch (mState){
                case STATE_SHOWING:
                    mAlphaRate+=(1-mAlphaRate)*0.2;
                    if (mAlphaRate>0.9){
                        mAlphaRate=1;
                        setState(STATE_SHOWN);
                    }

                    mListView.invalidate();
                    fade(10);
                    break;
                case STATE_SHOWN:
                    setState(STATE_HIDING);
                    break;
                case STATE_HIDING:
                    mAlphaRate-=mAlphaRate*0.2;
                    if (mAlphaRate<0.1){
                        mAlphaRate=0;
                        setState(STATE_HIDDEN);
                    }

                    mListView.invalidate();
                    fade(10);
                    break;

            }
        }
    };

    private void setState(int state){
        if (state<STATE_HIDDEN||state>STATE_HIDING)
            return;
        mState=state;
        switch (mState){
            case STATE_HIDDEN:
                mhandler.removeMessages(0);
                break;
            case STATE_SHOWING:
                mAlphaRate=0;
                fade(0);
                break;
            case STATE_SHOWN:
                mhandler.removeMessages(0);
                break;
            case STATE_HIDING:
                mAlphaRate=1;
                fade(3000);
                break;
        }
    }

    private boolean contains(float x,float y){
        return (x>=mIndexbarRect.left&&y>=mIndexbarRect.top&&y<=mIndexbarRect.top+mIndexbarRect.height());

    }

    private int getSectionByPoint(float y){
        if (mSections==null||mSections.length==0)
            return 0;
        if (y<mIndexbarRect.top+mIndexbarMargin)
            return 0;
        if (y>=mIndexbarRect.top+mIndexbarRect.height()-mIndexbarMargin)
            return mSections.length-1;
        return (int) ((y-mIndexbarRect.top-mIndexbarMargin)/((mIndexbarRect.height()-2*mIndexbarMargin)/mSections.length));
    }

    //管理触摸索引条的触摸事件方法
    public boolean onTouchEvent(MotionEvent ev){
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (mState!=STATE_HIDDEN&&contains(ev.getX(),ev.getY())){
                    setState(STATE_SHOWN);

                    mIsIndexing=true;
                    //通过触摸点获取当前的Section的索引
                    mCurrentSection=getSectionByPoint(ev.getY());
                    //将ListView定位到指定的item
                    mListView.setSelection(mIndexer.getPositionForSection(mCurrentSection));
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsIndexing){
                    if (contains(ev.getX(),ev.getY())){
                        mCurrentSection=getSectionByPoint(ev.getY());
                        mListView.setSelection(mIndexer.getPositionForSection(mCurrentSection));
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mIsIndexing){
                    mIsIndexing=false;
                    mCurrentSection=-1;

                }
                if (mState==STATE_SHOWN)
                    setState(STATE_HIDING);
                break;
        }
        return false;
    }




}
