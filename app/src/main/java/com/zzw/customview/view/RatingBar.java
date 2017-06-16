package com.zzw.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.zzw.customview.R;
import com.zzw.customview.view.utils.DisplayUtil;

/**
 * Created by zzw on 2017/6/16.
 * Version:
 * Des:评分控件
 */

public class RatingBar extends View {

    private Bitmap mStarFocusBitmap, mStarNormalBitmap;
    private int mGradeNumber;//最大分数
    private int mCurrentGrade;//当前分数  1开始的  最少为1分
    private int mStarPadding;//间距

    public RatingBar(Context context) {
        this(context, null);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        int starNormalId = array.getResourceId(R.styleable.RatingBar_starNormal, 0);
        if (starNormalId == 0) {
            throw new RuntimeException("请设置属性 starNormal ");
        }

        mStarNormalBitmap = BitmapFactory.decodeResource(getResources(), starNormalId);

        int starFocusId = array.getResourceId(R.styleable.RatingBar_starFocus, 0);
        if (starFocusId == 0) {
            throw new RuntimeException("请设置属性 starFocus ");
        }

        mStarFocusBitmap = BitmapFactory.decodeResource(getResources(), starFocusId);
        mGradeNumber = array.getInt(R.styleable.RatingBar_gradeNumber, 0);
        mStarPadding = array.getDimensionPixelOffset(R.styleable.RatingBar_statPadding, DisplayUtil.dip2px(context, 5));
        mCurrentGrade = array.getInt(R.styleable.RatingBar_currentGrade, 1);

        array.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 高度  一张图片的高度
        int height = mStarFocusBitmap.getHeight() + getPaddingTop() + getPaddingBottom();
        int width = mStarFocusBitmap.getWidth() * mGradeNumber
                + getPaddingLeft() + getPaddingRight()
                + mStarPadding * (mGradeNumber - 1);
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mGradeNumber; i++) {
            if (i < mCurrentGrade) {
                canvas.drawBitmap(mStarFocusBitmap,
                        getPaddingLeft() + i * mStarFocusBitmap.getWidth() + i * mStarPadding, getPaddingTop(), null);
            } else {
                canvas.drawBitmap(mStarNormalBitmap,
                        getPaddingLeft() + i * mStarFocusBitmap.getWidth() + i * mStarPadding, getPaddingTop(), null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //计算下标
                int pos = comPos(event.getX());
                if (pos != mCurrentGrade - 1) {//分数-1 等于 坐标pos
                    mCurrentGrade = pos + 1;
                    invalidate();
                }
                break;
        }
        return true;
    }

    /**
     * 计算位置
     *
     * @param x
     * @return
     */
    private int comPos(float x) {

//        int startX = getPaddingLeft();
//        if (x < startX) {
//            return 0;
//        }
//
//        if (x > getWidth() - getPaddingRight()) {
//            return mGradeNumber;
//        }
//
//        int pos = 0;
//        for (int i = 0; i < mGradeNumber; i++) {
//            int endX;
//            if (i == 0 || i == mGradeNumber - 1) {//第0个位置的后面的x
//                endX = startX + mStarFocusBitmap.getWidth() + mStarPadding / 2;
//            } else {
//                endX = startX + mStarFocusBitmap.getWidth() + mStarPadding;
//            }
//            if (x < endX) {
//                pos = i;
//                break;
//            }
//            startX = endX;
//        }
//        return pos;
        int pos = (int) (x / (mStarFocusBitmap.getWidth() + mStarPadding));
        if (pos < 0) pos = 0;
        if (pos >= mGradeNumber) pos = mGradeNumber - 1;
        return pos;
    }

    /**
     * 得到分数
     *
     * @return
     */
    public int getGrade() {
        return mCurrentGrade;
    }

    /**
     * 设置分数
     *
     * @param grade
     */
    public void setGrade(int grade) {
        if (mCurrentGrade != grade) {
            mCurrentGrade = grade;
            invalidate();
        }
    }

    /**
     * 回收
     */
    public void recycle() {
        if (mStarFocusBitmap != null)
            mStarFocusBitmap.recycle();

        if (mStarNormalBitmap != null)
            mStarNormalBitmap.recycle();

        mStarFocusBitmap = null;
        mStarNormalBitmap = null;
    }


}
