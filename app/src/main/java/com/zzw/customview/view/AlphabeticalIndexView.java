package com.zzw.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

import com.zzw.customview.view.utils.DisplayUtil;

/**
 * Created by zzw on 2017/6/8.
 * Version:
 * Des:字母索引View
 */

public class AlphabeticalIndexView extends ListView {

    private Paint mPaint;
    private float mSingLetterHeight;
    private String[] mLetters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private String mCurrentTouchLetter = mLetters[0];


    public AlphabeticalIndexView(Context context) {
        this(context, null);
    }

    public AlphabeticalIndexView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlphabeticalIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = (int) (getPaddingLeft() + getPaddingRight() + DisplayUtil.getTextWidth("W", mPaint));
        int h = getMeasuredHeight();
        setMeasuredDimension(w, h);
        mSingLetterHeight = (float) (h - getPaddingTop() - getPaddingBottom()) / mLetters.length;
    }


    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(DisplayUtil.sp2px(context, 15));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 每个字母所占用的高度
        for (int i = 0; i < mLetters.length; i++) {
            String letter = mLetters[i];
            if (letter.equals(mCurrentTouchLetter))
                mPaint.setColor(Color.BLUE);
            else
                mPaint.setColor(Color.GRAY);

            // 获取字体的宽度
            float measureTextWidth = mPaint.measureText(letter);
            // 获取内容的宽度
            int contentWidth = getWidth() - getPaddingLeft() - getPaddingRight();

            canvas.drawText(letter, getPaddingLeft() + (contentWidth - measureTextWidth) / 2, getPaddingTop() + mSingLetterHeight / 2 + mSingLetterHeight * i + DisplayUtil.getTextBaseLine(mPaint), mPaint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // 获取当前手指触摸的Y位置
                float fingerY = ev.getY();
                int pos = (int) (fingerY / mSingLetterHeight);
                if (pos > -1 && pos < mLetters.length && !mLetters[pos].equals(mCurrentTouchLetter)) {
                    mCurrentTouchLetter = mLetters[pos];
                    triggerTouchListener(true);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                triggerTouchListener(false);
                break;
        }

        return true;
    }

    private void triggerTouchListener(boolean isTouch) {
        if (mTouchListener != null)
            mTouchListener.onTouch(mCurrentTouchLetter, isTouch);
    }

    // 设置触摸监听
    private SideBarTouchListener mTouchListener;

    public void setOnSideBarTouchListener(SideBarTouchListener touchListener) {
        this.mTouchListener = touchListener;
    }

    public interface SideBarTouchListener {
        void onTouch(String letter, boolean isTouch);
    }
}
