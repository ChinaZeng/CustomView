package com.zzw.customview.acitvity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zzw.customview.R;
import com.zzw.customview.view.AlphabeticalIndexView;

public class AlphabeticalIndexViewActivity extends AppCompatActivity implements AlphabeticalIndexView.SideBarTouchListener {

    private TextView tv;
    private AlphabeticalIndexView alphabeticalIndexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabetical_index_view);

        tv = (TextView) findViewById(R.id.tv);
        alphabeticalIndexView = (AlphabeticalIndexView) findViewById(R.id.alv);

        alphabeticalIndexView.setOnSideBarTouchListener(this);
    }

    @Override
    public void onTouch(String letter, boolean isTouch) {
        if(isTouch){
            tv.setVisibility(View.VISIBLE);
            tv.setText(letter);
        }else {
            tv.setVisibility(View.GONE);
        }

    }
}
