package com.zzw.customview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zzw on 2017/6/17.
 * Version:
 * Des:
 */

public class TouchView extends View {

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("zzz", "View->dispatchTouchEvent ->" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("zzz", "View->onTouchEvent ->" + event.getAction());
        return super.onTouchEvent(event);
    }


    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
