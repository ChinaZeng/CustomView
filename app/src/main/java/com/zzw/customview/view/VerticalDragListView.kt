package com.zzw.customview.view

import android.content.Context
import android.support.annotation.AttrRes
import android.support.v4.view.ViewCompat
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.AbsListView
import android.widget.FrameLayout

/**
 * Created by zzw on 2017/6/26.
 * Version:
 * Des: 汽车之家折叠列表
 */

class VerticalDragListView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, @AttrRes defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    private var mDragView: View? = null//拖动的view
    private var mMenuViewHeight: Int = 0 //拖动的view 高度
    private var mMenuIsOpen: Boolean = false//是否打开
    private var mViewDragHelper: ViewDragHelper? = null //拖动的辅助类

    private val mCallback: ViewDragHelper.Callback = object : ViewDragHelper.Callback() {
        //指定view是否可以拖动
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return mDragView == child
        }

        //返回移动的距离
        override fun clampViewPositionVertical(child: View?, top: Int, dy: Int): Int {
            //滑动的范围只能是在menu的高度
            var t: Int = top
            if (top <= 0) t = 0
            if (top >= mMenuViewHeight) t = mMenuViewHeight
            return t
        }

        //手松开的时候回调 打开还是关闭
        override fun onViewReleased(releasedChild: View?, xvel: Float, yvel: Float) {
            //打开菜单
            if (mDragView!!.top >= mMenuViewHeight / 2) {
                mViewDragHelper?.settleCapturedViewAt(0, mMenuViewHeight)
                mMenuIsOpen = true
            } else {//关闭菜单
                mViewDragHelper?.settleCapturedViewAt(0, 0)
                mMenuIsOpen = false
            }
            invalidate()
        }
    }

    //响应滚动
    override fun computeScroll() {
        if (mViewDragHelper!!.continueSettling(true)) invalidate()
    }


    init {
        mViewDragHelper = ViewDragHelper.create(this, mCallback)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount != 2) throw RuntimeException("childCount只能包含两个子布局")
        mDragView = getChildAt(1)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) mMenuViewHeight = getChildAt(0).measuredHeight
    }

    private var mDownY: Float = 0.0f
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // 菜单打开要拦截
        if (mMenuIsOpen) {
            return true
        }

        // 向下滑动拦截，不让ListView或者RecyclerView做处理
        // 谁拦截谁 父View拦截子View ，但是子 View 可以调这个方法
        // requestDisallowInterceptTouchEvent 请求父View不要拦截，改变的其实就是 mGroupFlags 的值
        when (ev!!.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownY = ev.y
                // 让 DragHelper 拿一个完整的事件
                mViewDragHelper!!.processTouchEvent(ev)
            }

            MotionEvent.ACTION_MOVE -> {
                val moveY = ev.y
                if (moveY - mDownY > 0 && !canChildScrollUp()) {
                    // 向下滑动 && 滚动到了顶部，拦截不让ListView或者RecyclerView做处理
                    return true
                }
            }
        }

        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mViewDragHelper?.processTouchEvent(event)
        return true
    }


    /**
     * @return Whether it is possible for the child view of this layout to
     * *         scroll up. Override this if the child view is a custom view.
     */
    fun canChildScrollUp(): Boolean {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mDragView is AbsListView) {
                val absListView = mDragView as AbsListView
                return absListView.childCount > 0 && (absListView.firstVisiblePosition > 0 || absListView.getChildAt(0)
                        .top < absListView.paddingTop)
            } else {
                return ViewCompat.canScrollVertically(mDragView, -1) || mDragView!!.scrollY > 0
            }
        } else {
            return ViewCompat.canScrollVertically(mDragView, -1)
        }
    }
}
