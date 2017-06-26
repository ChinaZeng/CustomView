package com.zzw.customview.acitvity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.zzw.customview.R;
import com.zzw.customview.view.TouchView;

/**
 * Created by zzw on 2017/6/17.
 * Version:
 * Des:
 */

public class TouchActivity extends AppCompatActivity {

    private TouchView mTouchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        mTouchView = (TouchView) findViewById(R.id.touchView);

        mTouchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("zzz", "View->onTouch ->" + event.getAction());
                return false;
            }
        });

//        mTouchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("zzz", "view ->onClick");
//            }
//        });
    }
}
