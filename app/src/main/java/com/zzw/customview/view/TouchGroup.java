package com.zzw.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by zzw on 2017/6/19.
 * Version:
 * Des:
 */

public class TouchGroup extends LinearLayout {

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("zzz", "ViewGroup->dispatchTouchEvent ->" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("zzz", "ViewGroup->onInterceptTouchEvent ->" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("zzz", "ViewGroup->onTouchEvent ->" + event.getAction());
        return super.onTouchEvent(event);
    }



    public TouchGroup(Context context) {
        super(context);
    }

    public TouchGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




}
