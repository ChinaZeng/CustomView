package com.zzw.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zzw.customview.view.ColorTrackTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzw on 2017/6/15.
 * Version:
 * Des: 字体变色和viewpager配合使用
 */
public class ViewPagerActivity extends AppCompatActivity {
    private String[] items = {"热点", "推荐", "社会", "图片", "科技", "运动"};
    private LinearLayout mIndicatorContainer;// 变成通用的
    private List<ColorTrackTextView> mIndicators;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mIndicators = new ArrayList<>();
        mIndicatorContainer = (LinearLayout) findViewById(R.id.indicator_view);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        initIndicator();
        initViewPager();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return ItemFragment.newInstance(items[position]);
            }

            @Override
            public int getCount() {
                return items.length;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("TAG", "position -> " + position + "  positionOffset -> " + positionOffset + "  positionOffsetPixels->" + positionOffsetPixels);
                // position 代表当前的位置
                // positionOffset 代表滚动的 0 - 1 百分比  左滑 1->0  右滑-> 0-1

                // 1.左边  位置 position
                ColorTrackTextView left = mIndicators.get(position);
                left.setDirection(ColorTrackTextView.DIRECTION_RIGHT_TO_LEFT);
                left.setCurrentProgress(1 - positionOffset);

                try {
                    ColorTrackTextView right = mIndicators.get(position + 1);
                    right.setDirection(ColorTrackTextView.DIRECTION_LEFT_TO_RIGHT);
                    right.setCurrentProgress(positionOffset);
                } catch (Exception e) {

                }
            }

            @Override
            public void onPageSelected(int position) {
                selectPos(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化可变色的指示器
     */
    private void initIndicator() {
        for (int i = 0; i < items.length; i++) {
            // 动态添加颜色跟踪的TextView
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            ColorTrackTextView colorTrackTextView = new ColorTrackTextView(this);
            // 设置颜色
            colorTrackTextView.setTextSize(20);
            colorTrackTextView.setChangeColor(Color.RED);
            colorTrackTextView.setText(items[i]);
            colorTrackTextView.setLayoutParams(params);

            colorTrackTextView.setOnClickListener(new Click(i));

            // 把新的加入LinearLayout容器
            mIndicatorContainer.addView(colorTrackTextView);
            // 加入集合
            mIndicators.add(colorTrackTextView);
        }
    }


    private void selectPos(int pos) {
        for (int i = 0; i < mIndicators.size(); i++) {
            ColorTrackTextView colorTrackTextView = mIndicators.get(i);
            if (i == pos) {
                colorTrackTextView.setCurrentProgress(1.0f);
            } else {
                colorTrackTextView.setCurrentProgress(0.0f);
            }
        }
    }

    private class Click implements View.OnClickListener {

        private int pos;

        public Click(int pos) {
            this.pos = pos;
        }

        @Override
        public void onClick(View v) {
            mViewPager.setCurrentItem(pos, false);
        }
    }
}
