package com.zzw.customview;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import com.zzw.customview.view.QQSportStepView;

public class QQSportStepViewActivity extends AppCompatActivity {

    private QQSportStepView qqSportStepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_sport_stepview);
        qqSportStepView = (QQSportStepView) findViewById(R.id.stepView);
        int maxStepNun = 100000;
        qqSportStepView.setMaxStepNum(maxStepNun);
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(2000);
        valueAnimator.setIntValues(0, maxStepNun);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                qqSportStepView.setCurrentStepNum(value);
            }
        });
        valueAnimator.start();
    }
}
