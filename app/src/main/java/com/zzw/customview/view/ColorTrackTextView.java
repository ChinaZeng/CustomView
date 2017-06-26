package com.zzw.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

import com.zzw.customview.R;
import com.zzw.customview.view.utils.DisplayUtil;

/**
 * Created by zzw on 2017/6/15.
 * Version:
 * Des:字体变色的View
 */

public class ColorTrackTextView extends AppCompatTextView {

    private int mOriginColor;//不变化的颜色
    private int mChangeColor;//变化的颜色

    private Paint mOriginPaint, mChangePaint;

    //不同的朝向
    public static final int DIRECTION_LEFT_TO_RIGHT = 1;//从左边变色
    public static final int DIRECTION_RIGHT_TO_LEFT = 2;//从右边变色


    private int mDirection = DIRECTION_LEFT_TO_RIGHT;

    //当前进度
    private float mCurrentProgress;

    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        mOriginColor = ta.getColor(R.styleable.ColorTrackTextView_originColor, Color.BLACK);
        mChangeColor = ta.getColor(R.styleable.ColorTrackTextView_changeColor, Color.RED);
        ta.recycle();

        mOriginPaint = new Paint();
        mOriginPaint.setColor(mOriginColor);
        mOriginPaint.setAntiAlias(true);
        mOriginPaint.setTextSize(getTextSize());

        mChangePaint = new Paint();
        mChangePaint.setColor(mChangeColor);
        mChangePaint.setAntiAlias(true);
        mChangePaint.setTextSize(getTextSize());

    }


    //思路：利用clipRect  来裁剪   使用两个画笔
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);   //不使用TextView的绘制 自己画

        int mid = comMiddle();
        if (mDirection == DIRECTION_LEFT_TO_RIGHT) {
            drawText(canvas, mChangePaint, 0, mid); //画左边  颜色
            drawText(canvas, mOriginPaint, mid, getWidth());//画右边
        } else {
            mid = getWidth() - mid;
            drawText(canvas, mChangePaint, mid, getWidth());//画右边  颜色
            drawText(canvas, mOriginPaint, 0, mid); //画左边
        }
    }

    /**
     * 根据当前进度算出中间值
     *
     * @return
     */
    private int comMiddle() {
        return (int) (mCurrentProgress * getWidth());
    }


    /**
     * 根据start  end  确定rect绘制文字
     *
     * @param canvas
     * @param paint
     * @param start
     * @param end
     */
    private void drawText(Canvas canvas, Paint paint, int start, int end) {
        canvas.save();
        Rect rect = new Rect(start, 0, end, getHeight());//确定区域
        canvas.clipRect(rect);

        String text = getText().toString();
        int x = (int) (getPaddingLeft() + getWidth() / 2 - paint.measureText(text) / 2);
        int y = getPaddingTop() + DisplayUtil.getTextBaseLine(getHeight(), paint);
        canvas.drawText(text, x, y, paint);
        canvas.restore();
    }


    public void setCurrentProgress(float currentProgress) {
        if (mCurrentProgress == currentProgress)//当前进度相同就不执行下一步
            return;

        this.mCurrentProgress = currentProgress;
        invalidate();
    }

    public void setDirection(int direction) {
        this.mDirection = direction;
    }

    public int getOriginColor() {
        return mOriginColor;
    }

    public void setOriginColor(int originColor) {
        this.mOriginColor = originColor;
    }

    public int getChangeColor() {
        return mChangeColor;
    }

    public void setChangeColor(int changeColor) {
        this.mChangeColor = changeColor;
    }



}

