package com.zzw.customview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.zzw.customview.R
import android.graphics.Shader
import android.graphics.Color.parseColor
import android.graphics.SweepGradient


/**
 * Created by zzw on 2017/6/23.
 * Version:
 * Des:
 */
class RadarView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    //每个圆圈所占的比例
    private val circleProportion = floatArrayOf(1 / 13f, 2 / 13f, 3 / 13f, 4 / 13f, 5 / 13f, 6 / 13f)
    private var mCirclePaint: Paint? = null//画圆需要用到的paint

    private var mWidth: Float = 0.0f
    private var mHeight: Float = 0.0f
    private var mCenterBitmap: Bitmap? = null

    private val mPaintScan: Paint? = null//画扫描需要用到的paint
    private var mRoteDegree: Int = 0//扫描旋转的角度
    private var scanShader: Shader? = null//扫描渲染shader


    init {
        mCirclePaint = Paint()
        mCirclePaint!!.color = Color.WHITE
        mCirclePaint!!.style = Paint.Style.STROKE
        mCirclePaint!!.isDither = true
        mCirclePaint!!.isAntiAlias = true


        // 通过bitmap工厂区获取用户图像的bitmap
        mCenterBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round);
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        mWidth = measuredWidth.toFloat()
        mHeight = measuredHeight.toFloat()

        mWidth = Math.min(mWidth, mHeight)
        mHeight = mWidth

        setMeasuredDimension(mWidth.toInt(), mHeight.toInt())


        //设置扫描渲染的shader
        scanShader = SweepGradient(mWidth / 2, mHeight / 2,
                intArrayOf(Color.TRANSPARENT, Color.parseColor("#84B5CA")), null)
    }


    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            // 绘制六个白色圆圈
            drawCircle(it)
            drawCenterIcon(it)
//            drawScan(it)
        }
    }

    /**
     * 绘制扫描

     * @param canvas
     */
    private fun drawScan(canvas: Canvas) {
        canvas.save()
        mPaintScan!!.shader = scanShader
        canvas.concat(matrix)
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth * circleProportion[4], mPaintScan)
        canvas.restore()
    }

    fun drawCenterIcon(canvas: Canvas) {
        val iconWidth = mWidth * circleProportion[0]
        canvas.drawBitmap(mCenterBitmap,
                null,
                Rect((mWidth / 2 - iconWidth / 2).toInt(),
                        (mWidth / 2 - iconWidth / 2).toInt(),
                        (mWidth / 2 + iconWidth.toInt() / 2).toInt(),
                        (mWidth / 2 + iconWidth.toInt() / 2).toInt()),
                null)
    }

    fun drawCircle(canvas: Canvas) {
        for (fl in circleProportion) {
            canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth * fl, mCirclePaint)
        }
    }

}

