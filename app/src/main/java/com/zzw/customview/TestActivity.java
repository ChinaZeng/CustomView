package com.zzw.customview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TestActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ObjectAnimator.ofInt(0, 100);
                valueAnimator.setDuration(1000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int a = (int) animation.getAnimatedValue();
                        iv.setX(a);
                    }
                });
                valueAnimator.start();

            }
        });




    }
}
