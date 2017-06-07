package com.zzw.customview.view.JumpLoadView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zzw on 2017/6/6.
 * Version:
 * Des:仿58同城跳动加载  底部弧形阴影
 */

public class ArcView extends View {

    private int mRadio = 30;
    private Paint mArcPaint;
    private RectF mRectF;

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = mRadio * 2;
        }

        return result;
    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 2 * mRadio;
        }
        return result;
    }

    private void initPaint() {
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mRectF == null) {
            int left = getWidth() / 2 - mRadio;
            int top = getHeight() / 2 - mRadio - mRadio / 3;
            int right = getWidth() / 2 + mRadio;
            int bottom = getHeight() / 2 - mRadio + mRadio / 3;
            mRectF = new RectF(left, top, right, bottom);
            //设置缩放点
            setPivotX(getWidth() / 2);
            setPivotY(getHeight() / 2 - mRadio);
        }
        canvas.drawArc(mRectF, 0, 180, false, mArcPaint);
    }

    public void setRadio(int mRadio) {
        this.mRadio = mRadio;
    }
}
