package com.zzw.customview.acitvity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zzw.customview.R;
import com.zzw.customview.view.JumpLoadView.JumpLoadView;

public class JumpLoadViewActivity extends AppCompatActivity {

    private JumpLoadView jumpLoadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_loadview);
        jumpLoadView = (JumpLoadView) findViewById(R.id.jumpLoadView);
        jumpLoadView.setRadio(30);
    }
}
