package com.zzw.customview.acitvity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzw.customview.R;
import com.zzw.customview.view.tagLayout.BaseAdapter;
import com.zzw.customview.view.tagLayout.TagLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzw on 2017/6/17.
 * Version:
 * Des:
 */

public class TagLayoutActivity extends AppCompatActivity {

    private TagLayout mTagLayout;




    private List<String> mItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taglayout);
        mTagLayout = (TagLayout) findViewById(R.id.taglayout);

        mItems = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            mItems.add("ABC"+i);
        }
        mTagLayout.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mItems.size();
            }

            @Override
            public View getView(int position, ViewGroup parent) {
                TextView tagTv = (TextView) LayoutInflater.from(TagLayoutActivity.this)
                        .inflate(R.layout.item_tag, parent, false);
                tagTv.setText(mItems.get(position));
                // 操作ListView的方式差不多
                return tagTv;
            }
        });
    }
}
