package com.zzw.customview.acitvity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zzw.customview.R;
import com.zzw.customview.view.ColorTrackTextView;

/**
 * Created by zzw on 2017/6/15.
 * Version:
 * Des:字体变色测试
 */

public class ColorTrackTextViewActivity extends AppCompatActivity {

    private ColorTrackTextView mColorTrackTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colortrackview);
        mColorTrackTextView = (ColorTrackTextView) findViewById(R.id.colortv);
    }


    public void leftToRight(View view) {
        mColorTrackTextView.setDirection(ColorTrackTextView.DIRECTION_LEFT_TO_RIGHT);
        startAnim(0, 1);
    }

    public void RightToLeft(View view) {
        mColorTrackTextView.setDirection(ColorTrackTextView.DIRECTION_RIGHT_TO_LEFT);
        startAnim(0, 1);
    }


    private void startAnim(float startPro, float endPro) {
        ValueAnimator animator = ObjectAnimator.ofFloat(startPro, endPro);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentProgress = (float) animation.getAnimatedValue();
                mColorTrackTextView.setCurrentProgress(currentProgress);
            }
        });
        animator.start();
    }


}
