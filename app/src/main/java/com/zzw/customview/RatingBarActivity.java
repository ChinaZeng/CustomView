package com.zzw.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zzw.customview.view.RatingBar;

/**
 * Created by zzw on 2017/6/16.
 * Version:
 * Des:
 */

public class RatingBarActivity extends AppCompatActivity {

    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingbar);
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRatingBar.recycle();
    }
}
