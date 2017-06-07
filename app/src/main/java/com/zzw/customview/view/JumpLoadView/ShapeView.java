package com.zzw.customview.view.JumpLoadView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zzw on 2017/6/6.
 * Version:
 * Des:仿58同城跳动加载 跳动的形状
 */

public class ShapeView extends View {

    private int mFlog = 0;//标志  当前要绘制什么图案

    private int mRadio = 30;

    private Paint mCirclePaint;
    private Paint mRectPaint;
    private Paint mTrianglePaint;
    private Paint mArcPaint;

    private int mCenterX = -1;
    private int mCenterY = -1;


    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.BLUE);

        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);
        mRectPaint.setColor(Color.RED);

        mTrianglePaint = new Paint();
        mTrianglePaint.setAntiAlias(true);
        mTrianglePaint.setColor(Color.GREEN);

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(Color.GRAY);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initCenterPoint();

        if (mFlog == 0)
            drawCircle(canvas);
        else if (mFlog == 1)
            drawRect(canvas);
        else
            drawTriangle(canvas);
    }

    private void initCenterPoint() {
        if (mCenterX == -1 || mCenterY == -1) {
            mCenterX = getMeasuredWidth() / 2;
            mCenterY = getMeasuredHeight() / 2;
//            setPivotX(mCenterX);
//            setPivotY(mCenterY);
        }
    }

    /**
     * 画圆
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(mCenterX, mCenterY, mRadio, mCirclePaint);
    }




    /**
     * 画正方形
     *
     * @param canvas
     */
    private void drawRect(Canvas canvas) {
        Rect rect = new Rect(mCenterX - mRadio,
                mCenterY - mRadio,
                mCenterX + mRadio,
                mCenterY + mRadio);
        canvas.drawRect(rect, mRectPaint);
    }


    public void setFlog(int mFlog) {
        this.mFlog = mFlog;
        invalidate();
    }

    /**
     * 绘制三角形
     *
     * @param canvas
     */
    private void drawTriangle(Canvas canvas) {
        Path path = new Path();
        path.moveTo(mCenterX, mCenterY - mRadio);
        path.lineTo(mCenterX - mRadio, mCenterY + mRadio);
        path.lineTo(mCenterX + mRadio, mCenterY + mRadio);
        canvas.drawPath(path, mTrianglePaint);
    }




    public void setRadio(int mRadio) {
        this.mRadio = mRadio;
    }
}
