package com.zzw.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zzw.customview.R;
import com.zzw.customview.view.utils.DisplayUtil;

/**
 * Created by zzw on 2017/6/6.
 * Version:
 * Des:仿照qq运动记步View
 */

public class QQSportStepView extends View {

    //在这定义三个画笔  因为paint.setColor是调用native方法  耗时
    private Paint mBottomPaint;//底层圆的画笔
    private Paint mTopPaint;//顶层圆的画笔
    private Paint mTextPaint;//文字的画笔

    private int mBottomColor;//底层圆的颜色
    private int mTopColor;//顶层圆的颜色
    private int mTextColor;//文字颜色

    private int mMaxStepNum;//最大步数
    private int mCurrentStepNum;//当前步数

    private int mTextSize;//文字大小
    private int mCircleRadio;//圆的半径
    private int mCircleStrokeWidth;//圆线条的宽度

    private float mArcAngle;//弧度大小
    private float mStartAngle;//通过幅度计算出开始的角度位置

    private RectF mRectF;//画弧的边界
    private int mTextX = -1;
    private int mTextY = -1;

    public QQSportStepView(Context context) {
        this(context, null);
    }

    public QQSportStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQSportStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
    }


    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.QQSportStep);
        mBottomColor = ta.getColor(R.styleable.QQSportStep_bottomColor, Color.BLUE);
        mTopColor = ta.getColor(R.styleable.QQSportStep_topColor, Color.RED);
        mTextColor = ta.getColor(R.styleable.QQSportStep_textColor, Color.RED);
        mMaxStepNum = ta.getInteger(R.styleable.QQSportStep_maxStepNum, 0);
        mCurrentStepNum = ta.getInteger(R.styleable.QQSportStep_currentStepNum, 0);
        mTextSize = ta.getDimensionPixelSize(R.styleable.QQSportStep_textSize, DisplayUtil.sp2px(context, 17));
        mCircleRadio = ta.getDimensionPixelSize(R.styleable.QQSportStep_circleRadio, DisplayUtil.dip2px(context, 100));
        mArcAngle = ta.getFloat(R.styleable.QQSportStep_arcAngle, 270.0f);
        if (mArcAngle > 360.0f || mArcAngle < -360.0f)
            mArcAngle = 360.0f;

        mCircleStrokeWidth = ta.getDimensionPixelOffset(R.styleable.QQSportStep_circleStrokeWidth, DisplayUtil.dip2px(context, 5));
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initPaint() {
        mBottomPaint = new Paint();
        mBottomPaint.setAntiAlias(true);
        mBottomPaint.setColor(mBottomColor);
        mBottomPaint.setStrokeCap(Paint.Cap.ROUND);//边缘设置为圆形的
        mBottomPaint.setStrokeJoin(Paint.Join.ROUND);//边缘设置为圆形的
        mBottomPaint.setStrokeWidth(mCircleStrokeWidth);
        mBottomPaint.setStyle(Paint.Style.STROKE);

        mTopPaint = new Paint();
        mTopPaint.setColor(mTopColor);
        mTopPaint.setAntiAlias(true);
        mTopPaint.setStrokeWidth(mCircleStrokeWidth);
        mTopPaint.setStrokeCap(Paint.Cap.ROUND);//边缘设置为圆形的
        mBottomPaint.setStrokeJoin(Paint.Join.ROUND);//边缘设置为圆形的
        mTopPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRectF == null) {
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            mRectF = new RectF(centerX - mCircleRadio, centerY - mCircleRadio, centerX + mCircleRadio, centerY + mCircleRadio);
        }

        //1.画底部的圆
        float gapAngle = mArcAngle - 180.0f;
        if (mArcAngle >= 0) {//大于0表示在上方
            mStartAngle = 180.0f - gapAngle / 2;
        } else {//小于0表示在下方
            mStartAngle = -gapAngle / 2;
        }
        canvas.drawArc(mRectF, mStartAngle, mArcAngle, false, mBottomPaint);

        //2.画上面的进度
        float currentAngle = (float) mCurrentStepNum / mMaxStepNum * mArcAngle;
        canvas.drawArc(mRectF, mStartAngle, currentAngle, false, mTopPaint);

        if (mMaxStepNum <= 0)
            return;
        //3.画文字
        String step = String.valueOf(mCurrentStepNum);
        if (mTextX == -1) {
            mTextX = (int) ((getWidth() - DisplayUtil.getTextWidth(step, mTextPaint)) / 2);
        }
        // 计算文字的基线
        if (mTextY == -1) {
            mTextY = getHeight() / 2 + DisplayUtil.getTextBaseLine(mTextPaint);
        }
        // 绘制步数文字
        canvas.drawText(step, mTextX, mTextY, mTextPaint);

    }

    public int getBottomColor() {
        return mBottomColor;
    }

    public void setBottomColor(int mBottomColor) {
        this.mBottomColor = mBottomColor;
        invalidate();
    }

    public int getTopColor() {
        return mTopColor;
    }

    public void setTopColor(int mTopColor) {
        this.mTopColor = mTopColor;
        invalidate();
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        invalidate();
    }

    public int getMaxStepNum() {
        return mMaxStepNum;
    }

    public void setMaxStepNum(int mMaxStepNum) {
        this.mMaxStepNum = mMaxStepNum;
        invalidate();
    }

    public int getCurrentStepNum() {
        return mCurrentStepNum;
    }

    public void setCurrentStepNum(int mCurrentStepNum) {
        this.mCurrentStepNum = mCurrentStepNum;
        invalidate();
    }

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        invalidate();
    }

    public int getCircleRadio() {
        return mCircleRadio;
    }

    public void setCircleRadio(int mCircleRadio) {
        this.mCircleRadio = mCircleRadio;
        invalidate();
    }

    public int getCircleStrokeWidth() {
        return mCircleStrokeWidth;
    }

    public void setCircleStrokeWidth(int mCircleStrokeWidth) {
        this.mCircleStrokeWidth = mCircleStrokeWidth;
        invalidate();
    }

    public float getArcAngle() {
        return mArcAngle;
    }

    public void setArcAngle(float mArcAngle) {
        this.mArcAngle = mArcAngle;
        invalidate();
    }

    public float getStartAngle() {
        return mStartAngle;
    }

    public void setStartAngle(float mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
    }
}
