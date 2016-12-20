package com.example.yilunwu.abcworry.view.Float;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.view.View;

import com.example.yilunwu.abcworry.R;

/**
 * Created by yilunwu on 16/12/20.
 */
public class FloatCircleView extends View {
    public int width = 150;
    public int height = 150;
    private Paint circlePaint;
    private Paint textPaint;
    private String text = "50%";
    /**是否被拖拽*/
    private boolean drag = false;
    private Bitmap bitmap;

    public FloatCircleView(Context context) {
        super(context);
        initPaints();
    }

    public FloatCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaints() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.GRAY);
        circlePaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(25);
        textPaint.setAntiAlias(true);
        textPaint.setFakeBoldText(true);

        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        bitmap = Bitmap.createScaledBitmap(src, width, height, true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drag) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        } else {
            canvas.drawCircle(width / 2, height / 2, width / 2, circlePaint);
            float textWidth = textPaint.measureText(text);
            float x = width / 2 - textWidth / 2;
            Paint.FontMetrics metrics = textPaint.getFontMetrics();
            float dy = -(metrics.descent + metrics.ascent) / 2;
            float y = height / 2 + dy;
            canvas.drawText(text, x, y, textPaint);
        }
    }

    /**
     * 设置是否处于拖拽状态
     * @param drag
     */
    public void setDragState(boolean drag) {
        this.drag = drag;
        invalidate();
    }
}
