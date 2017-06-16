package com.zzw.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by zzw on 2017/6/15.
 * Version:
 * Des:
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.qq).setOnClickListener(this);
        findViewById(R.id.load58).setOnClickListener(this);
        findViewById(R.id.suoyin).setOnClickListener(this);
        findViewById(R.id.ziticolor).setOnClickListener(this);
        findViewById(R.id.colortv_vp).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qq:
                startActivity(QQSportStepViewActivity.class);
                break;
            case R.id.load58:
                startActivity(JumpLoadViewActivity.class);
                break;
            case R.id.suoyin:
                startActivity(AlphabeticalIndexViewActivity.class);
                break;
            case R.id.ziticolor:
                startActivity(ColorTrackTextViewActivity.class);
                break;
            case R.id.colortv_vp:
                startActivity(ViewPagerActivity.class);
                break;
        }
    }

    private void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
