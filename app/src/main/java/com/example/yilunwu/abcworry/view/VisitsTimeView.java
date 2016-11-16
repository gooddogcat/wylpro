package com.example.yilunwu.abcworry.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class VisitsTimeView extends View {

    private static float CELL_TEXT_SIZE;
    private static float CELL_NO_SIZE;
    private static float CELL_WEEK_SIZE;
    public static final int DEFAULT_BOARD_SIZE = 20;
    public static int mCellHeight;
    public static int mCellWidth;

    private static final String[] weekTitle = new String[14];
    private static final String[] week = new String[14];
    private int left;
    private int left2;
    private String weekDay;
    Context mContext;
    Paint mBackgroundColor;
    Paint mDayTitle;
    Paint mWeekTitle;
    Paint mNo;
    Paint mLinePaint;
    Paint mLinePaint2;
    Bitmap mIsFullBitmap;
    Bitmap mTzbitmap;
    Bitmap mRegBitmap;



    public VisitsTimeView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        this.mContext = paramContext;
        initView();
    }

    private void initView() {
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < 7; i++) {
            String day = new SimpleDateFormat("MM" + "－" + "dd" , Locale.getDefault()).format(c.getTime());
            switch (c.get(Calendar.DAY_OF_WEEK)) {
                case 1:
                    weekDay = "周日";
                    break;
                case 2:
                    weekDay = "周一";
                    break;
                case 3:
                    weekDay = "周二";
                    break;
                case 4:
                    weekDay = "周三";
                    break;
                case 5:
                    weekDay = "周四";
                    break;
                case 6:
                    weekDay = "周五";
                    break;
                case 7:
                    weekDay = "周六";
                    break;
            }
            day = day.replaceFirst("^0*", "");
            weekTitle[i] = day;
            week[i] = weekDay;
            c.add(Calendar.DAY_OF_YEAR, 1);
        }

        this.mBackgroundColor = new Paint();
        this.mBackgroundColor.setAntiAlias(true);
        this.mBackgroundColor.setColor(Color.WHITE);
        this.mLinePaint = new Paint();
        this.mLinePaint.setAntiAlias(true);
        this.mLinePaint.setColor(Color.rgb(118, 222, 244));
        this.mDayTitle = new Paint();
        this.mDayTitle.setColor(Color.BLACK);
        this.mDayTitle.setAntiAlias(true);
        this.mDayTitle.setTypeface(Typeface.DEFAULT);
        this.mWeekTitle = new Paint();
        this.mWeekTitle.setColor(Color.BLACK);
        this.mWeekTitle.setAntiAlias(true);
        this.mWeekTitle.setTypeface(Typeface.DEFAULT);
        this.mNo = new Paint();
        this.mNo.setColor(Color.WHITE);
        this.mNo.setAntiAlias(true);
        this.mNo.setTypeface(Typeface.DEFAULT_BOLD);
        this.mNo.setTextSize(20);
    }

    protected void onDraw(Canvas paramCanvas) {
        super.onDraw(paramCanvas);
        Rect localRect1 = new Rect(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + mCellWidth, getPaddingTop() + mCellHeight);
        localRect1.offset(0, 0);
        Rect localRect2 = new Rect(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + mCellWidth, getPaddingTop() + mCellHeight);
        localRect2.offset(0, 0);
        paramCanvas.drawRect(getPaddingLeft() + mCellWidth, getPaddingTop(), 3 * mCellWidth - getPaddingLeft(), 14F * mCellHeight - getPaddingTop(), this.mBackgroundColor);

        String str1 = "";
        int i1 = (int) (this.mDayTitle.measureText(str1) / 2);
        int ii1 = (int) ((-this.mDayTitle.ascent() + this.mDayTitle.descent()) / 2.0F);
        paramCanvas.drawText(str1, localRect1.centerX() - i1, ii1 + localRect1.centerY(), this.mDayTitle);
        localRect1.offset(mCellWidth, 0);

        for (int j = 0; j < 7; j++) {
            String str2 = weekTitle[j];
            String str3 = week[j];
            int i2 = (int) (this.mDayTitle.measureText(str2) / 2);
            int ii2 = (int) ((-this.mDayTitle.ascent() + this.mDayTitle.descent()) / 3.0F);
//            paramCanvas.drawText(str2, localRect2.centerX() - i2, 2 * ii2 + localRect2.centerY(), this.mDayTitle);
            paramCanvas.drawText(str2, localRect2.centerY() - i2, 2 * ii2 + localRect2.centerX(), this.mDayTitle);


//            paramCanvas.drawText(str3, localRect2.centerX() - i2 / 2, localRect2.centerY() - ii2, this.mWeekTitle);
            paramCanvas.drawText(str3, localRect2.centerY() - i2 / 2, localRect2.centerX() - ii2, this.mWeekTitle);
            localRect2.offset(0, mCellHeight);
        }
        // 水平线
        for (int f = 1; f < 4; f++) {
            float f1 = f * mCellHeight;
            paramCanvas.drawLine(getPaddingLeft(), f1, 14 * mCellWidth - getPaddingLeft(), f1, this.mLinePaint);
        }

        // 垂直线
        for (int k = 1; k < 14; k++) {
            float f2 = k * mCellWidth;
            paramCanvas.drawLine(f2 + getPaddingLeft(), getPaddingTop(), f2 + getPaddingLeft(), 3 * mCellHeight - getPaddingBottom(), this.mLinePaint);
        }


        Bitmap localBitmap = null;


    }



    protected void onMeasure(int paramInt1, int paramInt2) {
        int i = View.MeasureSpec.getMode(paramInt1);
        int j = View.MeasureSpec.getSize(paramInt1);
        int k = View.MeasureSpec.getMode(paramInt2);
        int m = View.MeasureSpec.getSize(paramInt2);
        int n = 50;
        int p = 50;
        if (i == MeasureSpec.EXACTLY) {
            n = j;
            if (k == MeasureSpec.EXACTLY) {
                p = m;
            } else if (k == MeasureSpec.AT_MOST) {
                p = m;
            }
        } else if (i == MeasureSpec.AT_MOST) {
            n = j;
        }
        mCellWidth = n / 7;
        mCellHeight = p / 16;
        setMeasuredDimension(n, p);
        CELL_TEXT_SIZE = 0.3F * mCellHeight;
        CELL_WEEK_SIZE = 0.3F * mCellHeight;
        final Resources res = mContext.getResources();
        this.mDayTitle.setTextSize(CELL_TEXT_SIZE);
        this.mWeekTitle.setTextSize(CELL_WEEK_SIZE);
//        this.mNo.setTextSize(res.getDimensionPixelSize(R.dimen.yuhao));
    }


}

